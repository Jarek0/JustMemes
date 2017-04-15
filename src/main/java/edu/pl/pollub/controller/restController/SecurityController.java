package edu.pl.pollub.controller.restController;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.entity.VerificationToken;
import edu.pl.pollub.entity.request.UserRegisterRequest;
import edu.pl.pollub.event.OnRegistrationCompleteEvent;
import edu.pl.pollub.exception.InvalidRequestException;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.RegException;
import edu.pl.pollub.response.GenericResponse;
import edu.pl.pollub.service.MailService;
import edu.pl.pollub.service.RoleService;
import edu.pl.pollub.service.UserService;
import edu.pl.pollub.service.VerificationTokenService;
import edu.pl.pollub.validator.UserValidator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Dell on 2017-03-16.
 */
@RestController
@RequestMapping
public class SecurityController {

    private final ApplicationEventPublisher eventPublisher;

    private final UserService userService;

    private final VerificationTokenService verificationTokenService;

    private final MailService mailService;

    private final UserValidator userValidator;

    @Inject
    public SecurityController(final ApplicationEventPublisher eventPublisher,final MailService mailService, final UserService userService, final VerificationTokenService verificationTokenService, final UserValidator userValidator) {
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.mailService = mailService;
        this.verificationTokenService = verificationTokenService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse registration(@RequestBody @Valid UserRegisterRequest userForm, HttpServletRequest request, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid user", bindingResult);
        }
        User user=userService.registerNewUserAccount(new User(userForm));
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), request.getContextPath()));
        return new GenericResponse(user.getEmail());
    }

    @RequestMapping(value = "/registration/resendToken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse resendToken(@RequestBody @Valid GenericResponse response, HttpServletRequest request) throws ObjectNotFoundException {
        User user=userService.getByEmail(response.getMessage());
        VerificationToken newToken=verificationTokenService.generateNewVerificationToken(user);
        verificationTokenService.update(newToken);
        String recipientAddress = user.getEmail();
        String appUrl = request.getContextPath();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = appUrl + "/registration/confirm?token=" + newToken.getToken();
        String message = "Please click this link to verify your account: ";

        mailService.sendMail("from@no-spam.com",recipientAddress,subject,message + "http://localhost:8081" + confirmationUrl);
        return new GenericResponse(user.getEmail());
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(WebRequest request, HttpServletResponse response, @RequestParam("token") String token) throws IOException, RegException, ObjectNotFoundException {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenService.getByToken(token);
        if (verificationToken == null) {
            throw new RegException("Your activation token is invalid, please resend it");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getDate() - cal.getTime().getTime()) <= 0) {
            throw new RegException("this token is expired");
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);

        response.sendRedirect("/registration/complete");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getAuth")
    public String getAuth(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserRole = userService.getByUsername(authentication.getName()).getRole().getName();
                return currentUserRole;
            } else return "ANONYMOUS";
    }
}
