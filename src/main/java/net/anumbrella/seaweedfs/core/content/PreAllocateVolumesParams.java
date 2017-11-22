package net.anumbrella.seaweedfs.core.content;

public class PreAllocateVolumesParams {
    private String replication;
    private int count;
    private String dataCenter;
    private String ttl;
    private String collection;

    public PreAllocateVolumesParams() {
    }

    public PreAllocateVolumesParams(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public PreAllocateVolumesParams(int count) {
        this.count = count;
    }

    public PreAllocateVolumesParams(String replication, int count, String dataCenter, String ttl) {
        this.replication = replication;
        this.count = count;
        this.dataCenter = dataCenter;
        this.ttl = ttl;
    }

    public String getReplication() {
        return replication;
    }

    public void setReplication(String replication) {
        this.replication = replication;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String toUrlParams() {
        String result = "?";
        if (replication != null) {
            result = result + "replication=" + replication + "&";
        }
        if (dataCenter != null) {
            result = result + "dataCenter=" + dataCenter + "&";
        }
        if (count > 0) {
            result = result + "count=" + Integer.toString(count) + "&";
        }
        if (collection != null) {
            result = result + "collection=" + collection + "&";
        }
        if (ttl != null) {
            result = result + "ttl=" + ttl;
        }
        return result;
    }

    @Override
    public String toString() {
        return "PreAllocateVolumesParams{" +
                "replication='" + replication + '\'' +
                ", count=" + count +
                ", dataCenter='" + dataCenter + '\'' +
                ", ttl='" + ttl + '\'' +
                ", collection='" + collection + '\'' +
                '}';
    }
}
