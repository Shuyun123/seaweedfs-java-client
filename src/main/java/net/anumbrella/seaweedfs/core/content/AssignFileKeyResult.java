package net.anumbrella.seaweedfs.core.content;

import net.anumbrella.seaweedfs.util.ConnectionUtil;

import java.io.Serializable;

public class AssignFileKeyResult implements Serializable {
    private Integer count;
    private String fid;
    private String url;
    private String publicUrl;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = ConnectionUtil.convertUrlWithScheme(url);
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = ConnectionUtil.convertUrlWithScheme(publicUrl);
    }

    @Override
    public String toString() {
        return "AssignFileKeyResult{" +
                "count=" + count +
                ", fid='" + fid + '\'' +
                ", url='" + url + '\'' +
                ", publicUrl='" + publicUrl + '\'' +
                '}';
    }
}
