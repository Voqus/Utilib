package com.utilib.data_structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Queue<K, V>
{
	private Node<K, V> _firstNode;
	private Node<K, V> _lastNode;
	private int _numNodes = 0;
	private boolean _allowDuplicates = true;
	
	public Queue(final boolean allowDuplicates)
	{
		_allowDuplicates = allowDuplicates;
		
		_firstNode = null;
		_lastNode = null;
	}
	
	public Queue(final K key, final V value, final boolean allowDuplicates)
	{
		Node<K, V> node = new Node<K, V>(key, value);
		_allowDuplicates = allowDuplicates;
		
		_firstNode = node;
		_lastNode = node;
		_numNodes++;
	}
	
	/**
	 * Inserts the key-value pair into the queue.
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean insert(final K key, final V value)
	{
		// If the queue is empty, initialize first and last nodes.
		if (isEmpty())
		{
			Node<K, V> node = new Node<K, V>(key, value);
			_firstNode = node;
			_lastNode = node;
			_numNodes++;
			
			return true;
		}
		
		if (!_allowDuplicates)
		{
			if (searchDuplicate(key))
			{
				System.err.println("Duplicate key[" + key + "] detected.");
				return false;
			}
		}
		
		Node<K, V> node = new Node<K, V>(key, value);
		Node<K, V> tmpNode = _lastNode;
		tmpNode.setPrevious(node);
		_lastNode = node;
		_numNodes++;
		
		return true;
	}
	
	/**
	 * Removes and returns the first node off the queue.
	 * 
	 * @return V
	 */
	public V remove()
	{
		// If the queue is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		
		Node<K, V> tmpNode = _firstNode;
		_firstNode = tmpNode.getPrevious();
		_numNodes--;
		
		return tmpNode.getValue();
	}
	
	/**
	 * Returns the first node that was inserted into the Queue.
	 * 
	 * @return V
	 */
	public V peek()
	{
		return _firstNode.getValue();
	}
	
	/**
	 * Searches for duplicate key in the queue.
	 * 
	 * @param key
	 * @return boolean
	 */
	private boolean searchDuplicate(final K key)
	{
		Node<K, V> currNode = _firstNode;
		
		while (currNode != null)
		{
			if (currNode.getKey() != key)
				currNode = currNode.getPrevious();
			else
				return true;
		}
		return false;
	}
	
	/**
	 * Converts the queue to {@code ArrayList}.
	 * 
	 * @return
	 */
	public ArrayList<Node<K, V>> toList()
	{
		// If the stack is empty there is nothing to iterate.
		if (isEmpty())
			return null;
		
		// Iterate the nodes of the stack and add them to a list to iterate
		ArrayList<Node<K, V>> list = new ArrayList<Node<K, V>>();
		Node<K, V> tmp = _firstNode;
		
		while (tmp != null)
		{
			list.add(tmp);
			tmp = tmp.getPrevious();
		}
		
		return list;
	}
	
	/**
	 * Clears the stack.
	 */
	public void clear()
	{
		while (_numNodes > 0)
			remove();
	}
	
	/**
	 * Checks if the queue is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return (_numNodes == 0);
	}
	
	/**
	 * Returns the size of the queue.
	 * 
	 * @return int
	 */
	public int size()
	{
		return _numNodes;
	}
	
	private class Node<Key, Value>
	{
		private Node<Key, Value> _previous;
		private Key _key;
		private Value _value;
		
		public Node(final Key key, final Value value)
		{
			setKey(key);
			setValue(value);
			
			_previous = null;
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
		
		public void setPrevious(final Node<Key, Value> node)
		{
			_previous = node;
		}
		
		public Node<Key, Value> getPrevious()
		{
			return _previous;
		}
		
		@Override
		public String toString()
		{
			return "Key " + _key + " Value: " + _value + " Previous: " + System.identityHashCode(_previous);
			
		}
	}
	
	public static void main(String[] args)
	{
		Queue<Integer, Integer> queue = new Queue<Integer, Integer>(true);
		
		// Insertion usage
		for (int i = 0; i < 100; i++)
		{
			queue.insert(i, i);
		}
		
		// Print usage
		queue.toList().forEach((e) -> {
			System.out.println(e);
		});
		
		// Deletion usage
		for (int i = 100; i > 0; i--)
		{
			System.out.println(queue.remove());
		}
		
		// Or clear the queue
		// queue.clear();
		
		System.out.println("Queue size: " + queue.size());
	}
}