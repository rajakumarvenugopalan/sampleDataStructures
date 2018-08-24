package dataStructures;

import dataStructures.exceptions.DSStandardException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

    private Map<T, Vertice> vertices = new HashMap<>();
    private final boolean isBiDirectionalGraph;

    public Graph() {
        this (false);
    }

    public Graph(boolean isBiDirectionalGraph){
        this.isBiDirectionalGraph = isBiDirectionalGraph;
    }

    public void add(T value) throws DSStandardException {
        if(value == null) {
            throw new DSStandardException("I never like you NULL ");
        }
        vertices.putIfAbsent(value, new Vertice(value));
    }

    public void remove(T value) throws DSStandardException{
        if(value == null) {
            throw new DSStandardException("I never like you NULL ");
        }
        vertices.remove(value);
    }

    public Vertice find(T value) {
        return vertices.get(value);
    }

    public void addEdge(T source, T destination) {
        Vertice sourceNode = find(source);
        Vertice destinationNode = find(destination);

        if(sourceNode != null && destinationNode != null) {
            sourceNode.getEdges().add(new Edge(destinationNode));
            if(isBiDirectionalGraph) {
                destinationNode.getEdges().add(new Edge(sourceNode));
            }
        }
    }

    class Vertice {
        private T value;
        private Set<Edge> edges = new HashSet<>();
        private boolean isVisited = false;

        private Vertice(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Set<Edge> getEdges() {
            return edges;
        }

        public void setEdges(Set<Edge> edges) {
            this.edges = edges;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        public boolean equals(Object o) {
            if(o instanceof Graph.Vertice) {
                return this.getValue().equals(((Graph.Vertice)o).getValue());
            }
            return false;
        }

        public String toString() {
            return this.getValue().toString();
        }
    }

    class Edge {
        private Vertice toNode;
        private int distance;

        private Edge(Vertice toNode) {
            this(toNode, 1);
        }

        private Edge(Vertice toNode, int distance) {
            this.toNode = toNode;
            this.distance = distance;
        }

        public Vertice getToNode() {
            return toNode;
        }

        public void setToNode(Vertice toNode) {
            this.toNode = toNode;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String toString() {
            return " --> " + toNode.toString();
        }
    }
}
