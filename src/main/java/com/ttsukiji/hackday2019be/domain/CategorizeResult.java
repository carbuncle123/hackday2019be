package com.ttsukiji.hackday2019be.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CategorizeResult {
    private int result;
    private Map<String, Integer> detail;
}
