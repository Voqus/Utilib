package com.utilib.data_structures;

public class Node<K, V>
{
	private Node<K, V> 	_next;
	private Node<K, V> 	_previous;
	private V 			_value;
	private K 			_key;

	public Node(final K key, final V value)
	{
		_key 	= key;
		_value	= value;
	}

	public Node(final V value)
	{
		_value = value;
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

	public Node<K, V> getNext()
	{
		return _next;
	}

	public void setNext(final Node<K, V> next)
	{
		_next = next;
	}

	public Node<K, V> getPrevious()
	{
		return _previous;
	}

	public void setPrevious(final Node<K, V> previous)
	{
		_previous = previous;
	}

	@Override
	public String toString()
	{
		return "Key: " + _key + " Value: " + _value;
	}
}
