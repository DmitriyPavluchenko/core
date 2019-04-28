package algorithm.graph;

import java.util.*;

public class DijkstraAlgorithm {

    public static void findShortestPathToNode(Node startNode) {
        Set<Node> settled = new HashSet<>();
        Queue<Node> unSettled = new LinkedList<>();

        startNode.setDistance(0);
        unSettled.add(startNode);

        while (!unSettled.isEmpty()) {
            Node current = unSettled.poll();
            for (Map.Entry<Node, Integer> nodeDistanceEntry : current.getAdjacentNodeDistances().entrySet()) {
                Node adjacentNode = nodeDistanceEntry.getKey();
                Integer adjacentEdgeDistance = nodeDistanceEntry.getValue();

                if (!settled.contains(adjacentNode)) {
                    if (current.getDistance() + adjacentEdgeDistance < adjacentNode.getDistance()) {
                        adjacentNode.setDistance(current.getDistance() + adjacentEdgeDistance);
                        List<Node> newShortestPath = new LinkedList<>(current.getShortestPath());
                        newShortestPath.add(current);
                        adjacentNode.setShortestPath(newShortestPath);
                    }
                    unSettled.add(adjacentNode);
                }

                settled.add(current);
            }
        }
    }

}
