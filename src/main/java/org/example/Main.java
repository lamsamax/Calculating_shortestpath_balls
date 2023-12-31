package org.example;

import java.io.File;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        Places.populateShortCodesFromFile("places.txt");
        Constraints.loadConstraintsFromFile("constraints.txt");

        Graph graph1 = createGraph("simple.txt");
        Graph graph2 = createGraph("five_places.txt");
        Graph graph3 = createGraph("ten_places.txt");
        Graph graph4 = createGraph("all_places_a.txt");
        Graph graph5 = createGraph("all_places_b.txt");
        Graph graph6 = createGraph("complex.txt");

     /* graph1.printGraphToFile("graph1.txt");
        graph2.printGraphToFile("graph2.txt");
        graph3.printGraphToFile("graph3.txt");
        graph4.printGraphToFile("graph4.txt");
        graph5.printGraphToFile("graph5.txt");
        graph6.printGraphToFile("graph6.txt");*/

        ShortestPathSolver solver1 = new ShortestPathSolver(graph1.getGraph());
        ShortestPathSolver solver2 = new ShortestPathSolver(graph2.getGraph());
        ShortestPathSolver solver3 = new ShortestPathSolver(graph3.getGraph());
        ShortestPathSolver solver4 = new ShortestPathSolver(graph4.getGraph());
        ShortestPathSolver solver5 = new ShortestPathSolver(graph5.getGraph());
        ShortestPathSolver solver6 = new ShortestPathSolver(graph6.getGraph());

        solver1.printShortestPathsFromAllSourcesToFile("Graph1_shortest.txt");
        solver2.printShortestPathsFromAllSourcesToFile("Graph2_shortest.txt");
        solver3.printShortestPathsFromAllSourcesToFile("Graph3_shortest.txt");
        solver4.printShortestPathsFromAllSourcesToFile("Graph4_shortest.txt");
        solver5.printShortestPathsFromAllSourcesToFile("Graph5_shortest.txt");
        solver6.printShortestPathsFromAllSourcesToFile("Graph6_shortest.txt");


    }
    private static Graph createGraph(String filePath) {
        Graph graph = new Graph();
        File input = new File(filePath);
        try {
            Scanner s = new Scanner(input);

            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                String[] lineParts = currentLine.split(" ");

                String startShortcode = lineParts[0].trim();
                String endShortCode = lineParts[1].trim();
                Integer weight = Integer.parseInt(lineParts[2].trim());

                graph.addEdge(startShortcode, endShortCode, weight);
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

}