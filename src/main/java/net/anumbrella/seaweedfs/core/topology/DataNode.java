package net.anumbrella.seaweedfs.core.topology;


public class DataNode {
    private int EcShards;

    private int Free;

    private int Max;

    private String PublicUrl;

    private String Url;

    private int Volumes;

    public void setEcShards(int EcShards){
        this.EcShards = EcShards;
    }
    public int getEcShards(){
        return this.EcShards;
    }
    public void setFree(int Free){
        this.Free = Free;
    }
    public int getFree(){
        return this.Free;
    }
    public void setMax(int Max){
        this.Max = Max;
    }
    public int getMax(){
        return this.Max;
    }
    public void setPublicUrl(String PublicUrl){
        this.PublicUrl = PublicUrl;
    }
    public String getPublicUrl(){
        return this.PublicUrl;
    }
    public void setUrl(String Url){
        this.Url = Url;
    }
    public String getUrl(){
        return this.Url;
    }
    public void setVolumes(int Volumes){
        this.Volumes = Volumes;
    }
    public int getVolumes(){
        return this.Volumes;
    }
}
