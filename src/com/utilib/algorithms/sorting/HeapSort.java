package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapSort<T extends Comparable<T>>
{
	private T[]	_array;
	private int	_size;
	private int	_index	= 0;
	
	@SuppressWarnings("unchecked")
	public HeapSort(final Class<T> cls, final int size)
	{
		_array = (T[]) Array.newInstance(cls, size);
		_size = size;
		
		// Initialize array
		for (int i = 0; i < size; i++)
			_array[i] = null;
	}
	
	/**
	 * Sets the array of QuickSort class to the one provided.
	 * 
	 * @param array
	 */
	public void setArray(final T[] array)
	{
		_array = Arrays.copyOf(array, array.length);
		_size = array.length;
	}
	
	/**
	 * Adds an element on the array.<br/>
	 * <b>Warning!:</b><br/>
	 * Do not combine {@code setArray} method with this method.
	 * 
	 * @param element
	 */
	public void add(final T element)
	{
		_array[_index++] = element;
	}
	
	/**
	 * Sorts the array using heap sort.
	 */
	public void sort()
	{
		if (_array.length - 1 <= 1 || _array[0] == null)
			return;
		
		T temp;				// temporary item
		int n = _size;		// heap counter
		int parent = n / 2; // parent formula
		int current;		// heap current item index
		int leftChild;		// leftchild pointer
		
		while (true)
		{
			if (parent > 0)
				temp = _array[--parent];
			else
			{
				n--;
				if (n == 0)	// If heap size is 0, means the heap is empty.
					return;
				
				temp = _array[n];
				_array[n] = _array[0];
			}
			
			current = parent;
			leftChild = current * 2 + 1; // left child formula
			
			while (leftChild < n)
			{
				if (leftChild + 1 < n && _array[leftChild + 1].compareTo(_array[leftChild]) > 0)
					leftChild++;
				else if (_array[leftChild].compareTo(temp) > 0)
				{
					_array[current] = _array[leftChild];
					current = leftChild;
					leftChild = current * 2 + 1;
				}
				else
					break;
			}
			
			_array[current] = temp;
		}
	}
	
	/**
	 * Sorts the array using heap sort.
	 * @param array
	 * @return sorted T[] array
	 */
	public static <T extends Comparable<T>> T[] sort(final T[] array)
	{
		if (array.length - 1 <= 1 || array[0] == null)
			return array;
		
		T temp;					// temporary item
		int n = array.length;	// heap counter
		int parent = n / 2; 	// parent formula
		int current;			// heap current item index
		int leftChild;			// leftchild pointer
		
		while (true)
		{
			if (parent > 0)
				temp = array[--parent];
			else
			{
				n--;
				if (n == 0) // If heap size is 0, means the heap is empty.
					return array;
				
				temp = array[n];
				array[n] = array[0];
			}
			
			current = parent;
			leftChild = current * 2 + 1; // left child formula
			
			while (leftChild < n)
			{
				if (leftChild + 1 < n && array[leftChild + 1].compareTo(array[leftChild]) > 0)
					leftChild++;
				else if (array[leftChild].compareTo(temp) > 0)
				{
					array[current] = array[leftChild];
					current = leftChild;
					leftChild = current * 2 + 1;
				}
				else
					break;
			}
			
			array[current] = temp;
		}
	}
	
	/**
	 * Prints the array.
	 */
	public void print()
	{
		for (int i = 0; i < _size; ++i)
			System.out.print(_array[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		HeapSort<Integer> heapSort = new HeapSort<Integer>(Integer.class, 5);
		
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		heapSort.add(new Integer(4));
		heapSort.add(new Integer(3));
		heapSort.add(new Integer(2));
		heapSort.add(new Integer(1));
		heapSort.add(new Integer(0));
		
		// Sort array & print
		heapSort.sort();
		heapSort.print();
		
		// Or provide your own array : sort & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		heapSort.setArray(arr);
		heapSort.sort();
		heapSort.print();
		
		// Static sort array & print
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		HeapSort.sort(arr);
		
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
	}
}
