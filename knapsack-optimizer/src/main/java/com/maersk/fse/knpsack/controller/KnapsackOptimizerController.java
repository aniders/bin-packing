package com.maersk.fse.knpsack.controller;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.SolutionResponse;
import com.maersk.fse.knpsack.dto.Task;
import com.maersk.fse.knpsack.dto.Task.Status;
import com.maersk.fse.knpsack.exception.BadRequestException;
import com.maersk.fse.knpsack.service.KnapsackService;
import com.maersk.fse.knpsack.util.RequestValidator;

@RestController
@Api(description = "Kapsack Optimizer Service for submitting problem task, Status enquiry, Get solution details.")
public class KnapsackOptimizerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackOptimizerController.class);

    @Autowired
    private KnapsackService knapsackService;
    @Autowired
    private RequestValidator requestValidator;

    @GetMapping(value = "/admin/tasks")
    @ApiOperation(value = "Returns list of submitted, running, and completed tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getAll() {
        LOGGER.debug("Fetching list of all Tasks");
        List<Task> allTasks = knapsackService.getAllTasks();
        if (allTasks.isEmpty()) {
            LOGGER.debug("No tasks found");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(allTasks, HttpStatus.OK);
    }

    @GetMapping(value = "/tasks/{taskId}")
    @ApiOperation(value = "Returns status of a Task with {taskId}", produces = "application/json")
    public ResponseEntity<Task> checkStatus(@PathVariable String taskId) {
        LOGGER.debug("Checking status of Task with id {}", taskId);
        Task task = knapsackService.getTaskById(taskId);
        if (task == null) {
            LOGGER.debug("Task with id {} not found", taskId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
    
    @GetMapping(value = "/solutions/{taskId}")
    @ApiOperation(value = "Returns solution to the Tassk with {id}", produces = "application/json")
    public ResponseEntity<SolutionResponse> getSolution(@PathVariable String taskId) {
        LOGGER.debug("Checking status of Task with id {}", taskId);
        Task task = knapsackService.getTaskById(taskId);
        if (task == null) {
            LOGGER.debug("Task with id {} not found", taskId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Problem problem;
        Solution response;
        if(task.getStatus() == Status.COMPLETED.getValue()) {
            problem = knapsackService.getProblem(taskId);
            response =  knapsackService.getSolution(taskId);
        } else {
            LOGGER.debug("Solution is not avaialbe for Task {} ", taskId);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SolutionResponse>(new SolutionResponse(taskId, problem, response ), HttpStatus.OK);
    }

   
    @PostMapping(value = "/tasks")
    @ApiOperation(value = "Submit new problem task", produces = "application/json")
    public Task submitTask(@RequestBody Problem request, BindingResult result, Principal principle) {
        LOGGER.debug("Submitting Problem task with values {}", request);
        requestValidator.validate(request, result);
        if (result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors());
        }
        Task task = new Task();
        knapsackService.submitTask(task, request);
		return task;
    }
    

}
