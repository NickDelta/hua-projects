package org.hua.datastructures.bfs;

import java.time.Duration;

public class BFSResult {

    private final long sourceId;
    private final boolean sourceExists;
    private final long targetId;
    private final boolean targetExists;
    private final boolean areConnected;
    private final Integer distance;
    private final Duration timeElapsed;

    public BFSResult(long sourceId, boolean sourceExists, long targetId,
                     boolean targetExists, boolean areConnected,
                     Integer distance, Duration timeElapsed) {
        this.sourceId = sourceId;
        this.sourceExists = sourceExists;
        this.targetId = targetId;
        this.targetExists = targetExists;
        this.areConnected = areConnected;
        this.distance = distance;
        this.timeElapsed = timeElapsed;
    }

    public long getSourceId() {
        return sourceId;
    }

    public boolean sourceExists() {
        return sourceExists;
    }

    public long getTargetId() {
        return targetId;
    }

    public boolean targetExists() {
        return targetExists;
    }

    public boolean areConnected() {
        return areConnected;
    }

    public Integer getDistance() {
        return distance;
    }

    public Duration getTimeElapsed() {
        return timeElapsed;
    }

    @Override
    public String toString() {
        return "Query Result{" +
                "source_id=" + sourceId +
                ", source_exists=" + sourceExists +
                ", target_id=" + targetId +
                ", target_exists=" + targetExists +
                ", are_connected=" + areConnected +
                ", distance=" + distance +
                ", time_elapsed=" + timeElapsed.toMillis() + "ms" +
                '}';
    }
}
