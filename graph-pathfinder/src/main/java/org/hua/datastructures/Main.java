package org.hua.datastructures;

import org.hua.datastructures.bfs.BidirectionalBFS;
import org.hua.datastructures.graph.AdjacencyListGraph;
import org.hua.datastructures.graph.CSRGraph;
import org.hua.datastructures.input.FileInputHandler;
import org.hua.datastructures.input.InputHandler;
import org.hua.datastructures.input.KeyboardInputHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        InputHandler inputHandler = null;
        switch (args.length) {
            case 1:
                inputHandler = new KeyboardInputHandler();
                break;
            case 3:
                if (args[1].equalsIgnoreCase("-f"))
                    inputHandler = new FileInputHandler(args[2]);
                else {
                    System.err.println("Usage: program <graphPath> [-f <queriesFile> ] ");
                    System.exit(-1);
                }
                break;
            default:
                System.err.println("Usage: program <graphPath> [-f <queriesFile> ] ");
                System.exit(-1);
                break;

        }

        try {
            AdjacencyListGraph graph = new AdjacencyListGraph(args[0]);
            CSRGraph normalCSR = new CSRGraph(graph.getNormalGraph());
            CSRGraph invertedCSR = new CSRGraph(graph.getInvertedGraph());

            BidirectionalBFS bfs = new BidirectionalBFS(normalCSR, invertedCSR);

            inputHandler.processQueries(bfs);
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "\nTerminating application...");
            System.exit(-1);
        }
    }
}
