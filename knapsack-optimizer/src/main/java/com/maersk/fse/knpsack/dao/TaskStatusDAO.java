package com.maersk.fse.knpsack.dao;

import java.util.List;

import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.Task;

public interface TaskStatusDAO {

    /**
     * Add new Problem task to data-store
     * 
     * @param submittedTask
     * @param problem
     */
    void addTask(Task submittedTask, Problem problem);

    /**
     * Returns Task details for submitted task.
     * 
     * @param id
     * @return
     */
    Task getTaskStatus(String id);

    /**
     * Returns list of all tasks submitted to the Optimizer
     * 
     * @return
     */
    List<Task> getAllTasks();

    /**
     * Returns Problem parameters for submitted task.
     * 
     * @param task
     * @return
     */
    Problem getProblem(String task);

    /**
     * Returns Solution for completed task.
     * 
     * @param task
     * @return
     */
    Solution getSolution(String task);

    /**
     * Add Solution details to the Data-store
     * 
     * @param task
     * @param solution
     */
    void addSolution(String task, Solution solution);

}
