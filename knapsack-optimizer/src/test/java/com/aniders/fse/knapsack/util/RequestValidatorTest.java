package com.aniders.fse.knapsack.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.aniders.fse.knpsack.dto.Problem;
import com.aniders.fse.knpsack.util.RequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class RequestValidatorTest {

    @InjectMocks
    private RequestValidator requestValidator;
    
    
    BindingResult result; 
    
    @Test
    public void validateInputTest() {
        
        Problem problem = Problem.builder().capacity(60).weights(new int[] {10, 20, 33}).values(new int[] {10, 3, 30}).build();
        result =  new BeanPropertyBindingResult(problem, "problem");
        requestValidator.validate(problem, result);
        assertEquals(false, result.hasErrors());
    }
    
    
    @Test
    public void invalideInputTest() {
        
        Problem problem = Problem.builder().capacity(-60).weights(new int[] {10, 20, 33}).values(new int[] {10, 3, 30}).build();
        result =  new BeanPropertyBindingResult(problem, "problem");
        requestValidator.validate(problem, result);
        assertEquals(true, result.hasErrors());
    }
    
    
    @Test
    public void invalideNoOFValuesInputTest() {
        
        Problem problem = Problem.builder().capacity(60).weights(new int[] {10, 20, 33}).values(new int[] { 3, 30}).build();
        result =  new BeanPropertyBindingResult(problem, "problem");
        requestValidator.validate(problem, result);
        assertEquals(true, result.hasErrors());
    }
    
    
    @Test
    public void invalideWeightsInputTest() {
        
        Problem problem = Problem.builder().capacity(60).weights(new int[] {10, -20, 33}).values(new int[] { 3, 30, 10}).build();
        result =  new BeanPropertyBindingResult(problem, "problem");
        requestValidator.validate(problem, result);
        assertEquals(true, result.hasErrors());
    }
    
    
    @Test
    public void invalideValuesInputTest() {
        
        Problem problem = Problem.builder().capacity(60).weights(new int[] {10, 20, 33}).values(new int[] { 10, -3, 30}).build();
        result =  new BeanPropertyBindingResult(problem, "problem");
        requestValidator.validate(problem, result);
        assertEquals(true, result.hasErrors());
    }
}


