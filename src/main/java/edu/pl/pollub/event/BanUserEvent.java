package edu.pl.pollub.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Dell on 2017-03-15.
 */
public class BanUserEvent extends ApplicationEvent {

    private final String userName;
    private final boolean online;

    public BanUserEvent(String userName, boolean online) {
        super(userName);
        this.userName = userName;
        this.online = online;
    }

    public boolean getOnline() {
        return online;
    }


    public String getUserName() {
        return userName;
    }

}
