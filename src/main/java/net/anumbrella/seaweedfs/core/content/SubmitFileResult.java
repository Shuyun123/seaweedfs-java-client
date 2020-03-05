package net.anumbrella.seaweedfs.core.content;

import com.google.common.base.MoreObjects;

public class SubmitFileResult {
    private String eTag;

    private String fid;

    private String fileName;

    private String fileUrl;

    private long size;

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("eTag", eTag)
                .add("fid", fid)
                .add("fileName", fileName)
                .add("fileUrl", fileUrl)
                .add("size", size)
                .toString();
    }
}
