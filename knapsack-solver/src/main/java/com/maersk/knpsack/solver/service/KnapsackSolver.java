package com.maersk.knpsack.solver.service;

import com.maersk.knpsack.solver.dto.Problem;
import com.maersk.knpsack.solver.dto.Solution;

/**
 * 
 * @author Aniruddh
 *
 */
public interface KnapsackSolver {

    /**
     * 
     * @param request
     * @return
     */
    public Solution solve(Problem request);


}
