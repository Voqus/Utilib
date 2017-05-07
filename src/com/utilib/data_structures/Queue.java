package com.utilib.data_structures;

import java.util.NoSuchElementException;

public class Queue<K, V>
{
	private Node<K, V> 	_firstNode;
	private Node<K, V> 	_lastNode;
	private int 		_numNodes;

	public Queue()
	{
		_firstNode	= null;
		_lastNode	= null;
	}

	public Queue(Node<K, V> node)
	{
		_firstNode	= node;
		_lastNode	= node;
		_numNodes++;
	}

	/**
	 * Inserts the {@code node} into the queue.
	 * 
	 * @param node
	 */
	public void insert(final Node<K, V> node)
	{
		// If the queue is empty, initialize first and last nodes.
		if (isEmpty())
		{
			_firstNode	= node;
			_lastNode	= node;
			_numNodes++;

			return;
		}

		Node<K, V> tmpNode = _lastNode;
		tmpNode.setPrevious(node);
		_lastNode = node;
		_numNodes++;

	}

	/**
	 * Removes and returns the first node off the queue.
	 * 
	 * @return Node
	 */
	public Node<K, V> pop()
	{
		// If the queue is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");

		Node<K, V> tmpNode = _firstNode;
		_firstNode = tmpNode.getPrevious();
		_numNodes--;

		return tmpNode;
	}

	/**
	 * Returns the first node that was inserted into the Queue.
	 * 
	 * @return Node
	 */
	public Node<K, V> peek()
	{
		return _firstNode;
	}

	/**
	 * Checks if the queue is empty
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
}