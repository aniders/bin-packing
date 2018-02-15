package com.maersk.fse.knpsack.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.fse.knpsack.dao.TaskStatusDAO;
import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.Task;
import com.maersk.fse.knpsack.dto.Task.Status;
import com.maersk.fse.knpsack.service.KnapsackService;

@Service
public class KnapsackServiceImpl implements KnapsackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private TaskStatusDAO dao;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public KnapsackServiceImpl(TaskStatusDAO dao) {
        this.dao = dao;
    }

    @Override
    @Async
    public Task submitTask(Task submittedTask, Problem problem) {
        LOGGER.info("Submitting new Task to an Optimizer");
        dao.addTask(submittedTask, problem);

        CompletableFuture<Task> future = new CompletableFuture<>();
        CompletableFuture.supplyAsync(() -> callSover(submittedTask, problem));
        return submittedTask;
    }

    private Task callSover(Task submittedTask, Problem problem) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // String username;
        // String password;
        // String auth = username + ":" + password;
        // byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        // String authHeader = "Basic " + new String( encodedAuth );
        // //set( "Authorization", authHeader );
        headers.add("Authorization", "Basic YXBwOmFuaWRlcnM=");
        headers.setContentType(MediaType.APPLICATION_JSON);
        String problemJson = "";

        try {
            problemJson = objectMapper.writeValueAsString(problem);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse Problem request", problem);
        }
        HttpEntity<String> entity = new HttpEntity<String>(problemJson, headers);

        ResponseEntity<Solution> response = restTemplate.postForEntity("http://localhost:8080/solver/solve", entity, Solution.class);
        Solution solution = response.getBody();

        dao.addSolution(submittedTask.getTask(), solution);
        dao.getTaskStatus(submittedTask.getTask()).setStatus(Status.COMPLETED.getValue());
        dao.getTaskStatus(submittedTask.getTask()).getTimestamps().setCompleted(System.currentTimeMillis());
        return submittedTask;
    }

    @Override
    public Task getTaskById(String id) {
        return dao.getTaskStatus(id);
    }

    @Override
    public Solution getSolution(String task) {
        return dao.getSolution(task);
    }

    @Override
    public Problem getProblem(String task) {
        return dao.getProblem(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }

}
