package net.anumbrella.seaweedfs.core.topology;

import java.util.List;

public class Rack {
    private List<DataNode> DataNodes;

    private int Free;

    private String Id;

    private int Max;

    public void setDataNodes(List<DataNode> DataNodes){
        this.DataNodes = DataNodes;
    }
    public List<DataNode> getDataNodes(){
        return this.DataNodes;
    }
    public void setFree(int Free){
        this.Free = Free;
    }
    public int getFree(){
        return this.Free;
    }
    public void setId(String Id){
        this.Id = Id;
    }
    public String getId(){
        return this.Id;
    }
    public void setMax(int Max){
        this.Max = Max;
    }
    public int getMax(){
        return this.Max;
    }
}
