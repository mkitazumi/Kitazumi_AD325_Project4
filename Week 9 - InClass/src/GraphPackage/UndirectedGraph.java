package GraphPackage;

import ADTPackage.StackInterface;

public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>
{
    /**
     * Constructor for Undirected Graph
     */
    public UndirectedGraph()
    {
        super();
    } // end default constructor

    /**
     * Adds an edge to the graph that connects two vertices with weight
     * @param begin  An object that labels the origin vertex of the edge.
     * @param end    An object, distinct from begin, that labels the end
    vertex of the edge.
     * @param edgeWeight  The real value of the edge's weight.
     * @return True if edge was created, false if not
     */
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        return super.addEdge(begin, end, edgeWeight) &&
                super.addEdge(end, begin, edgeWeight);
        // Assertion: edge count is twice its correct value due to calling addEdge twice
    } // end addEdge

    /**
     * Adds an edge to the graph that connects two vertices without weight
     * @param begin  An object that labels the origin vertex of the edge.
     * @param end    An object, distinct from begin, that labels the end vertex of the edge.
     * @return true if edge was created, false if not
     */
    public boolean addEdge(T begin, T end)
    {
        return this.addEdge(begin, end, 0);
    } // end addEdge

    /**
     * gets the number of edges in the grapg
     * @return number of edges
     */
    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges() / 2;
    } // end getNumberOfEdges

    /**
     * Gets the Topological Order of vertecies in the graph
     * @return error because we cannot use this sort in an undirected graph
     */
    public StackInterface<T> getTopologicalOrder()
    {
        throw new UnsupportedOperationException("Topological sort is illegal in an undirected graph.");
    } // end getTopologicalOrder
} // end UndirectedGraph

