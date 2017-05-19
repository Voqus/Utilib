package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.*;
import java.util.TreeMap;

public class CountingSort<T extends Comparable<T>>
{
	private T[]	_array;
	private int	_size;
	private int	_index	= 0;
	
	@SuppressWarnings("unchecked")
	public CountingSort(final Class<T> cls, final int size)
	{
		_array = (T[]) Array.newInstance(cls, size);
		_size = size;
		
		// Initialize array
		for (int i = 0; i < size; i++)
			_array[i] = null;
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
	 * Original solution:
	 * However I cannot get the entry key of a generic class in java like this:
	 *  [count[_array[i]] += 1;]
	 *  
	public void sort()
	{
		T[] output = Arrays.copyOf(_array, _array.length);
		
		int[] count = new int[_size];
		
		for (int i = 0; i < _size; i++)
			count[_array[i]] += 1;
		
		int total = 0;
		int oldcount;
		for (int i = 0; i < _size; i++)
		{
			oldcount = count[i];
			count[i] = total;
			total += oldcount;
		}
		
		for (int i = 0; i < _size; i++)
		{
			_array[count[_array[i]]] = output[i];
			count[_array[i]] -= 1;
		}
	}*/
	
	/**
	 * Sorts the array using counting sort.
	 * 
	 * Implementation by : Tagir Valeev
	 * source: http://stackoverflow.com/questions/33666488/how-to-make-generic-counting-sort-method
	 */
	public void sort()
	{
		Map<T, Integer> counts = new TreeMap<>(); // treemap sorts according to natural ordering
		
		// Iterate through and associate with the T element as keys
		for (T element : _array)
			counts.merge(element, 1, Integer::sum);
		
		int i = 0;
		for (Entry<T, Integer> entry : counts.entrySet())
		{
			for (int j = 0; j < entry.getValue(); j++)
				_array[i++] = entry.getKey();
		}
	}
	
	/**
	 * Sorts the array using counting sort.
	 * 
	 * Implementation by : Tagir Valeev
	 * source: http://stackoverflow.com/questions/33666488/how-to-make-generic-counting-sort-method
	 */
	public static <T> T[] sort( final T[] array)
	{
		Map<T, Integer> counts = new TreeMap<>();	// treemap sorts according to natural ordering
		
		// Iterate through and associate with the T element as keys
		for (T element : array)
			counts.merge(element, 1, Integer::sum);
		
		int i = 0;
		for (Map.Entry<T, Integer> entry : counts.entrySet())
		{
			for (int j = 0; j < entry.getValue(); j++)
				array[i++] = entry.getKey();
		}
		
		return array;
	}
	
	/**
	 * Prints the array.
	 */
	public void print()
	{
		for (int i = 0; i < _size; i++)
			System.out.print(_array[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		CountingSort<Integer> countingSort = new CountingSort<Integer>(Integer.class, 5);
		
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		countingSort.add(new Integer(4));
		countingSort.add(new Integer(3));
		countingSort.add(new Integer(2));
		countingSort.add(new Integer(1));
		countingSort.add(new Integer(0));
		
		// Sort array & print
		countingSort.sort();
		countingSort.print();
		
		// Or provide your own array : sort & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		countingSort.setArray(arr);
		countingSort.sort();
		countingSort.print();
		
		// Static sort array & print
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		arr = CountingSort.sort(arr);
		
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
	}
}
