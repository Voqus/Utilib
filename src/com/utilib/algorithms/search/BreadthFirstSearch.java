package com.utilib.algorithms.search;

import com.utilib.data_structures.Graph;
import com.utilib.data_structures.Queue;
import com.utilib.data_structures.trees.Node;

public class BreadthFirstSearch<K extends Comparable<K>, V extends Comparable<V>>
{
	private Graph<K, V> _graph;
	
	public BreadthFirstSearch(Graph<K, V> graph)
	{
		_graph = graph;
	}
	
	/**
	 * Performs breadth-first search on the graph provided.
	 */
	public void search()
	{
		// If graph has no vertices return
		if (_graph.size() == 0)
			return;
			
		// Store vertices positions to the queue
		// The queue used is the one written in data_structures package.
		Queue<Integer, Integer> queue = new Queue<Integer, Integer>(true);
		queue.insert(0, 0);
		
		// vertex position
		int vPos1, vPos2 = 0;
		
		// Set the first vertex as visited
		Node<K, V>[] vList = _graph.getVertexList();
		vList[0].setVisited(true);
		System.out.println(vList[0]);
		
		// While queue is not empty
		while (!queue.isEmpty())
		{
			vPos1 = queue.remove();
			
			// Visit adjacent vertices
			while (_graph.getAdjacentUnvisitedVertex(vPos1, vPos2) != -1)
			{
				vPos2 = _graph.getAdjacentUnvisitedVertex(vPos1, vPos2);
				vList[vPos2].setVisited(true);
				System.out.println(vList[vPos2]);
				queue.insert(vPos2, vPos2);
			}
		}
	}
	
	public static void main(String[] args)
	{
		// Create graph.
		Graph<String, Integer> graph = new Graph<String, Integer>(11);
		
		// Set the vertices.
		graph.setVertex(0, new Node<String, Integer>("0", 0));
		graph.setVertex(1, new Node<String, Integer>("1", 1));
		graph.setVertex(2, new Node<String, Integer>("2", 2));
		graph.setVertex(3, new Node<String, Integer>("3", 3));
		graph.setVertex(4, new Node<String, Integer>("4", 4));
		graph.setVertex(5, new Node<String, Integer>("5", 5));
		graph.setVertex(6, new Node<String, Integer>("6", 6));
		graph.setVertex(7, new Node<String, Integer>("7", 7));
		graph.setVertex(8, new Node<String, Integer>("8", 8));
		graph.setVertex(9, new Node<String, Integer>("9", 9));
		graph.setVertex(10, new Node<String, Integer>("10", 10));
		
		// Setting edges.
		graph.addBidirectionalEdge(0, 1);
		graph.addBidirectionalEdge(0, 4);
		graph.addBidirectionalEdge(0, 5);
		graph.addBidirectionalEdge(0, 9);
		graph.addBidirectionalEdge(1, 2);
		graph.addBidirectionalEdge(1, 3);
		graph.addBidirectionalEdge(2, 3);
		graph.addBidirectionalEdge(2, 10);
		graph.addBidirectionalEdge(3, 7);
		graph.addBidirectionalEdge(3, 10);
		graph.addBidirectionalEdge(4, 5);
		graph.addBidirectionalEdge(4, 7);
		graph.addBidirectionalEdge(5, 6);
		graph.addBidirectionalEdge(6, 7);
		graph.addBidirectionalEdge(6, 8);
		graph.addBidirectionalEdge(7, 8);
		
		// Print graph.
		graph.print();
		
		// Perform breadth-first search on the graph created above.
		BreadthFirstSearch<String, Integer> bfs = new BreadthFirstSearch<>(graph);
		System.out.println("\n---- BFS ----");
		bfs.search();
	}
}
