package com.aniders.knpsack.solver.service;

import com.aniders.knpsack.solver.dto.Problem;
import com.aniders.knpsack.solver.dto.Solution;

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
