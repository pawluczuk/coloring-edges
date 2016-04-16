package com.elka.coloringedges.domain;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private int maxColors;

    public Graph() {}

    public Graph(int maxColors) {
        this.maxColors = maxColors;
    }

    public void addEdge(Long v1, Long v2) {
        Vertex sourceVertex = null;
        Vertex destinationVertex = null;
        for (Vertex currentVertex : vertices) {
            if (v1.equals(currentVertex.getId())) {
                sourceVertex = currentVertex;
            } else if (v2.equals(currentVertex.getId())) {
                destinationVertex = currentVertex;
            }
        }
        if(sourceVertex != null && destinationVertex != null)
            return;

        if(sourceVertex == null){
            sourceVertex = new Vertex(v1);
            vertices.add(sourceVertex);
        }
        if(destinationVertex == null) {
            destinationVertex = new Vertex(v2);
            vertices.add(destinationVertex);
        }

        sourceVertex.addNeighbour(destinationVertex);
        destinationVertex.addNeighbour(sourceVertex);
        edges.add(new Edge(sourceVertex, destinationVertex));
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Vertex getVertexWithHighestDegree() {
        Vertex vertexWithHighestDegree = new Vertex();
        List<Vertex> vertices = this.getVertices();
        for (Vertex vertex : vertices) {
            vertexWithHighestDegree = vertex.getNeighbours().size() > vertexWithHighestDegree.getNeighbours().size() ? vertex : vertexWithHighestDegree;
        }
        return vertexWithHighestDegree;
    }

    public int getMaxColors() {
        return maxColors;
    }

    public void setMaxColors(int maxColors) {
        this.maxColors = maxColors;
    }
}
