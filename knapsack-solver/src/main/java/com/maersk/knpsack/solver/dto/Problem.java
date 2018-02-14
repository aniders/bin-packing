package com.maersk.knpsack.solver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    @Min(0)
    @JsonProperty("capacity")
    private int capacity;
    
    @NotNull
    @JsonProperty("weights")
    private int[] weights;
    
    @NotNull
    @JsonProperty("values")
    private int[] values;

}
