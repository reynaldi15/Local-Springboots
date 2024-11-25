package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.FileRequest;
import com.hand.demo.app.service.FileService;
import com.hand.demo.config.SwaggerTags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.core.util.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = SwaggerTags.FILE)
@RestController("FileController.v1.48209")
@RequestMapping("/v1/{organizationId}/files")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "File Info By UUID")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/file-info")
    public ResponseEntity<Map<String, List<FileDTO>>> fileInfoByUUID(@PathVariable Long organizationId,FileRequest fileRequest){
        return Results.success(fileService.getFileInfoByUUID(organizationId,fileRequest));
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "File Upload!")
    @PostMapping("/multipart")
    public ResponseEntity<Map<String, Object>> uploadFile(
            @PathVariable("organizationId") Long organizationId,
            @RequestParam("bucketName") String bucketName,
            @RequestParam("directory") String directory,
            @RequestParam("fileName") String fileName,
            @RequestParam("fileType") String fileType,
            @RequestParam("storageCode") String storageCode,
            @RequestParam("file") MultipartFile multipartFile){

        log.info(bucketName);
        String result = fileService.uploadFile(organizationId, bucketName, directory, fileName, fileType, storageCode, multipartFile);
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("result", result);

        return ResponseEntity.status(HttpStatus.OK).body(mapResponse);
    }


//    @GetMapping("/attachment/uuid")
//    public ResponseEntity<String> fetchAttachmentUUID(@RequestParam("organization") Long organization) {
//        try {
//            // Memanggil fileClient untuk mendapatkan UUID
//            String attachmentUUID = fileClient.getAttachmentUUID(organization);
//
//            // Mengembalikan UUID sebagai respons
//            return ResponseEntity.ok(attachmentUUID);
//
//        } catch (Exception e) {
//            log.error("Error fetching attachment UUID: {}", e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

//    @Permission(level = ResourceLevel.SITE)
//    @ApiOperation(value = "Download file by fileKey")
//    @GetMapping("/file/download")
//    public ResponseEntity<byte[]> downloadFileByKey(
//            @RequestParam("organization") Long organization,
//            @RequestParam("fileKey") String fileKey) {
//
//        try {
//            // Memanggil fileClient untuk mendapatkan respons file
//            Response response = fileClient.downloadFileResponse(organization, fileKey);
//
//            // Menangkap konten file dari response body
//            byte[] fileContent = response.body().bytes();
//
//            // Menangkap metadata (misalnya, nama file dan tipe file)
//            String fileName = response.header("Content-Disposition")
//                    .replace("attachment; filename=", "");
//            String contentType
//
//
//

}
