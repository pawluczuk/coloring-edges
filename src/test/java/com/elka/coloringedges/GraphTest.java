package com.elka.coloringedges;

import com.elka.coloringedges.domain.Edge;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.domain.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        List<Vertex> vertices = new ArrayList<>(graph.getVertices());

        assertEquals(1, vertices.get(0).getNeighbours().size());
        assertEquals(1, vertices.get(1).getNeighbours().size());
    }

    @Test
    public void shouldAddingMultipleEdgesShouldAddNeighbours() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 2, (long) 3);

        List<Vertex> vertices = new ArrayList<>(graph.getVertices());

        assertNotEquals(null, vertices.get(0));
        assertNotEquals(null, vertices.get(1));
        assertNotEquals(null, vertices.get(2));
        assertNotNull(vertices.get(0).getNeighbours().size());
        assertNotNull(vertices.get(1).getNeighbours().size());
        assertNotNull(vertices.get(2).getNeighbours().size());
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

    @Test
    public void shouldGraphReturnDeltaGraph() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);

        assertEquals(new Integer(2), graph.getDeltaGraph());
    }

    @Test
    public void shouldGraphReturnTheEdgeWithHighestColor() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);

        Edge edge = new ArrayList<>(graph.getEdges()).get(0);
        edge.setColor(5);

        assertEquals(new Integer(5), graph.getEdgeWithMaxColor().getColor());
    }

    @Test
    public void shouldGraphReturnMaxColor() {
        Graph graph = new Graph(5);

        assertEquals(5, graph.getMaxColors());
    }

    @Test
    public void shouldGraphBeBepartite() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);

        assertTrue(graph.isBipartite());
    }

/*  Only if graph allows cycles
    @Test
    public void shouldGraphNotBeBepartite() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 1, (long) 3);
        graph.addEdge((long) 2, (long) 3);

        assertTrue(!graph.isBipartite());
    }*/
}
