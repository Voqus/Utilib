package com.utilib.data_structures.trees;

import java.util.Comparator;

import com.utilib.data_structures.Stack;

public class BinarySearchTree<K extends Comparable<K>, V extends Comparable<V>> implements Comparator<Node<K, V>>
{
	private Node<K, V> _root;
	
	public BinarySearchTree()
	{
		_root = null;
	}
	
	public BinarySearchTree(final K key, final V value)
	{
		_root = new Node<K, V>(key, value);
	}
	
	/**
	 * Inserts a key-value pair into the binary search tree.
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(final K key, final V value)
	{
		_root = insert(_root, key, value);
	}
	
	/**
	 * Implementation for insertion method on binary search tree.
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return Node
	 */
	private Node<K, V> insert(Node<K, V> node, final K key, final V value)
	{
		Node<K, V> newNode = new Node<K, V>(key, value);
		if (node == null)
			node = newNode;
		// If node's key is less than the new node's
		else if (compare(node, newNode) < 0)
			node._left = insert(node._left, key, value);
		// else node's key is greater or equal than the new node's.
		else
			node._right = insert(node._right, key, value);
		return node;
	}
	
	/**
	 * Finds a node based on the key provided, recursively.
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
	 * Implementation on find method on binary search tree.
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return boolean
	 */
	private boolean find(final Node<K, V> node, final K key, final V value)
	{
		if (node == null)
			return false;
		else
		{
			Node<K, V> newNode = new Node<K, V>(key, value);
			if (compare(node, newNode) == 0)
				return true;
			
			if (node._left != null)
				if (find(node._left, key, value))
					return true;
			if (node._right != null)
				if (find(node._right, key, value))
					return true;
		}
		
		return false;
	}
	
	/**
	 * Finds the node with the minimum key-value.
	 * 
	 * @return Node
	 */
	public Node<K, V> findMin()
	{
		return findMin(_root);
	}
	
	/**
	 * Implementation for findMin method.
	 * 
	 * @param node
	 * @return Node
	 */
	private Node<K, V> findMin(Node<K, V> node)
	{
		if (node == null)
			return null;
		else if (node._left == null)
			return node;
		else
			return findMin(node._left);
	}
	
	/**
	 * Finds the node with the maximum key-value.
	 * 
	 * @return Node
	 */
	public Node<K, V> findMax()
	{
		return findMax(_root);
	}
	
	/**
	 * Implementation for findMax method.
	 * 
	 * @param node
	 * @return Node
	 */
	private Node<K, V> findMax(Node<K, V> node)
	{
		if (node == null)
			return null;
		else if (node._right == null)
			return node;
		else
			return findMin(node._right);
	}
	
	/**
	 * Deletes a node based on the key provided, recursively.
	 * 
	 * @param key
	 * @param value
	 */
	public void delete(final K key, final V value)
	{
		_root = delete(_root, key, value);
	}
	
	/**
	 * Implementation for delete method.
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return Node
	 */
	private Node<K, V> delete(Node<K, V> node, final K key, final V value)
	{
		Node<K, V> NodeToDelete = new Node<K, V>(key, value);
		Node<K, V> tmp = null;
		
		// If the node is null, return null.
		if (node == null)
			return null;
		// If the node is less than the node to be deleted, recurse left.
		else if (compare(node, NodeToDelete) < 0)
		{
			node._left = delete(node._left, key, value);
		}
		// If the node is greater than the node to be deleted, recurse right.
		else if (compare(node, NodeToDelete) > 0)
		{
			node._right = delete(node._right, key, value);
		}
		// If there are 2 subtrees, replace with its successor.
		else if (node._left != null && node._right != null)
		{
			tmp = findMin(node);
			node = tmp;
			node._right = delete(node._right, key, value);
		}
		// Else there is one subtree.
		else
		{
			if (node._left == null)
				node = node._right;
			else if (node._right == null)
				node = node._left;
		}
		return node;
	}
	
	/**
	 * Performs pre-order traversal on binary search tree while printing the
	 * node's contents.
	 */
	public void preOrderTraversal()
	{
		preOrderTraversal(_root);
	}
	
	/**
	 * Performs in-order traversal on binary search tree while printing the
	 * node's contents.
	 */
	public void inOrderTraversal()
	{
		inOrderTraversal(_root);
	}
	
	/**
	 * Performs post-order traversal on binary search tree while printing the
	 * node's contents.
	 */
	public void postOrderTraversal()
	{
		postOrderTraversal(_root);
	}
	
	/**
	 * Implementation for pre-order traversal on binary search tree.
	 * 
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
	 * Implementation for pre-order traversal on binary search tree, iterative
	 * way.
	 */
	public void preOrderTraversal_it()
	{
		if (_root == null)
			return;
		else
		{
			Stack<Integer, Node<K, V>> stack = new Stack<Integer, Node<K, V>>(true);
			Node<K, V> node = _root;
			
			int counter = 0;
			stack.push(counter, node);
			while (!stack.isEmpty())
			{
				node = stack.pop();
				System.out.println(node);
				
				if (node._right != null)
					stack.push(++counter, node);
				if (node._left != null)
					stack.push(++counter, node);
			}
		}
	}
	
	/**
	 * Implementation for in-order traversal on binary search tree.
	 * 
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
	 * Implementation for in-order traversal on binary search tree, iterative
	 * way.
	 */
	public void inOrderTraversal_it()
	{
		Stack<Integer, Node<K, V>> stack = new Stack<Integer, Node<K, V>>(true);
		Node<K, V> node = _root;
		
		int counter = 0;
		while (!stack.isEmpty() || node != null)
		{
			if (node != null)
			{
				stack.push(++counter, node);
				node = node._left;
			}
			else
			{
				node = stack.pop();
				System.out.println(node);
				node = node._right;
			}
		}
	}
	
	/**
	 * Implementation for post-order traversal on binary search tree.
	 * 
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
	 * Implementation for post-order traversal on binary search tree, iterative
	 * way.
	 */
	public void postOrderTraversal_it()
	{
		
		Stack<Integer, Node<K, V>> stack = new Stack<Integer, Node<K, V>>(true);
		Node<K, V> node = null;
		
		int counter = 0;
		while (!stack.isEmpty() || node != null)
		{
			if (node != null)
			{
				stack.push(++counter, node);
				node = node._left;
			}
			else
			{
				Node<K, V> peekNode = stack.peek();
				
				if (peekNode._right != null && node != peekNode._right)
					node = peekNode._right;
				else
				{
					System.out.println(peekNode);
					node = stack.pop();
				}
			}
		}
		
	}
	
	/**
	 * Returns the number of nodes in the binary search tree.
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
			return (1 + countNodes(node._left) + countNodes(node._right));
		}
	}
	
	@Override
	public int compare(Node<K, V> arg0, Node<K, V> arg1)
	{
		return (arg0.getKey().compareTo(arg1.getKey()));
	}
	
	public static void main(String[] args)
	{
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
		
		// Insertion usage
		bst.insert(1, 1); // the first node acts as the root node.
		bst.insert(2, 2);
		bst.insert(3, 3);
		bst.insert(4, 4);
		bst.insert(5, 5);
		
		// Find usage
		System.out.println("---- Find a node with key 5 ----");
		System.out.println(bst.find(5, 0));
		
		// Delete usage
		System.out.println("---- Delete node with key 5 ----");
		bst.delete(5, 0);
		
		// Find usage again
		System.out.println("---- Find a node with key 5 ----");
		System.out.println(bst.find(5, 0));
		
		// Pre-order traversal
		System.out.println("---- Pre order traversal ----");
		bst.preOrderTraversal(bst._root);
		
		// Pre-order traversal iterative
		System.out.println("---- Pre order traversal iterative ----");
		bst.preOrderTraversal();
		
		// In-order traversal
		System.out.println("---- In order traversal ----");
		bst.inOrderTraversal(bst._root);
		
		// In-order traversal iterative
		System.out.println("---- In order traversal iterative ----");
		bst.inOrderTraversal();
		
		// Post-order traversal
		System.out.println("---- Post order traversal ----");
		bst.postOrderTraversal(bst._root);
		
		// Post-order traversal iterative
		System.out.println("---- Post order traversal iterative ----");
		bst.postOrderTraversal();
		
		// Tree size
		System.out.println("---- Tree Size ----");
		System.out.println(bst.size());
	}
}