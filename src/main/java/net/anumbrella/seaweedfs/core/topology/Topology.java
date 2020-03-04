package net.anumbrella.seaweedfs.core.topology;

import java.util.List;

public class Topology {
    private List<DataCenter> DataCenters;

    private int Free;

    private List<Layout> Layouts;

    private int Max;

    public void setDataCenters(List<DataCenter> DataCenters){
        this.DataCenters = DataCenters;
    }
    public List<DataCenter> getDataCenters(){
        return this.DataCenters;
    }
    public void setFree(int Free){
        this.Free = Free;
    }
    public int getFree(){
        return this.Free;
    }
    public void setLayouts(List<Layout> Layouts){
        this.Layouts = Layouts;
    }
    public List<Layout> getLayouts(){
        return this.Layouts;
    }
    public void setMax(int Max){
        this.Max = Max;
    }
    public int getMax(){
        return this.Max;
    }
}
