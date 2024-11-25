package com.hand.demo.app.service;

import com.hand.demo.api.dto.FileRequest;
import org.hzero.boot.file.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {
    String uploadFile(Long organizationId, String bucketName, String directory, String fileName, String fileType, String storageCode, MultipartFile multipartFile);

    Map<String, List<FileDTO>> getFileInfoByUUID(Long organizationId, FileRequest fileRequest);
}
