package com.maersk.knpsack.solver.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.maersk.knpsack.solver.dto.Problem;

@Component
public class RequestValidator implements Validator {

    public void validate(Object target, Errors errors) {
        Problem request = (Problem) target;

        if (request.getCapacity() < 0) {
            errors.rejectValue("capacity", "capacity must be greater than 0");
        }
        if (request.getWeights() == null) {
            errors.rejectValue("weights", "weights are required");
        }
        if (request.getValues() == null) {
            errors.rejectValue("values", "values are required");
        } else {
            if (request.getValues().length != request.getWeights().length) {
                errors.rejectValue("values", "values must be as many as weights");
            } 
        }
    }

   
    public boolean supports(Class<?> clazz) {
        return Problem.class.equals(clazz);
    }

}