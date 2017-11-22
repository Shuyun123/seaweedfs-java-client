package net.anumbrella.seaweedfs.core.topology;

import java.io.Serializable;
import java.util.List;

public class SystemTopologyStatus implements Serializable{
    private int max;
    private int free;
    private String version;
    private List<DataCenter> dataCenters;
    private List<Layout> layouts;

    public SystemTopologyStatus() {
    }

    public SystemTopologyStatus(int max, int free, List<DataCenter> dataCenters, List<Layout> layouts) {
        this.max = max;
        this.free = free;
        this.dataCenters = dataCenters;
        this.layouts = layouts;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public List<DataCenter> getDataCenters() {
        return dataCenters;
    }

    public void setDataCenters(List<DataCenter> dataCenters) {
        this.dataCenters = dataCenters;
    }

    public List<Layout> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<Layout> layouts) {
        this.layouts = layouts;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SystemTopologyStatus{" +
                "max=" + max +
                ", free=" + free +
                ", version='" + version + '\'' +
                ", dataCenters=" + dataCenters +
                ", layouts=" + layouts +
                '}';
    }
}
