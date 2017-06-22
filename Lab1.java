package Labs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Lab1 
{
	private String filePath = ".//lab1_data.txt";
	private long startTime = 0;
	private long endTime = 0;
	private long time = 0;
	
	/*
	 * Main Method for class:
	 * Running testing on sorting methods
	 * Used (currently) for metric gathering
	 */
	public static void main(String[] args)
	{
		int[] data = new int[10000000];
		Lab1 run = new Lab1();
		
		//------------------- Step 1 -------------------//
		run.startTime = System.nanoTime();
		run.getDataArray(data);
		run.testData(data);
		System.out.println("Pre-sort check: " + run.flgIsSorted(data)); //only works because doesn't get stack-overflow before discovers unsorted.
		run.endTime = System.nanoTime();
		run.time = (run.endTime - run.startTime)/1000000;
		System.out.println("Time to execute Step 1 (milliseconds):" + run.time);
		System.out.println("----------------------------");
		System.out.println("START STEP 6 - Sorting metrics");
		//------------------- Step 6 -------------------//
		int size = 1000;
		int i = 0;
		for (;size <= 10000000; size *= 10)
		{
			int[] data2 = new int[size];
			data2 = Arrays.copyOfRange(data, 0, size);
			run.startTime = System.nanoTime();
			run.auxMergeSort(data2, 0, data2.length);
		    System.out.println("Merge Sort: size " + size + " is Sorted: " + run.flgIsSorted(Arrays.copyOfRange(data2, 0, i+1)));
			run.endTime = System.nanoTime();
			run.time = (run.endTime - run.startTime)/1000000;
			System.out.println("Merge Sort: Time to execute Step 6 size " + size + " (milliseconds):" + run.time);
			System.out.println("----------------------------");
		}
		size = 1000;
		for (;size <= 10000000; size *= 10)
		{
			int[] data2 = new int[size];
			data2 = Arrays.copyOfRange(data, 0, size);
			run.startTime = System.nanoTime();
			run.auxQuickSort(data2, 0, size-1);
			System.out.println("Quick Sort: size " + size + " is Sorted: " + run.flgIsSorted(data2));
			run.endTime = System.nanoTime();
			run.time = (run.endTime - run.startTime)/1000000;
			System.out.println("Quick Sort: Time to execute Step 6 size " + size + " (milliseconds):" + run.time);
			System.out.println("----------------------------");
		}
	}

	
	/*
	 * Try to read data from provided text file into an array (data).
	 * Catch if the file does not exist and/or cannot be opened.
	 */
	public void getDataArray (int[] array) 
	{
		try 
		{ 
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null)
			{
				array[i] = Integer.parseInt(line);
				i++;
			}
			reader.close();
			System.out.println("Data read into the array!");
		}
		catch(IOException e)
		{
			System.out.println("Cannot find file: " + filePath);
		}
		
	}
	
	/* 
	 * Check to see if the array data is correct for the lab's provided data.
	 * sum should equal 49999995000000.
	 */
	public void testData (int [] array)
	{
		long sum = 0;
		long check = 49999995000000L;
		for(int num : array) 
		{
			sum += num;
		}
		if (sum == check)
		{
			System.out.println("Success: Array data verified.");
		}
		else
		{
			System.out.println("Error: Array data faulty.");
		}
	}
	
	/*
	 * Sort the elements between the startIndex and endIndex using Merge Sort.
	 * Code altered from: http://www.sanfoundry.com/java-program-implement-merge-sort/
	 * @param array The given array to be sorted
	 * @param startIndex Index to start sorting at
	 * @param endIndex Index to end sorting at
	 */
	public void auxMergeSort (int[] array, int startIndex, int endIndex)
	{
		int n = endIndex - startIndex;
		if (n <= 1)
		{
			return;
		}
		int midIndex = startIndex + n/2;
		auxMergeSort (array, startIndex, midIndex);
		auxMergeSort (array, midIndex, endIndex);
		merge(array, n, startIndex, midIndex, endIndex);
	}
	private void merge (int[] array, int n, int startIndex, int midIndex, int endIndex)
	{
		int[] temp = new int[n];
		int i = startIndex;
		int j = midIndex;
		for (int k = 0; k < n; k++)
		{
			if (i == midIndex)
			{
				temp[k] = array[j++];
			}
			else if (j == endIndex)
			{
				temp[k] = array[i++];
			}
			else if (array[j] < array[i])
			{
				temp[k] = array[j++];
			}
			else
			{
				temp[k] = array[i++];
			}
		}
		for (int k = 0; k < n; k++)
		{
			array[startIndex + k] = temp[k];
		}
	}
	
	/*
	 * Sort the elements between the startIndex and endIndex using Quick Sort with 
	 * the pivot to be the average of the values at startIndex, endIndex, and the 
	 * middle element between startIndex and endIndex.
	 * @param array The given array to be sorted
	 * @param startIndex Index to start sorting at
	 * @param endIndex Index to end sorting at
	 */
	public void auxQuickSort (int[] array, int startIndex, int endIndex)
	{
		if (startIndex < endIndex)
		{
			int q = partition(array, startIndex, endIndex);
			auxQuickSort(array, startIndex, q-1);
			auxQuickSort(array, q+1, endIndex);
		}
	}
	private int partition (int[] array, int startIndex, int endIndex)
	{
		int mid = (startIndex + endIndex)/2;
		int pivot = (startIndex + mid + endIndex)/3; //Get pivot from average of startIndex, mid, and endIndex
		exchange(array, pivot, endIndex);	//Switch the pivot element with the last element to get pivot in place
		int x = array[endIndex];
		int i = startIndex - 1;
		for (int j = startIndex; j <= (endIndex -1); j++)
		{
			if (array[j] <= x)
			{
				i++;
				exchange(array, i, j);
			}
		}
		exchange(array, (i+1), endIndex);
		return i + 1;
	}
	public void exchange(int[] array, int i, int j)
	{
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;	
	}
	
	/*
	 * Check if a given array is sorted in increasing order. (recursive)
	 * STACKOVERFLOW on 100,000+ sized arrays (fix if time allows).
	 * @param array The given array to check. 
	 * @return true if and only if the array is sorted in increasing order.
	 */
	public boolean flgIsSorted (int[] array)
	{
		int n = array.length;
		if (n <= 5000)
		{
			return flgIsSortedRecursion(array, n);
		}
		else
		{
			if (flgIsSortedRecursion(Arrays.copyOfRange(array, 0, 5000), 5000) == true)
			{
				return flgIsSortedLooper(Arrays.copyOfRange(array, 4999, n));
			}
			return false;
		}
	}
	private boolean flgIsSortedRecursion (int[] array, int n)
	{
		if (n == 1)
		{
			return true;
		}
		else if (array[n-2] > array[n-1])
		{
			return false;
		}
		return flgIsSortedRecursion (array, n-1);
	}
	private boolean flgIsSortedLooper(int[] array)
	{
		int prevNum = 0;
		for (int num : array)
		{
			if (num < prevNum)
			{
				return false;
			}
			prevNum = num;
		}
		return true;
	}
}
