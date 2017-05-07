package com.utilib;

import com.utilib.data_structures.Node;
import com.utilib.data_structures.Queue;
import com.utilib.data_structures.Stack;
import com.utilib.util.Timer;

public class Main
{
	public static void main(String[] args)
	{
		Timer.calculateExecutionTime(() -> queueInsertionTest(500000));
		Timer.calculateExecutionTime(() -> stackInsertionTest(500000));
	}

	/**
	 * A queue insertion test based on the number of insertions given.
	 * 
	 * @param insertionsNum
	 * @return
	 */
	private static Queue<?, ?> queueInsertionTest(final int insertionsNum)
	{
		Queue<Integer, Integer> queue = new Queue<Integer, Integer>();

		for (int i = 0; i < insertionsNum; i++)
			queue.insert(new Node<Integer, Integer>(i, i));

		return queue;
	}
	
	/**
	 * A stack insertion test based on the number of insertions given.
	 * 
	 * @param insertionNum
	 * @return
	 */
	private static Stack<?, ?> stackInsertionTest(final int insertionNum)
	{
		Stack<Integer, Integer> stack = new Stack<Integer, Integer>();
		
		for (int i = 0; i < insertionNum; i++)
			stack.push(new Node<Integer, Integer>(i, i));
		
		return stack;
	}
}
