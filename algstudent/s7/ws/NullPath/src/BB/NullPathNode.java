package BB;

import java.util.ArrayList;
import java.util.UUID;

public class NullPathNode extends Node {
	private int[][] weights;
	private int cost;
	private int currentIndex;
	private ArrayList<Integer> path;
	private boolean[] visited;

	public NullPathNode(int[][] weights) {
		this.weights = weights;
		this.path = new ArrayList<>();
		this.visited = new boolean[weights.length];
		this.path.add(0);
		this.currentIndex = 0;
		this.visited[0] = true;
		this.cost = 0;
		this.depth = 0;
		this.parentID = null;
		this.ID = UUID.randomUUID();

		calculateHeuristicValue();
	}

	private NullPathNode(NullPathNode parent, int nextNode) {
		this.weights = parent.weights;
		this.visited = parent.visited.clone();
		this.path = new ArrayList<Integer>(parent.path);
		this.path.add(nextNode);
		this.currentIndex = nextNode;
		this.visited[nextNode] = true;
		this.cost = parent.cost + weights[parent.currentIndex][nextNode];
		this.depth = parent.depth + 1;
		this.parentID = parent.getID();
		this.ID = UUID.randomUUID();

		calculateHeuristicValue();
	}

	@Override
	public void calculateHeuristicValue() {
		/*
		if (prune()) {
	        heuristicValue = NullPath.INFINITE;
	    } else {
	        int rem   = weights.length - path.size();
	        int base  = Math.abs(cost) + rem * NullPath.MINWEIGHT;
	        int boost = path.size();
	        heuristicValue = Math.max(0, base - boost);
	    }*/
		if (prune()) {
	        heuristicValue = NullPath.INFINITE;
	    } else {
	        heuristicValue = weights.length - path.size();
	    }
	}

	private boolean prune() {
	    int n        = weights.length;
	    int k        = n - path.size();               // steps still to go
	    int bestDrop = k * NullPath.MAXWEIGHT;        // maximum possible negative swing
	    /*System.out.print("[");
	    for (boolean b : visited) {
	    	String s = b ? "T," : "F,";
	    	System.out.print(s);
	    }
	    System.out.println("]");
	    System.out.println(cost + " " + bestDrop);*/
	    // 1) If even with the best possible future you end up above +TOLERANCE
	    if (cost - bestDrop > NullPath.TOLERANCE || cost + bestDrop < -NullPath.TOLERANCE) {
	    	//System.out.println("NO");
	        return true;
	    }
	    
	    // 2) You still need to end at node n–1; if you've already used up all steps but not there, prune.
	    if (path.size() == n && currentIndex != n - 1)
	        return true;

	    // 3) You still have nodes apart from node n–1; if you've already used up the end node, prune.
	    if (path.size() != n && currentIndex == n - 1)
	        return true;
	    
	    return false;
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> children = new ArrayList<Node>();
		for (int i = 0; i < weights.length; i++) {
			if (!visited[i]) {
				NullPathNode child = new NullPathNode(this, i);
				children.add(child);
			}
		}
		return children;
	}

	@Override
	public boolean isSolution() {
		if(path.size() == weights.length && currentIndex == weights.length - 1) {
			return Math.abs(cost) <= NullPath.TOLERANCE;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < path.size(); i++) {
			s.append("NODE " + path.get(i));
			if (i != path.size() - 1) {
				s.append(" --> ");
			}
		}
		s.append("\nCost: " + cost + "\n");
		return s.toString();
	}
	
	@Override
	public int initialValuePruneLimit() {
	    // Quick nearest-neighbor tour to get a finite upper bound
	    boolean[] seen = new boolean[weights.length];
	    int tourCost = 0, curr = 0;
	    seen[0] = true;
	    for (int step = 1; step < weights.length; step++) {
	        int bestW = Integer.MAX_VALUE, bestJ = -1;
	        for (int j = 0; j < weights.length; j++) {
	            if (!seen[j] && j != curr) {
	                int w = Math.abs(weights[curr][j]);
	                if (w < bestW) {
	                    bestW = w;
	                    bestJ = j;
	                }
	            }
	        }
	        tourCost += (bestW == Integer.MAX_VALUE ? NullPath.MAXWEIGHT : bestW);
	        curr = bestJ;
	        seen[curr] = true;
	    }
	    // finally jump to last node if needed
	    tourCost += Math.abs(weights[curr][weights.length - 1]);
	    return tourCost;
	}
}