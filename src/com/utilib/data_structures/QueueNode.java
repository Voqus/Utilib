package com.utilib.data_structures;

public class QueueNode<K, V>
{
	private QueueNode<K, V> _previous;
	private K _key;
	private V _value;
	
	public QueueNode(final K key, final V value)
	{
		setKey(key);
		setValue(value);
		
		_previous = null;
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
	
	public void setPrevious(final QueueNode<K, V> node)
	{
		_previous = node;
	}
	
	public QueueNode<K, V> getPrevious()
	{
		return _previous;
	}
	
	@Override
	public String toString()
	{
		return "Key " + _key + " Value: " + _value + " Previous: " + System.identityHashCode(_previous);
		
	}
}
