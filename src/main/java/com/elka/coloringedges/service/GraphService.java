package com.elka.coloringedges.service;

import com.elka.coloringedges.domain.Graph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class GraphService {

    Logger log = Logger.getLogger(this.getClass().getName());

    void colourEdges(Graph graph) {

    }

    public Graph buildGraph(MultipartFile file) throws IOException {
        Scanner input = new Scanner(file.getInputStream());
        return buildGraph(input);
    }

    public Graph buildGraph(String fileContents) throws IOException {
        Scanner input = new Scanner(fileContents);
        return buildGraph(input);
    }

    public Graph buildGraph(Scanner scannerInput){
        Graph graph = new Graph();
        boolean hasDefinedMaxColors = false;

        try {
            while (scannerInput.hasNextLine()) {
                String foundInLine = scannerInput.findInLine("Colors: (\\d+)");
                if (!hasDefinedMaxColors && foundInLine != null){
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
            if(!hasDefinedMaxColors) graph.setMaxColors(graph.getVertexWithHighestDegree().getDegree() - 1);
            scannerInput.close();
        }
        return graph;
    }
}
