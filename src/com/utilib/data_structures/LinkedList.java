package com.utilib.data_structures;

import java.util.NoSuchElementException;

public class LinkedList<K, V>
{
	private Node<K, V>	_head;
	private Node<K, V>	_tail;
	private int			_numNodes			= 0;
	private boolean		_allowDuplicates	= true;
	
	public LinkedList(final boolean allowDuplicates)
	{
		_allowDuplicates = allowDuplicates;
		_head = null;
		_tail = null;
	}
	
	public LinkedList(final K key, final V value, final boolean allowDuplicates)
	{
		Node<K, V> node = new Node<K, V>(key, value);
		_allowDuplicates = allowDuplicates;
		_head = node;
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Adds a node to the linked list.
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean add(final Node<K, V> node)
	{
		if (isEmpty())
		{
			_head = node;
			_tail = node;
			
			_numNodes++;
			return true;
		}
		
		if (!_allowDuplicates)
		{
			if (searchDuplicates(node.getKey()))
			{
				System.err.println("Duplicate key[" + node.getKey() + "] detected.");
				return false;
			}
		}
		
		_tail.setNext(node);
		node.setPrevious(_tail);
		_tail = node;
		_numNodes++;
		
		return true;
	}
	
	/**
	 * Adds a node with key-value pair to the linked list.
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean add(final K key, final V value)
	{
		if (isEmpty())
		{
			Node<K, V> node = new Node<K, V>(key, value);
			_head = node;
			_tail = node;
			
			_numNodes++;
			return true;
		}
		
		if (!_allowDuplicates)
		{
			if (searchDuplicates(key))
			{
				System.err.println("Duplicate key[" + key + "] detected.");
				return false;
			}
		}
		
		Node<K, V> node = new Node<K, V>(key, value);
		_tail.setNext(node);
		node.setPrevious(_tail);
		_tail = node;
		
		_numNodes++;
		
		return true;
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
	 * Searches for duplicate keys in the list.
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean searchDuplicates(final K key)
	{
		Node<K, V> currNode = _head;
		
		while (currNode != null)
		{
			if (currNode.getKey() != key)
				currNode = currNode.getNext();
			else
				return true;
		}
		return false;
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
	
	private class Node<Key, Value>
	{
		private Node<Key, Value>	_next;
		private Node<Key, Value>	_previous;
		private Key					_key;
		private Value				_value;
		
		public Node(final Key key, final Value value)
		{
			setKey(key);
			setValue(value);
			
			_next = null;
			_previous = null;
		}
		
		public Key getKey()
		{
			return _key;
		}
		
		public void setKey(final Key key)
		{
			this._key = key;
		}
		
		public Value getValue()
		{
			return _value;
		}
		
		public void setValue(final Value value)
		{
			_value = value;
		}
		
		public Node<Key, Value> getNext()
		{
			return _next;
		}
		
		public void setNext(final Node<Key, Value> next)
		{
			_next = next;
		}
		
		public Node<Key, Value> getPrevious()
		{
			return _previous;
		}
		
		public void setPrevious(final Node<Key, Value> previous)
		{
			_previous = previous;
		}
		
		@Override
		public String toString()
		{
			return "Key: " + _key + " Value: " + _value;
		}
	}
	
	public static void main(String[] args)
	{
		LinkedList<Integer, Integer> list = new LinkedList<Integer, Integer>(true);
		
		// Insertion usage
		for (int i = 0; i < 7; i++)
		{
			list.add(i, i * 10);
		}
		
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
