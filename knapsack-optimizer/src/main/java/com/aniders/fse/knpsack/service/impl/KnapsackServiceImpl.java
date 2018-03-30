package com.aniders.fse.knpsack.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aniders.fse.knpsack.dao.TaskStatusDAO;
import com.aniders.fse.knpsack.dto.Problem;
import com.aniders.fse.knpsack.dto.Solution;
import com.aniders.fse.knpsack.dto.Task;
import com.aniders.fse.knpsack.dto.Task.Status;
import com.aniders.fse.knpsack.service.KnapsackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KnapsackServiceImpl implements KnapsackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private TaskStatusDAO dao;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${solver.app.user}")
    private String solverAppUser;

    @Value("${solver.app.key}")
    private String solverAppKey;

    @Value("${knapsack.solver.service.url}")
    private String solverApiUrl;

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

    /**
     * Calls Solver service in Async, stores Solution details to data-store when it is available
     * 
     * @param submittedTask
     * @param problem
     * @return
     */
    private Task callSover(Task submittedTask, Problem problem) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        String auth = solverAppUser + ":" + solverAppKey;
        byte[] encodedBytes = Base64.getEncoder().encode(auth.getBytes());

        headers.add("Authorization", "Basic " + new String(encodedBytes));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String problemJson = "";
        try {
            problemJson = objectMapper.writeValueAsString(problem);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse Problem request", problem);
        }
        HttpEntity<String> entity = new HttpEntity<String>(problemJson, headers);
        dao.getTaskStatus(submittedTask.getTask()).setStatus(Status.STARTED.getValue());
        dao.getTaskStatus(submittedTask.getTask()).getTimestamps().setStarted(System.currentTimeMillis() / 1000L);
        System.out.println("calling solver "+solverApiUrl);
        System.out.println("entity "+entity);
        ResponseEntity<Solution> response = restTemplate.postForEntity(solverApiUrl + "/solve", entity, Solution.class);

        Solution solution = response.getBody();
        System.out.println("Response " + response.getStatusCodeValue());
        System.out.println("Response " + response);

        dao.addSolution(submittedTask.getTask(), solution);
        dao.getTaskStatus(submittedTask.getTask()).setStatus(Status.COMPLETED.getValue());
        dao.getTaskStatus(submittedTask.getTask()).getTimestamps().setCompleted(System.currentTimeMillis() / 1000L);
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
