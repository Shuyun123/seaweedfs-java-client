package net.anumbrella.seaweedfs.core.content;

public class ForceGarbageCollectionParams {
    private Float garbageThreshold;

    public ForceGarbageCollectionParams() {
    }

    public ForceGarbageCollectionParams(Float garbageThreshold) {
        this.garbageThreshold = garbageThreshold;
    }

    public Float getGarbageThreshold() {
        return garbageThreshold;
    }

    public void setGarbageThreshold(Float garbageThreshold) {
        this.garbageThreshold = garbageThreshold;
    }

    public String toUrlParams() {
        String result = "?";
        if (garbageThreshold != null) {
            result = result + "garbageThreshold=" + garbageThreshold;
        }
        return result;
    }

    @Override
    public String toString() {
        return "ForceGarbageCollectionParams{" +
                "garbageThreshold=" + garbageThreshold +
                '}';
    }
}
