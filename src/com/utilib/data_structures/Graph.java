package com.utilib.data_structures;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import com.utilib.data_structures.trees.Node;

public class Graph<K extends Comparable<K>, V extends Comparable<V>>
{
	private AdjacencyMatrix<Boolean> adjMatrix;
	private Node<K, V> vertexList[];
	private int numVertices = 0;
	
	@SuppressWarnings("unchecked")
	public Graph(final int num)
	{
		vertexList = (Node<K, V>[]) Array.newInstance(Node.class, num);
		adjMatrix = new AdjacencyMatrix<Boolean>(Boolean.class, num, false);
		numVertices = num;
	}
	
	/**
	 * Sets a vertex on the vertex list.
	 * 
	 * @param index
	 * @param node
	 */
	public void setVertex(final int index, final Node<K, V> node)
	{
		if(index >= numVertices)
			throw new NoSuchElementException("You have exceeded the number of vertices.");
		
		vertexList[index] = node;
	}
	
	/**
	 * Removes a vertex of the vertex list.
	 * 
	 * @param index
	 */
	public void removeVertex(final int index)
	{
		setVertex(index, null);
	}
	
	/**
	 * Gets the vertex of the vertex list.
	 * 
	 * @param index
	 * @return Node
	 */
	public Node<K,V> getVertex(final int index)
	{
		return (vertexList[index]);
	}
	
	/**
	 * Adds a unidirectional(one-way) edge from {@code start} to {@code end}.
	 * 
	 * @param start
	 * @param end
	 */
	public void addUnidirectionalEdge(final int start, final int end)
	{
		adjMatrix.setValue(start, end, true);
	}
	
	/**
	 * Removes a unidirectional(one-way) edge from {@code start} to {@code end}.
	 * 
	 * @param start
	 * @param end
	 */
	public void removeUnidirectionalEdge(final int start, final int end)
	{
		adjMatrix.setValue(start, end, false);
	}
	
	/**
	 * Adds a bidirectional(both-ways) edge from {@code start} to {@code end}.
	 * 
	 * @param start
	 * @param end
	 */
	public void addBidirectionalEdge(final int start, final int end)
	{
		adjMatrix.setValue(start, end, true);
		adjMatrix.setValue(end, start, true);
	}
	
	/**
	 * Removes a bidirectional(both-ways) edge from {@code start} to {@code end}.
	 * 
	 * @param start
	 * @param end
	 */
	public void removeBidirectionalEdge(final int start, final int end)
	{
		adjMatrix.setValue(start, end, false);
		adjMatrix.setValue(end, start, false);
	}
	
	/**
	 * Prints the vertex list and adjacency matrix.
	 */
	public void print()
	{
		System.out.println("---- Vertex List ----");
		for(int i = 0; i < numVertices; i ++)
			System.out.println("[ " + vertexList[i] + " ]");
		
		System.out.println("\n---- Adjacency Matrix ----");
		adjMatrix.printMatrix();
	}
	
	public static void main(String[] args)
	{
		Graph<Integer,Integer> graph = new Graph<Integer, Integer>(5);
		
		// Setting vertices
		graph.setVertex(0, new Node<Integer, Integer>(0, 0));
		graph.setVertex(1, new Node<Integer, Integer>(1, 1));
		graph.setVertex(2, new Node<Integer, Integer>(2, 2));
		graph.setVertex(3, new Node<Integer, Integer>(3, 3));
		graph.setVertex(4, new Node<Integer, Integer>(4, 4));
		
		// Settings edges
		graph.addBidirectionalEdge(0, 1);
		graph.addUnidirectionalEdge(1, 2);
		graph.addUnidirectionalEdge(2, 3);
		graph.addUnidirectionalEdge(3, 4);
		
		// Print the graph
		graph.print();
	}
	
}
