package edu.pl.pollub.listener;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    private final UserService userService;

    @Inject
    public LogoutListener(final UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetails ud;
        for (SecurityContext securityContext : lstSecurityContext) {
            ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
            User logoutUser = null;
            logoutUser = userService.getByUsername(ud.getUsername());
            logoutUser.setOnline(false);
            userService.saveRegisteredUser(logoutUser);
        }
    }
}
