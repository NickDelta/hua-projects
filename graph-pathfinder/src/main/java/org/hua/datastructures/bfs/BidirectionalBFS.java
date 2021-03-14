package org.hua.datastructures.bfs;

import org.hua.datastructures.graph.CSRGraph;
import org.hua.datastructures.graph.VertexPair;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BidirectionalBFS {

    private final CSRGraph normalGraph;
    private final CSRGraph invertedGraph;

    public BidirectionalBFS(CSRGraph normalGraph, CSRGraph invertedGraph) {
        this.normalGraph = normalGraph;
        this.invertedGraph = invertedGraph;
    }

    public BFSResult query(VertexPair query) {
        long tStart = System.nanoTime();

        long source = query.getSourceNode();
        long target = query.getTargetNode();

        /*We then check if the source and target nodes exist in the graph.
          We win a lot of time if we skip doomed BFS queries.*/
        boolean sourceExists = normalGraph.vertexExists(source);
        boolean targetExists = invertedGraph.vertexExists(target);

        if (!sourceExists || !targetExists)
            return new BFSResult(source, sourceExists, target, targetExists,
                    false, null, Duration.ofNanos(System.nanoTime() - tStart));

        LinkedList<Long> queueNormal = new LinkedList<>();
        LinkedList<Long> queueInverted = new LinkedList<>();

        Map<Long, Integer> nodeInfoNormal = new HashMap<>();
        Map<Long, Integer> nodeInfoInverted = new HashMap<>();

        nodeInfoNormal.put(source, 0);
        nodeInfoInverted.put(target, 0);

        queueNormal.add(source);
        queueInverted.add(target);

        while (!queueNormal.isEmpty() || !queueInverted.isEmpty()) {
            Intersection intersection;
            if ((intersection = performBidirectionalBFS(normalGraph, queueNormal, nodeInfoNormal, nodeInfoInverted)).intersectionExists() ||
                    (intersection = performBidirectionalBFS(invertedGraph, queueInverted, nodeInfoInverted,
                            nodeInfoNormal)).intersectionExists()) {
                int normalDistance = nodeInfoNormal.get(intersection.getIntersectionNode());
                int invertedDistance = nodeInfoInverted.get(intersection.getIntersectionNode());
                int totalDistance = normalDistance + invertedDistance;

                return new BFSResult(source, true, target, true,
                        true, totalDistance, Duration.ofNanos(System.nanoTime() - tStart));
            }
        }
        return new BFSResult(source, true, target, true,
                false, null, Duration.ofNanos(System.nanoTime() - tStart));
    }

    private Intersection performBidirectionalBFS(CSRGraph graph, LinkedList<Long> queue,
                                                 Map<Long, Integer> nodeInfoThisGraph,
                                                 Map<Long, Integer> nodeInfoOtherGraph) {

        if (!queue.isEmpty()) {
            long currentNode = queue.remove();

            LinkedList<Long> adjacentNodes = graph.getNeighbors(currentNode);

            while (!adjacentNodes.isEmpty()) {
                long adjacent = adjacentNodes.poll();

                if (nodeInfoOtherGraph.containsKey(adjacent)) {
                    nodeInfoThisGraph.put(adjacent, nodeInfoThisGraph.get(currentNode) + 1);
                    return new Intersection(true, adjacent);
                } else if (!nodeInfoThisGraph.containsKey(adjacent)) {
                    nodeInfoThisGraph.put(adjacent, nodeInfoThisGraph.get(currentNode) + 1);
                    queue.add(adjacent);
                }
            }
        }
        return new Intersection(false, null);
    }
}

class Intersection {

    private final boolean intersectionExists;
    private final Long intersectionNode;

    public Intersection(boolean intersectionExists, Long intersectionNode) {
        this.intersectionExists = intersectionExists;
        this.intersectionNode = intersectionNode;
    }

    public boolean intersectionExists() {
        return intersectionExists;
    }

    public Long getIntersectionNode() {
        return intersectionNode;
    }
}
