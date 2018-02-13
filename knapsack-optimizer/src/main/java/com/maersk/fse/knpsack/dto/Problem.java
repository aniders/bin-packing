package com.maersk.fse.knpsack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Problem {

    @Min(0)
    @JsonProperty("capacity")
    private long capacity;
    
    @NotNull
    @JsonProperty("weights")
    private long[] weights;
    
    @NotNull
    @JsonProperty("values")
    private long[] values;

}
