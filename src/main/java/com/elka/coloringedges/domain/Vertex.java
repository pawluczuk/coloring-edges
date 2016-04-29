package com.elka.coloringedges.domain;

import java.util.*;

public class Vertex {

    private Long id;
    private List<Vertex> neighbours = new ArrayList<>();
    private Set<Edge> edges = new HashSet<>();

    public Vertex() {
        this.id = new Random().nextLong();
    }

    public Vertex(Long vertexId) {
        this.id = vertexId;
    }

    public Vertex(Long vertexId, Vertex parent) {
        this.id = vertexId;
        this.neighbours.add(parent);
        this.edges.add(new Edge(this, parent));
    }

    public Long getId() {
        return this.id;
    }

    public List<Vertex> getNeighbours() {
        return this.neighbours;
    }

    public void addNeighbour(Vertex vertex) {
        this.neighbours.add(vertex);
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public Set<Edge> getEdges() {
        return this.edges;
    }

    public int getDegree() {
        return this.neighbours.size();
    }

    public Set<Integer> getUsedColors() {
        Set<Integer> colorSet = new HashSet<>();
        for (Edge edge : this.getEdges()) {
            colorSet.add(edge.getColor());
        }
        return colorSet;
    }
}
