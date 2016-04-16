package com.elka.coloringedges.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vertex {

    private Long id;
    private List<Vertex> neighbours = new ArrayList<>();

    public Vertex() {
        this.id = new Random().nextLong();
    }

    public Vertex(Long vertexId) {
        this.id = vertexId;
    }

    public Vertex(Long vertexId, Vertex parent) {
        this.id = vertexId;
        this.neighbours.add(parent);
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

    public int getDegree() {
        return this.neighbours.size();
    }
}
