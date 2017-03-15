package edu.pl.pollub.event;

import edu.pl.pollub.entity.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by Dell on 2017-03-15.
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(
            User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }

}
