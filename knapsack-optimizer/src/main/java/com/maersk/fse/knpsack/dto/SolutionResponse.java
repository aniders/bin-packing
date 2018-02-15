package com.maersk.fse.knpsack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolutionResponse {

    private String task;
    
    private Problem problem;
    
    private Solution solution;
}
