package com.elka.coloringedges.domain;

public class Edge {

    private Vertex sourceVertex, destinationVertex;
    private int color;

    public Edge(Vertex v1, Vertex v2) {
        this.sourceVertex = v1;
        this.destinationVertex = v2;
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
