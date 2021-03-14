package org.hua.datastructures.graph;

public class VertexPair {
    private final long sourceNode;
    private final long targetNode;

    public VertexPair(long sourceNode, long targetNode) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    public long getSourceNode() {
        return sourceNode;
    }

    public long getTargetNode() {
        return targetNode;
    }
}
