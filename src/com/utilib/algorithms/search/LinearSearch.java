package com.utilib.algorithms.search;

import com.utilib.algorithms.sorting.QuickSort;

public class LinearSearch
{
	/**
	 * Searches for the element provided, using linear search approach.<br/>
	 * Time complexity: <b>O(N)</b>
	 * 
	 * @param array
	 * @param element
	 * @return the index of the element in the array. If not exists, returns -1.
	 */
	public static <T extends Comparable<T>> int search(final T[] array, final T element)
	{
		for(int i = 0; i < array.length; i++)
		{
			if(array[i].compareTo(element) == 0)
				return i;
		}
		
		return -1;
	}
	
	public static void main(String[] args)
	{
		// Build an array and initialize it
		Integer[] arr = new Integer[20];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = i + 1;
		
		// Sort the array
		QuickSort.sort(arr, 0, arr.length - 1);
		
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		// Search for the element given
		System.out.println("Found element on index: " + search(arr, 6));
		System.out.println("Found element on index: " + search(arr, 13));
		System.out.println("Found element on index: " + search(arr, -2));
	}
}
