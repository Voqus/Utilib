package com.utilib.data_structures;

import java.lang.reflect.Array;

import com.utilib.algorithms.search.BinarySearch;
import com.utilib.algorithms.search.LinearSearch;
import com.utilib.algorithms.sorting.QuickSort;

public class TArray<T extends Comparable<T>>
{
	private T[] _array;
	private int _index;
	
	@SuppressWarnings("unchecked")
	public TArray(final Class<T> cls, final int size)
	{
		_array = (T[]) Array.newInstance(cls, size);
		_index = 0;
		
		// Initialize the array.
		for (int i = 0; i < size; i++)
			_array[i] = null;
	}
	
	/**
	 * Adds the element into the array.
	 * 
	 * @param element
	 * @return true/false if it added to array or not.
	 */
	public boolean add(final T element)
	{
		// Search for null element in array, and replace that with the new
		// element.
		for (int i = 0; i < _array.length; i++)
		{
			if (_array[i] == null)
			{
				_array[i] = element;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Performs a binary search on the array to find the element provided.
	 * 
	 * @param element
	 * @return index of the element provided.
	 */
	public int binarySearch(final T element)
	{
		return BinarySearch.search(_array, element);
	}
	
	/**
	 * Performs a linear search on the array to find the element provided.
	 * 
	 * @param element
	 * @return index of the element provided.
	 */
	public int linearSearch(final T element)
	{
		return LinearSearch.search(_array, element);
	}
	
	/**
	 * @param index
	 * @return element at index provided.
	 */
	public T get(final int index)
	{
		return _array[index];
	}
	
	/**
	 * Removes the element provided from the array.
	 * 
	 * @param element
	 * @return index of the element removed.
	 */
	public int remove(final T element)
	{
		// Search for the element in the array.
		// If the array is sorted, use binary search instead.
		int index = linearSearch(element);
		
		// Not found
		if (index == -1)
			return -1;
		
		_array[index] = null;
		
		return index;
	}
	
	/**
	 * Checks if the array is empty.
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return (_index == 0);
	}
	
	/**
	 * Prints the array.
	 */
	public void print()
	{
		for (int i = 0; i < _array.length; i++)
			System.out.print(_array[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		TArray<Integer> arr = new TArray<Integer>(Integer.class, 5);
		// Insertion usage
		arr.add(9);
		arr.add(-2);
		arr.add(20);
		arr.add(-5);
		arr.add(5);
		
		// To use binary search, you must have the array sorted first.
		// To sort the array you can use the sorting algorithms in package
		// com.utilib.algorithms.sorting, For example:
		QuickSort.sort(arr._array, 0, arr._array.length - 1);
		arr.print();
		
		System.out.println("Found element[5] at index: " + arr.binarySearch(5));
		
		System.out.println("Removing element[-2] from array at index: " + arr.remove(-2));
		arr.print();
		
		System.out.println("Adding element[-2] back to the array: " + arr.add(-2));
		// Normally after this step, if its not the same element, you would need
		// to sort the array again.
		arr.print();
	}
}
