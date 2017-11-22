package net.anumbrella.seaweedfs.core.content;

import java.io.Serializable;

public class PreAllocateVolumesResult implements Serializable{
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PreAllocateVolumesResult{" +
                "count=" + count +
                '}';
    }
}
