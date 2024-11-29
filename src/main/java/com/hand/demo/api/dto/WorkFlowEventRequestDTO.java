package com.hand.demo.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class WorkFlowEventRequestDTO {
    private String businessKey;
    private String docStatus;
    private Long workflowId;
    private Date approvedTime;
}