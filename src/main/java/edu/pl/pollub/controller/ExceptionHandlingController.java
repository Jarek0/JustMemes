package edu.pl.pollub.controller;

import edu.pl.pollub.exception.*;
import edu.pl.pollub.validator.ErrorResource;
import edu.pl.pollub.validator.FieldErrorResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017-03-04.
 */
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidRequestException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setResource(fieldError.getObjectName());
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }

        ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({PageNotExistException.class,StorageFileNotFoundException.class, ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handlePageNotFoundException(Exception ex, HttpServletResponse response) throws IOException
    {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({RuntimeException.class,Exception.class, StorageException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex, HttpServletResponse response) throws IOException
    {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
