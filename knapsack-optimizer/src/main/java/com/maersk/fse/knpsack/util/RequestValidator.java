package com.maersk.fse.knpsack.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.maersk.fse.knpsack.dto.Problem;

@Component
public class RequestValidator implements Validator {

    public void validate(Object target, Errors errors) {
        Problem request = (Problem) target;

        if (request.getWeights() == null) {
            errors.rejectValue("weights", "error.weights", "weights are required");
        }
        if (request.getValues() == null) {
            errors.rejectValue("values", "error.values", "values are required");
        } else {
            if (request.getValues().length != request.getWeights().length) {
                errors.rejectValue("values", "error.values", "values must be as many as weights");
            } 
        }
    }

   
    public boolean supports(Class<?> clazz) {
        return Problem.class.equals(clazz);
    }

}