package algorithm.graph;

import java.util.*;

public class Node {
    private Object value;
    private Map<Node, Integer> adjacentNodeDistances;
    private List<Node> shortestPath;
    private Integer distance;

    public Node(Object value) {
        this.value = value;
        this.adjacentNodeDistances = new HashMap<>();
        this.shortestPath = new LinkedList<>();
        this.distance = Integer.MAX_VALUE;
    }

    public void addAdjacentNode(Node node, Integer distanceToNode) {
        adjacentNodeDistances.putIfAbsent(node, distanceToNode);
    }

    public Map<Node, Integer> getAdjacentNodeDistances() {
        return adjacentNodeDistances;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
