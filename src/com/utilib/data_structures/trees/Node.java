package com.utilib.data_structures.trees;

import java.util.Comparator;

public class Node<K extends Comparable<K>, V extends Comparable<V>> implements Comparator<Node<K, V>>
{
	public Node<K, V> _left;
	public Node<K, V> _right;
	private K _key;
	private V _value;
	
	public Node(final K key, final V value)
	{
		_key = key;
		_value = value;
		
		_left = null;
		_right = null;
	}
	
	public K getKey()
	{
		return _key;
	}
	
	public void setKey(final K key)
	{
		this._key = key;
	}
	
	public V getValue()
	{
		return _value;
	}
	
	public void setValue(final V value)
	{
		_value = value;
	}
	
	@Override
	public String toString()
	{
		return "Key: " + _key + " Value: " + _value;
	}
	
	@Override
	public int compare(Node<K, V> nodeA, Node<K, V> nodeB)
	{
		return (nodeA.getKey().compareTo(nodeB.getKey()));
	}
}