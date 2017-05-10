package com.utilib.data_structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<K, V>
{
	private StackNode<K, V> _firstNode;
	private StackNode<K, V> _lastNode;
	private int _numNodes = 0;
	
	public Stack()
	{
		_firstNode = null;
	}
	
	public Stack(StackNode<K, V> node)
	{
		_firstNode = node;
		_lastNode = _firstNode;
		
		_numNodes++;
	}
	
	/**
	 * Inserts a node into the stack.
	 * 
	 * @param node
	 */
	public void push(final StackNode<K, V> node)
	{
		StackNode<K, V> tmpNode = _lastNode;
		_lastNode = node;
		_lastNode.setNext(tmpNode);
		_numNodes++;
	}
	
	/**
	 * Inserts a node into the stack.
	 * 
	 * @param node
	 */
	public void push(final K key, final V value)
	{
		// If the stack is empty, push the new node to it
		if (isEmpty())
		{
			StackNode<K, V> node = new StackNode<K, V>(key, value);
			_firstNode = node;
			_lastNode = _firstNode;
			
			_numNodes++;
			return;
		}
		
		StackNode<K, V> node = new StackNode<K, V>(key, value);
		StackNode<K, V> tmpNode = _lastNode;
		_lastNode = node;
		_lastNode.setNext(tmpNode);
		_numNodes++;
	}
	
	/**
	 * Removes the last node of the stack.
	 * 
	 * @return Node
	 */
	public StackNode<K, V> pop()
	{
		// If the stack is empty, throw error
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		StackNode<K, V> tmpNode = _lastNode;
		_lastNode = _lastNode.getNext();
		_numNodes--;
		
		System.out.println(size());
		return tmpNode;
	}
	
	/**
	 * Returns the top node of the stack without removing it.
	 * 
	 * @return
	 */
	public StackNode<K, V> peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		return _firstNode;
	}
	
	/**
	 * Converts the stack to {@code ArrayList}.
	 * 
	 * @return
	 */
	public ArrayList<StackNode<K, V>> toList()
	{
		// If the stack is empty there is nothing to iterate.
		if (isEmpty())
			return null;
		
		// Iterate the nodes of the stack and add them to a list to iterate
		ArrayList<StackNode<K, V>> list = new ArrayList<StackNode<K, V>>();
		StackNode<K, V> tmp = _lastNode;
		
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
	
	public static void main(String[] args)
	{
		Stack<Integer, Integer> stack = new Stack<Integer, Integer>();
		
		// Insertion usage
		for (int i = 0; i < 50; i++)
		{
			stack.push(new StackNode<Integer, Integer>(i, i));
		}
		
		// Or
		for (int i = 50; i < 100; i++)
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
