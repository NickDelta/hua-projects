package org.hua.datastructures.input;

import org.hua.datastructures.bfs.BidirectionalBFS;
import org.hua.datastructures.graph.VertexPair;

import java.util.Scanner;

public class KeyboardInputHandler implements InputHandler {
    private final Scanner scan = new Scanner(System.in);

    public void processQueries(BidirectionalBFS bfs) {
        System.out.println("Using keyboard input.");
        while (true) {
            System.out.println("Give a node pair:");
            VertexPair query = new VertexPair(nextNotNegativeInt(), nextNotNegativeInt());
            var result = bfs.query(query);
            System.out.println(result.toString());

            System.out.println("Would you like an another query? (Yes/No):");
            String answer = stringEqualsIgnoreCase(new String[]{"Yes", "No"});

            if (answer.equalsIgnoreCase("No")) {
                System.out.println("Thank you for using our software!");
                System.exit(0);
            }

        }
    }

    private int nextNotNegativeInt() {
        int value;
        while (true) {
            while (!scan.hasNextInt()) {
                System.err.println("Expected an Integer. Please type again.");
                scan.next();
            }
            value = scan.nextInt();
            if (value >= 0)
                return value;
            else
                System.err.println("Field cannot be negative. Please type again.");
        }

    }

    private String stringEqualsIgnoreCase(String[] args) {
        while (true) {
            String value = scan.next();
            for (String arg : args) {
                if (value.equalsIgnoreCase(arg))
                    return value;
            }
            System.err.println("Invalid value. Please type again.");
        }
    }
}
