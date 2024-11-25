package com.hand.demo.api.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class FileRequest {
    @JsonProperty(value = "organizationId")
    private long organizationId;
    @JsonProperty(value = "bucketName")
    private String bucketName;
    @JsonProperty(value = "directory")
    private String directory;
    @JsonProperty(value = "fileName")
    private String fileName;
    @JsonProperty(value = "fileType")
    private String fileType;
    @JsonProperty(value = "storageCode")
    private String storageCode;

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public FileRequest(long organizationId, String bucketName, String directory, String fileName, String fileType, String storageCode) {
        this.organizationId = organizationId;
        this.bucketName = bucketName;
        this.directory = directory;
        this.fileName = fileName;
        this.fileType = fileType;
        this.storageCode = storageCode;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }
}
