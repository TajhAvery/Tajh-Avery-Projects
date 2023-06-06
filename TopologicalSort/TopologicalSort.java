//TODO: Implement the required methods and add JavaDoc for them.
//Remember: Do NOT add any additional instance or class variables (local variables are ok)
//and do NOT alter any provided methods or change any method signatures!

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

import java.awt.Color;

import javax.swing.JPanel;

import java.util.Collection;
import java.util.NoSuchElementException;

import java.util.LinkedList;

/**
 *  Simulation of topological sorting algorithm.
 *  
 */
class TopologicalSort implements ThreeTenAlg {
	/**
	 *  The graph the algorithm will run on.
	 */
	Graph<GraphNode, GraphEdge> graph;
	
	/**
	 *  The priority queue of nodes for the algorithm.
	 */
	WeissPriorityQueue<GraphNode> pqueue;
	
	/**
	 *  The sorted list of nodes for the algorithm.
	 */
	LinkedList<GraphNode> queue;
	
	/**
	 *  Whether or not the algorithm has been started.
	 */
	private boolean started = false;

	/**
	 * The max rank that has been assigned in the current sorting.
	 */
	private int maxRank;
	
	/**
	 *  The color when a node has "no color".
	 */
	public static final Color COLOR_NONE_NODE = Color.WHITE;
	
	/**
	 *  The color when an edge has "no color".
	 */
	public static final Color COLOR_NONE_EDGE = Color.BLACK;
		
	/**
	 *  The color when a node is inactive.
	 */
	public static final Color COLOR_INACTIVE_NODE = Color.LIGHT_GRAY;

	/**
	 *  The color when an edge is inactive.
	 */
	public static final Color COLOR_INACTIVE_EDGE = Color.LIGHT_GRAY;
	
	/**
	 *  The color when a node is highlighted.
	 */
	public static final Color COLOR_HIGHLIGHT = new Color(255,204,51);
	
	/**
	 *  The color when a node is in warning.
	 */
	public static final Color COLOR_WARNING = new Color(255,51,51);

			
	/**
	 *  {@inheritDoc}
	 */
	public EdgeType graphEdgeType() {
		return EdgeType.DIRECTED;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void reset(Graph<GraphNode, GraphEdge> graph) {
		this.graph = graph;
		started = false;
		queue = null;
		pqueue = null;		
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public boolean isStarted() {
		return started;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void start() {
		started = true;
		
		//create an empty list
		queue = new LinkedList<>();
		
		//create an empty priority queue
		pqueue = new WeissPriorityQueue<>();
		
		//no nodes sorted yet
		maxRank = -1;
			
		for(GraphNode v : graph.getVertices()) {
			
			//clear rank;
			v.setRank(-1);
			
			//Set the cost of each node to be its degree
			v.setCost(graph.inDegree(v));
			
			//Set each node to be active
			//This enables the display of cost for the node
			v.setActive();
		
			//add node into priority queue
			pqueue.add(v);
		}
		
		//highlight the node with best priority 
		highlightNext();
			
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void finish() {
	
		// Sorting completed. Set all edges back to "no color".
		for (GraphEdge e: graph.getEdges()){
			e.setColor(COLOR_NONE_EDGE);
		}

		//Set all sorted nodes back to "no special color".
		for(GraphNode v : graph.getVertices()) {			
			if (v.color.equals(COLOR_INACTIVE_NODE))
				v.setColor(COLOR_NONE_NODE);
		}
				
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void cleanUpLastStep() {
		// Unused. Required by the interface.		
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public boolean setupNextStep() {
	
		// no more nodes, done with simulation. 
		if (pqueue.size() == 0){
			return false;
		}
				
		//Return true to indicate more steps to continue.
		return true;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void doNextStep() {
	
		//find and process next node
		GraphNode minNode = selectNext();
				
		//update successor info as needed
		updateSuccessorCost(minNode);
		
		//highlight next node with best priority 
		highlightNext();

	
	}
	
	//----------------------------------------------------
	// TODO: Implement the methods below to complete the sorting algorithm.
	// - DO NOT change the signature of any required public method;
	// - Feel free to define additional method but they must be private.
	//
	// YOUR CODE HERE
	//----------------------------------------------------
	/**
     * Gets the next node in the priority queue that has 0 incoming edges and changes the rank depending on the 
     * sorting order.
     * @return the graph node with the best priority.
     */
	public GraphNode selectNext()
    {
	
        // 1. Remove the node with the best priority from the priority queue;
        // 
        // Note: the node removed should be the one with the lowest cost 
        // (i.e min number of active incoming edges).  If there is a tie in cost,
        // the one with the lowest ID should be selected.  
        // Hint: if your priority queue has been implemented correctly, this 
        // should be straightforward.
        //
        // 2. Check the cost of this node. 
        //    - if the cost / number of active incoming edges is zero: 
        //         1) add it to the end of the sorted list (queue), 
        //         2) set its rank to indicate the sorted order,  
        //         3) set it to be inactive and change its color to be COLOR_INACTIVE_NODE.
        //     - if the cost / number of active incoming edges is not zero, 
        //       it means this node cannot be topologically sorted.  
        //       Just change its color to be COLOR__WARNING.
        //
        // 3. Return the min node.  If priority queue is empty, return null.

        //GraphNode for the next element.
        GraphNode next = null;

        //Checks to see if the priority queue is empty.
        if(pqueue.size() > 0)
        {
            //Gets the element with the best priority
            next = pqueue.remove();

            //Checks the amount of predecessors the next node has

            //If the number of predecessors is 0.
            if(next.getCost() == 0)
            {
                //Changes the rank to indicate its sorted order.
                next.setRank(queue.size());
                //Sets the color to inactive.
                next.setColor(COLOR_INACTIVE_NODE);
                //Adds the element into the queue.
                queue.add(next);
            }
            else
                //Sets the nodes color to warning since the number of incoming edges is not 0.   
                next.setColor(COLOR_WARNING);
        }
        //Returns the min node or null if the priority queue is empty.
        return next;
            
	}

    /**
     * If the given node has a cost of 0 the method will update the nodes successors to 
     * have one less incoming edge since the minNode has already been sorted.
     * 
     * @param minNode the node to update successors.
     */
    public void updateSuccessorCost(GraphNode minNode)
    {
        // If minNode has cost of 0:
        //  - Update the cost for all active neighbor nodes 
        //  - Set the edge connecting minNode and each active neighbor to OLOR_INACTIVE_EDGE
        // Otherwise no change needed.
        // Note that the cost of a node is equal to the number of its incoming edges.

        //Checks if the minnode has a cost of 0.
        if(minNode.getCost() == 0)
        {
            //Gets a linked list of all the minNode's outcoming edges.
            LinkedList<GraphEdge> list = (LinkedList<GraphEdge>)graph.getOutEdges(minNode);

            //Goes untill the list is empty
            while(list.size() > 0)
            {
                //Gets the next out edge from minNode.
                GraphEdge outEdge = list.removeFirst();

                //Checks that the edge is active.
                if(outEdge.getColor() != COLOR_INACTIVE_EDGE)
                {
                    //Gets the destination of the given edge.
                    GraphNode destination = graph.getDest(outEdge);

                    //Decreases the cost of the node.
                    destination.setCost(destination.getCost() - 1);
                    //Updates the destination node in the priority queue since its cost changed.
                    pqueue.update(destination);
                }
                //Marks the edge as inactive.
                outEdge.setColor(COLOR_INACTIVE_EDGE);

            }
        }
    }	
	
    /**
     * Higlights the node that will be sorted next.
     */
    public void highlightNext()
    {

        // Find the current min node in the priority queue
        // and change the color of the node to be COLOR_HIGHLIGHT.
        // Note: do not dequeue the node.

        if(pqueue.size() > 0)
        {
            //Gets the min node of the priority queue.
            GraphNode min = pqueue.element();

            //Highlights the color of the min node.
            min.setColor(COLOR_HIGHLIGHT);
        }
    }
	
    /**
     * Simplifies the graph by removing edges that go from a node with a lower ID to one with a higher ID.
     * 
     * @return true if the graph was simplified, false otherwise.
     */
    public boolean simplify()
    {

        // No updating and return false if sorting has already started.
        // Otherwise, remove all edges that points from ID_s to ID_e s.t. ID_s<ID_e
        //  (i.e. edges that connect from a lower ID node to a higher ID node), 
        //   and return true.

        //Checks to see if the sorting has already started.
        if(!started)
        {
            //gets all edges in the graph.
            LinkedList<GraphEdge> allEdges = (LinkedList<GraphEdge>) graph.getEdges();

            //Goes until all edges have been removed from the list.
            while(allEdges.size() > 0)
            {
                GraphEdge edge = allEdges.removeFirst();

                //Checks if the source id is less than the destination id.
                if(graph.getSource(edge).getId() < graph.getDest(edge).getId())
                    graph.removeEdge(edge); //Removes the edge from the graph.
            }

            return true;

        }
        return false; //default return, change or delete as needed.
        
    }
	
    /**
     * Main method for testing.
     * @param args command line arguments.
     */
    public static void main(String[] args){
        ThreeTenGraph graph = new ThreeTenGraph();
        TopologicalSort topSort = new TopologicalSort();
        
        GraphNode[] nodes = {
            new GraphNode(0), 
            new GraphNode(1), 
            new GraphNode(2), 
            new GraphNode(3), 
            new GraphNode(4), 
            new GraphNode(5), 
            new GraphNode(6), 
            new GraphNode(7), 
            new GraphNode(8), 
            new GraphNode(9)
        };

        GraphEdge[] edges = {
            new GraphEdge(0), new GraphEdge(1), new GraphEdge(2), new GraphEdge(3), new GraphEdge(4), new GraphEdge(5),
            new GraphEdge(6), new GraphEdge(7), new GraphEdge(8), new GraphEdge(9), new GraphEdge(10), new GraphEdge(11),
            new GraphEdge(12), new GraphEdge(13), new GraphEdge(14), new GraphEdge(15), new GraphEdge(16), new GraphEdge(17),
            new GraphEdge(18), new GraphEdge(19), new GraphEdge(20), new GraphEdge(21), new GraphEdge(22), new GraphEdge(23),
            new GraphEdge(24), new GraphEdge(25), new GraphEdge(26), new GraphEdge(27), new GraphEdge(28), new GraphEdge(29),
            new GraphEdge(30), new GraphEdge(31), new GraphEdge(32), new GraphEdge(33), new GraphEdge(34), new GraphEdge(35),
            new GraphEdge(36), new GraphEdge(37), new GraphEdge(38), new GraphEdge(39), new GraphEdge(40), new GraphEdge(41),
            new GraphEdge(42), new GraphEdge(43), new GraphEdge(44), new GraphEdge(45), new GraphEdge(46), new GraphEdge(47),
            new GraphEdge(48), new GraphEdge(49), new GraphEdge(50), new GraphEdge(51), new GraphEdge(52), new GraphEdge(53),
            new GraphEdge(54), new GraphEdge(55), new GraphEdge(56), new GraphEdge(57), new GraphEdge(58), new GraphEdge(59),
            new GraphEdge(60), new GraphEdge(61), new GraphEdge(62), new GraphEdge(63), new GraphEdge(64), new GraphEdge(65)
        };
        
        
        
        graph.addVertex(nodes[0]);
        graph.addVertex(nodes[1]);

        graph.addEdge(edges[0], nodes[0], nodes[1]); //node 0 edge 1
        
        
        
        topSort.reset(graph);
        while (topSort.step()) {} //execution of all steps

        
        if (nodes[1].getRank()==1 && nodes[0].getRank()==0)
            System.out.println("pass one edge!");
        
        graph = new ThreeTenGraph();
        
        graph.addVertex(nodes[0]);
        graph.addVertex(nodes[1]);
        graph.addVertex(nodes[2]);
        graph.addVertex(nodes[3]);
        graph.addVertex(nodes[4]);
        graph.addVertex(nodes[5]);

        graph.addEdge(edges[0], nodes[5], nodes[2]); //5-->2
        graph.addEdge(edges[1], nodes[5], nodes[0]); //5-->0
        graph.addEdge(edges[2], nodes[4], nodes[0]); //4-->0
        graph.addEdge(edges[3], nodes[4], nodes[1]); //4-->1
        graph.addEdge(edges[4], nodes[2], nodes[3]); //2-->3
        graph.addEdge(edges[5], nodes[3], nodes[1]); //3-->1

        topSort.reset(graph);
        while (topSort.step()) {} //execution of all steps

        if (nodes[4].getRank()==0 && nodes[5].getRank()==1 && nodes[0].getRank()==2
            && nodes[2].getRank()==3 && nodes[3].getRank()==4 && nodes[1].getRank()==5)
            System.out.println("pass six edges!");
        
        //write your own testing code ...		
    }

}