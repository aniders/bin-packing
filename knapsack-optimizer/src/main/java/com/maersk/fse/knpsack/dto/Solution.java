package com.maersk.fse.knpsack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Solution {

    private Integer[] items;
    private long time;
}
