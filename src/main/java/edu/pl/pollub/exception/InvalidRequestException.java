package edu.pl.pollub.exception;

import org.springframework.validation.Errors;

/**
 * Created by Dell on 2017-03-15.
 */
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
