package edu.pl.pollub.listener;

import edu.pl.pollub.service.implementation.LoginAttemptServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Dell on 2017-03-15.
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final LoginAttemptServiceImpl loginAttemptService;

    @Inject
    AuthenticationFailureListener(final LoginAttemptServiceImpl loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();

        loginAttemptService.loginFailed(auth.getRemoteAddress());
    }
}
