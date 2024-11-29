package com.hand.demo.app.service;

import com.hand.demo.api.dto.WorkFlowRequest;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.boot.workflow.dto.RunTaskHistory;

import java.util.List;

public interface WorkFlowService {

    RunInstance start(Long organizationId, WorkFlowRequest workFlowRequest);
    String withDraw(Long organizationId, WorkFlowRequest workFlowRequest);
    List<RunTaskHistory> approveHistory(Long tenantId, Long instanceId);
}
