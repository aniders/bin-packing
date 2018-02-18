package com.maersk.fse.knpsack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 
 * @author Aniruddh
 *
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private List<ObjectError> errors;

    public BadRequestException() {
        this(BAD_REQUEST.getReasonPhrase());
    }

    public BadRequestException(List<ObjectError> errors) {
        this(BAD_REQUEST.getReasonPhrase());
        this.errors = errors;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return BAD_REQUEST;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

}