package com.elka.coloringedges.service;

import com.elka.coloringedges.domain.Edge;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.domain.Vertex;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
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

    private Graph buildGraph(Scanner scannerInput) {
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
        for (Vertex vertex : graph.getVertices()) {
            for (Edge edge : vertex.getEdges()) {
                if (edge.getColor() == -1) {
                    Integer i = 1;
                    Set<Integer> colorsSet = edge.getSourceVertex().getUsedColors();
                    colorsSet.addAll(edge.getDestinationVertex().getUsedColors());
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