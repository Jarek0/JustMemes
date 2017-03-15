package edu.pl.pollub.service;

/**
 * Created by Dell on 2017-03-15.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
