package com.hand.demo.app.service.impl;

//import com.hand.demo.app.Client.FileClient;
import com.hand.demo.api.dto.FileRequest;
import com.hand.demo.app.service.FileService;
import org.hzero.boot.file.FileClient;
import org.hzero.boot.file.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileClient fileClient;

    @Override
    public String uploadFile(Long organizationId, String bucketName, String directory, String fileName, String fileType, String storageCode, MultipartFile multipartFile){
        String url = null;
        try {
            url = fileClient.uploadFile(organizationId, bucketName, directory, fileName, fileType, storageCode, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public Map<String, List<FileDTO>> getFileInfoByUUID(Long organizationId, FileRequest fileRequest) {
        return Collections.emptyMap();
    }

//    @Override
//    public  String downloadFile(){
//
//    }
}