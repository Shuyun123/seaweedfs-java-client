package net.anumbrella.seaweedfs.core.topology;

import com.google.common.base.MoreObjects;

public class Volume {
    private int Id;

    private int Size;

    private ReplicaPlacement ReplicaPlacement;

    private Ttl Ttl;

    private String Collection;

    private int Version;

    private int FileCount;

    private int DeleteCount;

    private int DeletedByteCount;

    private boolean ReadOnly;

    private int CompactRevision;

    private int ModifiedAtSecond;

    private String RemoteStorageName;

    private String RemoteStorageKey;

    public void setId(int Id){
        this.Id = Id;
    }
    public int getId(){
        return this.Id;
    }
    public void setSize(int Size){
        this.Size = Size;
    }
    public int getSize(){
        return this.Size;
    }
    public void setReplicaPlacement(ReplicaPlacement ReplicaPlacement){
        this.ReplicaPlacement = ReplicaPlacement;
    }
    public ReplicaPlacement getReplicaPlacement(){
        return this.ReplicaPlacement;
    }
    public void setTtl(Ttl Ttl){
        this.Ttl = Ttl;
    }
    public Ttl getTtl(){
        return this.Ttl;
    }
    public void setCollection(String Collection){
        this.Collection = Collection;
    }
    public String getCollection(){
        return this.Collection;
    }
    public void setVersion(int Version){
        this.Version = Version;
    }
    public int getVersion(){
        return this.Version;
    }
    public void setFileCount(int FileCount){
        this.FileCount = FileCount;
    }
    public int getFileCount(){
        return this.FileCount;
    }
    public void setDeleteCount(int DeleteCount){
        this.DeleteCount = DeleteCount;
    }
    public int getDeleteCount(){
        return this.DeleteCount;
    }
    public void setDeletedByteCount(int DeletedByteCount){
        this.DeletedByteCount = DeletedByteCount;
    }
    public int getDeletedByteCount(){
        return this.DeletedByteCount;
    }
    public void setReadOnly(boolean ReadOnly){
        this.ReadOnly = ReadOnly;
    }
    public boolean getReadOnly(){
        return this.ReadOnly;
    }
    public void setCompactRevision(int CompactRevision){
        this.CompactRevision = CompactRevision;
    }
    public int getCompactRevision(){
        return this.CompactRevision;
    }
    public void setModifiedAtSecond(int ModifiedAtSecond){
        this.ModifiedAtSecond = ModifiedAtSecond;
    }
    public int getModifiedAtSecond(){
        return this.ModifiedAtSecond;
    }
    public void setRemoteStorageName(String RemoteStorageName){
        this.RemoteStorageName = RemoteStorageName;
    }
    public String getRemoteStorageName(){
        return this.RemoteStorageName;
    }
    public void setRemoteStorageKey(String RemoteStorageKey){
        this.RemoteStorageKey = RemoteStorageKey;
    }
    public String getRemoteStorageKey(){
        return this.RemoteStorageKey;
    }
}
