package net.anumbrella.seaweedfs.core.topology;

public class GarbageResult {
    private Topology Topology;

    private String Version;

    public void setTopology(Topology Topology){
        this.Topology = Topology;
    }
    public Topology getTopology(){
        return this.Topology;
    }
    public void setVersion(String Version){
        this.Version = Version;
    }
    public String getVersion(){
        return this.Version;
    }
}
