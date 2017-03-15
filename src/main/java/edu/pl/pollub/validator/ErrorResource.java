package edu.pl.pollub.validator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
    private String code;
    private String message;
    private List<FieldErrorResource> fieldErrors;

    public ErrorResource() {
    }

    public ErrorResource(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorResource> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
