package com.utilib.data_structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Queue<K, V>
{
	private QueueNode<K, V> _firstNode;
	private QueueNode<K, V> _lastNode;
	private int _numNodes = 0;
	private boolean _allowDuplicates = true;
	
	public Queue(final boolean allowDuplicates)
	{
		_allowDuplicates = allowDuplicates;
		
		_firstNode = null;
		_lastNode = null;
	}
	
	public Queue(final QueueNode<K, V> node, final boolean allowDuplicates)
	{
		_allowDuplicates = allowDuplicates;
		
		_firstNode = node;
		_lastNode = node;
		_numNodes++;
	}
	
	/**
	 * Inserts the {@code node} into the queue.
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean insert(final QueueNode<K, V> node)
	{
		// If the queue is empty, initialize first and last nodes.
		if (isEmpty())
		{
			_firstNode = node;
			_lastNode = node;
			_numNodes++;
			
			return true;
		}
		
		if (!_allowDuplicates)
		{
			if (searchDuplicate(node.getKey()))
			{
				System.err.println("Duplicate key[" + node.getKey() + "] detected.");
				return false;
			}
		}
		
		QueueNode<K, V> tmpNode = _lastNode;
		tmpNode.setPrevious(node);
		_lastNode = node;
		_numNodes++;
		
		return true;
	}
	
	/**
	 * Inserts the {@code node} into the queue.
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean insert(final K key, final V value)
	{
		// If the queue is empty, initialize first and last nodes.
		if (isEmpty())
		{
			QueueNode<K, V> node = new QueueNode<K, V>(key, value);
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
		
		QueueNode<K, V> node = new QueueNode<K, V>(key, value);
		QueueNode<K, V> tmpNode = _lastNode;
		tmpNode.setPrevious(node);
		_lastNode = node;
		_numNodes++;
		
		return true;
	}
	
	/**
	 * Removes and returns the first node off the queue.
	 * 
	 * @return Node
	 */
	public QueueNode<K, V> remove()
	{
		// If the queue is empty, throw exception.
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		
		QueueNode<K, V> tmpNode = _firstNode;
		_firstNode = tmpNode.getPrevious();
		_numNodes--;
		
		return tmpNode;
	}
	
	/**
	 * Returns the first node that was inserted into the Queue.
	 * 
	 * @return Node
	 */
	public QueueNode<K, V> peek()
	{
		return _firstNode;
	}
	
	/**
	 * Searches for duplicate key in the queue.
	 * 
	 * @param key
	 * @return boolean
	 */
	private boolean searchDuplicate(final K key)
	{
		QueueNode<K, V> currNode = _firstNode;
		
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
	public ArrayList<QueueNode<K, V>> toList()
	{
		// If the stack is empty there is nothing to iterate.
		if (isEmpty())
			return null;
		
		// Iterate the nodes of the stack and add them to a list to iterate
		ArrayList<QueueNode<K, V>> list = new ArrayList<QueueNode<K, V>>();
		QueueNode<K, V> tmp = _firstNode;
		
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
	
	public static void main(String[] args)
	{
		Queue<Integer, Integer> queue = new Queue<Integer, Integer>(true);
		
		// Insertion usage
		for (int i = 0; i < 50; i++)
		{
			queue.insert(new QueueNode<Integer, Integer>(i, i));
		}
		// Or
		for (int i = 50; i < 100; i++)
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