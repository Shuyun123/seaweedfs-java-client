package net.anumbrella.seaweedfs.core.content;

import java.io.Serializable;

/**
 * @author chengxiang
 */
public class FilerUploadResult implements Serializable
{
    private static final long serialVersionUID = 1884248936465355459L;

    private String name;

    private long size;

    private String fid;

    private String url;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public String getFid()
    {
        return fid;
    }

    public void setFid(String fid)
    {
        this.fid = fid;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
