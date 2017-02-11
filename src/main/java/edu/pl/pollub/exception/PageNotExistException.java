package edu.pl.pollub.exception;

/**
 * Created by Dell on 2017-02-11.
 */
public class PageNotExistException extends Throwable {
    public PageNotExistException(int pageNumber) {
        super("Sorry, but page with number "+pageNumber+" not exist");
    }
}
