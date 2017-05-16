package com.utilib;

import java.util.Random;

import com.utilib.algorithms.sorting.BubbleSort;
import com.utilib.algorithms.sorting.InsertionSort;
import com.utilib.algorithms.sorting.MergeSort;
import com.utilib.algorithms.sorting.QuickSort;
import com.utilib.algorithms.sorting.SelectionSort;
import com.utilib.data_structures.LinkedList;
import com.utilib.data_structures.List;
import com.utilib.data_structures.Queue;
import com.utilib.data_structures.Stack;
import com.utilib.util.Timer;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("---- Data structures insertion----");
		System.out.print("Queue insertion:		");
		Timer.calculateExecutionTime(() -> queueInsertionTest(500000, true));
		System.out.print("Stack insertion:		");
		Timer.calculateExecutionTime(() -> stackInsertionTest(500000, true));
		System.out.print("Single-linked list insertion:	");
		Timer.calculateExecutionTime(() -> listInsertionTest(500000, true));
		System.out.print("Double-linked list insertion:	");
		Timer.calculateExecutionTime(() -> linkedListInsertionTest(500000, true));
		
		// Initialize a random array
		int N = 100000; 	 // array capacity
		int numRange = 1000; // range for random number generation
		
		Integer[] array = new Integer[N];
		for (int i = 0; i < N; i++)
			array[i] = new Random().nextInt(numRange);
		
		// Sorting algorithms
		System.out.println("\n---- Sorting algorithms ----");
		System.out.print("BubbleSort:	");
		Timer.calculateExecutionTime(() -> bubbleSortTest(array, N));
		System.out.print("QuickSort:	");
		Timer.calculateExecutionTime(() -> quickSortTest(array, N));
		System.out.print("MergeSort:	");
		Timer.calculateExecutionTime(() -> mergeSortTest(array, N));
		System.out.print("InsertionSort:	");
		Timer.calculateExecutionTime(() -> insertionSortTest(array, N));
		System.out.print("SelectionSort:	");
		Timer.calculateExecutionTime(() -> selectionSortTest(array, N));
	}
	
	/**
	 * A queue insertion test based on the number of insertions given.
	 * 
	 * @param insertionsNum
	 * @param allowDuplicates
	 * @return Queue
	 */
	private static Queue<?, ?> queueInsertionTest(final int insertionsNum, final boolean allowDuplicates)
	{
		Queue<Integer, Integer> queue = new Queue<Integer, Integer>(allowDuplicates);
		
		for (int i = 0; i < insertionsNum; i++)
			queue.insert(i, i);
		
		return queue;
	}
	
	/**
	 * A stack insertion test based on the number of insertions given.
	 * 
	 * @param insertionNum
	 * @param allowDuplicates
	 * @return Stack
	 */
	private static Stack<?, ?> stackInsertionTest(final int insertionNum, final boolean allowDuplicates)
	{
		Stack<Integer, Integer> stack = new Stack<Integer, Integer>(allowDuplicates);
		
		for (int i = 0; i < insertionNum; i++)
			stack.push(i, i);
		
		return stack;
	}
	
	/**
	 * A list insertion test based on the number of insertions given.
	 * 
	 * @param insertionNum
	 * @param allowDuplicates
	 * @return List
	 */
	private static List<?, ?> listInsertionTest(final int insertionNum, final boolean allowDuplicates)
	{
		List<Integer, Integer> list = new List<Integer, Integer>(allowDuplicates);
		
		for (int i = 0; i < insertionNum; i++)
			list.add(i, i);
		
		return list;
	}
	
	/**
	 * A linked list insertion test based on the number of the insertions given.
	 * 
	 * @param insertionNum
	 * @param allowDuplicates
	 * @return LinkedList
	 */
	private static LinkedList<?, ?> linkedListInsertionTest(final int insertionNum, final boolean allowDuplicates)
	{
		LinkedList<Integer, Integer> list = new LinkedList<Integer, Integer>(allowDuplicates);
		
		for (int i = 0; i < insertionNum; i++)
			list.add(i, i);
		
		return list;
	}
	
	/**
	 * A bubble sort sorting test based on the number of the insertions given.
	 * 
	 * @param array
	 * @param insertionNum
	 * @return BubbleSort
	 */
	private static <T> BubbleSort<?> bubbleSortTest(final T[] array, final int insertionNum)
	{
		BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>(Integer.class, insertionNum);
		
		bubbleSort.setArray((Integer[]) array);
		bubbleSort.sort();
		return bubbleSort;
	}
	
	/**
	 * A quick sort sorting test based on the number of the insertions given.
	 * 
	 * @param array
	 * @param insertionNum
	 * @return QuickSort
	 */
	private static <T> QuickSort<?> quickSortTest(final T[] array, final int insertionNum)
	{
		QuickSort<Integer> qSort = new QuickSort<Integer>(Integer.class, insertionNum);
		
		qSort.setArray((Integer[]) array);
		qSort.sort();
		return qSort;
	}
	
	/**
	 * A merge sort sorting test based on the number of the insertions given.
	 * 
	 * @param array
	 * @param insertionNum
	 * @return MergeSort
	 */
	private static <T> MergeSort<?> mergeSortTest(final T[] array, final int insertionNum)
	{
		MergeSort<Integer> mSort = new MergeSort<Integer>(Integer.class, insertionNum);
		mSort.setArray((Integer[]) array);
		mSort.sort();
		return mSort;
	}
	
	/**
	 * An insertion sort sorting test based on the number of the insertions given.
	 * 
	 * @param array
	 * @param insertionNum
	 * @return InsertionSort
	 */
	private static <T> InsertionSort<?> insertionSortTest(final T[] array, final int insertionNum)
	{
		InsertionSort<Integer> inSort = new InsertionSort<Integer>(Integer.class, insertionNum);
		inSort.setArray((Integer[]) array);
		inSort.sort();
		return inSort;
	}
	
	/**
	 * A selection sort sorting test based on the number of the insertions given.
	 * 
	 * @param array
	 * @param insertionNum
	 * @return SelectionSort
	 */
	private static <T> SelectionSort<?> selectionSortTest(final T[] array, final int insertionNum)
	{
		SelectionSort<Integer> sSort = new SelectionSort<Integer>(Integer.class, insertionNum);
		sSort.setArray((Integer[]) array);
		sSort.sort();
		return sSort;
	}
}
