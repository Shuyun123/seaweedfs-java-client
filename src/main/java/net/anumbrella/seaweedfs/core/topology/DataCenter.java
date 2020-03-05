package net.anumbrella.seaweedfs.core.topology;

import com.google.common.base.MoreObjects;

import java.util.List;

public class DataCenter {
    private int Free;

    private String Id;

    private int Max;

    private List<Rack> Racks;

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
    public void setRacks(List<Rack> Racks){
        this.Racks = Racks;
    }
    public List<Rack> getRacks(){
        return this.Racks;
    }
}
