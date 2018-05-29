package net.anumbrella.seaweedfs.core.file;

import java.io.Serializable;

public class FileHandleStatus implements Serializable{

    private String fileId;
    private long lastModified;
    private String fileName;
    private String contentType;
    private long size;
    private String fileUrl;


    public FileHandleStatus(String fileId, long lastModified, String fileName, String contentType, long size) {
        this.fileId = fileId;
        this.lastModified = lastModified;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
    }

    public FileHandleStatus(String fileId, long size) {
        this.fileId = fileId;
        this.size = size;
    }
    
    public FileHandleStatus(String fileId, long size,String publicUrl) {
        this.fileId = fileId;
        this.size = size;
        this.fileUrl = publicUrl+"/"+fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public long getSize() {
        return size;
    }

    public long getLastModified() {
        return lastModified;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }
    
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "FileHandleStatus{" +
                "fileId='" + fileId + '\'' +
                ", size=" + size +
                '}';
    }
}
