package net.anumbrella.seaweedfs.core.topology;

import java.util.List;

public class Layout {
    private String collection;
    private String replication;
    private String ttl;
    private List<Integer> writables;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getReplication() {
        return replication;
    }

    public void setReplication(String replication) {
        this.replication = replication;
    }

    public List<Integer> getWritables() {
        return writables;
    }

    public void setWritables(List<Integer> writables) {
        this.writables = writables;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "collection='" + collection + '\'' +
                ", replication='" + replication + '\'' +
                ", ttl='" + ttl + '\'' +
                ", writables=" + writables +
                '}';
    }
}
