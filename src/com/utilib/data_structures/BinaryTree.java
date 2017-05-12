package com.utilib.data_structures;

import java.util.Comparator;

// BinaryTree class can take in duplicate keys on this example.
public class BinaryTree<K extends Comparable<K>, V extends Comparable<V>> implements Comparator<Node<K, V>>
{
	private Node<K, V> _root;
	
	public BinaryTree()
	{
		_root = null;
	}
	
	public BinaryTree(final K key, final V value)
	{
		_root = new Node<K, V>(key, value);
	}
	
	/**
	 * Inserts a key-value pair to binary tree recursively.
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(final K key, final V value)
	{
		Node<K, V> node = new Node<K, V>(key, value);
		_root = insert(_root, node);
	}
	
	/**
	 * Implementation for insertion method on binary tree. 
	 * 
	 * @param node
	 * @param nodeToInsert
	 * @return Node
	 */
	private Node<K, V> insert(Node<K, V> node, Node<K, V> nodeToInsert)
	{
		if (node == null)
			node = nodeToInsert;
		else
		{
			if (node._right == null)
				node._right = insert(node._right, nodeToInsert);
			else
				node._left = insert(node._left, nodeToInsert);
		}
		
		return node;
	}
	
	/**
	 * Finds a node based on the key provided on a binary tree object recursively.
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean find(final K key, final V value)
	{
		return find(_root, key, value);
	}
	
	/**
	 * Implementation for find method on binary tree.
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return boolean
	 */
	private boolean find(Node<K, V> node, final K key, final V value)
	{
		Node<K, V> nodeToFind = new Node<K, V>(key, value);
		
		if (node == null)
			return false;
		else
		{
			// Compare the current node and the node to be found
			// If its zero, it means their keys are the same, so return true.
			if (compare(node, nodeToFind) == 0)
				return true;
			
			if (node._left != null)
				if (find(node._left, key, value))
					return true;
				
			if (node._right != null)
				if (find(node._right, key, value))
					return true;
				
			return false;
		}
	}
	
	/**
	 * Performs pre-order traversal on binary tree while printing the node's contents.
	 */
	public void preOrderTraversal()
	{
		preOrderTraversal(_root);
	}
	
	/**
	 * Performs in-order traversal on binary tree while printing the node's contents.
	 */
	public void inOrderTraversal()
	{
		inOrderTraversal(_root);
	}
	
	/**
	 * Performs post-order traversal on binary tree while printing the node's contents.
	 */
	public void postOrderTraversal()
	{
		postOrderTraversal(_root);
	}
	
	/**
	 * Implementation for pre-order traversal on binary tree.
	 * @param node
	 */
	private void preOrderTraversal(Node<K, V> node)
	{
		if (node == null)
			return;
		else
		{
			System.out.println(node);
			preOrderTraversal(node._left);
			preOrderTraversal(node._right);
		}
	}
	
	/**
	 * Implementation for in-order traversal on binary tree.
	 * @param node
	 */
	private void inOrderTraversal(Node<K, V> node)
	{
		if (node == null)
			return;
		else
		{
			inOrderTraversal(node._left);
			System.out.println(node);
			inOrderTraversal(node._right);
		}
	}
	
	/**
	 * Implementation for post-order traversal on binary tree.
	 * @param node
	 */
	private void postOrderTraversal(Node<K, V> node)
	{
		if (node == null)
			return;
		else
		{
			inOrderTraversal(node._left);
			inOrderTraversal(node._right);
			System.out.println(node);
		}
	}
	
	/**
	 * Returns the number of nodes in the binary tree.
	 * 
	 * @return int
	 */
	public int size()
	{
		return countNodes(_root);
	}
	
	/**
	 * Implementation for node counting using pre-order traversal.
	 * 
	 * @param node
	 * @return int
	 */
	public int countNodes(final Node<K, V> node)
	{
		if (node == null)
			return 0;
		else
		{
			// Using pre-order traversal to count nodes.
			int size = 1;
			size += countNodes(node._left);
			size += countNodes(node._right);
			return size;
		}
	}
	
	/**
	 * Compares two nodes by their key value.
	 */
	@Override
	public int compare(Node<K, V> o1, Node<K, V> o2)
	{
		return (o1.getKey().compareTo(o2.getKey()));
	}
	
	public static void main(String[] args)
	{
		BinaryTree<Integer, Integer> tree = new BinaryTree<Integer, Integer>();
		
		// Insertion usage
		tree.insert(1, 1);
		tree.insert(2, 2);
		tree.insert(3, 3);
		tree.insert(4, 4);
		tree.insert(5, 5);
		tree.insert(6, 6);
		
		// Find usage
		System.out.println("---- Find a node with key 3 ----");
		System.out.println(tree.find(3, 0));
		
		// Pre-order traversal
		System.out.println("---- Pre order traversal ----");
		tree.preOrderTraversal(tree._root);
		
		// In-order traversal
		System.out.println("---- In order traversal ----");
		tree.inOrderTraversal(tree._root);
		
		// Post-order traversal
		System.out.println("---- Post order traversal ----");
		tree.postOrderTraversal(tree._root);
		
		// Tree size
		System.out.println("---- Tree Size ----");
		System.out.println(tree.size());
	}
}

// This node class can be used if necessary, it's not an inner class.
class Node<K extends Comparable<K>, V extends Comparable<V>> implements Comparator<Node<K, V>>
{
	public Node<K, V> _left;
	public Node<K, V> _right;
	private K _key;
	private V _value;
	
	public Node(final K key, final V value)
	{
		_key = key;
		_value = value;
		
		_left = null;
		_right = null;
	}
	
	public K getKey()
	{
		return _key;
	}
	
	public void setKey(final K key)
	{
		this._key = key;
	}
	
	public V getValue()
	{
		return _value;
	}
	
	public void setValue(final V value)
	{
		_value = value;
	}
	
	@Override
	public String toString()
	{
		return "Key: " + _key + " Value: " + _value;
	}
	
	@Override
	public int compare(Node<K, V> nodeA, Node<K, V> nodeB)
	{
		return (nodeA.getKey().compareTo(nodeB.getKey()));
	}
}
