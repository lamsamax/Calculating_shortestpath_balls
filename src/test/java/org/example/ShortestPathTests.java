package org.example;

import junit.framework.TestCase;

public class ShortestPathTests extends TestCase {

    public void testValidPath() {
        Graph graph = createGraph("valid_path.txt");
        ShortestPathSolver solver = new ShortestPathSolver(graph.getGraph());
        solver.printShortestPathsFromAllSourcesToFile("Test1_Output.txt");
    }

    public void testInvalidPath() {
        Graph graph = createGraph("invalid_path.txt");
        ShortestPathSolver solver = new ShortestPathSolver(graph.getGraph());
        solver.printShortestPathsFromAllSourcesToFile("Test2_Output.txt");
    }

    public void testNoPath() {
        Graph graph = createGraph("no_path.txt");
        ShortestPathSolver solver = new ShortestPathSolver(graph.getGraph());
        solver.printShortestPathsFromAllSourcesToFile("Test3_Output.txt");
    }

    public void testAppliedConstraints() {
        Graph graph = createGraph("valid_path.txt");
        ShortestPathSolver solver = new ShortestPathSolver(graph.getGraph());

        Constraints constraint = new Constraints("A", "B", "foggy", 0.5);
        Constraints.loadConstraintsFromFile("constraints.txt");
        solver.applyConstraints(Constraints.getAllConstraints());

        solver.printShortestPathsFromAllSourcesToFile("Test4_Output.txt");
    }
    public void testNoConstraints() {
        Graph graph = createGraph("valid_path.txt");
        ShortestPathSolver solver = new ShortestPathSolver(graph.getGraph());
        solver.printShortestPathsFromAllSourcesToFile("Test5_Output.txt");
    }

    private Graph createGraph(String filePath) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        return graph;
    }
}