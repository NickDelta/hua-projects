package org.hua.datastructures.graph;

import java.time.Duration;
import java.util.*;

public class CSRGraph {
    private final Map<Long, Integer> idMap;
    private final int[] IA;
    private final long[] JA;

    public CSRGraph(Map<Long, List<Long>> graph) {
        long tStart = System.nanoTime();

        this.idMap = new HashMap<>(graph.size());
        this.IA = new int[graph.size() + 1];
        this.IA[0] = 0;

        int totalEdges = graph.values().stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum();

        this.JA = new long[totalEdges];

        int IA_Index = 0;
        int JA_Index = 0;
        for (Map.Entry<Long, List<Long>> entry : graph.entrySet()) {
            long key = entry.getKey();
            List<Long> values = entry.getValue();

            for (long target : values)
                this.JA[JA_Index++] = target;

            this.idMap.put(key, IA_Index);
            this.IA[IA_Index + 1] = IA[IA_Index] + values.size();

            IA_Index++;
        }

        long tEnd = System.nanoTime();
        System.out.println("Adjacency list to CSR conversion took " + Duration.ofNanos(tEnd - tStart).toMillis() + "ms");
    }

    public LinkedList<Long> getNeighbors(Long id) {
        LinkedList<Long> children = new LinkedList<>();

        if (!idMap.containsKey(id))
            return children;

        int idIndex = idMap.get(id);

        long[] childrenArray = Arrays.copyOfRange(JA, IA[idIndex], IA[idIndex + 1]);

        for (long target : childrenArray)
            children.add(target);

        return children;
    }

    public boolean vertexExists(Long id) {
        return idMap.containsKey(id);
    }
}