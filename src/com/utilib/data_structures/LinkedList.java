package com.utilib.data_structures;

import java.util.NoSuchElementException;

public class LinkedList<K, V>
{
	private Node<K, V> 	_head;
	private Node<K, V> 	_tail;
	private int 		_numNodes = 0;
	
	public LinkedList()
	{
		_head = null;
		_tail = null;
	}
	
	public LinkedList(Node<K, V> node)
	{
		_head = node;
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Adds a node to the linked list.
	 * 
	 * @param node
	 */
	public void add(final Node<K, V> node)
	{
		if (isEmpty())
		{
			_head = node;
			_tail = node;
			
			_numNodes++;
			return;
		}
		
		_tail.setNext(node);
		node.setPrevious(_tail);
		_tail = node;
		_numNodes++;
	}
	
	/**
	 * Adds a node with key-value pair to the linked list.
	 * 
	 * @param key
	 * @param value
	 */
	public void add(final K key, final V value)
	{
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
		node.setPrevious(_tail);
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Removes a node off the linked list.
	 * 
	 * @param node
	 * @return
	 */
	public boolean remove(final Node<K, V> node)
	{
		if (isEmpty())
			throw new NoSuchElementException("LinkedList is empty.");
		
		// If the head is the tail, there's only one node left.
		if (_head == _tail)
		{
			_head = null;
			_tail = null;
			
			_numNodes--;
			return true;
		}
		
		Node<K, V> currNode = getByKey(node.getKey());
		if (currNode == null)
			throw new NoSuchElementException("No such element in the linked list.");
		
		// If the previous node is not null.
		if (currNode.getPrevious() != null)
			currNode.getPrevious().setNext(currNode.getNext());
		
		// If the next node is not null.
		if (currNode.getNext() != null)
			currNode.getNext().setPrevious(currNode.getPrevious());
		
		// Update the tail node.
		Node<K, V> tailNode = _head;
		while (tailNode.getNext() != null)
		{
			tailNode = tailNode.getNext();
		}
		_tail = tailNode;
		_numNodes--;
		
		return true;
	}
	
	/**
	 * Removes a node off the linked list using a key-value pair.
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean remove(final K key, final V value)
	{
		if (isEmpty())
			throw new NoSuchElementException("LinkedList is empty.");
		
		// If the head is the tail, there's only one node left.
		if (_head == _tail)
		{
			_head = null;
			_tail = null;
			
			_numNodes--;
			return true;
		}
		
		Node<K, V> currNode = getByKey(key);
		if (currNode == null)
			throw new NoSuchElementException("No such element in the linked list.");
		
		// If the previous node is not null.
		if (currNode.getPrevious() != null)
			currNode.getPrevious().setNext(currNode.getNext());
		
		// If the next node is not null.
		if (currNode.getNext() != null)
			currNode.getNext().setPrevious(currNode.getPrevious());
		
		// Update the tail node.
		Node<K, V> tailNode = _head;
		while (tailNode.getNext() != null)
		{
			tailNode = tailNode.getNext();
		}
		
		_tail = tailNode;
		_numNodes--;
		
		return true;
	}
	
	/**
	 * Searches and returns a node by the key given.
	 * 
	 * @param key
	 * @return Node
	 */
	public Node<K, V> getByKey(final K key)
	{
		Node<K, V> currNode = _head;
		while (currNode != null)
		{
			if (currNode.getKey() != key)
				currNode = currNode.getNext();
			else
				return currNode;
		}
		
		return null;
	}
	
	/**
	 * Searches and returns a node by the value given.
	 * 
	 * @param value
	 * @return Node
	 */
	public Node<K, V> getByValue(final V value)
	{
		Node<K, V> currNode = _head;
		while (currNode != null)
		{
			if (currNode.getValue() != value)
				currNode = currNode.getNext();
			else
				return currNode;
		}
		
		return null;
	}
	
	/**
	 * Prints the linked list.
	 */
	public void print()
	{
		Node<K, V> currNode = _head;
		while (currNode != null)
		{
			System.out.println("[Node: " + System.identityHashCode(currNode) + " ] = " + currNode + " Previous: "
					+ System.identityHashCode(currNode.getPrevious()) + " Next: "
					+ System.identityHashCode(currNode.getNext()));
			currNode = currNode.getNext();
		}
	}
	
	/**
	 * Clears the linked list.
	 */
	public void clear()
	{
		Node<K, V> currNode = _head;
		while (currNode != null)
		{
			remove(currNode);
			currNode = currNode.getNext();
		}
	}
	
	/**
	 * Checks if the linked list is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return (_numNodes == 0);
	}
	
	/**
	 * Returns the size of the linked list.
	 * 
	 * @return
	 */
	public int size()
	{
		return _numNodes;
	}
	
	public static void main(String[] args)
	{
		LinkedList<Integer, Integer> list = new LinkedList<Integer, Integer>();
		
		// Insertion usage
		list.add(new Node<Integer, Integer>(0, 0));
		list.add(new Node<Integer, Integer>(1, 10));
		list.add(new Node<Integer, Integer>(2, 20));
		list.add(new Node<Integer, Integer>(3, 30));
		// Or
		list.add(4, 40);
		list.add(5, 50);
		list.add(6, 60);
		
		// Printing the list
		list.print();
		
		// Deletion usage
		list.remove(5, 50);
		list.remove(3, 30);
		list.remove(6, 60);
		list.remove(2, 20);
		list.remove(4, 40);
		list.remove(1, 10);
		list.remove(0, 0);
		
		list.print();
		
		// Clear list
		// list.clear();
		
		System.out.println(list.size());
	}
}
