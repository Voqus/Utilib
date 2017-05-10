package com.utilib.data_structures;

public class ListNode<K,V>
{
	private ListNode<K, V> _next;
	private K _key;
	private V _value;
	
	public ListNode(final K key, final V value)
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
	
	public void setNext(final ListNode<K, V> node)
	{
		_next = node;
	}
	
	public ListNode<K, V> getNext()
	{
		return _next;
	}
	
	@Override
	public String toString()
	{
		return "Key " + _key + " Value: " + _value + " Next: " + System.identityHashCode(_next);
		
	}
}
