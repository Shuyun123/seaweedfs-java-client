package net.anumbrella.seaweedfs.core.topology;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Volume {
    @JsonProperty("Id")
    private long id;
    @JsonProperty("Size")
    private long size;
    @JsonProperty("ReplicaPlacement")
    private ReplicaPlacement replicaPlacement;
    @JsonProperty("Ttl")
    private List<String> ttl;
    @JsonProperty("Collection")
    private String collection;
    @JsonProperty("Version")
    private long version;
    @JsonProperty("FileCount")
    private long fileCount;
    @JsonProperty("DeleteCount")
    private long deleteCount;
    @JsonProperty("DeletedByteCount")
    private long deletedByteCount;
    @JsonProperty("ReadOnly")
    private boolean readOnly;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public ReplicaPlacement getReplicaPlacement() {
        return replicaPlacement;
    }

    public void setReplicaPlacement(ReplicaPlacement replicaPlacement) {
        this.replicaPlacement = replicaPlacement;
    }

    public List<String> getTtl() {
        return ttl;
    }

    public void setTtl(List<String> ttl) {
        this.ttl = ttl;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getFileCount() {
        return fileCount;
    }

    public void setFileCount(long fileCount) {
        this.fileCount = fileCount;
    }

    public long getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(long deleteCount) {
        this.deleteCount = deleteCount;
    }

    public long getDeletedByteCount() {
        return deletedByteCount;
    }

    public void setDeletedByteCount(long deletedByteCount) {
        this.deletedByteCount = deletedByteCount;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public String toString() {
        return "Volume{" +
                "id=" + id +
                ", size=" + size +
                ", replicaPlacement=" + replicaPlacement +
                ", ttl=" + ttl +
                ", collection='" + collection + '\'' +
                ", version=" + version +
                ", fileCount=" + fileCount +
                ", deleteCount=" + deleteCount +
                ", deletedByteCount=" + deletedByteCount +
                ", readOnly=" + readOnly +
                '}';
    }
}
