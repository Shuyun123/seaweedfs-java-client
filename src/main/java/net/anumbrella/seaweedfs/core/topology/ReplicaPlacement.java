package net.anumbrella.seaweedfs.core.topology;


public class ReplicaPlacement {
    private int SameRackCount;

    private int DiffRackCount;

    private int DiffDataCenterCount;

    public void setSameRackCount(int SameRackCount){
        this.SameRackCount = SameRackCount;
    }
    public int getSameRackCount(){
        return this.SameRackCount;
    }
    public void setDiffRackCount(int DiffRackCount){
        this.DiffRackCount = DiffRackCount;
    }
    public int getDiffRackCount(){
        return this.DiffRackCount;
    }
    public void setDiffDataCenterCount(int DiffDataCenterCount){
        this.DiffDataCenterCount = DiffDataCenterCount;
    }
    public int getDiffDataCenterCount(){
        return this.DiffDataCenterCount;
    }
}
