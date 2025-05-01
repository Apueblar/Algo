package BB;

import java.util.ArrayList;

/**
 * Main class to solve problems using the Branch and Bound technique
 * We need to extend it for any specific problem
 */
public class BranchAndBound {
	protected Heap ds; //Nodes to be explored (not used nodes)
	protected Node bestNode; //To save the final node of the best solution
	protected Node rootNode; //Initial node
	protected int pruneLimit; //To prune nodes above this value

	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBound() {
		ds = new Heap(); //We create an instance of the Heap class to save the nodes
	}

	/**
	 * Manages all the process, from the beginning to the end
	 * @param rootNode Starting state of the problem
	 */
	public void branchAndBound(Node rootNode) { 
		this.rootNode = rootNode;
		ds.insert(rootNode); //First node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	
			
			ArrayList<Node> children = node.expand(); 
			
			for (Node child : children) {
				if (child.isSolution()) {
					int heuristicValue = child.getHeuristicValue();
					if (heuristicValue < pruneLimit) {
						pruneLimit = heuristicValue;
						bestNode = child;
						return;
					}
				}
				else if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
				}
			}
		} //while
	}

	/**
	 * Gets the root node
	 * @return The root node
	 */
    public Node getRootNode() {
    	return rootNode;
    }
    
	/**
	 * Gets the best node
	 * @return The best node
	 */
    public Node getBestNode() {
    	return bestNode;
    }

    /**
     * Prints the solution from the root node to the best node
     */
    public void printSolutionTrace() {
    	if (bestNode == null) {
			System.out.print("Original:");
			System.out.print(rootNode.toString());
    		System.out.print("THERE IS NO SOLUTION");
    	} 
    	else {
    		//Extract the path of the used nodes from bestNode to the rootNode
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode);

            for (int i = 0; i < result.size();  i++) {
    			if (i == 0) 
    				System.out.print("Original:");
    			else 
    				System.out.print("Step " + i + ":");
				System.out.print(result.get(result.size()-i-1).toString());
    	    }
            System.out.println("\nSolution with " + bestNode.getDepth() + " step(s).");
    	}
    }
}
