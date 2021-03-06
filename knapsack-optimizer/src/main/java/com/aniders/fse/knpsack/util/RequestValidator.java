package com.aniders.fse.knpsack.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aniders.fse.knpsack.dto.Problem;

@Component
public class RequestValidator implements Validator {

    public void validate(Object target, Errors errors) {
        Problem request = (Problem) target;

        if (request.getCapacity() < 0) {
            errors.rejectValue("capacity", "capacity must be greater than 0");
        }
        if (request.getWeights() == null) {
            errors.rejectValue("weights", "weights are required");
        } else {
            isValidValues(request.getWeights(), errors, "weights");
        }
        if (request.getValues() == null) {
            errors.rejectValue("values", "values are required");
        } else {
            if (request.getValues().length != request.getWeights().length) {
                errors.rejectValue("values", "values must be as many as weights");
            }
            isValidValues(request.getValues(), errors, "values");
        }
    }

    private boolean isValidValues(int[] values, Errors errors, String feild) {
        for (int i : values) {
            if (i < 0) {
                errors.rejectValue(feild, "must be non negative value", String.valueOf(i));
            }
        }
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return Problem.class.equals(clazz);
    }

}