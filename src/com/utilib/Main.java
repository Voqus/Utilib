package com.utilib;

import com.utilib.data_structures.Node;
import com.utilib.data_structures.Queue;
import com.utilib.util.Timer;

public class Main
{
	public static void main(String[] args)
	{
		Timer.calculateExecutionTime(() -> queueInsertionTest(500000));
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
}
