package com.maersk.fse.knpsack.service;

import java.util.List;

import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.Task;

/**
 * 
 * @author Aniruddh
 *
 */
public interface KnapsackService {

    /**
     * 
     * @param request
     * @return
     */
    public Task submitTask(Problem request);

    /**
     * 
     * @param id
     * @return
     */
    public Task getTaskById(String id);

    /**
     * 
     * @param task
     * @return
     */
    public Solution getSolution(String task);

    /**
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
