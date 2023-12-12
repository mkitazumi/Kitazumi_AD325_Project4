package GraphPackage;

import ADTPackage.*;

import java.util.Iterator;

public class DirectedGraph<T> implements GraphInterface<T>
{
    public DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    /**
     * Constructor for a Directed Graph
     */
    public DirectedGraph()
    {
        vertices = new UnsortedLinkedDictionary<>();
        edgeCount = 0;
    } // end default constructor

    /**
     * Adds a vertex to this graoh
     * @param vertexLabel  An object that labels the new vertex and is
    distinct from the labels of current vertices.
     * @return true if vertex was added, false if not
     */
    public boolean addVertex(T vertexLabel)
    {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null; // Was addition to dictionary successful?
    } // end addVertex

    /**
     * Adds an Edge between two vertices in this graph with weight
     * @param begin  An object that labels the origin vertex of the edge.
     * @param end    An object, distinct from begin, that labels the end vertex of the edge.
     * @param edgeWeight  The real value of the edge's weight.
     * @return true if edge was added, false if not
     */
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ( (beginVertex != null) && (endVertex != null) )
            result = beginVertex.connect(endVertex, edgeWeight);
        if (result)
            edgeCount++;
        return result;
    } // end addEdge

    /**
     * Adds an Edge between two vertices in this graph without weight
     * @param begin  An object that labels the origin vertex of the edge.
     * @param end    An object, distinct from begin, that labels the end vertex of the edge.
     * @return true if edge was added, false if not
     */
    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    } // end addEdge

    /**
     * checks if a path/edge exists between two given vertices
     * @param begin  An object that labels the origin vertex of the edge.
     * @param end    An object that labels the end vertex of the edge.
     * @return true if edge/path exists, false if not
     */
    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ( (beginVertex != null) && (endVertex != null) )
        {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    found = true;
            } // end while
        } // end if

        return found;
    } // end hasEdge

    /**
     * checks if the graph is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty()
    {
        return vertices.isEmpty();
    } // end isEmpty

    /**
     * clears the graph
     */
    public void clear()
    {
        vertices.clear();
        edgeCount = 0;
    } // end clear

    /**
     * gets number of vertices in the graph
     * @return number of vertices in graph
     */
    public int getNumberOfVertices()
    {
        return vertices.getSize();
    } // end getNumberOfVertices

    /**
     * Gets number of edges in the graph
     * @return number of edges in graph
     */
    public int getNumberOfEdges()
    {
        return edgeCount;
    } // end getNumberOfEdges

    /**
     * resets all vertices in graph (unvisit all, cost of any paths are set to 0, and all predecessors are now null)
     */
    protected void resetVertices()
    {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } // end while
    } // end resetVertices

    /**
     * gets an order of vertices in a BreadthFirst order
     * @param origin  An object that labels the origin vertex of the traversal.
     * @return Breadth First order of vertices in this graph
     */
    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<>();               // Queue of vertex labels
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>(); // Queue of Vertex objects

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();

        traversalOrder.enqueue(origin);    // Enqueue vertex label
        vertexQueue.enqueue(originVertex); // Enqueue vertex

        while (!vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

            while (neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
            } // end while
        } // end while

        return traversalOrder;
    } // end getBreadthFirstTraversal

    /**
     * Gets the order of vertices in this graph by a DepthFirstTraversal
     * @param origin  An object that labels the origin vertex of the traversal.
     * @return DepthFirstSearch of the vertices in this graph
     */
    // Exercise 10, Chapter 29
    public QueueInterface<T> getDepthFirstTraversal(T origin)
    {
        // Assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // Enqueue vertex label
        vertexStack.push(originVertex); // Enqueue vertex

        while (!vertexStack.isEmpty())
        {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

            if (nextNeighbor != null)
            {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            }
            else // All neighbors are visited
                vertexStack.pop();
        } // end while

        return traversalOrder;
    } // end getDepthFirstTraversal

    /**
     * Gets the order of vertices in this graph in a topological order
     * @return vertices in topological order
     */
    public StackInterface<T> getTopologicalOrder()
    {
        resetVertices();

        StackInterface<T> vertexStack = new LinkedStack<>();
        int numberOfVertices = getNumberOfVertices();
        for (int counter = 1; counter <= numberOfVertices; counter++)
        {
            VertexInterface<T> nextVertex = findTerminal();
            nextVertex.visit();
            vertexStack.push(nextVertex.getLabel());
        } // end for

        return vertexStack;
    } // end getTopologicalOrder

    /**
     * Gets the shortest path between two given vertices
     * @param begin  An object that labels the path's origin vertex.
     * @param end    An object that labels the path's destination vertex.
     * @param path   A stack of labels that is empty initially;
    at the completion of the method, this stack contains
    the labels of the vertices along the shortest path;
    the label of the origin vertex is at the top, and
    the label of the destination vertex is at the bottom
     * @return Length of the Shortest path
     */
    public int getShortestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();

        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex

        vertexQueue.enqueue(originVertex);
        while (!done && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                } // end if

                if (nextNeighbor.equals(endVertex))
                    done = true;
            } // end while
        } // end while

        // Traversal ends; construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathLength;
    } // end getShortestPath

    // Exercise 15, Chapter 29
    /** Precondition: path is an empty stack (NOT null) */
    /**
     * Gets the cheapest (lowest weighted) path between teo given vertices
     * @param begin  An object that labels the path's origin vertex.
     * @param end    An object that labels the path's destination vertex.
     * @param path   A stack of labels that is empty initially;
    at the completion of the method, this stack contains
    the labels of the vertices along the cheapest path;
    the label of the origin vertex is at the top, and
    the label of the destination vertex is at the bottom
     * @return the cost of the cheapest path
     */
    public double getCheapestPath(T begin, T end, StackInterface<T> path) // STUDENT EXERCISE
    {
        resetVertices();
        boolean done = false;

        // Use EntryPQ instead of Vertex because multiple entries contain
        // the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
        PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        priorityQueue.add(new EntryPQ(originVertex, 0, null));

        while (!done && !priorityQueue.isEmpty())
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();

            if (!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());

                if (frontVertex.equals(endVertex))
                    done = true;
                else
                {
                    Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
                    Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
                    while (neighbors.hasNext())
                    {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        Double weightOfEdgeToNeighbor = edgeWeights.next();

                        if (!nextNeighbor.isVisited())
                        {
                            double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
                        } // end if
                    } // end while
                } // end if
            } // end if
        } // end while

        // Traversal ends, construct cheapest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathCost;
    } // end getCheapestPath

    /**
     *
     * @return
     */
    protected VertexInterface<T> findTerminal()
    {
        boolean found = false;
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

        while (!found && vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();

            // If nextVertex is unvisited AND has only visited neighbors)
            if (!nextVertex.isVisited())
            {
                if (nextVertex.getUnvisitedNeighbor() == null )
                {
                    found = true;
                    result = nextVertex;
                } // end if
            } // end if
        } // end while

        return result;
    } // end findTerminal


    // Used for testing

    /**
     * Displays all the edges in the graph
     */
    public void displayEdges()
    {
        System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
        System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            ((Vertex<T>)(vertexIterator.next())).display();
        } // end while
    } // end displayEdges


    private class EntryPQ implements Comparable<EntryPQ>
    {
        private VertexInterface<T> vertex;
        private VertexInterface<T> previousVertex;
        private double cost; // cost to nextVertex

        /**
         * Constructor for EntryPQ
         * @param vertex vertex you are on
         * @param cost cost of path connected to vertex
         * @param previousVertex vertex before the current one
         */
        private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex)
        {
            this.vertex = vertex;
            this.previousVertex = previousVertex;
            this.cost = cost;
        } // end constructor

        /**
         * Gets vertex
         * @return vertex
         */
        public VertexInterface<T> getVertex()
        {
            return vertex;
        } // end getVertex

        /**
         * gets predecessor
         * @return predecessor (previous Vertex)
         */
        public VertexInterface<T> getPredecessor()
        {
            return previousVertex;
        } // end getPredecessor

        /**
         * Gets cost of path
         * @return cost of path
         */
        public double getCost()
        {
            return cost;
        } // end getCost

        /**
         * Compares Entries
         * @param otherEntry the object to be compared.
         * @return Integer resulting in comparison
         */
        public int compareTo(EntryPQ otherEntry)
        {
            // Using opposite of reality since our priority queue uses a maxHeap;
            // could revise using a minheap
            return (int)Math.signum(otherEntry.cost - cost);
        } // end compareTo

        /**
         * Method to put vertex data into a string
         * @return String of vertex data
         */
        public String toString()
        {
            return vertex.toString() + " " + cost;
        } // end toString
    } // end EntryPQ
} // end DirectedGraph