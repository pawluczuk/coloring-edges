package com.elka.coloringedges.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"getEdges"})
public class Graph {

    private Set<Vertex> vertices = new HashSet<>();
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
        //disables option to make multi graph
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

        Edge newEdge = new Edge(sourceVertex, destinationVertex);
        sourceVertex.addNeighbour(destinationVertex);
        destinationVertex.addNeighbour(sourceVertex);
        sourceVertex.addEdge(newEdge);
        destinationVertex.addEdge(newEdge);
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for(Vertex vertex : this.getVertices()) {
            vertex.getEdges();
            edges.addAll(vertex.getEdges());
        }
        return edges;
    }

    public Vertex getVertexWithHighestDegree() {
        Vertex vertexWithHighestDegree = new Vertex();
        Set<Vertex> vertices = this.getVertices();
        for (Vertex vertex : vertices) {
            vertexWithHighestDegree = vertex.getNeighbours().size() > vertexWithHighestDegree.getNeighbours().size() ? vertex : vertexWithHighestDegree;
        }
        return vertexWithHighestDegree;
    }

    public Edge getEdgeWithMaxColor() {
        List<Edge> edges = new ArrayList<>(this.getEdges());
        Edge edgeWithMaxColor = edges.get(0);
        for (Vertex vertex: this.getVertices()) {
            for (Edge edge: vertex.getEdges()) {
                edgeWithMaxColor = edge.getColor() > edgeWithMaxColor.getColor() ? edge : edgeWithMaxColor;
            }
        }
        return edgeWithMaxColor;
    }

    public int getMaxColors() {
        return maxColors;
    }

    public void setMaxColors(int maxColors) {
        this.maxColors = maxColors;
    }
}
