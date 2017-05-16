package com.utilib.algorithms.search;

import com.utilib.algorithms.sorting.QuickSort;

public class BinarySearch
{
	/**
	 * Searches for the element provided, using binary search.<br/>
	 * The array <b>must</b> be sorted.<br/><br/>
	 * Time complexity: <b>O(log(N))</b>
	 * 
	 * @param array
	 * @param element
	 * @return the index of the element in the array. If not exists, returns -1.
	 */
	public static <T extends Comparable<T>> int search(final T[] array, final T element)
	{
		int left = 0;
		int right = array.length - 1;
		int middle = 0;
		
		while (left <= right)
		{
			middle = left + (right - left) / 2;
			
			if (array[middle].compareTo(element) < 0)
				left = middle + 1;
			else if (array[middle].compareTo(element) > 0)
				right = middle - 1;
			else
			{
				return middle;
			}
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
