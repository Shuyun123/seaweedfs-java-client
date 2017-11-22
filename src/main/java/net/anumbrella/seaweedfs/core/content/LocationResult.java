package net.anumbrella.seaweedfs.core.content;

import net.anumbrella.seaweedfs.util.ConnectionUtil;

import java.io.Serializable;

public class LocationResult implements Serializable {
    private String url;
    private String publicUrl;

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
        return "LocationResult{" +
                "url='" + url + '\'' +
                ", publicUrl='" + publicUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationResult)) return false;

        LocationResult that = (LocationResult) o;

        if (!url.equals(that.url)) return false;
        return publicUrl.equals(that.publicUrl);

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + publicUrl.hashCode();
        return result;
    }
}
