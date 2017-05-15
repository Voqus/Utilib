package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class QuickSort<T extends Comparable<T>> implements Comparator<T>
{
	private T[] _array;
	private int _size;
	private int _index = 0;
	
	@SuppressWarnings("unchecked")
	public QuickSort(final Class<T> cls, final int size)
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
	 * Sorts the entire array provided from start to finish.
	 */
	public void sort()
	{
		sort(0, _size - 1);
	}
	
	/**
	 * Sorts the array using quick sort.
	 */
	public void sort(int low, int high)
	{
		if (_size - 1 <= 1 || _array[0] == null)
			return;
		
		if (low < high)
		{
			int partition = partition(low, high);
			
			sort(low, partition - 1);
			sort(partition + 1, high);
		}
	}
	
	/**
	 * This method, sets pivot and sorts array, lesser elements go left of
	 * pivot, greater elements go right.
	 * 
	 * @param low
	 * @param high
	 * @return int
	 */
	private int partition(int low, int high)
	{
		T pivot = _array[high];
		int i = (low - 1);
		
		for (int j = low; j <= high - 1; j++)
		{
			if (compare(_array[j], pivot) < 0 || compare(_array[j], pivot) == 0)
			{
				swap(_array, ++i, j);
			}
		}
		
		swap(_array, i + 1, high);
		return i + 1;
	}
	
	/**
	 * Swaps the two elements in the indexes in the array provided.
	 * 
	 * @param array
	 * @param index
	 * @param index2
	 */
	private void swap(T[] array, int index, int index2)
	{
		T temp = array[index];
		array[index] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * Sorts the array using quick sort.
	 * 
	 * @param array
	 * @return T[]
	 */
	public static <T extends Comparable<T>> T[] sort(T[] array, int low, int high)
	{
		if (array.length - 1 <= 1 || array[0] == null)
			return null;
		
		if (low < high)
		{
			int partition = partition(array, low, high);
			
			sort(array, low, partition - 1);
			sort(array, partition + 1, high);
		}
		return array;
	}
	
	/**
	 * This method, sets pivot and sorts array, lesser elements go left of
	 * pivot, greater elements go right.
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @return int
	 */
	private static <T extends Comparable<T>> int partition(T[] array, int low, int high)
	{
		T pivot = array[high];
		int i = (low - 1);
		
		for (int j = low; j <= high - 1; j++)
		{
			if (array[j].compareTo(pivot) < 0 || array[j].compareTo(pivot) == 0)
			{
				i++;
				
				T temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		
		T temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;
		return i + 1;
		
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
	
	@Override
	public int compare(T num1, T num2)
	{
		return (num1.compareTo(num2));
	}
	
	public static void main(String[] args)
	{
		QuickSort<Integer> qSort = new QuickSort<Integer>(Integer.class, 5);
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		qSort.add(new Integer(4));
		qSort.add(new Integer(3));
		qSort.add(new Integer(2));
		qSort.add(new Integer(1));
		qSort.add(new Integer(0));
		
		qSort.sort();
		qSort.print();
		
		// Or set your own array & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = new Integer[] { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		qSort.setArray(arr);
		qSort.sort();
		qSort.print();
		
		// Example with static sort usage
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
		QuickSort.sort(arr, 0, arr.length - 1);
		
		// Print the array
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
}
