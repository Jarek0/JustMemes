package edu.pl.pollub.service;

/**
 * Created by Dell on 2017-03-15.
 */
public interface LoginAttemptService {
    void loginSucceeded(String key);

    void loginFailed(String key);

    boolean isBlocked(String key);
}
