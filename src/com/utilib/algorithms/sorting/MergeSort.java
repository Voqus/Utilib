package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort<T extends Comparable<T>>
{
	private T[] 		_array;
	private Class<T> 	_cls;
	private int 		_size;
	private int 		_index = 0;
	
	@SuppressWarnings("unchecked")
	public MergeSort(final Class<T> cls, final int size)
	{
		_array = (T[]) Array.newInstance(cls, size);
		_cls = cls;
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
	 * Sorts the entire array provided from start to finish.
	 */
	public void sort()
	{
		sort(0, _size - 1);
	}
	
	/**
	 * Sorts the array using merge sort.
	 * 
	 * @param left
	 * @param right
	 */
	public void sort(int left, int right)
	{
		if (_array.length - 1 <= 1 || _array[0] == null)
			return;
		
		if (left < right)
		{
			int m = left + (right - left) / 2;
			
			sort(left, m);
			sort(m + 1, right);
			
			merge(left, m, right);
		}
	}
	
	/**
	 * Merges the two sub-arrays to the main array.
	 * 
	 * @param left
	 * @param middle
	 * @param right
	 */
	@SuppressWarnings("unchecked")
	private void merge(int left, int middle, int right)
	{
		int leftSize = middle - left + 1;
		int rightSize = right - middle;
		
		T leftArray[] = (T[]) Array.newInstance(_cls, leftSize);
		T rightArray[] = (T[]) Array.newInstance(_cls, rightSize);
		
		// Initialize the sub arrays
		int i, j;
		for (i = 0; i < leftSize; ++i)
			leftArray[i] = _array[left + i];
		for (j = 0; j < rightSize; ++j)
			rightArray[j] = _array[middle + 1 + j];
		
		// Re-initialize indexes for left-right and main array.
		i = 0;
		j = 0;
		int k = left;
		while (i < leftSize && j < rightSize)
		{
			// if left array is <= the right array
			if (leftArray[i].compareTo(rightArray[j]) < 0 || leftArray[i].compareTo(rightArray[j]) == 0)
				_array[k] = leftArray[i++];
			else
				_array[k] = rightArray[j++];
			k++;
		}
		
		// If there are any slots left in either sub-array copy them to main
		// array.
		while (i < leftSize)
			_array[k++] = leftArray[i++];
		
		while (j < rightSize)
			_array[k++] = rightArray[j++];
	}
	
	/**
	 * Sorts the array using merge sort.
	 * 
	 * @param cls
	 * @param array
	 * @param left
	 * @param right
	 */
	public static <T extends Comparable<T>> T[] sort(final Class<T> cls, T[] array, int left, int right)
	{
		if (array.length - 1 <= 1 || array[0] == null)
			return null;
		
		if (left < right)
		{
			int m = left + (right - left) / 2;
			
			sort(cls, array, left, m);
			sort(cls, array, m + 1, right);
			
			merge(cls, array, left, m, right);
		}
		
		return array;
	}
	
	/**
	 * Merges the two sub-arrays to the main array.
	 * 
	 * @param cls
	 * @param array
	 * @param left
	 * @param middle
	 * @param right
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> void merge(final Class<T> cls, T[] array, int left, int middle, int right)
	{
		int leftSize = middle - left + 1;
		int rightSize = right - middle;
		
		T leftArray[] = (T[]) Array.newInstance(cls, leftSize);
		T rightArray[] = (T[]) Array.newInstance(cls, rightSize);
		
		// Initialize the sub arrays
		int i, j;
		for (i = 0; i < leftSize; ++i)
			leftArray[i] = array[left + i];
		for (j = 0; j < rightSize; ++j)
			rightArray[j] = array[middle + 1 + j];
		
		// Re-initialize indexes for left-right and main array.
		i = 0;
		j = 0;
		int k = left;
		while (i < leftSize && j < rightSize)
		{
			// if left array is <= the right array
			if (leftArray[i].compareTo(rightArray[j]) < 0 || leftArray[i].compareTo(rightArray[j]) == 0)
				array[k] = leftArray[i++];
			else
				array[k] = rightArray[j++];
			k++;
		}
		
		// If there are any slots left in either sub-array copy them to main
		// array.
		while (i < leftSize)
			array[k++] = leftArray[i++];
		
		while (j < rightSize)
			array[k++] = rightArray[j++];
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
	
	public static void main(String args[])
	{
		MergeSort<Integer> mSort = new MergeSort<Integer>(Integer.class, 5);
		
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		mSort.add(new Integer(4));
		mSort.add(new Integer(3));
		mSort.add(new Integer(2));
		mSort.add(new Integer(1));
		mSort.add(new Integer(0));
		
		// Sort array & print
		mSort.sort();
		mSort.print();
		
		// Or provide your own array : sort & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		mSort.setArray(arr);
		mSort.sort();
		mSort.print();
		
		// Static sort array & print
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		MergeSort.sort(Integer.class, arr, 0, arr.length - 1);
		
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
