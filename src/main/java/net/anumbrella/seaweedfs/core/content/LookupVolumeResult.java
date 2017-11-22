package net.anumbrella.seaweedfs.core.content;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class LookupVolumeResult implements Serializable{
    private long volumeId;
    private Random random = new Random();
    private List<LocationResult> locations;

    public long getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    public List<LocationResult> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationResult> locations) {
        this.locations = locations;
    }

    public LocationResult getRandomLocation() {
        if (locations != null) {
            return locations.get((random.nextInt(locations.size())));
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "LookupVolumeResult{" +
                "volumeId='" + volumeId + '\'' +
                ", locations=" + locations +
                '}';
    }
}
