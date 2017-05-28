package com.utilib.data_structures;

import java.util.NoSuchElementException;

public class List<K, V>
{
	private Node<K, V> 	_head;
	private Node<K, V> 	_tail;
	private int 		_numNodes = 0;
	private boolean		_allowDuplicates = true;
	
	public List(final boolean allowDuplicates)
	{
		_allowDuplicates = allowDuplicates;
		
		_head = null;
		_tail = null;
	}
	
	public List(final K key, final V value, final boolean allowDuplicates)
	{
		Node<K, V> node = new Node<K,V>(key, value);
		_allowDuplicates = allowDuplicates;
		_head = node;
		_tail = node;
		
		_numNodes++;
	}
	
	/**
	 * Adds a node with a key-value pair to the list.
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean add(final K key, final V value)
	{
		// If the list is empty, initialize the head and tail nodes.
		if (isEmpty())
		{
			Node<K, V> node = new Node<K, V>(key, value);
			_head = node;
			_tail = node;
			
			_numNodes++;
			return true;
		}
		
		if(!_allowDuplicates)
		{
			if(searchDuplicates(key))
			{
				System.err.println("Duplicate key[" + key + "] detected.");
				return false;
			}
		}
		
		Node<K, V> node = new Node<K, V>(key, value);
		_tail.setNext(node);
		_tail = node;
		
		_numNodes++;
		return true;
	}
	
	/**
	 * Searches and returns a node by the key given.
	 * 
	 * @param key
	 * @return V
	 */
	public V getByKey(final K key)
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// Iterate through the list to find the node with the key given.
		Node<K, V> curr = _head;
		while (curr != null)
		{
			if (curr.getKey() != key)
				curr = curr.getNext();
			else
				return curr.getValue();
		}
		
		return null;
	}
	
	/**
	 * Searches and returns a node by the value given.
	 * 
	 * @param value
	 * @return K
	 */
	public K getByValue(final V value)
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// Iterate through the list to find the node with the value given.
		Node<K, V> curr = _head;
		while (curr != null)
		{
			if (curr.getValue() != value)
				curr = curr.getNext();
			else
				return curr.getKey();
		}
		
		return null;
	}
	
	/**
	 * Removes the first node of the list.
	 * 
	 * @return
	 */
	public V removeFirst()
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// If the head is the tail, there is only one node left, remove it.
		if (_head == _tail)
		{
			Node<K, V> tmpNode = _tail;
			_head = null;
			_tail = null;
			
			_numNodes--;
			return tmpNode.getValue();
		}
		
		Node<K, V> tmpNode = _head;
		_head = _head.getNext();
		_numNodes--;
		
		return tmpNode.getValue();
	}
	
	/**
	 * Removes last node of the list.
	 * 
	 * @return Node
	 */
	public V removeLast()
	{
		// If the list is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("List is empty.");
		
		// If the head is the tail, there is only one node left, remove it.
		if (_head == _tail)
		{
			Node<K, V> tmpNode = _tail;
			_head = null;
			_tail = null;
			
			_numNodes--;
			return tmpNode.getValue();
		}
		
		Node<K, V> tmpNode = _tail;
		
		// Get the node before the tail.
		Node<K, V> currNode = _head;
		while (currNode.getNext() != _tail)
		{
			currNode = currNode.getNext();
		}
		
		// Update the tail node and set next pointer to null.
		_tail = currNode;
		_tail.setNext(null);
		_numNodes--;
		
		return tmpNode.getValue();
	}
	
	/**
	 * Searches for duplicate keys in the list.
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean searchDuplicates(final K key)
	{
		Node<K,V> currNode = _head;
		
		while(currNode != null)
		{
			if(currNode.getKey() != key)
				currNode = currNode.getNext();
			else
				return true;
		}
		return false;
	}
	
	/**
	 * Prints the list.
	 */
	public void print()
	{
		Node<K, V> currNode = _head;
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
	
	private class Node<Key,Value>
	{
		private Node<Key, Value> _next;
		private Key _key;
		private Value _value;
		
		public Node(final Key key, final Value value)
		{
			setKey(key);
			setValue(value);
			
			_next = null;
		}
		
		public Value getValue()
		{
			return _value;
		}
		
		public void setValue(Value value)
		{
			_value = value;
		}
		
		public Key getKey()
		{
			return _key;
		}
		
		public void setKey(Key _key)
		{
			this._key = _key;
		}
		
		public void setNext(final Node<Key, Value> node)
		{
			_next = node;
		}
		
		public Node<Key, Value> getNext()
		{
			return _next;
		}
		
		@Override
		public String toString()
		{
			return "Key " + _key + " Value: " + _value + " Next: " + System.identityHashCode(_next);
			
		}
	}
	
	public static void main(String[] args)
	{
		List<Integer, Integer> list = new List<Integer, Integer>(true);
		
		// Insertion usage
		list.add(0, 00);
		list.add(1, 10);
		list.add(2, 20);
		
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
