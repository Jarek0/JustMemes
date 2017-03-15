package edu.pl.pollub.listener;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.service.UserService;
import edu.pl.pollub.service.implementation.LoginAttemptServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Dell on 2017-03-15.
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserService userService;

    private final LoginAttemptServiceImpl loginAttemptService;

    @Inject
    AuthenticationSuccessListener(final LoginAttemptServiceImpl loginAttemptService, final UserService userService) {
        this.loginAttemptService = loginAttemptService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();

        loginAttemptService.loginSucceeded(auth.getRemoteAddress());
        User loginUser = null;

        loginUser = userService.getByUsername(e.getAuthentication().getName());


        loginUser.setOnline(true);
        userService.saveRegisteredUser(loginUser);
    }
}
