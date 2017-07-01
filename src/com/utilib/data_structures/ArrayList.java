package com.utilib.data_structures;

import java.util.Arrays;

public class ArrayList<T extends Comparable<T>>
{
	// using the TArray from data_structures package.
	private TArray<T> arrayObj;
	
	public ArrayList(final Class<T> cls, final int size)
	{
		arrayObj = new TArray<T>(cls, size);
	}
	
	/**
	 * Adds the element into the generic array.
	 * 
	 * @param element
	 * @return true/false if element was added in the array or not.
	 */
	public boolean add(final T element)
	{
		if (arrayObj.getSize() == arrayObj._array.length)
		{
			arrayObj._array = Arrays.copyOf(arrayObj._array, arrayObj._array.length * 2);
		}
		
		return arrayObj.add(element);
	}
	
	/**
	 * Removes the element provided from the generic array.
	 * 
	 * @param element
	 * @return index of the element removed.
	 */
	public int remove(final T element)
	{
		return arrayObj.remove(element);
	}
	
	/**
	 * @return the number of the elements inserted in the generic array.
	 */
	public int getSize()
	{
		return arrayObj.getSize();
	}
	
	/**
	 * @return the total size of the generic array.
	 */
	public int getTotalSize()
	{
		return arrayObj._array.length;
	}
	
	/**
	 * Checks if the generic array is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return arrayObj.isEmpty();
	}
	
	/**
	 * Prints the array.
	 */
	public void print()
	{
		arrayObj.print();
	}
	
	public static void main(String[] args)
	{
		ArrayList<Integer> arrList = new ArrayList<Integer>(Integer.class, 5);
		
		// Adding 5 elements to reach the cap of the array
		arrList.add(1);
		arrList.add(2);
		
		// Removing and re-adding the number: searches the array and fills in
		// the first null pointer it finds.
		System.out.println("-- Before removal of integer 2");
		arrList.print();
		arrList.remove(2);
		arrList.print();
		System.out.println("-- After removal of integer 2 \n");
		
		// Continuing on number insertions
		arrList.add(2);
		arrList.add(3);
		arrList.add(4);
		arrList.add(5);
		arrList.print();
		System.out.println("Size: " + arrList.getSize() + "\n");
		
		// Adding the 6th element, that would usually end up in array out of
		// bounds exception.
		// However the length of the array doubles, in order to fit the new
		// element.
		arrList.add(6);
		
		System.out.println("-- After adding the 6th element");
		arrList.print();
		System.out.println("Size: " + arrList.getSize() + "\n");
		
		// Adding 50 more numbers in the array.
		for (int i = 0; i < 50; i++)
			arrList.add(i);
		
		// The array size is now 56 out of 80.
		arrList.print();
		System.out.println("Size: " + arrList.getSize() + " / " + arrList.getTotalSize());
	}
	
}
