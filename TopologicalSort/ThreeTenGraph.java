//TODO:
//  (1) Implement the graph!
//  (2) Update this code to meet the style and JavaDoc requirements.
//			Why? So that you get experience with the code for a graph!
//			Also, this happens a lot in industry (updating old code
//			to meet your new standards).

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Class that represents a directed graph.
 * 
 * @author Tajh J. Avery
 */
class ThreeTenGraph implements Graph<GraphNode,GraphEdge>, DirectedGraph<GraphNode,GraphEdge>
{	
    /**
     * Maximum number of nodes the graph can hold.
     */
    private static final int MAX_NUMBER_OF_NODES = 200;

    /**
     * An array of Graph nodes that represent the verticies.
     */
    private GraphNode[] vertexList = null;

    /**
     * An array of graph edges that represent the adjacency matrix.
     */
    private GraphEdge[][] matrix = null;

    /**
     * Constructor for the ThreeTenGraph, that creates a 
     * matrix of 200x200.
     */
    public ThreeTenGraph() 
    {
        //Creates a vertexList with the max size it can be.
        vertexList =  new GraphNode [MAX_NUMBER_OF_NODES];

        //Creates a matrix with the max number of nodes.
        matrix = new GraphEdge[MAX_NUMBER_OF_NODES][MAX_NUMBER_OF_NODES];
    }

    /**
     * Returns a view of all edges in this graph. In general, this
     * obeys the Collection contract, and therefore makes no guarantees 
     * about the ordering of the edges within the set.
     * 
     * @return a Collection view of all edges in this graph
     */
    public Collection<GraphEdge> getEdges() 
    {
        //O(n^2) where n is the max number of nodes in the graph

        //Hint: this method returns a Collection, look at the imports for
        //what collections you could return here.

        //Linked list to hold the graph edges.
        LinkedList<GraphEdge> list = new LinkedList<>();

        //Traverses the matrix rows.
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //Traverses the matrix columns.
            for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
            {
                //Checks to see if the current value in the matrix is null.
                if(matrix[row][col] != null)
                    list.addLast(matrix[row][col]); //Adds the value to the end of the list.
            }
        }
        return list;
    }

    /**
     * Returns a view of all vertices in this graph. In general, this
     * obeys the Collection contract, and therefore makes no guarantees 
     * about the ordering of the vertices within the set.
     * 
     * @return a Collection view of all vertices in this graph
     */
    public Collection<GraphNode> getVertices() 
    {
        //O(n) where n is the max number of nodes in the graph

        //Hint: this method returns a Collection, look at the imports for
        //what collections you could return here.

        //Linked list to hold the vertices.
        LinkedList<GraphNode> list = new LinkedList<>();

        //Traverses the vertex list.
        for(int index = 0; index < MAX_NUMBER_OF_NODES; index++)
        {
            //Checks to see if the value of the vertex array is null.
            if(vertexList[index] != null)
                list.addLast(vertexList[index]); //Adds to the end of the linked list.
        }
        return list;
    }

    /**
     * Returns the number of edges in this graph.
     * 
     * @return the number of edges in this graph
     */
    public int getEdgeCount() 
    {
        //O(n^2) where n is the max number of nodes in the graph

        //Variable to count the edges.
        int edgeCounter = 0;

        //Traverses the matrix rows
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //Traverses the matrix columns
            for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
            {
                //Checks if the current edge isnt null.
                if(matrix[row][col] != null)
                    edgeCounter++;
            }
        }
        return edgeCounter;
    }

    /**
     * Returns the number of vertices in this graph.
     * 
     * @return the number of vertices in this graph
     */
    public int getVertexCount() 
    {
        //O(n) where n is the max number of vertices in the graph

        //Variable to count the vertices.
        int vertexCounter = 0;

        //Traverses the vertex array.
        for(int index = 0; index < MAX_NUMBER_OF_NODES; index++)
        {
            //Checks if the current vertex in the list isn't null.
            if(vertexList[index] != null)
                vertexCounter++;
        }

        return vertexCounter; 
    }


    /**
     * Returns true if this graph's vertex collection contains vertex.
     * Equivalent to getVertices().contains(vertex).
     * @param vertex the vertex whose presence is being queried
     * @return true iff this graph contains a vertex vertex
     */
    public boolean containsVertex(GraphNode vertex) 
    {
        //O(1)

        //checks if the id is within range of the vertex list.
        if(vertex.getId() >= 0 && vertex.getId() <= 199)
        {
            //Checks if the given vertex matches the vertex in the vertex list. 
            if(vertexList[vertex.getId()].equals(vertex))
                return true;
            else
                return false;
        }
        else 
            return false;
    }


    /**
     * Returns a Collection view of the incoming edges incident to vertex
     * in this graph.
     * 
     * @param vertex the vertex whose incoming edges are to be returned
     * @return  a Collection view of the incoming edges incident to vertex in this graph.
     */
    public Collection<GraphEdge> getInEdges(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Gets the column of the matrix to traverse.
        int index = vertex.getId();

        //List to hold the incoming edges.
        LinkedList<GraphEdge> list = new LinkedList<>();

        //Traverses the rows of the matrix.
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //Checks if the current value is null.
            if(matrix[row][index] != null)
                list.addLast(matrix[row][index]); //Adds the edge to the list.
        }  
        return list; 
    }

    /**
     * Returns a Collection view of the outgoing edges incident to vertex
     * in this graph.
     * @param vertex    the vertex whose outgoing edges are to be returned
     * @return  a Collection view of the outgoing edges incident to vertex in this graph
     */
    public Collection<GraphEdge> getOutEdges(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Gets the row of the matrix to iterate.
        int index = vertex.getId();

        //List to hold the outgoing edges.
        LinkedList<GraphEdge> list = new LinkedList<>();

        //Traveres the columns of the matrix.
        for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
        {
            //Checks if the current value is null.
            if(matrix[index][col] != null)
                list.addLast(matrix[index][col]); //Adds the edge to the list.
        }

        return list;
    }

    /**
     * Returns the number of incoming edges incident to vertex.
     * Equivalent to getInEdges(vertex).size().
     * @param vertex    the vertex whose indegree is to be calculated
     * @return  the number of incoming edges incident to vertex
     */
    public int inDegree(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Calls the getInEdges to get a list of of the incoming edges of the vertex.
        LinkedList<GraphEdge> list = (LinkedList<GraphEdge>)getInEdges(vertex);
        
        //Returns the size of the incoming edge list.
        return list.size(); 
    }

    /**
     * Returns the number of outgoing edges incident to vertex.
     * Equivalent to getOutEdges(vertex).size().
     * @param vertex    the vertex whose outdegree is to be calculated
     * @return  the number of outgoing edges incident to vertex
     */
    public int outDegree(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Calls the getOutEdges to get a list of of the out going edges of the vertex.
        LinkedList<GraphEdge> list = (LinkedList<GraphEdge>)getOutEdges(vertex);
        
        //Returns the size of the incoming edge list.
        return list.size(); 
    }


    /**
     * Returns a Collection view of the predecessors of vertex 
     * in this graph.  A predecessor of vertex is defined as a vertex v 
     * which is connected to 
     * vertex by an edge e, where e is an outgoing edge of 
     * v and an incoming edge of vertex.
     * @param vertex    the vertex whose predecessors are to be returned
     * @return  a Collection view of the predecessors of vertex in this graph
     */
    public Collection<GraphNode> getPredecessors(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Gets the index of the given vertex.
        int index = vertex.getId();

        //List to hold the predecessor graph nodes.
        LinkedList<GraphNode> list = new LinkedList<>();

        //Traverses the matrix rows.
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //Checks if the current value is null.
            if(matrix[row][index] != null)
                list.addLast(vertexList[row]); //Adds the value of the node connected to the vertex by the current edge.
        }
        return list;
    }

    /**
     * Returns a Collection view of the successors of vertex 
     * in this graph.  A successor of vertex is defined as a vertex v 
     * which is connected to 
     * vertex by an edge e, where e is an incoming edge of 
     * v and an outgoing edge of vertex.
     * @param vertex    the vertex whose predecessors are to be returned
     * @return  a Collection view of the successors of vertex in this graph
     */
    public Collection<GraphNode> getSuccessors(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Gets the index of the given vertex.
        int index = vertex.getId();

        //List to hold the successors graph node.
        LinkedList<GraphNode> list = new LinkedList<>();

        //Traverses the matrix columns.
        for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
        {   
            //Checks if the current value is null.
            if(matrix[index][col] != null)
                list.addLast(vertexList[col]); //Adds the value of the node connected to the vertex given the current edge.
        }
        return list;
    }

    /**
     * Returns true if v1 is a predecessor of v2 in this graph.
     * Equivalent to v1.getPredecessors().contains(v2).
     * @param v1 the first vertex to be queried
     * @param v2 the second vertex to be queried
     * @return true if v1 is a predecessor of v2, and false otherwise.
     */
    public boolean isPredecessor(GraphNode v1, GraphNode v2) 
    {
        //O(1)

        //Checks if there is an edge between v1 -> v2.
        if(matrix[v1.getId()][v2.getId()] != null)
            return true;
        else 
            return false;
    }

    /**
     * Returns true if v1 is a successor of v2 in this graph.
     * Equivalent to v1.getSuccessors().contains(v2).
     * @param v1 the first vertex to be queried
     * @param v2 the second vertex to be queried
     * @return true if v1 is a successor of v2, and false otherwise.
     */
    public boolean isSuccessor(GraphNode v1, GraphNode v2) 
    {
        //O(1)

        //Checks if there is an edge between v2 -> v1.
        if(matrix[v2.getId()][v1.getId()] != null)
            return true;
        else 
            return false;
    }

    /**
     * Returns the collection of vertices which are connected to vertex
     * via any edges in this graph.
     * If vertex is connected to itself with a self-loop, then 
     * it will be included in the collection returned.
     * 
     * @param vertex the vertex whose neighbors are to be returned
     * @return  the collection of vertices which are connected to vertex, or null if vertex is not present
     */
    public Collection<GraphNode> getNeighbors(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //NOTE: there should be no duplicate nodes in the neighbors.

        //Gets the index of vertex.
        int index = vertex.getId();

        //LinkedList to hold all the neighbors.
        LinkedList<GraphNode> list = new LinkedList<>();

        //Traverses the matrix.
        for(int i = 0; i < MAX_NUMBER_OF_NODES; i++)
        {
            //Checks if the current column or edge has an edge.
            if(matrix[index][i] != null || matrix[i][index] != null)
                list.addLast(vertexList[i]); //Adds the value node to the list.
        }
        return list; 		

    }


    /**
     * Returns the number of vertices that are adjacent to vertex
     * (that is, the number of vertices that are incident to edges in vertex's
     * incident edge set).
     * 
     * <p>Equivalent to getNeighbors(vertex).size().
     * @param vertex the vertex whose neighbor count is to be returned
     * @return the number of neighboring vertices
     */
    public int getNeighborCount(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //NOTE: Not the same as degree() since there should be no duplicate neighbors.

        //Gets a list of the given vertex neighbors.
        LinkedList<GraphNode> list = (LinkedList<GraphNode>) getNeighbors(vertex);

        //Returns the size of the list.
        return list.size();

    }


    /**
     * If directed_edge is a directed edge in this graph, returns the source; otherwise returns null. 
     * The source of a directed edge d is defined to be the vertex for which d is an outgoing edge.
     * directed_edge is guaranteed to be a directed edge if its EdgeType is DIRECTED. 
     * @param directedEdge the edge to get the source.
     * @return  the source of directed_edge if it is a directed edge in this graph, or null otherwise
     */
    public GraphNode getSource(GraphEdge directedEdge) 
    {
        //O(n^2) where n is the max number of vertices in the graph

        //Node to hold the source of a directed edge.
        GraphNode source = null;

        //Traverses the matrix rows.
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //Breaks if the source has already been found.
            if(source != null)
                break;

            //Traverses the matrix columns.
            for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
            {
                //Breaks if the source has been found.
                if(source != null)
                    break;

                //Checks if the current value in the matrix is null and if it equals the given edge.
                if(matrix[row][col] != null && matrix[row][col].equals(directedEdge))
                    source = vertexList[row]; //Gets the node that has the given edge as the outgoing edge.
            }
        }
        return source;	
    }

    /**
     * If directed_edge is a directed edge in this graph, returns the destination; otherwise returns null. 
     * The destination of a directed edge d is defined to be the vertex incident to d for which d is an incoming edge.
     * directed_edge is guaranteed to be a directed edge if its EdgeType is DIRECTED. 
     * @param directedEdge the directed edge to gest destination.
     * @return  the destination of directed_edge if it is a directed edge in this graph, or null otherwise
     */
    public GraphNode getDest(GraphEdge directedEdge) 
    {
        //O(n^2) where n is the max number of vertices in the graph

        //Node to hold the destination of a directed edge.
        GraphNode dest = null;

        //Traverses the matrix rows.
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //Breaks if the destination has already been found.
            if(dest != null)
                break;
        
            //Traverses the matrix columns.
            for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
            {
                //Breaks if the destination has already been found.
                if(dest != null)
                    break;
                
                //Checks if the current value in the matrix is null and if it equals the given edge.
                if(matrix[row][col] != null && matrix[row][col].equals(directedEdge))
                    dest = vertexList[col]; //Gets the node that has the given edge as the incoming edge.
            }
        }
        return dest;   	
    }


    /**
     * Returns an edge that connects v1 to v2.
     * If this edge is not uniquely
     * defined (that is, if the graph contains more than one edge connecting 
     * v1 to v2), any of these edges 
     * may be returned.  findEdgeSet(v1, v2) may be 
     * used to return all such edges.
     * Returns null if either of the following is true:
     * <ul>
     * <li/>v1 is not connected to v2
     * <li/>either v1 or v2 are not present in this graph
     * </ul> 
     * <b>Note</b>: for purposes of this method, v1 is only considered to be connected to
     * v2 via a given <i>directed</i> edge e if
     * v1 == e.getSource() && v2 == e.getDest() evaluates to true.
     * (v1 and v2 are connected by an undirected edge u if 
     * u is incident to both v1 and v2.)
     * 
     * @param v1 the source node to get the edge.
     * @param v2 the destination node to find the edge.
     * @return  an edge that connects v1 to v2, or null if no such edge exists (or either vertex is not present)
     * @see Hypergraph#findEdgeSet(Object, Object) 
     */
    public GraphEdge findEdge(GraphNode v1, GraphNode v2) 
    {
        //O(1) 

        //Graph edge to hold the edge between v1 and v2
        GraphEdge edge = null;

        //Checks if v1 and v2 are in the matrix.
        if(containsVertex(v1) && containsVertex(v2))
            //gets the edge between v1 and v2
            edge = matrix[v1.getId()][v2.getId()];

        return edge;

    }

    /**
     * Returns true if vertex and edge are incident to each other.
     * Equivalent to getIncidentEdges(vertex).contains(edge) and to getIncidentVertices(edge).contains(vertex).
     * @param vertex the node to check if edge conects from or to.
     * @param edge the edge to check if it comes out or in to vertex.
     * @return true if vertex and edge are incident to each other
     */
    public boolean isIncident(GraphNode vertex, GraphEdge edge) 
    {
        //O(n) where n is the max number of vertices in the graph

        int index = vertex.getId();

        for(int i = 0; i < MAX_NUMBER_OF_NODES; i++)
        {
            //Checks if the given vertex is a source using edge.
            if(matrix[index][i] != null && matrix[index][i].equals(edge))
                return true;
            //Checks if the given vertex is a destination using edge.
            if(matrix[i][index] != null && matrix[i][index].equals(edge))
                return true;
        }

        return false;

    }



    /**
     * Adds edge e to this graph such that it connects vertex v1 to v2.
     * Equivalent to addEdge(e, new Pair<{@link GraphNode}>(v1, v2)).
     * If this graph does not contain v1, v2, 
     * or both, implementations may choose to either silently add 
     * the vertices to the graph or throw an IllegalArgumentException.
     * If this graph assigns edge types to its edges, the edge type of
     * e will be the default for this graph.
     * See Hypergraph.addEdge() for a listing of possible reasons
     * for failure.
     * @param e the edge to be added
     * @param v1 the first vertex to be connected
     * @param v2 the second vertex to be connected
     * @return true if the add is successful, false otherwise
     * @see Hypergraph#addEdge(Object, Collection)
     * @see #addEdge(Object, Object, Object, EdgeType)
     */
    public boolean addEdge(GraphEdge e, GraphNode v1, GraphNode v2) 
    {
        //O(n^2) where n is the max number of vertices in the graph

        //Variable to record if the edge was added.
        boolean added = false;

        //Variable to check if the edge is already in the matrix.
        boolean found = false;

        //Checks if the given graph nodes are in the vertex list.
        if(containsVertex(v1) && containsVertex(v2))
        {
            //Traverses the matrix rows
            for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
            {
                //Traverses the matrix columns
                for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
                {
                    //Checks if the egde isnt null then if the given edge e is already in the matrix.
                    if(matrix[row][col] != null && matrix[row][col].equals(e))
                        found = true;//Changes found to true since the edge was already found.
                }
            }

            //If the edge wasn't found
            if(!found)
            {
                //Checks if the given graph nodes have an edge already linked between them.
                if(matrix[v1.getId()][v2.getId()] == null)
                {
                    //Adds the edge e to the matrix.
                    matrix[v1.getId()][v2.getId()] = e;
                    //changes added to true since the edge was added.
                    added = true;
                }
            }
        }

        return added;

    }

    /**
     * Adds vertex to this graph.
     * Fails if vertex is null or already in the graph.
     * 
     * @param vertex    the vertex to add
     * @return true if the add is successful, and false otherwise
     * @throws IllegalArgumentException if vertex is null
     */
    public boolean addVertex(GraphNode vertex) 
    {
        //O(1) 
        //Variable to record if the vertex was added.
        boolean added = false;

        //Checks if the vertex is null.
        if(vertex != null)
        {
            //Checks if the vertex is already in the vertex list.
            if(vertexList[vertex.getId()] == null)
            {
                //Adds the vertex to the vertex list.
                vertexList[vertex.getId()] = vertex;
                //Changes added to true since the vertex was added.
                added = true;
            }
        }
        return added;
    }

    /**
     * Removes edge from this graph.
     * Fails if edge is null, or is otherwise not an element of this graph.
     * 
     * @param edge the edge to remove
     * @return true if the removal is successful, false otherwise
     */
    public boolean removeEdge(GraphEdge edge) 
    {
        //O(n^2) where n is the max number of vertices in the graph

        //Variable to record if the edge has been removed.
        boolean removed = false;

        //traverses the matrix rows
        for(int row = 0; row < MAX_NUMBER_OF_NODES; row++)
        {
            //traverses the matrix columns
            for(int col = 0; col < MAX_NUMBER_OF_NODES; col++)
            {
                //Checks if the current edge isnt null and if it equals the given edge.
                if(matrix[row][col] != null && matrix[row][col].equals(edge))
                {
                    //Removes the edge by setting it equal to null.
                    matrix[row][col] = null;
                    //Sets removed to true since the edge was found and removed.
                    removed = true;
                }
            }
        }

        return removed; 

    }

    /**
     * Removes vertex from this graph.
     * As a side effect, removes any edges e incident to vertex if the 
     * removal of vertex would cause e to be incident to an illegal
     * number of vertices.  (Thus, for example, incident hyperedges are not removed, but 
     * incident edges--which must be connected to a vertex at both endpoints--are removed.) 
     * 
     * <p>Fails under the following circumstances:
     * <ul>
     * <li/>vertex is not an element of this graph
     * <li/>vertex is null
     * </ul>
     * 
     * @param vertex the vertex to remove
     * @return true if the removal is successful, false otherwise
     */
    public boolean removeVertex(GraphNode vertex) 
    {
        //O(n) where n is the max number of vertices in the graph

        //Variable to record if the vertex was removed.
        boolean removed = false;

        //Checks if the given vertex is null and if it exist in the graph.
        if(vertex != null && containsVertex(vertex))
        {
            //Gets the index of the vertex
            int index = vertex.getId();

            //Removes the vertex from the vertex list.
            vertexList[index] = null;

            //Traverses the columns and rows of the matrix.
            for(int i = 0; i < MAX_NUMBER_OF_NODES; i++)
            {
                //Checks if the column has an edge 
                if(matrix[index][i] != null)
                    matrix[index][i] = null;//removes the edge if there is one
                //Checks if the row has an edge
                if(matrix[i][index] != null)
                    matrix[i][index] = null; //removes the edge if there is one.
            }
            //Changes removed to true since the vertex was removed.
            removed = true;
        }

        return removed;

    }



    /**
     *  Returns a string of the depth first traversal of the graph. 
     *  We may need to perform depth first traversal for multiple rounds until all nodes
     *  are visited.  Always pick the lowest ID vertex that has not been visited to start
     *  a round and all nodes reachable from that starting node should be traversed in 
     *  this round before we move on to the next round. 
     *  
     *  @return a string representation of the depth first traversal, or an empty string if the graph is empty
     */
    public String depthFirstTraversal() 
    {
        //O(n^2) where n is the max number of vertices in the graph

        // Feel free to define private helpers.
        // Recursion can be helpful.
        // Make sure to use StringBuilder instead of String concatenation.

        //String to represent the depth first traversal.
        StringBuilder string = new StringBuilder();

        //A linkedList of every successor in the graph starting from (and including) the first node
        LinkedList<GraphNode> allSuccessors = new LinkedList<>();

        //Array to keep track of which vertexs have been visted.
        boolean[] visted = new boolean[MAX_NUMBER_OF_NODES];

        //Traversers the vertex list 
        for(int vertex = 0; vertex < MAX_NUMBER_OF_NODES; vertex++)
        {
            //Checks if the current vertex isn't null and hasnt been visted.
            if(vertexList[vertex] != null && !visted[vertex])
            {
                //Makes the node visted.
                visted[vertex] = true;
                //Gets every the successor of the nodes successors
                allSuccessors.addAll(getAllSuccessors(vertexList[vertex], visted));   
            }
        }

        //Makes an iterator
        ListIterator<GraphNode> it = allSuccessors.listIterator();

        //Keeps going untill the end of the allSuccessors linked list.
        while(it.hasNext())
        {
            //Gets the next item from the iterator.
            GraphNode currNode = it.next();

            //Adds the node to the string.
            string.append(currNode.getId());

            //Checks if the iterator has another item next
            if(it.hasNext())
                string.append(" ");//Adds a space between elements.
        }
        return string.toString();
    }

    /**
     * Helper method to get every successor of a nodes successors.
     * 
     * @param node the node to get all successors.
     * @param visted a boolean array to keep track of which nodes have been visted.
     * @return a LinkedList of every succesor of a node's successors.
     */
    private LinkedList<GraphNode> getAllSuccessors(GraphNode node, boolean[] visted)
    {
        //A linkedList to hold all the successors.
        LinkedList<GraphNode> allSuccessors = new LinkedList<>();

        //Adds the given node to the linkedlist
        allSuccessors.add(node);

        //LinkedList to hold the nodes direct successors.
        LinkedList<GraphNode> successors = (LinkedList<GraphNode>) getSuccessors(node);

        //Goes through the successors linkedlist.
        for(GraphNode succesor: successors)
        {
            //Checks if the node has been visted.
            if(!visted[succesor.getId()])
            {
                //Changes the node to visted.
                visted[succesor.getId()] = true;
                //Adds the current nodes successors to the allSuccessors linkedlist.
                allSuccessors.addAll(getAllSuccessors(succesor, visted));
            }
        }
        return allSuccessors;
    }


        


    //********************************************************************************
    //   testing code goes here... edit this as much as you want!
    //********************************************************************************

    /**
     * Main method for testing.
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) 
    {
        //create a set of nodes and edges to test with
        GraphNode[] nodes = 
        {
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

        GraphEdge[] edges = 
        {
            new GraphEdge(0), 
            new GraphEdge(1), 
            new GraphEdge(2),
            new GraphEdge(3), 
            new GraphEdge(4), 
            new GraphEdge(5),
            new GraphEdge(6),
            new GraphEdge(7)
        };

        //constructs a graph
        ThreeTenGraph graph = new ThreeTenGraph();
        for(GraphNode n : nodes) 
        {
            graph.addVertex(n);
        }

        graph.addEdge(edges[0],nodes[0],nodes[1]);
        graph.addEdge(edges[1],nodes[1],nodes[2]);
        graph.addEdge(edges[2],nodes[3],nodes[6]);
        graph.addEdge(edges[3],nodes[6],nodes[7]);
        graph.addEdge(edges[4],nodes[8],nodes[9]);
        graph.addEdge(edges[5],nodes[9],nodes[0]);
        graph.addEdge(edges[6],nodes[2],nodes[7]);
        graph.addEdge(edges[7],nodes[1],nodes[8]);


        if(graph.getVertexCount() == 10 && graph.getEdgeCount() == 8) 
        {
            System.out.println("Yay 1");
        }

        if(graph.inDegree(nodes[0]) == 1 && graph.outDegree(nodes[1]) == 2) 
        {
            System.out.println("Yay 2");
        }

        //depth-first traversal
        if(graph.toString().trim().equals("0 1 2 7 8 9 3 6 4 5")) 
        {
            System.out.println("Yay 3");
        }
        //lots more testing here...

        //If your graph "looks funny" you probably want to check:
        //getVertexCount(), getVertices(), getInEdges(vertex),
        //and getIncidentVertices(incomingEdge) first. These are
        //used by the layout class.
    }

    //********************************************************************************
    //   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
    //   NOTE: you do need to fix JavaDoc issues if there is any in this section.
    //********************************************************************************



    /**
     * Returns the number of edges incident to vertex.  
     * Special cases of interest:
     * <ul>
     * <li/> Incident self-loops are counted once.
     * <li> If there is only one edge that connects this vertex to
     * each of its neighbors (and vice versa), then the value returned 
     * will also be equal to the number of neighbors that this vertex has
     * (that is, the output of getNeighborCount).
     * <li> If the graph is directed, then the value returned will be 
     * the sum of this vertex's indegree (the number of edges whose 
     * destination is this vertex) and its outdegree (the number
     * of edges whose source is this vertex), minus the number of
     * incident self-loops (to avoid double-counting).
     * </ul>
     * <p>Equivalent to getIncidentEdges(vertex).size().</p>
     * 
     * @param vertex the vertex whose degree is to be returned
     * @return the degree of this node
     * @see Hypergraph#getNeighborCount(Object)
     */
    public int degree(GraphNode vertex) 
    {
        return inDegree(vertex) + outDegree(vertex);
    }

    /**
     * Returns true if v1 and v2 share an incident edge.
     * Equivalent to getNeighbors(v1).contains(v2).
     * 
     * @param v1 the first vertex to test
     * @param v2 the second vertex to test
     * @return true if v1 and v2 share an incident edge
     */
    public boolean isNeighbor(GraphNode v1, GraphNode v2) 
    {
        return (findEdge(v1, v2) != null || findEdge(v2, v1)!=null);
    }

    /**
     * Returns the endpoints of edge as a Pair<{@link GraphNode}>.
     * @param edge the edge whose endpoints are to be returned
     * @return the endpoints (incident vertices) of edge
     */
    public Pair<GraphNode> getEndpoints(GraphEdge edge) 
    {

        if (edge==null) return null;

        GraphNode v1 = getSource(edge);
        if (v1==null)
            return null;

        GraphNode v2 = getDest(edge);
        if (v2==null)
            return null;

        return new Pair<>(v1, v2);
    }


    /**
     * Returns the collection of edges in this graph which are connected to vertex.
     * 
     * @param vertex the vertex whose incident edges are to be returned
     * @return  the collection of edges which are connected to vertex, or null if vertex is not present
     */
    public Collection<GraphEdge> getIncidentEdges(GraphNode vertex) 
    {
        LinkedList<GraphEdge> ret = new LinkedList<>();
        ret.addAll(getInEdges(vertex));
        ret.addAll(getOutEdges(vertex));
        return ret;
    }

    /**
     * Returns the collection of vertices in this graph which are connected to edge.
     * Note that for some graph types there are guarantees about the size of this collection
     * (i.e., some graphs contain edges that have exactly two endpoints, which may or may 
     * not be distinct).  Implementations for those graph types may provide alternate methods 
     * that provide more convenient access to the vertices.
     * 
     * @param edge the edge whose incident vertices are to be returned
     * @return  the collection of vertices which are connected to edge, or null if edge is not present
     */
    public Collection<GraphNode> getIncidentVertices(GraphEdge edge) 
    {
        Pair<GraphNode> p = getEndpoints(edge);
        LinkedList<GraphNode> ret = new LinkedList<>();
        ret.add(p.getFirst());
        ret.add(p.getSecond());
        return ret;
    }


    /**
     * Returns true if this graph's edge collection contains edge.
     * Equivalent to getEdges().contains(edge).
     * @param edge the edge whose presence is being queried
     * @return true iff this graph contains an edge edge
     */
    public boolean containsEdge(GraphEdge edge) 
    {
        return (getEndpoints(edge) != null);
    }

    /**
     * Returns the collection of edges in this graph which are of type edge_type.
     * @param edgeType the type of edges to be returned
     * @return the collection of edges which are of type edge_type, or null if the graph does not accept edges of this type
     * @see EdgeType
     */
    public Collection<GraphEdge> getEdges(EdgeType edgeType) 
    {
        if(edgeType == EdgeType.DIRECTED) 
        {
            return getEdges();
        }
        return null;
    }

    /**
     * Returns the number of edges of type edge_type in this graph.
     * @param edgeType the type of edge for which the count is to be returned
     * @return the number of edges of type edge_type in this graph
     */
    public int getEdgeCount(EdgeType edgeType) 
    {
        if(edgeType == EdgeType.DIRECTED) 
        {
            return getEdgeCount();
        }
        return 0;
    }

    /**
     * Returns the number of predecessors that vertex has in this graph.
     * Equivalent to vertex.getPredecessors().size().
     * @param vertex the vertex whose predecessor count is to be returned
     * @return  the number of predecessors that vertex has in this graph
     */
    public int getPredecessorCount(GraphNode vertex) 
    {
        return inDegree(vertex);
    }

    /**
     * Returns the number of successors that vertex has in this graph.
     * Equivalent to vertex.getSuccessors().size().
     * @param vertex the vertex whose successor count is to be returned
     * @return  the number of successors that vertex has in this graph
     */
    public int getSuccessorCount(GraphNode vertex) 
    {
        return outDegree(vertex);
    }

    /**
     * Returns the vertex at the other end of edge from vertex.
     * (That is, returns the vertex incident to edge which is not vertex.)
     * @param vertex the vertex to be queried
     * @param edge the edge to be queried
     * @return the vertex at the other end of edge from vertex
     */
    public GraphNode getOpposite(GraphNode vertex, GraphEdge edge) 
    {
        if (getSource(edge).equals(vertex))
        {
            return getDest(edge);
        }
        else if (getDest(edge).equals(vertex))
        {
            return getSource(edge);
        }
        else
            return null;			
    }

    /**
     * Returns all edges that connects v1 to v2.
     * If this edge is not uniquely
     * defined (that is, if the graph contains more than one edge connecting 
     * v1 to v2), any of these edges 
     * may be returned.  findEdgeSet(v1, v2) may be 
     * used to return all such edges.
     * Returns null if v1 is not connected to v2.
     * <br/>Returns an empty collection if either v1 or v2 are not present in this graph.
     *  
     * <p><b>Note</b>: for purposes of this method, v1 is only considered to be connected to
     * v2 via a given <i>directed</i> edge d if
     * v1 == d.getSource() && v2 == d.getDest() evaluates to true.
     * (v1 and v2 are connected by an undirected edge u if 
     * u is incident to both v1 and v2.)
     * 
     * @param v1 the source node to get the edges from.
     * @param v2 the destination node to get the edges from.
     * @return  a collection containing all edges that connect v1 to v2, or null if either vertex is not present
     * @see Hypergraph#findEdge(Object, Object) 
     */
    public Collection<GraphEdge> findEdgeSet(GraphNode v1, GraphNode v2) 
    {
        GraphEdge edge = findEdge(v1, v2);
        if(edge == null) 
        {
            return null;
        }

        LinkedList<GraphEdge> ret = new LinkedList<>();
        ret.add(edge);
        return ret;

    }

    /**
     * Returns true if vertex is the source of edge.
     * Equivalent to getSource(edge).equals(vertex).
     * @param vertex the vertex to be queried
     * @param edge the edge to be queried
     * @return true iff vertex is the source of edge
     */
    public boolean isSource(GraphNode vertex, GraphEdge edge) 
    {
        return getSource(edge).equals(vertex);
    }

    /**
     * Returns true if vertex is the destination of edge.
     * Equivalent to getDest(edge).equals(vertex).
     * @param vertex the vertex to be queried
     * @param edge the edge to be queried
     * @return true iff vertex is the destination of edge
     */
    public boolean isDest(GraphNode vertex, GraphEdge edge) 
    {
        return getDest(edge).equals(vertex);
    }

    /**
     * Adds edge e to this graph such that it connects 
     * vertex v1 to v2.
     * Equivalent to addEdge(e, new Pair<{@link GraphNode}>(v1, v2)).
     * If this graph does not contain v1, v2, 
     * or both, implementations may choose to either silently add 
     * the vertices to the graph or throw an IllegalArgumentException.
     * If edgeType is not legal for this graph, this method will
     * throw IllegalArgumentException.
     * See Hypergraph.addEdge() for a listing of possible reasons
     * for failure.
     * @param e the edge to be added
     * @param v1 the first vertex to be connected
     * @param v2 the second vertex to be connected
     * @param edgeType the type to be assigned to the edge
     * @return true if the add is successful, false otherwise
     * @see Hypergraph#addEdge(Object, Collection)
     * @see #addEdge(Object, Object, Object)
     */
    public boolean addEdge(GraphEdge e, GraphNode v1, GraphNode v2, EdgeType edgeType) 
    {
        //NOTE: Only directed edges allowed

        if(edgeType == EdgeType.UNDIRECTED) 
        {
            throw new IllegalArgumentException();
        }

        return addEdge(e, v1, v2);
    }

    /**
     * Adds edge to this graph.
     * Fails under the following circumstances:
     * <ul>
     * <li/>edge is already an element of the graph 
     * <li/>either edge or vertices is null
     * <li/>vertices has the wrong number of vertices for the graph type
     * <li/>vertices are already connected by another edge in this graph,
     * and this graph does not accept parallel edges
     * </ul>
     * 
     * @param edge the edge to add
     * @param vertices a collection of vertices for the edge to connect to.
     * @return true if the add is successful, and false otherwise
     * @throws IllegalArgumentException if edge or vertices is null, or if a different vertex set in this graph is already connected by edge, or if vertices are not a legal vertex set for edge 
     */
    @SuppressWarnings("unchecked")
    public boolean addEdge(GraphEdge edge, Collection<? extends GraphNode> vertices) 
    {
        if(edge == null || vertices == null || vertices.size() != 2) 
        {
            return false;
        }

        GraphNode[] vs = (GraphNode[])vertices.toArray();
        return addEdge(edge, vs[0], vs[1]);
    }

    /**
     * Adds edge to this graph with type edge_type.
     * Fails under the following circumstances:
     * <ul>
     * <li/>edge is already an element of the graph 
     * <li/>either edge or vertices is null
     * <li/>vertices has the wrong number of vertices for the graph type
     * <li/>vertices are already connected by another edge in this graph,
     * and this graph does not accept parallel edges
     * <li/>edge_type is not legal for this graph
     * </ul>
     * 
     * @param edge the edge to add.
     * @param vertices a collection of vertice for the edge to connect to.
     * @param edgeType the edgeType for the edge.
     * @return true if the add is successful, and false otherwise
     * @throws IllegalArgumentException if edge or vertices is null, or if a different vertex set in this graph is already connected by edge, or if vertices are not a legal vertex set for edge 
     */
    @SuppressWarnings("unchecked")
    public boolean addEdge(GraphEdge edge, Collection<? extends GraphNode> vertices, EdgeType edgeType) 
    {
        if(edge == null || vertices == null || vertices.size() != 2) 
        {
            return false;
        }

        GraphNode[] vs = (GraphNode[])vertices.toArray();
        return addEdge(edge, vs[0], vs[1], edgeType);
    }

    //********************************************************************************
    //   DO NOT EDIT ANYTHING BELOW THIS LINE EXCEPT FOR FIXING JAVADOC
    //********************************************************************************

    /**
     * Returns a {@code Factory} that creates an instance of this graph type.
     * @param <V> any type
     * @param <E> any type
     * @return a ThreeTenGraph
     */

    public static <V,E> Factory<Graph<GraphNode,GraphEdge>> getFactory() 
    { 
        return new Factory<Graph<GraphNode,GraphEdge>> () 
        {
            @SuppressWarnings("unchecked")
            public Graph<GraphNode,GraphEdge> create() 
            {
                return (Graph<GraphNode,GraphEdge>) new ThreeTenGraph();
            }
        };
    }



    /**
     * Returns the edge type of edge in this graph.
     * @param edge the edge to get the type.
     * @return the EdgeType of edge, or null if edge has no defined type
     */
    public EdgeType getEdgeType(GraphEdge edge) 
    {
        return EdgeType.DIRECTED;
    }

    /**
     * Returns the default edge type for this graph.
     * 
     * @return the default edge type for this graph
     */
    public EdgeType getDefaultEdgeType() 
    {
        return EdgeType.DIRECTED;
    }

    /**
     * Returns the number of vertices that are incident to edge.
     * For hyperedges, this can be any nonnegative integer; for edges this
     * must be 2 (or 1 if self-loops are permitted). 
     * 
     * <p>Equivalent to getIncidentVertices(edge).size().
     * @param edge the edge whose incident vertex count is to be returned
     * @return the number of vertices that are incident to edge.
     */
    public int getIncidentCount(GraphEdge edge) 
    {
        return 2;
    }

    /**
     *  {@inheritDoc}
     */
    public String toString() 
    {
        return depthFirstTraversal();
    }


}
