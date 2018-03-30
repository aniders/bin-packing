package com.aniders.fse.knpsack.service;

import java.util.List;

import com.aniders.fse.knpsack.dto.Problem;
import com.aniders.fse.knpsack.dto.Solution;
import com.aniders.fse.knpsack.dto.Task;

/**
 * 
 * @author Aniruddh
 *
 */
public interface KnapsackService {

    /**
     * Submits Task to the Solver
     * 
     * @param task
     * @param request
     * @return
     */
    public Task submitTask(Task task, Problem request);

    /**
     * 
     * @param id
     * @return
     */
    public Task getTaskById(String id);

    /**
     * Returns Solution for completed task.
     * 
     * @param task
     * @return
     */
    public Solution getSolution(String task);

    /**
     * Returns Problem parameters for submitted task.
     * 
     * @param task
     * @return
     */
    public Problem getProblem(String task);

    /**
     * 
     * @return
     */
    public List<Task> getAllTasks();

}
