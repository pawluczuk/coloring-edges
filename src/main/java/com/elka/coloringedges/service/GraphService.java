package com.elka.coloringedges.service;

import com.elka.coloringedges.domain.Edge;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.domain.Vertex;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class GraphService {

    Logger log = Logger.getLogger(this.getClass().getName());

    public Graph buildGraph(MultipartFile file) throws IOException {
        Scanner input = new Scanner(file.getInputStream());
        return buildGraph(input);
    }

    public Graph buildGraph(String fileContents) throws IOException {
        Scanner input = new Scanner(fileContents);
        return buildGraph(input);
    }

    public Graph buildGraph(Scanner scannerInput) {
        Graph graph = new Graph();
        boolean hasDefinedMaxColors = false;

        try {
            while (scannerInput.hasNextLine()) {
                String foundInLine = scannerInput.findInLine("Colors: (\\d+)");
                if (!hasDefinedMaxColors && foundInLine != null) {
                    hasDefinedMaxColors = true;
                    int numberOfColors = Integer.parseInt(foundInLine.substring("Colors: ".length()));
                    graph.setMaxColors(numberOfColors);
                } else {
                    long sourceVertex = scannerInput.nextLong();
                    long destinationVertex = scannerInput.nextLong();
                    graph.addEdge(sourceVertex, destinationVertex);
                }
                scannerInput.nextLine();
            }
        } catch (Exception e) {
            log.info("Could not build graph, error parsing file: " + e);
        } finally {
            if (!hasDefinedMaxColors) graph.setMaxColors(graph.getVertexWithHighestDegree().getDegree() - 1);
            scannerInput.close();
        }
        return graph;
    }

    public Graph colorEdges(Graph graph) {

        Set<Vertex> vertices = graph.getVertices();
        for (Vertex vertex : vertices) {
            Set<Integer> colorsSet = vertex.getUsedColors();
            for (Vertex ver : vertex.getNeighbours()){
                colorsSet.addAll(ver.getUsedColors());
            }
            Integer i = 0;
            for (Edge edge : vertex.getEdges()) {
                if(edge.getColor() == -1) {
                    while (colorsSet.contains(i)) {
                        i++;
                    }
                    edge.setColor(i);
                    colorsSet.add(i);
                }
            }
        }

        return graph;
    }
}