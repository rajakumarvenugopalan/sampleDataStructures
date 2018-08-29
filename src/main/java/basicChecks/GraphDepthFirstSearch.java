package basicChecks;

import dataStructures.Graph;
import exceptions.DSStandardException;

public class GraphDepthFirstSearch<T> {

    /**
     *
     * Traverse through the Graph in Depth First Search and return the elements.
     * Main use of Depth First Search in Graph is to check all the dependent nodes
     * before processing the Node (e.g Mark and Sweep algorithm in GC, Dependency calculation in Maven).
     *
     * Assumption: The Graph is DAG (Directed Acyclic Graph).
     *
     * */

    public static void main(String... args) throws DSStandardException {
        GraphDepthFirstSearch<Integer> gdfs = new GraphDepthFirstSearch<Integer>();
        Graph<Integer> graph = new Graph<>(false);

        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);
        graph.add(5);
        graph.add(6);
        graph.add(7);
        graph.add(8);
        graph.add(9);
        graph.add(10);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(3, 2);
        graph.addEdge(4, 2);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 8);
        graph.addEdge(6, 9);
        graph.addEdge(9, 10);

        for(Integer temp: graph.depthFirstSearch()) {
            System.out.println(temp);
        }
    }
}
