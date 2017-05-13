package com.utilib.data_structures;

import java.lang.reflect.Array;

public class AdjacencyMatrix<T>
{
	private T[][] matrix;
	private int size;
	
	@SuppressWarnings("unchecked")
	public AdjacencyMatrix(final Class<T> cls, final int capacity, final T initVal)
	{
		matrix = (T[][]) Array.newInstance(cls, capacity, capacity);
		size = capacity;
		
		for (int y = 0; y < capacity; y++)
			for (int x = 0; x < capacity; x++)
				matrix[x][y] = initVal;
	}
	
	/**
	 * Sets a value on the adjacency matrix.
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setValue(final int x, final int y, final T value)
	{
		matrix[x][y] = value;
	}
	
	/**
	 * Gets a value from the adjacency matrix.
	 * 
	 * @param x
	 * @param y
	 * @return T
	 */
	public T getValue(final int x, final int y)
	{
		return matrix[x][y];
	}
	
	/**
	 * Prints the adjacency matrix.
	 */
	public void printMatrix()
	{
		for (int y = 0; y < size; y++)
		{
			for (int x = 0; x < size; x++)
			{
				System.out.print("[ " + matrix[x][y] + " ]");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		AdjacencyMatrix<Integer> adj = new AdjacencyMatrix<Integer>(Integer.class, 5, 0);
		adj.setValue(0, 0, 5);
		adj.setValue(1, 1, 10);
		adj.setValue(2, 2, 15);
		adj.setValue(3, 3, 20);
		adj.setValue(4, 4, 25);
		
		adj.printMatrix();
	}
}
