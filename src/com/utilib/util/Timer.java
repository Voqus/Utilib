package com.utilib.util;

import java.util.concurrent.Callable;

public class Timer
{
	public Timer()
	{}

	/**
	 * Calculates the total time taken for the {@code method} to be executed.
	 * 
	 * @param method
	 * @return
	 */
	public static <T> T calculateExecutionTime(Callable<T> method)
	{
		T methodTask = null;
		try
		{
			long timeStarted = System.nanoTime();
			methodTask 		 = method.call();
			long timeEnded	 = System.nanoTime();
			double totalTime = (double)(timeEnded - timeStarted) / 1000000000.0;
			System.out.println("Total time for execution of method: " + totalTime + " seconds.");
		}
		catch(Exception e)
		{
			System.err.println("Error calculating time: " + e.getMessage());
		}

		return methodTask;
	}
}
