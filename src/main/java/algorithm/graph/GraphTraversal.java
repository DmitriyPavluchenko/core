package algorithm.graph;

import java.util.*;

public class GraphTraversal {

    public static Set<Node> depthTraversal(Node startNode) {
        Set<Node> visited = new LinkedHashSet<>();
        Stack<Node> waitToVisit = new Stack<>();
        waitToVisit.push(startNode);
        while (!waitToVisit.isEmpty()) {
            Node current = waitToVisit.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Node adjacentNode : current.getAdjacentNodeDistances().keySet()) {
                    waitToVisit.push(adjacentNode);
                }
            }
        }
        return visited;
    }

    public static Set<Node> breadthTraversal(Node startNode) {
        Set<Node> visited = new LinkedHashSet<>();
        Queue<Node> waitToVisit = new LinkedList<>();
        waitToVisit.add(startNode);
        visited.add(startNode);
        while (!waitToVisit.isEmpty()) {
            Node current = waitToVisit.poll();
            for (Node adjacentNode : current.getAdjacentNodeDistances().keySet()) {
                if (!visited.contains(adjacentNode)){
                    waitToVisit.add(adjacentNode);
                    visited.add(adjacentNode);
                }
            }
        }

        return visited;
    }
}
