package edu.pl.pollub.controller;

import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.exception.StorageFileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dell on 2017-03-04.
 */
@Controller
public class ExceptionHandlingController {
    @ExceptionHandler({PageNotExistException.class,StorageFileNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handlePageNotFoundException(IllegalStateException ex, HttpServletResponse response) throws IOException
    {
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(IllegalStateException ex, HttpServletResponse response) throws IOException
    {
    }
}
