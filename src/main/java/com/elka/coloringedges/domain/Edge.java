package com.elka.coloringedges.domain;

public class Edge {

    private Long id;
    private String name;
    private Node sourceNode;
    private Node destinationNode;
    private Long color;

    public Edge(Long id, String name, Node sourceNode, Node destinationNode, Long color) {
        this.id = id;
        this.name = name;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(Node sourceNode) {
        this.sourceNode = sourceNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    public Long getColor() {
        return color;
    }

    public void setColor(Long color) {
        this.color = color;
    }
}
