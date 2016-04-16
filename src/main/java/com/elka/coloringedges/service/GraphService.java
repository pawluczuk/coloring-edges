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
        Graph graph = new Graph();
        while (input.hasNextLine()) {
            long sourceVertex = input.nextLong();
            long destinationVertex = input.nextLong();
            graph.addEdge(sourceVertex, destinationVertex);
        }
        input.close();
        return graph;
    }
}
