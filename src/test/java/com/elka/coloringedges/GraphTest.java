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

        assertTrue(vertices.contains(new Vertex((long) 1)));
        assertTrue(vertices.contains(new Vertex((long) 2)));
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
        assertEquals(1, vertices.get(0).getNeighbours().size());
        assertEquals(2, vertices.get(1).getNeighbours().size());
        assertEquals(1, vertices.get(2).getNeighbours().size());
    }

    @Test
    public void shouldAddingMultipleEdgesShouldAddNeighbours2() {
        Graph graph = new Graph();
        graph.addEdge((long) 1, (long) 2);
        graph.addEdge((long) 3, (long) 2);
        graph.addEdge((long) 2, (long) 1);
        graph.addEdge((long) 1, (long) 4);
        graph.addEdge((long) 3, (long) 2);

        List<Vertex> vertices = new ArrayList<>(graph.getVertices());

        assertEquals(2, vertices.get(0).getNeighbours().size());
        assertEquals(2, vertices.get(1).getNeighbours().size());
        assertEquals(1, vertices.get(2).getNeighbours().size());
        assertEquals(1, vertices.get(3).getNeighbours().size());
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
}
