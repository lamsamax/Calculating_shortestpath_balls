package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ShortestPathSolver {

    private final Map<String, Map<String, Integer>> graph;

    public ShortestPathSolver(Map<String, Map<String, Integer>> graph) {
        this.graph = graph;
    }

    public void printShortestPathsFromAllSourcesToFile(String outputFilePath) {
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            for (String sourceNode : graph.keySet()) {
                writer.write("Shortest paths from source " + sourceNode + ":" + System.lineSeparator());
                writeShortestPathsToFile(writer, sourceNode);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeShortestPathsToFile(FileWriter writer, String source) throws IOException {
        for (String destination : graph.keySet()) {
            if (!source.equals(destination)) {
                writeShortestPathToFile(writer, source, destination);
            }
        }
    }

    private void writeShortestPathToFile(FileWriter writer, String source, String destination) throws IOException {
        List<String> appliedConstraints = applyConstraints(Constraints.getAllConstraints());

        // The applied constraint doesn't work, but this is how I was planning on implementing it
        // I didn't have time to work on it more
       /* for (String appliedConstraint : appliedConstraints) {
            writer.write("Applied Constraint: " + appliedConstraint + System.lineSeparator());
        }*/
        if (!doesShortCodeExist(source) || !doesShortCodeExist(destination)) {
            writer.write("Distance " + source + " -> " + destination + ": -1" + System.lineSeparator());
        } else {
            int shortestPathLength = findShortestPath(source, destination);
            writer.write("Distance " + source + " -> " + destination + ": " + (shortestPathLength >= 0 ? shortestPathLength : "No path") + System.lineSeparator());
        }
    }

    private int findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        distances.put(start, 0);
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            for (Map.Entry<String, Integer> neighbor : graph.getOrDefault(current, Collections.emptyMap()).entrySet()) {
                String neighborNode = neighbor.getKey();
                int newDistance = distances.get(current) + neighbor.getValue();

                // Check if there is a constraint disabling the edge
                boolean edgeDisabled = Constraints.getAllConstraints().stream()
                        .anyMatch(c -> c.getStartCode().equals(current)
                                && c.getEndCode().equals(neighborNode)
                                && Math.random() <= c.getProbability());

                if (!edgeDisabled && (!distances.containsKey(neighborNode) || newDistance < distances.get(neighborNode))) {
                    distances.put(neighborNode, newDistance);
                    priorityQueue.add(neighborNode);
                }
            }
        }

        return distances.getOrDefault(end, -1);
    }

    private boolean doesShortCodeExist(String shortCode) {
        return Places.doesShortCodeExist(shortCode);
    }

    public List<String> applyConstraints(List<Constraints> constraints) {
        double globalRandomValue = Math.random();

        List<String> appliedConstraints = new ArrayList<>();

        for (Constraints constraint : constraints) {
            String startShortcode = constraint.getStartCode();
            String endShortcode = constraint.getEndCode();

            if (doesShortCodeExist(startShortcode) && doesShortCodeExist(endShortcode)) {
                if (globalRandomValue >= constraint.getProbability()) {
                    appliedConstraints.add(startShortcode + " " + endShortcode + " " + constraint.getConstraintType() + " " + constraint.getProbability());
                }
            }
        }

        return appliedConstraints;
    }
}
