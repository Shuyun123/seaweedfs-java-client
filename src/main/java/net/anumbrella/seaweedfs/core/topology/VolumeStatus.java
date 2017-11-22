package net.anumbrella.seaweedfs.core.topology;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class VolumeStatus implements Serializable {
    @JsonIgnoreProperties
    private String url;
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Volumes")
    private List<Volume> volumes;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    @Override
    public String toString() {
        return "VolumeStatus{" +
                "url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", volumes=" + volumes +
                '}';
    }
}
