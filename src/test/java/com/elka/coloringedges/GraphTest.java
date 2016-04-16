package com.elka.coloringedges;

import com.elka.coloringedges.domain.Graph;
import org.junit.Assert;
import org.junit.Test;

public class GraphTest extends Assert {
    @Test
    public void shouldAddingEdgeShouldAddVertices() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);

        assertEquals(2, graph.getVertices().size());
    }

    @Test
    public void shouldAddingEdgeShouldAddNeighbours() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);

        assertNotEquals(null, graph.getVertices().get(0));
        assertNotEquals(null, graph.getVertices().get(1));
        assertEquals(1, graph.getVertices().get(0).getNeighbours().size());
        assertEquals(1, graph.getVertices().get(1).getNeighbours().size());
    }

    @Test
    public void shouldAddingMultipleEdgesShouldAddNeighbours() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 2, (long) 3);

        assertNotEquals(null, graph.getVertices().get(0));
        assertNotEquals(null, graph.getVertices().get(1));
        assertNotEquals(null, graph.getVertices().get(2));
        assertEquals(1, graph.getVertices().get(0).getNeighbours().size());
        assertEquals(2, graph.getVertices().get(1).getNeighbours().size());
        assertEquals(1, graph.getVertices().get(2).getNeighbours().size());
    }

    @Test
    public void shouldAddingMultipleEdgesShouldAddNeighbours2() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 3, (long) 2);
        graph.addEdge((long) 2, (long) 1);
        graph.addEdge((long) 1, (long) 4);
        graph.addEdge((long) 3, (long) 2);

        assertEquals(2, graph.getVertices().get(0).getNeighbours().size());
        assertEquals(2, graph.getVertices().get(1).getNeighbours().size());
        assertEquals(1, graph.getVertices().get(2).getNeighbours().size());
        assertEquals(1, graph.getVertices().get(3).getNeighbours().size());
    }

    @Test
    public void shouldVertexReturnItsDegree() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);

        assertTrue(graph.getVertexWithHighestDegree().getId().equals((long) 1));
        assertEquals(2, graph.getVertexWithHighestDegree().getDegree());
    }

    @Test
    public void shouldGraphReturnTheVertexWithTheHighestDegree() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);

        assertEquals(2, graph.getVertexWithHighestDegree().getNeighbours().size());
    }

}
