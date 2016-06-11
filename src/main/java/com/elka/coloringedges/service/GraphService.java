package com.elka.coloringedges.service;

import com.elka.coloringedges.domain.Edge;
import com.elka.coloringedges.domain.Graph;
import com.elka.coloringedges.domain.Vertex;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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
        int deltaG = graph.getDeltaGraph();
        boolean bipartite = graph.isBipartite();
        int k = bipartite ? deltaG : deltaG + 1;
        for (Edge edge : graph.getEdges()) {
            if (edge.getColor() == -1) {
                Integer i = 1;
                Set<Integer> colorsSet = edge.getSourceVertex().getUsedColors();
                colorsSet.addAll(edge.getDestinationVertex().getUsedColors());
                while (colorsSet.contains(i) && i <= k) {
                    i++;
                }
                if (i > k && bipartite) {
                    edge.setColor(recolor(edge.getSourceVertex(), edge.getDestinationVertex()));
                } else {
                    edge.setColor(i);
                }
            }
        }
        return graph;
    }

    private Integer recolor(Vertex source, Vertex target) {

        Map<Object, Integer> switchColors = new HashMap<>();

        for (Integer i : source.getUsedColors()) {
            if(!target.getUsedColors().contains(i)) {
                switchColors.put("source", i);
                break;
            }
        }
        for (Integer i : target.getUsedColors()) {
            if(!source.getUsedColors().contains(i)) {
                switchColors.put("target", i);
                break;
            }
        }

        switchColors.put(switchColors.get("source"), switchColors.get("target"));
        switchColors.put(switchColors.get("target"), switchColors.get("source"));

        Set<Edge> edges = new HashSet<>();

        String[] foo = new String[2];
        foo[0] = "source";
        foo[1] = "target";

        int j = 0;
        while(true) {
            foo: {
                for (Edge edge : source.getEdges()) {
                    if (Objects.equals(edge.getColor(), switchColors.get(foo[j % 2]))) {
                        if (!edges.contains(edge)) {
                            edges.add(edge);
                            j++;
                            source = edge.getDestinationVertex().equals(source) ? edge.getSourceVertex() : edge.getDestinationVertex();
                            break foo;
                        }
                    }
                }
                break;
            }
        }

        for (Edge edge : edges) {
            edge.setColor(switchColors.get(edge.getColor()));
        }
        return switchColors.get("source");
    }
}