package edu.pl.pollub.validator;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.entity.request.UserRegisterRequest;
import edu.pl.pollub.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.inject.Inject;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 2017-03-15.
 */
@Component
public class UserValidator implements Validator {

    private final UserService userService;


    @Inject
    public UserValidator( final UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterRequest user = (UserRegisterRequest) o;
        Locale locale = new Locale("");
        if (userService.emailExist(user.getEmail())) {
            errors.rejectValue("email", "Email.exist", "There is account with this e-mail. Please login to this account instead register new one.");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username", "Please use count of characters between 6 and 32.");
        }


        if (userService.getByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username", "Someone already has that username. Please choose another one");

        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password", "Password should have more than 8 characters.");
        }

        System.out.println(user.getPasswordConfirm());
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm", "Confirmation of password does not match to password.");
        }
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        matcher = pattern.matcher(user.getEmail());
        if (!(matcher.matches())) {
            errors.rejectValue("email", "BadForm.userForm.email", "Email don't have appropriate form.");
        }

    }
}
