package net.anumbrella.seaweedfs.core.content;

public class LookupVolumeParams {
    private long volumeId;
    private String collection;

    public LookupVolumeParams() {
    }

    public LookupVolumeParams(long volumeId) {
        this.volumeId = volumeId;
    }

    public LookupVolumeParams(String volumeId) {
        this.volumeId = convertVolumeId(volumeId);
    }

    public LookupVolumeParams(long volumeId, String collection) {
        this.volumeId = volumeId;
        this.collection = collection;
    }

    public LookupVolumeParams(String volumeId, String collection) {
        this.volumeId = convertVolumeId(volumeId);
        this.collection = collection;
    }

    public long getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    private long convertVolumeId(String rawVolumeId) {
        final int index = rawVolumeId.indexOf(",");
        if (index < 1)
            return Long.parseLong(rawVolumeId);
        else
            return Long.parseLong(rawVolumeId.substring(0, rawVolumeId.indexOf(",")));
    }

    public String toUrlParams() {
        String result = "?";
        result = result + "volumeId=" + String.valueOf(volumeId) + "&";
        if (collection != null) {
            result = result + "collection=" + collection;
        }
        return result;
    }

    @Override
    public String toString() {
        return "LookupVolumeParams{" +
                "volumeId='" + volumeId + '\'' +
                ", collection='" + collection + '\'' +
                '}';
    }
}
