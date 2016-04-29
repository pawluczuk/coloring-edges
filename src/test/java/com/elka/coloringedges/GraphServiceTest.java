package com.elka.coloringedges;

import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.service.GraphService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GraphServiceTest extends Assert {

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
        assertTrue((long) 1 == graph.getEdges().get(0).getSourceVertex().getId());
        assertTrue((long) 2 == graph.getEdges().get(0).getDestinationVertex().getId());
        assertTrue((long) 3 == graph.getEdges().get(1).getSourceVertex().getId());
        assertTrue((long) 2 == graph.getEdges().get(1).getDestinationVertex().getId());
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

        assertTrue((long) 2 == graph.getVertexWithHighestDegree().getDegree());
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

        assertTrue((long) 6 == graph.getMaxColors());
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

        assertTrue((long) 6 == graph.getMaxColors());
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

        assertTrue((long) 6 == graph.getMaxColors());
    }


}
