package com.aniders.fse.knpsack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Problem {

    @JsonProperty("capacity")
    private int capacity;
    
    @JsonProperty("weights")
    private int[] weights;
    
    @JsonProperty("values")
    private int[] values;

}
