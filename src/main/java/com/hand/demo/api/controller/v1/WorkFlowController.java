package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.WorkFlowRequest;
import com.hand.demo.app.service.WorkFlowService;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("WorkflowController.v1")
@RequestMapping("/v1/{organizationId}/workflow-head")
public class WorkFlowController {
    @Autowired
    private WorkFlowService workFlowService;

    @ApiOperation("Start workflow")
    @PostMapping("/start")
    @Permission(level = ResourceLevel.ORGANIZATION)
    public ResponseEntity<RunInstance> startWork(
            @PathVariable Long organizationId,
            @RequestBody WorkFlowRequest workFlowRequest
            ){
        return Results.success(workFlowService.start(organizationId,workFlowRequest));
    }
    @ApiOperation("With Draw")
    @PostMapping("/withdraw")
    @Permission(level = ResourceLevel.ORGANIZATION)
    public ResponseEntity<String> withDraw(
            @PathVariable Long organizationId,
            @RequestBody WorkFlowRequest workFlowRequest
    ){
        return Results.success(workFlowService.withDraw(organizationId,workFlowRequest));
    }
    @ApiOperation("Approve History")
    @PostMapping("/approvehistory")
    @Permission(level = ResourceLevel.ORGANIZATION)
    public ResponseEntity<List<RunTaskHistory>> approveHistory(
            @PathVariable Long organizationId,
            @RequestParam Long instanceId
    ){
        return Results.success(workFlowService.approveHistory(organizationId,instanceId));
    }


}
