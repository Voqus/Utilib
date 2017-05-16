package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectionSort<T extends Comparable<T>>
{
	private T[]	_array;
	private int	_size;
	private int	_index	= 0;
	
	@SuppressWarnings("unchecked")
	public SelectionSort(final Class<T> cls, final int size)
	{
		_array = (T[]) Array.newInstance(cls, size);
		_size = size;
		
		// Initialize the array
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
	 * Sorts the array using selection sort.
	 */
	public void sort()
	{
		if (_array.length - 1 <= 1 || _array[0] == null)
			return;
		
		for (int j = 0; j < _size - 1; j++)
		{
			int min = j;
			
			for (int i = j + 1; i < _size; i++)
			{
				if (_array[i].compareTo(_array[min]) < 0)
					min = i;
			}
			if (min != j)
				swap(_array, j, min);
		}
	}
	
	/**
	 * Swaps two elements in the array provided.
	 * 
	 * @param array
	 * @param index
	 * @param index2
	 */
	private void swap(final T[] array, final int index, final int index2)
	{
		T temp = array[index];
		array[index] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * Sorts the array using selection sort.
	 * 
	 * @param array
	 */
	public static <T extends Comparable<T>> T[] sort(final T[] array)
	{
		if (array.length - 1 <= 1 || array[0] == null)
			return null;
		
		int size = array.length;
		for (int j = 0; j < size - 1; j++)
		{
			int min = j;
			
			for (int i = j + 1; i < size; i++)
			{
				if (array[i].compareTo(array[min]) < 0)
					min = i;
			}
			if (min != j)
			{
				T temp = array[j];
				array[j] = array[min];
				array[min] = temp;
			}
		}
		
		return array;
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
		SelectionSort<Integer> sSort = new SelectionSort<Integer>(Integer.class, 5);
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		sSort.add(new Integer(4));
		sSort.add(new Integer(3));
		sSort.add(new Integer(2));
		sSort.add(new Integer(1));
		sSort.add(new Integer(0));
		
		// Sort array & print
		sSort.sort();
		sSort.print();
		
		// Or provide your own array : sort & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		sSort.setArray(arr);
		sSort.sort();
		sSort.print();
		
		// Static sort array & print
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		SelectionSort.sort(arr);
		
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
	}
	
}
