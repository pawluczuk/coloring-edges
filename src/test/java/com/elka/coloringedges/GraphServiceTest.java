package com.elka.coloringedges;

import com.elka.coloringedges.domain.Edge;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.domain.Vertex;
import com.elka.coloringedges.service.GraphService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GraphServiceTest extends Assert {
    //TODO fix all the tests

    @Test
    public void shouldParseInputFile() throws IOException {
        String inputValues = "1 2\n" +
                "3 2\n" +
                "2 1\n" +
                "1 4\n" +
                "3 2\n";
        Graph graph = new Graph();

        GraphService graphService = new GraphService();
        graph = graphService.buildGraph(inputValues);

        assertEquals(4, graph.getVertices().size());
        assertEquals(3, graph.getEdges().size());
    }

    @Test
    public void shouldCalculateMaxColorIfNotProvided() throws IOException {
        String inputValues = "1 2\n" +
                "3 2\n" +
                "2 1\n" +
                "1 4\n" +
                "3 2\n";
        Graph graph = new Graph();

        GraphService graphService = new GraphService();
        graph = graphService.buildGraph(inputValues);

        assertEquals((long) 2, graph.getVertexWithHighestDegree().getDegree());
    }

    @Test
    public void shouldAssignMaxColorFromTxtFileHeaderLike() throws IOException {
        String inputValues = "Colors: 6\n" +
                "1 2\n" +
                "3 2\n" +
                "2 1\n" +
                "1 4\n" +
                "3 2\n";
        Graph graph = new Graph();

        GraphService graphService = new GraphService();
        graph = graphService.buildGraph(inputValues);

        assertEquals((long) 6, graph.getMaxColors());
    }


    @Test
    public void shouldAssignMaxColorFromTxtFile() throws IOException {
        String inputValues = "1 2\n" +
                "3 2\n" +
                "2 1\n" +
                "Colors: 6\n" +
                "1 4\n" +
                "3 2\n";
        Graph graph = new Graph();

        GraphService graphService = new GraphService();
        graph = graphService.buildGraph(inputValues);

        assertEquals((long) 6, graph.getMaxColors());
    }

    @Test
    public void shouldAssignMaxColorFromTxtFileEndOfFile() throws IOException {
        String inputValues = "1 2\n" +
                "3 2\n" +
                "2 1\n" +
                "1 4\n" +
                "3 2\n" +
                "Colors: 6\n";
        Graph graph = new Graph();

        GraphService graphService = new GraphService();
        graph = graphService.buildGraph(inputValues);

        assertEquals((long) 6, graph.getMaxColors());
    }

    @Test
    public void shouldColorGraph() {
        GraphService graphService = new GraphService();
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);
        graph.addEdge((long) 1, (long) 4);
        graph.addEdge((long) 2, (long) 5);
        graph.addEdge((long) 2, (long) 6);

        graph = graphService.colorEdges(graph);

        assertEquals(new Integer(2), (graph.getEdgeWithMaxColor().getColor()));
    }

}
