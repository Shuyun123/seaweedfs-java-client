package net.anumbrella.seaweedfs.core.topology;

import java.io.Serializable;
import java.util.List;

public class VolumeStatus implements Serializable {

    private String Version;

    private List<Volume> Volumes;

    public void setVersion(String Version){
        this.Version = Version;
    }
    public String getVersion(){
        return this.Version;
    }
    public void setVolumes(List<Volume> Volumes){
        this.Volumes = Volumes;
    }
    public List<Volume> getVolumes(){
        return this.Volumes;
    }
}
