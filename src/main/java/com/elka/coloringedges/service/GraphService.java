package com.elka.coloringedges.service;

import com.elka.coloringedges.domain.Edge;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.domain.Vertex;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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

        try {
            while (scannerInput.hasNextLine()) {
                try {
                    String foundInLine = scannerInput.findInLine("Colors: (\\d+)");
                    int numberOfColors = Integer.parseInt(foundInLine.substring("Colors: ".length()));
                    graph.setMaxColors(numberOfColors);
                } catch(NoSuchElementException | NullPointerException e) {
                    long sourceVertex = scannerInput.nextLong();
                    long destinationVertex = scannerInput.nextLong();
                    graph.addEdge(sourceVertex, destinationVertex);
                } finally {
                    scannerInput.nextLine();
                }
            }
        } catch (Exception e) {
            log.info("Could not build graph, error parsing file: " + e);
        } finally {
            if (graph.getMaxColors() == -1){
                int deltaG = graph.getDeltaGraph();
                graph.setMaxColors(graph.isBipartite() ? deltaG : deltaG + 1);
            }
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