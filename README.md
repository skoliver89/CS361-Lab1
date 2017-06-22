# CS361-Lab1
Algorithms Lab 1

What to do <br />
1.	Read your test data from the data file provide into an array of integers. The file contains 10,000,000 integers, one line per integer. The sum of these integers is 49,999,995,000,000. Make sure you are using this information to verify the accuracy of your input routine.
2.	Write a recursive method auxMergeSort that takes three parameters: the array, the startIndex, and the endIndex and sort the elements between the startIndex and endIndex using Merge Sort. Consider code the Merge part in a separate method that does not need to be recursive. Now, if you are copying code from the internet, please study the code so you understand the code. Also give the credit where the credit is due. 
3.	Write a recursive method auxQuickSort that takes three parameters: the array, the startIndex, and the endIndex and sort the elements between the startIndex and endIndex using Quick Sort with the pivot to be the average of the values at startIndex, endIndex, and the middle element between startIndex and endIndex. Consider code the split part in a separate method that does not need to be recursive. Again, if you are copying code from the internet, please study the code so you understand the code. Also give the credit where the credit is due. 
4.	Write a recursive method flgIsSorted to check if a given array (provided as a parameter) is sorted in increasing order. The method return true if and only if the array is sorted in increasing order. Hint, when the array has only one element, it is sorted. Your initial method can only take one parameter – the array. That method can call another auxiliary method that takes other parameters. 
5.	Looking into System.nanoTime(); to time the number of nanoseconds needed to perform the above four methods. 
6.	Now change your code so that, rather than perform all these steps on the 10 million integers, starts with 1,000 and increases at 10x until it to read more than 10 million numbers. Run your code 3 times, record the execution time in milliseconds for each run on each size, enter the milliseconds reading into an Excel spreadsheet, calculate the average execution time in milliseconds for each run on each size and display your results in both a table and as a line chart.
7.	Write a half to one page report to explain your execution time observation.

NOTE:: Due to stackoverflow error issues the class was instructed to split the flgIsSorted() function between recursive and non-recusive methodologies.
