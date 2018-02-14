package com.maersk.knpsack.solver.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.knpsack.solver.dto.Problem;
import com.maersk.knpsack.solver.dto.Solution;
import com.maersk.knpsack.solver.service.KnapsackSolver;

@Service
public class KnapsackSolverImpl implements KnapsackSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnapsackSolverImpl.class);


    @Autowired
    public KnapsackSolverImpl() {
        
    }


    @Override
    public Solution solve(Problem request) {
        // TODO Auto-generated method stub
        return null;
    }


}
