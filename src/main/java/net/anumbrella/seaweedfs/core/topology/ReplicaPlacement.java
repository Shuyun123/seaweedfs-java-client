package net.anumbrella.seaweedfs.core.topology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplicaPlacement {
    @JsonProperty("SameRackCount")
    private int sameRackCount;
    @JsonProperty("DiffRackCount")
    private int diffRackCount;
    @JsonProperty("DiffDataCenterCount")
    private int diffDataCenterCount;

    public int getSameRackCount() {
        return sameRackCount;
    }

    public void setSameRackCount(int sameRackCount) {
        this.sameRackCount = sameRackCount;
    }

    public int getDiffRackCount() {
        return diffRackCount;
    }

    public void setDiffRackCount(int diffRackCount) {
        this.diffRackCount = diffRackCount;
    }

    public int getDiffDataCenterCount() {
        return diffDataCenterCount;
    }

    public void setDiffDataCenterCount(int diffDataCenterCount) {
        this.diffDataCenterCount = diffDataCenterCount;
    }

    @Override
    public String toString() {
        return "ReplicaPlacement{" +
                "sameRackCount=" + sameRackCount +
                ", diffRackCount=" + diffRackCount +
                ", diffDataCenterCount=" + diffDataCenterCount +
                '}';
    }
}
