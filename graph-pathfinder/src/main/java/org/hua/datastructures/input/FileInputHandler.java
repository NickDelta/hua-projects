package org.hua.datastructures.input;

import org.hua.datastructures.bfs.BidirectionalBFS;
import org.hua.datastructures.graph.VertexPair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class FileInputHandler implements InputHandler {
    private final String path;

    public FileInputHandler(String path) {
        this.path = path;
    }

    public void processQueries(BidirectionalBFS bfs) {
        System.out.println("Using file input mode.");

        LinkedList<VertexPair> queries = null;
        try {
            queries = parseQueries();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "\nTerminating application...");
            System.exit(-1);
        }

        for (VertexPair query : queries) {
            var result = bfs.query(query);
            System.out.println(result.toString());
        }

        System.out.println("Thank you for using our software!");
    }

    private LinkedList<VertexPair> parseQueries() throws IOException {
        long totalQueries = 0;
        LinkedList<VertexPair> queries = new LinkedList<>();

        System.out.println("Parsing query file " + path);
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

                    queries.add(new VertexPair(source, target));
                    totalQueries++;

                } catch (NumberFormatException ex) {
                    System.err.println("Error at line " + lineCounter + " : Could not parse. String was: " + line + ". Skipping line ");
                }
            }
        }

        br.close();
        long tEnd = System.nanoTime();
        System.out.println("Loaded " + totalQueries + " queries in " + Duration.ofNanos(tEnd - tStart).toMillis() + "ms");

        return queries;

    }
}
