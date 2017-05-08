package com.utilib.data_structures;

import java.util.NoSuchElementException;

public class List<K, V>
{
	private Node<K, V>	_head;
	private Node<K, V>	_tail;
	private int			_numNodes	= 0;
	
	public List()
	{
		_head = null;
		_tail = null;
	}
	
	public List(Node<K, V> node)
	{
		_head = node;
		_tail = node;
	}
	
	/**
	 * Adds a node to the list.
	 * @param node
	 */
	public void add(Node<K, V> node)
	{
		// If the list is empty, initialize the head and tail nodes.
		if (isEmpty())
		{
			_head = node;
			_tail = node;
			
			_numNodes++;
			return;
		}
		
		_tail.setNext(node);
		_tail.setPrevious(null);
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Adds a node with a key-value pair to the list.
	 * 
	 * @param key
	 * @param value
	 */
	public void add(final K key, final V value)
	{
		// If the list is empty, initialize the head and tail nodes.
		if (isEmpty())
		{
			Node<K, V> node = new Node<K, V>(key, value);
			_head = node;
			_tail = node;
			
			_numNodes++;
			return;
		}
		
		Node<K, V> node = new Node<K, V>(key, value);
		_tail.setNext(node);
		_tail.setPrevious(null);
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Searches and returns a node by the key given.
	 * 
	 * @param key
	 * @return Node
	 */
	public Node<K,V> getByKey(final K key)
	{
		// If the list is empty, throw exception.
		if(isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// Iterate through the list to find the node with the key given.
		Node<K,V> curr = _head;
		while(curr != null)
		{
			if(curr.getKey() != key)
				curr = curr.getNext();
			else
				return curr;
		}
		
		return null;
	}
	
	/**
	 * Searches and returns a node by the value given.
	 * 
	 * @param value
	 * @return Node
	 */
	public Node<K,V> getByValue(final V value)
	{
		// If the list is empty, throw exception.
		if(isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// Iterate through the list to find the node with the value given.
		Node<K,V> curr = _head;
		while(curr != null)
		{
			if(curr.getValue() != value)
				curr = curr.getNext();
			else
				return curr;
		}
		
		return null;
	}
	
	public Node<K,V> removeFirst()
	{
		// If the list is empty, throw exception.
		if(isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// If the head is the tail, there is only one node left, remove it.
		if(_head == _tail)
		{
			Node<K,V> tmpNode = _tail;
			_head = null;
			_tail = null;
			return tmpNode;
		}
		
		Node<K,V> tmpNode = _head;
		_head = _head.getNext();
		_numNodes--;
		
		return tmpNode;
	}
	
	/**
	 * Removes last node of the list.
	 * 
	 * @return Node
	 */
	public Node<K,V> removeLast()
	{
		// If the list is empty, throw exception.
		if(isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// If the head is the tail, there is only one node left, remove it.
		if(_head == _tail)
		{
			Node<K,V> tmpNode = _tail;
			_head = null;
			_tail = null;
			return tmpNode;
		}
		
		Node<K,V> tmpNode = _tail;
		
		// Get the node before the tail.
		Node<K,V> currNode = _head;
		while(currNode.getNext() != _tail)
		{
			currNode = currNode.getNext();
		}
		
		// Update the tail node and set next pointer to null.
		_tail = currNode;
		_tail.setNext(null);
		_numNodes--;
		
		return tmpNode;
	}
	
	/**
	 * Prints the list.
	 */
	public void print()
	{
		Node<K,V> currNode = _head;
		while(currNode != null)
		{
			System.out.println(currNode);
			currNode = currNode.getNext();
		}
	}
	
	/**
	 * Checks if the list is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return (_numNodes == 0);
	}
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return int
	 */
	public int size()
	{
		return _numNodes;
	}
	
	public static void main(String[] args)
	{
		List<Integer, Integer> list = new List<Integer, Integer>();

		// Insertion usage
		list.add(new Node<Integer, Integer>(0,10));
		list.add(new Node<Integer, Integer>(1,20));
		list.add(new Node<Integer, Integer>(2,30));
		// Or
		list.add(3,40);
		list.add(4,50);
		list.add(5,60);
		
		// Printing the list
		list.print();
		
		// Deletion usage
		System.out.println("Removing first node: " + list.removeFirst());
		System.out.println("Removing last node : " + list.removeLast());
	}
}
