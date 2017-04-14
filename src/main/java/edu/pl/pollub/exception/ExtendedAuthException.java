package edu.pl.pollub.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Dell on 2017-04-14.
 */
public class ExtendedAuthException extends AuthenticationException {
    
    public ExtendedAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public ExtendedAuthException(String msg) {
        super(msg);
    }
}
