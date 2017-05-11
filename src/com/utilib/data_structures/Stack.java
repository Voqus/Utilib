package com.utilib.data_structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<K, V>
{
	private Node<K, V> 	_firstNode;
	private Node<K, V> 	_lastNode;
	private int 		_numNodes = 0;
	private boolean 	_allowDuplicates = true;
	
	public Stack(final boolean allowDuplicates)
	{
		_allowDuplicates = allowDuplicates;
		_firstNode = null;
	}
	
	public Stack(final K key, final V value, final boolean allowDuplicates)
	{
		Node<K, V> node = new Node<K, V>(key, value);
		_allowDuplicates = allowDuplicates;
		
		_firstNode = node;
		_lastNode = _firstNode;
		
		_numNodes++;
	}
	
	/**
	 * Inserts a node into the stack.
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean push(final K key, final V value)
	{
		// If the stack is empty, push the new node to it
		if (isEmpty())
		{
			Node<K, V> node = new Node<K, V>(key, value);
			_firstNode = node;
			_lastNode = _firstNode;
			
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
		_lastNode = node;
		_lastNode.setNext(tmpNode);
		_numNodes++;
		
		return true;
	}
	
	/**
	 * Removes the last node of the stack.
	 * 
	 * @return V
	 */
	public V pop()
	{
		// If the stack is empty, throw error
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		Node<K, V> tmpNode = _lastNode;
		_lastNode = _lastNode.getNext();
		_numNodes--;
		
		return tmpNode.getValue();
	}
	
	/**
	 * Returns the top node of the stack without removing it.
	 * 
	 * @return V
	 */
	public V peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		return _firstNode.getValue();
	}
	
	/**
	 * Searches for duplicate key in the stack.
	 * 
	 * @param key
	 * @return boolean
	 */
	private boolean searchDuplicate(final K key)
	{
		Node<K, V> currNode = _lastNode;
		
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
	 * Converts the stack to {@code ArrayList}.
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
		Node<K, V> tmp = _lastNode;
		
		while (tmp != null)
		{
			list.add(tmp);
			tmp = tmp.getNext();
		}
		
		return list;
	}
	
	/**
	 * Clears the stack.
	 */
	public void clear()
	{
		while (_numNodes > 0)
			pop();
	}
	
	/**
	 * Checks if the stack is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return (_numNodes == 0);
	}
	
	/**
	 * Returns the size of the stack.
	 * 
	 * @return int
	 */
	public int size()
	{
		return _numNodes;
	}
	
	private class Node<K, V>
	{
		private Node<K, V> _next;
		private K _key;
		private V _value;
		
		public Node(final K key, final V value)
		{
			setKey(key);
			setValue(value);
			
			_next = null;
		}
		
		public V getValue()
		{
			return _value;
		}
		
		public void setValue(V value)
		{
			_value = value;
		}
		
		public K getKey()
		{
			return _key;
		}
		
		public void setKey(K _key)
		{
			this._key = _key;
		}
		
		public void setNext(final Node<K, V> node)
		{
			_next = node;
		}
		
		public Node<K, V> getNext()
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
		Stack<Integer, Integer> stack = new Stack<Integer, Integer>(true);
		
		// Insertion usage
		for (int i = 0; i < 100; i++)
		{
			stack.push(i, i);
		}
		
		// Print usage
		stack.toList().forEach((e) -> {
			System.out.println(e);
		});
		
		// Deletion usage
		for (int i = 100; i > 0; i--)
		{
			System.out.println(stack.pop());
		}
		
		// Or to clear the stack in one go.
		// stack.clear();
		
		System.out.println("Stack size: " + stack.size());
	}
}
