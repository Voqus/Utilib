package com.utilib.data_structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<K, V>
{
	private Node<K, V>	_firstNode;
	private Node<K, V>	_lastNode;
	private int			_numNodes = 0;
	
	public Stack()
	{
		_firstNode = null;
	}
	
	public Stack(Node<K, V> node)
	{
		_firstNode	= node;
		_lastNode	= _firstNode;
		
		_numNodes++;
	}
	
	/**
	 * Inserts a node into the stack.
	 * 
	 * @param node
	 */
	public void push(final Node<K, V> node)
	{
		// If the stack is empty, push the new node to it
		if (isEmpty())
		{
			_firstNode	= node;
			_lastNode	= _firstNode;

			_numNodes++;
			return;
		}
		
		Node<K, V> tmpNode = _lastNode;
		_lastNode = node;
		_lastNode.setNext(tmpNode);
		_numNodes++;
	}
	
	/**
	 * Removes the last node of the stack.
	 * 
	 * @return Node
	 */
	public Node<K, V> pop()
	{
		// If the stack is empty, throw error
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		Node<K, V> tmpNode = _lastNode;
		_lastNode = tmpNode.getNext();
		_numNodes--;
		
		return tmpNode;
	}
	
	/**
	 * Returns the top node of the stack without removing it.
	 * 
	 * @return
	 */
	public Node<K, V> peek()
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
		for (int i = 0; i < 100; i++)
		{
			stack.push(new Node<Integer, Integer>(i, i));
		}
		
		// Print usage
		stack.toList().forEach((e)->
		{
			System.out.println(e);
		});
		
		// Deletion usage
		for (int i = 100; i > 0; i--)
		{
			System.out.println(stack.pop());
		}
	}
}
