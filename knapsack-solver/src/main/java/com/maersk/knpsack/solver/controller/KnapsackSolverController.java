package com.maersk.knpsack.solver.controller;

import java.security.Principal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.knpsack.solver.dto.Problem;
import com.maersk.knpsack.solver.dto.Solution;
import com.maersk.knpsack.solver.exception.BadRequestException;
import com.maersk.knpsack.solver.service.KnapsackSolver;

@RestController
@Api(description = "Kapsack Solver. ")
public class KnapsackSolverController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackSolverController.class);

    @Autowired
    private KnapsackSolver solver;

    @PostMapping(value = "/solve")
    @ApiOperation(value = "Submit new problem task", produces = "application/json")
    public ResponseEntity<Solution> submitTask(@RequestBody Problem request, BindingResult result, Principal principle) {
        LOGGER.debug("Solving Problem task with values {}", request);
        if (result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors());
        }
        Solution submittedTask = solver.solve(request);
        return new ResponseEntity<Solution>(submittedTask, HttpStatus.OK);
    }

}
