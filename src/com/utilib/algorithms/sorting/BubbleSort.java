package com.utilib.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class BubbleSort<T extends Comparable<T>> implements Comparator<T>
{
	private T[] _array;
	private int _size;
	private int _index = 0;
	
	@SuppressWarnings("unchecked")
	public BubbleSort(final Class<T> cls, final int size)
	{
		_array = (T[]) Array.newInstance(cls, size);
		_size = size;
		
		// Initialize array
		for (int i = 0; i < size; i++)
			_array[i] = null;
	}
	
	/**
	 * Sets the array of BubbleSort class to the one provided.
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
	 * Sorts the array using bubble sort.
	 */
	public void sort()
	{
		if (_size - 1 <= 1 || _array[0] == null)
			return;
		
		for (int j = 1; j < _size; j++)
		{
			for (int i = 0; i < _size - j; i++)
			{
				if (compare(_array[i], _array[i + 1]) > 0)
					swap(_array, i, i + 1);
			}
		}
	}
	
	/**
	 * Sorts the array using bubble sort.
	 * 
	 * @param array
	 * @return T[]
	 */
	public static <T extends Comparable<T>> T[] sort(T[] array)
	{
		if (array.length - 1 <= 1 || array[0] == null)
			return null;
		
		int size = array.length;
		for (int j = 1; j < size; j++)
		{
			for (int i = 0; i < size - j; i++)
			{
				if ((array[i].compareTo(array[i + 1]) > 0))
				{
					T temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
				}
			}
		}
		
		return array;
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
	 * Prints the array.
	 */
	public void print()
	{
		for (int i = 0; i < _size; i++)
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
		BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>(Integer.class, 5);
		
		// Insertion usage
		System.out.println("---- Adding one by one in the array ----");
		bubbleSort.add(new Integer(2));
		bubbleSort.add(new Integer(3));
		bubbleSort.add(new Integer(4));
		bubbleSort.add(new Integer(1));
		bubbleSort.add(new Integer(0));
		
		bubbleSort.sort();
		bubbleSort.print();
		
		// Or set your own array : sort & print
		System.out.println("---- Passing the array to the object to sort ----");
		Integer[] arr = new Integer[] { 0, 5, 5, 40, 3, 4, 92, 34, 55, 22, 11 };
		bubbleSort.setArray(arr);
		bubbleSort.sort();
		bubbleSort.print();
		
		// Example with static sort usage
		System.out.println("---- Sorting the array the static way ----");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		BubbleSort.sort(arr);
		
		// Print the array
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		
	}
}
