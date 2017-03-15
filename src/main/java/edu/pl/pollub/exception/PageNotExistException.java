package edu.pl.pollub.exception;

/**
 * Created by Dell on 2017-02-11.
 */
public class PageNotExistException extends Exception {
    public PageNotExistException(int pageNumber) {
        super("Sorry, but page with number "+pageNumber+" does not exist");
    }
}
