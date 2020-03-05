package net.anumbrella.seaweedfs.core.topology;


import java.util.List;

public class Layout {
    private String collection;

    private String replication;

    private String ttl;

    private List<Integer> writables;

    public void setCollection(String collection){
        this.collection = collection;
    }
    public String getCollection(){
        return this.collection;
    }
    public void setReplication(String replication){
        this.replication = replication;
    }
    public String getReplication(){
        return this.replication;
    }
    public void setTtl(String ttl){
        this.ttl = ttl;
    }
    public String getTtl(){
        return this.ttl;
    }

    public List<Integer> getWritables() {
        return writables;
    }

    public void setWritables(List<Integer> writables) {
        this.writables = writables;
    }
}
