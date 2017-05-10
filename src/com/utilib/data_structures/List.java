package com.utilib.data_structures;

import java.util.NoSuchElementException;

public class List<K, V>
{
	private ListNode<K, V> 	_head;
	private ListNode<K, V> 	_tail;
	private int 		_numNodes = 0;
	
	public List()
	{
		_head = null;
		_tail = null;
	}
	
	public List(final ListNode<K, V> node)
	{
		_head = node;
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Adds a node to the list.
	 * 
	 * @param node
	 */
	public void add(final ListNode<K, V> node)
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
			ListNode<K, V> node = new ListNode<K, V>(key, value);
			_head = node;
			_tail = node;
			
			_numNodes++;
			return;
		}
		
		ListNode<K, V> node = new ListNode<K, V>(key, value);
		_tail.setNext(node);
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Searches and returns a node by the key given.
	 * 
	 * @param key
	 * @return Node
	 */
	public ListNode<K, V> getByKey(final K key)
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// Iterate through the list to find the node with the key given.
		ListNode<K, V> curr = _head;
		while (curr != null)
		{
			if (curr.getKey() != key)
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
	public ListNode<K, V> getByValue(final V value)
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// Iterate through the list to find the node with the value given.
		ListNode<K, V> curr = _head;
		while (curr != null)
		{
			if (curr.getValue() != value)
				curr = curr.getNext();
			else
				return curr;
		}
		
		return null;
	}
	
	/**
	 * Removes the first node of the list.
	 * 
	 * @return
	 */
	public ListNode<K, V> removeFirst()
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// If the head is the tail, there is only one node left, remove it.
		if (_head == _tail)
		{
			ListNode<K, V> tmpNode = _tail;
			_head = null;
			_tail = null;
			
			_numNodes--;
			return tmpNode;
		}
		
		ListNode<K, V> tmpNode = _head;
		_head = _head.getNext();
		_numNodes--;
		
		return tmpNode;
	}
	
	/**
	 * Removes last node of the list.
	 * 
	 * @return Node
	 */
	public ListNode<K, V> removeLast()
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// If the head is the tail, there is only one node left, remove it.
		if (_head == _tail)
		{
			ListNode<K, V> tmpNode = _tail;
			_head = null;
			_tail = null;
			
			_numNodes--;
			return tmpNode;
		}
		
		ListNode<K, V> tmpNode = _tail;
		
		// Get the node before the tail.
		ListNode<K, V> currNode = _head;
		while (currNode.getNext() != _tail)
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
		ListNode<K, V> currNode = _head;
		while (currNode != null)
		{
			System.out.println(currNode);
			currNode = currNode.getNext();
		}
	}
	
	/** 
	 * Clears the list.
	 */
	public void clear()
	{
		while(_numNodes > 0)
			removeLast();
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
		list.add(new ListNode<Integer, Integer>(0, 10));
		list.add(new ListNode<Integer, Integer>(1, 20));
		list.add(new ListNode<Integer, Integer>(2, 30));
		// Or
		list.add(3, 40);
		list.add(4, 50);
		list.add(5, 60);
		
		// Printing the list
		list.print();
		
		// Deletion usage
		System.out.println("Removing first node: " + list.removeFirst());
		System.out.println("Removing last node : " + list.removeLast());
		
		// Clear list
		//list.clear();
		
		System.out.println("List size: " + list.size());
	}
}
