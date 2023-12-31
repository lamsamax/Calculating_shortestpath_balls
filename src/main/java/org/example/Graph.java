package org.example;

import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class Graph {
    private final Map<String, Map<String, Integer>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(String start, String end, int weight) {
        graph.computeIfAbsent(start, k -> new HashMap<>()).put(end, weight);
    }

    public void printGraphToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
                String start = entry.getKey();
                Map<String, Integer> edges = entry.getValue();
                for (Map.Entry<String, Integer> edge : edges.entrySet()) {
                    String end = edge.getKey();
                    int weight = edge.getValue();
                    String line = start + " -> " + end + ": " + weight + " seconds";
                    writer.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Map<String, Integer>> copyGraph() {
        Map<String, Map<String, Integer>> copy = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            String start = entry.getKey();
            Map<String, Integer> edges = entry.getValue();

            // Create a new map for the edges
            Map<String, Integer> edgesCopy = new HashMap<>(edges);

            // Put the new edges map into the copy
            copy.put(start, edgesCopy);
        }

        return copy;
    }


    public Map<String, Map<String, Integer>> getGraph() {
        return new HashMap<>(graph);
    }
}
