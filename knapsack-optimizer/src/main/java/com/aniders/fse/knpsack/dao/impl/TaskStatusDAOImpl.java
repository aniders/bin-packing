package com.aniders.fse.knpsack.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aniders.fse.knpsack.dao.TaskStatusDAO;
import com.aniders.fse.knpsack.dto.Problem;
import com.aniders.fse.knpsack.dto.Solution;
import com.aniders.fse.knpsack.dto.Task;

/**
 * DAO to store Task and Solution details in data store. An in-memory datastore is used, Alternatively this can be extended to interface
 * with Database
 * <p/>
 * 
 * taskStore - stores Task details.
 * <p/>
 * problemStore - stores submitted Problems.
 * <p/>
 * solutionStore - stores Solution for submitted task, when it is available.
 * 
 * @author Aniruddh
 *
 */
@Component
public class TaskStatusDAOImpl implements TaskStatusDAO {

    private HashMap<String, Task> taskStore;

    private HashMap<String, Problem> problemStore;

    private HashMap<String, Solution> solutionStore;

    public TaskStatusDAOImpl() {
        taskStore = new HashMap<String, Task>();
        problemStore = new HashMap<String, Problem>();
        solutionStore = new HashMap<String, Solution>();
    }

    @Override
    public void addTask(Task submittedTask, Problem problem) {
        taskStore.put(submittedTask.getTask(), submittedTask);
        problemStore.put(submittedTask.getTask(), problem);
    }

    @Override
    public Task getTaskStatus(String id) {
        return taskStore.get(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<Task>(taskStore.values());
    }

    @Override
    public Problem getProblem(String task) {
        return problemStore.get(task);
    }

    @Override
    public Solution getSolution(String task) {
        return solutionStore.get(task);
    }

    @Override
    public void addSolution(String task, Solution solution) {
        solutionStore.put(task, solution);
    }

}
