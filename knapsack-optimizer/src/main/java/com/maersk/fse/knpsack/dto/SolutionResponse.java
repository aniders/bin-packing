package com.maersk.fse.knpsack.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SolutionResponse {

    private String task;
    
    private Problem problem;
    
    private Solution solution;
}
