package edu.pl.pollub.controller.restController;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.entity.VerificationToken;
import edu.pl.pollub.entity.request.UserRegisterRequest;
import edu.pl.pollub.event.OnRegistrationCompleteEvent;
import edu.pl.pollub.exception.AuthException;
import edu.pl.pollub.exception.InvalidRequestException;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.service.UserService;
import edu.pl.pollub.service.VerificationTokenService;
import edu.pl.pollub.service.implementation.MailServiceImpl;
import edu.pl.pollub.validator.UserValidator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.rmi.NoSuchObjectException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Dell on 2017-03-16.
 */
@RestController
public class SecurityController {

    private final ApplicationEventPublisher eventPublisher;

    private final UserService userService;

    private final VerificationTokenService verificationTokenService;


    private final UserValidator userValidator;

    @Inject
    public SecurityController(final ApplicationEventPublisher eventPublisher, final UserService userService, final VerificationTokenService verificationTokenService, final UserValidator userValidator) {
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody @Valid UserRegisterRequest userForm, HttpServletRequest request, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid user", bindingResult);
        }
        User user=userService.registerNewUserAccount(new User(userForm));
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(WebRequest request, @RequestParam("token") String token) throws NoSuchObjectException, AuthException, ObjectNotFoundException {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenService.getByToken(token);
        if (verificationToken == null) {
            throw new AuthException("Your activation token is invalid, please resend it");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getDate() - cal.getTime().getTime()) <= 0) {
            throw new AuthException("this token is expired");
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
    }
}
