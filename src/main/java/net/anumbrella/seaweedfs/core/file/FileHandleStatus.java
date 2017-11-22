package net.anumbrella.seaweedfs.core.file;

import java.io.Serializable;

public class FileHandleStatus implements Serializable{

    private String fileId;
    private long lastModified;
    private String fileName;
    private String contentType;
    private long size;

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

    @Override
    public String toString() {
        return "FileHandleStatus{" +
                "fileId='" + fileId + '\'' +
                ", size=" + size +
                '}';
    }
}
