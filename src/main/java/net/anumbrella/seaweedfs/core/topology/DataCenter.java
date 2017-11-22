package net.anumbrella.seaweedfs.core.topology;

import java.util.List;

public class DataCenter {
    private String id;
    private int free;
    private int max;
    private List<Rack> racks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<Rack> getRacks() {
        return racks;
    }

    public void setRacks(List<Rack> racks) {
        this.racks = racks;
    }

    @Override
    public String toString() {
        return "DataCenter{" +
                "id='" + id + '\'' +
                ", free=" + free +
                ", max=" + max +
                ", racks=" + racks +
                '}';
    }
}
