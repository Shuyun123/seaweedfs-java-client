package net.anumbrella.seaweedfs.core.topology;

import net.anumbrella.seaweedfs.util.ConnectionUtil;

import java.io.Serializable;

public class MasterStatus  implements Serializable {

    private String url;
    private boolean isActive;

    public MasterStatus(String url) {
        this.url = ConnectionUtil.convertUrlWithScheme(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = ConnectionUtil.convertUrlWithScheme(url);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "MasterStatus{" +
                "url=" + url +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MasterStatus that = (MasterStatus) o;

        return url.equals(that.url);

    }

}