package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.WorkFlowRequest;
import com.hand.demo.app.service.WorkFlowService;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkFLowServiceImpl implements WorkFlowService {
    @Autowired
    private WorkflowClient workflowClient;

    @Override
    public RunInstance start(Long organizationId, WorkFlowRequest workFlowRequest) {
        return workflowClient.startInstanceByFlowKey(
                organizationId,
                workFlowRequest.getFlowKey(),
                workFlowRequest.getBusinessKey(),
                workFlowRequest.getDimension(),
                workFlowRequest.getStarter(),
                workFlowRequest.getVariableMap()
        );
    }
    @Override
    public String withDraw(Long organizationId, WorkFlowRequest workFlowRequest){
        try{
            workflowClient.flowWithdrawFlowKey(organizationId,workFlowRequest.getFlowKey(),workFlowRequest.getBusinessKey());
            return "with draw success";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<RunTaskHistory> approveHistory(Long organizationId, Long instanceId){
        try {
            return workflowClient.approveHistory(organizationId,instanceId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
