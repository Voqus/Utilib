package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InsertionSort<T extends Comparable<T>>
{
	private T[] _array;
	private int _size;
	private int _index = 0;
	
	@SuppressWarnings("unchecked")
	public InsertionSort(final Class<T> cls, final int size)
	{
		_array 	= (T[]) Array.newInstance(cls, size);
		_size 	= size;
		
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
		_array 	= Arrays.copyOf(array, array.length);
		_size 	= array.length;
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
	 * Sorts the array using insertion sort.
	 */
	public void sort()
	{
		int j = 0;
		for (int i = 1; i < _size; i++)
		{
			j = i;
			while (j > 0 && _array[j - 1].compareTo(_array[j]) > 0)
			{
				swap(_array, j, j - 1);
				j = j - 1;
			}
		}
	}
	
	/**
	 * Swaps the two elements in the array provided.
	 * 
	 * @param array
	 * @param index
	 * @param index2
	 */
	private void swap(final T[] array, final int index, final int index2)
	{
		T temp 			= array[index];
		array[index] 	= array[index2];
		array[index2] 	= temp;
	}
	
	/**
	 * Sorts the array provided using insertion sort.
	 * 
	 * @param array
	 */
	public static <T extends Comparable<T>> void sort(final T[] array)
	{
		int j = 0;
		for (int i = 1; i < array.length; i++)
		{
			j = i;
			while (j > 0 && array[j - 1].compareTo(array[j]) > 0)
			{
				T temp 			= array[j];
				array[j] 		= array[j - 1];
				array[j - 1] 	= temp;
				
				j = j - 1;
			}
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
		InsertionSort<Integer> inSort = new InsertionSort<Integer>(Integer.class, 5);
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		inSort.add(new Integer(4));
		inSort.add(new Integer(3));
		inSort.add(new Integer(2));
		inSort.add(new Integer(1));
		inSort.add(new Integer(0));
		
		// Sort array & print
		inSort.sort();
		inSort.print();
		
		// Or provide your own array : sort & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		inSort.setArray(arr);
		inSort.sort();
		inSort.print();
		
		// Static sort array & print
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		InsertionSort.sort(arr);
		
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
	}
}