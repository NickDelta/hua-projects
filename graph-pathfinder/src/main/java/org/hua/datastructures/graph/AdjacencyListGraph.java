package org.hua.datastructures.graph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AdjacencyListGraph {
    private final Map<Long, List<Long>> normalGraph = new HashMap<>();
    private final Map<Long, List<Long>> invertedGraph = new HashMap<>();
    private int totalEdges;

    public AdjacencyListGraph(String path) throws IOException {
        System.out.println("Parsing file " + path);
        long tStart = System.nanoTime();

        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.US_ASCII);
        BufferedReader br = new BufferedReader(isr);

        Pattern pattern = Pattern.compile("\\s");

        int lineCounter = 0;
        String line;
        while ((line = br.readLine()) != null) {
            lineCounter++;

            if (line.charAt(0) != '#') //Discarding any comments
            {
                try {
                    //Tokenize the line
                    final String[] tokens = pattern.split(line);
                    final long source = Long.parseLong(tokens[0]);
                    final long target = Long.parseLong(tokens[1]);

                    //Add the edge to the 2 Maps
                    addTargetToSource(normalGraph, source, target);
                    addTargetToSource(invertedGraph, target, source);
                    this.totalEdges++;

                } catch (NumberFormatException ex) {
                    System.err.println("Error at line " + lineCounter + " : Could not parse. String was: " + line + ". Skipping line ");
                }
            }
        }
        br.close();
        long tEnd = System.nanoTime();
        System.out.println("Loaded " + totalEdges + " edges in " + Duration.ofNanos(tEnd - tStart).toMillis() + "ms");

    }

    public Map<Long, List<Long>> getNormalGraph() {
        return normalGraph;
    }

    public Map<Long, List<Long>> getInvertedGraph() {
        return invertedGraph;
    }

    private void addTargetToSource(Map<Long, List<Long>> map, long source, long target) {
        map.putIfAbsent(source, new LinkedList<>());
        map.get(source).add(target);
    }
}