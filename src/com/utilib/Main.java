package com.utilib;

import java.util.Random;

import com.utilib.algorithms.sorting.BubbleSort;
import com.utilib.algorithms.sorting.QuickSort;
import com.utilib.data_structures.LinkedList;
import com.utilib.data_structures.List;
import com.utilib.data_structures.Queue;
import com.utilib.data_structures.Stack;
import com.utilib.util.Timer;

public class Main
{
	public static void main(String[] args)
	{
		Timer.calculateExecutionTime(() -> queueInsertionTest(500000, true));
		Timer.calculateExecutionTime(() -> stackInsertionTest(500000, true));
		Timer.calculateExecutionTime(() -> listInsertionTest(500000, true));
		Timer.calculateExecutionTime(() -> linkedListInsertionTest(500000, true));
		
		
		// Initialize a random array
		int N = 100000;			// array capacity
		int numRange = 1000;	// range for random number generation
		
		Integer[] array = new Integer[N];
		for (int i = 0; i < N; i++)
			array[i] = new Random().nextInt(numRange);
		
		// Sorting algorithms
		System.out.println("\n---- Sorting algorithms ----");
		Timer.calculateExecutionTime(() -> bubbleSortTest(array, N));
		Timer.calculateExecutionTime(() -> quickSortTest(array, N));
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
	
	private static <T> BubbleSort<?> bubbleSortTest(final T[] array, final int insertionNum)
	{
		BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>(Integer.class, insertionNum);

		bubbleSort.setArray((Integer[]) array);
		bubbleSort.sort();
		return bubbleSort;
	}
	
	private static <T> QuickSort<?> quickSortTest(final T[] array, final int insertionNum)
	{
		QuickSort<Integer> qSort = new QuickSort<Integer>(Integer.class, insertionNum);

		qSort.setArray((Integer[]) array);
		qSort.sort(0, array.length - 1);
		return qSort;
	}
}
