package com.hand.demo.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class WorkFlowRequest {
    private String flowKey;
    private String businessKey;
    private String dimension;
    private String starter;
    private Map<String, Object> variableMap;
}
