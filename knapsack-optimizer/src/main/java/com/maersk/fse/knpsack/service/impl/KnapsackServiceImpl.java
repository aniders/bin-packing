package com.maersk.fse.knpsack.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.maersk.fse.knpsack.dao.TaskStatusDAO;
import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.Task;
import com.maersk.fse.knpsack.service.KnapsackService;

@Service
public class KnapsackServiceImpl implements KnapsackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private TaskStatusDAO dao;

    @Autowired
    public KnapsackServiceImpl(TaskStatusDAO dao) {
        this.dao = dao;
    }


    @Override
    public Task submitTask(Problem problem) {
        LOGGER.info("Submitting new Task to an Optimizer");
        //TODO submit task to optimizer  ??????
       // Solution solution = Knapsack.solve(problem);
        
//        String username;
//		String password;
//		String auth = username + ":" + password;
//        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
//        String authHeader = "Basic " + new String( encodedAuth );
//        //set( "Authorization", authHeader );
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        headers.add("Authorization", "YXBwOmFuaWRlcnM=");
       // Authorization:Basic YXBwOmFuaWRlcnM=
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Solution> response = restTemplate.postForEntity( "http://localhost:8080/solver/solve", request , Solution.class );
        System.out.println("Response "+ response.getBody().getItems()[1]);
        //restTemplate.postForObject("http://localhost:9001/solver/solve", Solution.class);

        Task submittedTask = new Task();
        dao.addTask(submittedTask, problem);
        
        
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
