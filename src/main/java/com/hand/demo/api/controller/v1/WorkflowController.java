//package com.hand.demo.api.controller.v1;
//
//import io.choerodon.core.iam.ResourceLevel;
//import io.choerodon.swagger.annotation.Permission;
//import io.swagger.annotations.ApiOperation;
//import org.hzero.core.util.Results;
//import org.hzero.starter.keyencrypt.core.Encrypt;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController("invCountHeaderController.v1.48209")
//@RequestMapping("/v1/{organizationId}/workflow")
//public class WorkflowController {
//
////    @ApiOperation(value = "业务单据复制")
////    @Permission(level = ResourceLevel.ORGANIZATION)
////    @PostMapping("/copy")
////    public ResponseEntity<StandardResponseDTO> copyDefFlowDocument(
////            @PathVariable("organizationId") Long tenantId,
////            @RequestBody DefFlowDocumentCopyDTO defFlowDocumentCopyDTO) {
////        return Results.success(workflowClient.copyDefFlowDocument(tenantId, defFlowDocumentCopyDTO));
////    }
////    @PathVariable("organizationId") Long organizationId,
////    @RequestParam("bucketName") String bucketName,
////    @RequestParam("directory") String directory,
////    @RequestParam("fileName") String fileName,
////    @RequestParam("fileType") String fileType,
////    @RequestParam("storageCode") String storageCode,
//
//@ApiOperation(value = "部署ID启动流程实例")
//@Permission(level = ResourceLevel.ORGANIZATION)
//@PostMapping("/startInstanceByDeploymentId")
//public ResponseEntity<RunInstance> startInstanceByDeploymentId(
//        @PathVariable("organizationId") Long tenantId,
//        @RequestParam("deploymentId") @Encrypt Long deploymentId,
//        @RequestParam("businessKey") String businessKey,
//        @RequestParam("dimension") String dimension,
//        @RequestParam("starter") String starter,
//        @RequestParam(required = false) Map<String, Object> variableMap,
//        @RequestPart(value = "file",required = false) List<MultipartFile> fileList){
//    return Results.success(workflowClient.startInstanceByDeploymentIdWithMultipartFile(tenantId,deploymentId,businessKey,dimension,starter,variableMap,fileList));
//}
//}
