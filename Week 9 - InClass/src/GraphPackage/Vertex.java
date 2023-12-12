package GraphPackage;

import ADTPackage.LinkedListWithIterator;
import ADTPackage.ListWithIteratorInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Vertex<T> implements VertexInterface<T>
{
    /**
     * Private data for this class
     */
    private T label;
    private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
    private boolean visited;                          // True if visited
    private VertexInterface<T> previousVertex;        // On path to this vertex
    private double cost;                              // Of path to this vertex

    /**
     * Contructor for vertex
     * @param vertexLabel label for the vertex
     */
    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    /**
     * gets the label of the vertex
     * @return label of vertex
     */

    // Ex 8 from here to ....
    public T getLabel()
    {
        return label;
    } // end getLabel

    /**
     * checks if a vertex has a predecessor
     * @return true or false if vertex has predecessor or not
     */
    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    /**
     * sets a predecessor for current vertex
     * @param predecessor  The vertex previous to this one along a path.
     */
    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor

    /**
     * gets the predecessor of the current vertex
     * @return predecessor
     */
    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    /**
     * marks vertex as visited
     */
    public void visit()
    {
        visited = true;
    } // end visit

    /**
     * marks vertex as unvisited
     */
    public void unvisit()
    {
        visited = false;
    } // end unvisit

    /**
     * checks if the vertex has been visited or not
     * @return Boolean true or false depending on if the vertex has or has not been visited
     */
    public boolean isVisited()
    {
        return visited;
    } // end isVisited

    /**
     * gets the cost of a path to vertex
     * @return cost of path
     */
    public double getCost()
    {
        return cost;
    } // end getCost

    /**
     * sets the cost of a path to this vertex
     * @param newCost  The cost of the path.
     */
    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost

    /**
     * convertes vertex to a string
     * @return string representing the data of the vertex
     */
    public String toString()
    {
        return label.toString();
    } // end toString

    /**
     * Private class for the weightIterator for paths between vertices
     */
    private class WeightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;

        /**
         * Constructor for the Iterator
         */
        private WeightIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        /**
         * checks if Iterator has next
         * @return Boolean true or false if the iterator has next or not
         */
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /**
         * gets the next thing in the iterator
         * @return the next thing in the iterator
         */
        public Double next()
        {
            Double edgeWeight = 0.0;
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();

            return edgeWeight;
        } // end next

        /**
         * Removes a path from vertices
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end WeightIterator

    /**
     * gets the weight Iterator
     * @return the weight Iterator
     */
    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
    } // end getWeightIterator
// . . . to here Ex 8

    /**
     * Method that will connect vertices with edges/paths with desored weight
     *
     * @param endVertex   A vertex in the graph that ends the edge.
     * @param edgeWeight  A real-valued edge weight, if any.
     * @return True if the vertices have been connected, false if not
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;

        if (!this.equals(endVertex))
        {  // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    } // end connect

    /**
     * Method that connects vertices without a desired weight for edge/path
     * @param endVertex   A vertex in the graph that ends the edge.
     * @return true if vertices have been connected, false if not
     */
    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect

    /**
     * gets an Iterator that goes through the vertices neighbors
     * @return Iterator for the Neighbors
     */
    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } // end getNeighborIterator

    /**
     * Checks if a vertex has neighbor(s)
     * @return true if it has any neighbors, false if not
     */
    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    /**
     * gets any neighbors this vertex has that are UNVISITED
     * @return vertex or vertices that have NOT been visited
     */
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while

        return result;
    } // end getUnvisitedNeighbor

    /**
     * checks if another profile is the same as specified profile
     * @param other specified profile
     * @return True fi they are the same, false if not
     */
    public boolean equals(Object other)
    {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    /**
     * Private class that has the workings of Iterator for Neighbors
     */
    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        private Iterator<Edge> edges;

        /**
         * Constructor for the Neighbor Iterator
         */
        private NeighborIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        /**
         * checks if the Iterator has anything next
         * @return True if yes, false if no
         */
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /**
         * gets the neighbor that is next in the Iterator (if it has one)
         * @return neighbor that is next
         */
        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;

            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next

        /**
         * Removes what item the Iterator is on
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end NeighborIterator

    /**
     * Class that holds workings for Edges
     */
    protected class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;

        /**
         * Creates an edeg between vertices
         * @param endVertex vertex to be connected to
         * @param edgeWeight weight of edge
         */
        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        /**
         * Creates an edge between vertices that does not have a weight
         * @param endVertex vertex to be connected to
         */
        protected Edge(VertexInterface<T> endVertex)
        {
            vertex = endVertex;
            weight = 0;
        } // end constructor

        /**
         * gets the vertex at the end of the edge/path
         * @return vertex at the end
         */
        protected VertexInterface<T> getEndVertex()
        {
            return vertex;
        } // end getEndVertex

        /**
         * gets the weight of the edge/path if it has one
         * @return weight of edge/path
         */
        protected double getWeight()
        {
            return weight;
        } // end getWeight
    } // end Edge

    /**
     * Displays vetex and any neighbors to that vertex
     */
    public void display() // For testing
    {
        System.out.print(label + " " );
        Iterator<VertexInterface<T>> i = getNeighborIterator();
        Iterator<Double> w = getWeightIterator();

        while (i.hasNext())
        {
            Vertex<T> v = (Vertex<T>)i.next();
            System.out.print(v + " " + w.next() + " ");
        } // end while

        System.out.println();
    } // end display
} // end Vertex