package com.maersk.fse.knpsack.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.fse.knpsack.dao.TaskStatusDAO;
import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.Task;
import com.maersk.fse.knpsack.service.KnapsackService;
import com.maersk.fse.knpsack.util.Knapsack;

@Service
public class KnapsackServiceImpl implements KnapsackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackServiceImpl.class);

    private TaskStatusDAO dao;

    @Autowired
    public KnapsackServiceImpl(TaskStatusDAO dao) {
        this.dao = dao;
    }


    @Override
    public Task submitTask(Problem problem) {
        LOGGER.info("Submitting new Task to an Optimizer");
        //TODO submit task to optimizer  ??????
        //Solution solution = Knapsack.solve(problem);

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
