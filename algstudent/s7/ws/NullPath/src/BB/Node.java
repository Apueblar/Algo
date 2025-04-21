package BB;

import java.util.*;

public class Node implements Comparable<Node> {
    private List<Integer> path; // Nodes visited
    private int cost;           // Heuristic cost
    private int currentNode;
    private int[][] weights;
    private int end;

    public Node(int currentNode, List<Integer> path, int cost, int[][] weights, int end) {
        this.currentNode = currentNode;
        this.path = new ArrayList<>(path);
        this.cost = cost;
        this.weights = weights;
        this.end = end;
    }

    public boolean isSolution() {
        return currentNode == end && path.size() == weights.length;
    }

    public int getHeuristicValue() {
        return cost;
    }

    public ArrayList<Node> expand() {
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            if (!path.contains(i) && weights[currentNode][i] != 0) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(i);
                int newCost = cost + weights[currentNode][i];
                Node child = new Node(i, newPath, newCost, weights, end);
                children.add(child);
            }
        }
        return children;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return "Node[path=" + path + ", cost=" + cost + "]";
    }
    
    public List<Integer> getPath() {
        return new ArrayList<>(path);
    }

    public int getCost() {
        return cost;
    }

}
