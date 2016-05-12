package com.elka.coloringedges.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"sourceVertex", "destinationVertex"})
public class Edge {

    private Vertex sourceVertex, destinationVertex;
    private Integer color = -1;

    public Edge() {}

    public Edge(Vertex v1, Vertex v2) {
        this.color = -1;
        this.sourceVertex = v1;
        this.destinationVertex = v2;
    }

    public Edge(Vertex v1, Vertex v2, Integer color) {
        this.color = color;
        this.sourceVertex = v1;
        this.destinationVertex = v2;
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
