package com.elka.coloringedges;

import com.elka.coloringedges.domain.Graph;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class ParseFileTest extends Assert {

    @Test
    public void shouldParseInputFile() {
        String inputValues = "1 2\n" +
                            "3 2\n" +
                            "2 1\n" +
                            "1 4\n" +
                            "3 2";
        Graph graph = new Graph();


        Scanner scanner = new Scanner(inputValues);
        while (scanner.hasNextLine()) {
            while(scanner.hasNextLong()){
                graph.addEdge(scanner.nextLong(), scanner.nextLong());
            }
        }
        scanner.close();

        assertEquals(4, graph.getVertices().size());
        assertEquals(3, graph.getEdges().size());
        assertTrue((long) 1 == graph.getEdges().get(0).getSourceVertex().getId());
        assertTrue((long) 2 == graph.getEdges().get(0).getDestinationVertex().getId());
        assertTrue((long) 3 == graph.getEdges().get(1).getSourceVertex().getId());
        assertTrue((long) 2 == graph.getEdges().get(1).getDestinationVertex().getId());
    }

}
