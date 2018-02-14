package com.maersk.knpsack.solver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Problem {

    @Min(1)
    @JsonProperty("capacity")
    private Integer capacity;
    
    @NotNull
    @JsonProperty("weights")
    private Integer[] weights;
    
    @NotNull
    @JsonProperty("values")
    private Integer[] values;

}
