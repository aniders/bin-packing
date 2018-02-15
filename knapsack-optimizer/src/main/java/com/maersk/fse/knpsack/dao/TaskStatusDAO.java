package com.maersk.fse.knpsack.dao;

import java.util.List;

import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Solution;
import com.maersk.fse.knpsack.dto.Task;

public interface TaskStatusDAO {

    void addTask(Task submittedTask, Problem problem);

    Task getTaskStatus(String id);

    List<Task> getAllTasks();

    Problem getProblem(String task);

    Solution getSolution(String task);
    
    void addSolution(String task, Solution solution);

}
