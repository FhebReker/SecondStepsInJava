/**
 ***********************************************
 * @Author : Fardin Abbassi
 * @Originally made : November 30, 2023
 * @Last Modified: December 22, 2023
 * @Description: Interface for either sorting an array, searching for a number in an array, 
 * 				randomly generating a new array, printing out the first 1000 elements of an array,
 * 				as well as a general report on searches and sorts.
 ***********************************************
 */

import java.util.*;

public class TreasureHunter {    
    static MoveAndFind searchSortLibrary = new MoveAndFind();
    static MoveAndFind searchSortTester = new MoveAndFind();
	public static void main(String[] args) {
        System.out.println("Welcome to the Array Sort and Finder! ");
        Scanner scan = new Scanner(System.in);
        int[] theArrayInQuestion = randomFillArray(100000);
        boolean done = false;
        int indexOfNum, numberToFind = -1;
        while(!done) {
            System.out.println("What would you like to do?");
        	searchSortLibrary.intArray = theArrayInQuestion;
        	System.out.println("a) Selection Sort\nb) Quick Sort\nc) Merge Sort\nd) Sequential Search"
        		+ "\ne) Binary Search (needs to be sorted first)\nf) Randomly generate a different array"
        		+ "\ng) Print out the first 1000 elements in the array"
        		+ "\nh) Print out a report on the speeds of each array\ni) Quit program");
	        char menuSelect = scan.nextLine().toLowerCase().charAt(0);
	        switch(menuSelect) {
		        case 'a': // Selection Sort
		        	searchSortLibrary.selectionSortInt();
		        	theArrayInQuestion = searchSortLibrary.intArray;
		        	break;
		        	
		        case 'b': // Quick Sort
		        	searchSortLibrary.quickSortInt(0, searchSortLibrary.intArray.length - 1);
		        	theArrayInQuestion = searchSortLibrary.intArray;
		        	break;
		        	
		        case 'c': // Merge Sort
		        	searchSortLibrary.MergeSortInt(0, searchSortLibrary.intArray.length - 1);
		        	theArrayInQuestion = searchSortLibrary.intArray;
		        	break;	 
		        	
		        case 'd': // Sequential Search
		        	try {
		        		System.out.println("What number would you like to find?");
		        		numberToFind = Integer.parseInt(scan.nextLine());
		        	}catch (Exception e) {
		        		System.out.println("Not a valid response. Returning to menu...");
		        		System.out.println();
		        		break;
		        	}
		        	indexOfNum = searchSortLibrary.sequentialSearch(numberToFind);
	        		if (indexOfNum != -1) {
	        			System.out.println(numberToFind + " was found at index " + indexOfNum);
	        			long timeTaken = searchTime(theArrayInQuestion, numberToFind, "Sequential");
	        			System.out.println("It took " + timeTaken + " nanoseconds to find the number");
	        		}
	        		else
	        			System.out.println("Couldn't find " + numberToFind + " in the array.");
		        	break;	  
		        	
		        case 'e': // Binary Search
		        	try {
		        		System.out.println("What number would you like to find?");
		        		numberToFind = Integer.parseInt(scan.nextLine());
		        	}catch (Exception e) {
		        		System.out.println("Not a valid response. Returning to menu...");
		        		System.out.println();
		        		break;
		        	}		        	
		        	indexOfNum = searchSortLibrary.binarySearch(0, searchSortLibrary.intArray.length - 1, numberToFind);
	        		if (indexOfNum != -1) {
	        			System.out.println(numberToFind + " was found at index " + indexOfNum);
	        			long timeTaken = searchTime(theArrayInQuestion, numberToFind, "Sequential");
	        			System.out.println("It took " + timeTaken + " nanoseconds to find the number");
	        		}
	        		else
	        			System.out.println("Couldn't find " + numberToFind + " in the array.");
		        	break;	
		        	
		        case 'f': // Randomly Filling Array
		        	theArrayInQuestion = randomFillArray(100000);
		        	break;
		        	
		        case 'g': // Printing Array in 10 Columns
		        	printOut(theArrayInQuestion);
		        	break;
		        	
		        case 'h': // Report of Analysis
		        	sortTimeReport(theArrayInQuestion);
		        	break;
		        	
		        case'i': // Quit program
		        	done = !done;
		        	
	        	default: // Retry if not a valid response
	        		break;
	        }
	        System.out.println();
        }
        
        scan.close();
    }  
	
	/*
	 * Method Name: printOut 
	 * Author: Fardin Abbassi
	 * Creation Date: December 19, 2023
	 * Modified Date: December 19 2023
	 * Description: prints out the first 1000 elements of an integer array into 10 columns
	 * @Parameters: int[] array
	 * @Return Value: random array of user defined size 
	 * Data Type: Integer[]
	 * Dependencies:
	 * Throws/Exceptions: n/a
	 */
	public static void printOut(int[] array) {
	    for (int i = 0; i < 100; i++) { // 100 rows
	        for (int j = 0; j < 10; j++) { // 10 columns
	            int index = (i*10) + j; 
	            if (index >= array.length) {
	                break;
	            }
	            String element = String.valueOf(array[index]);
	            System.out.print(element);
	            int padding = 7 - element.length();
	            for (int k = 0; k < padding; k++) {
	                System.out.print(" ");
	            }
	        }
	        System.out.println();
	    }
	}
	
	/*
	 * Method Name: randomFillArray 
	 * Author: Fardin Abbassi
	 * Creation Date: December 14, 2023
	 * Modified Date: December 19, 2023
	 * Description: generates an array with random numbers between 1 and 999 inclusive based on a user defined size
	 * @Parameters: int size: the length of the array
	 * @Return Value: random array of user defined size 
	 * Data Type: Integer[]
	 * Dependencies:
	 * Throws/Exceptions: n/a
	 */
	public static int[] randomFillArray(int size) {
		int[] randomArray = new int[size]; // random array
		for (int i = 0; i < size; i++) {
			randomArray[i] = (int) ((Math.random() * (1000 - 1)) + 1);
		} // end for
		return randomArray;
	} // end randomFillArray
	
	/*
	 * Method Name: getTime Author: Kyle McKay 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: gets current time in nanoseconds
	 * @Parameters: n/a
	 * @Return Value: current time 
	 * Data Type: long 
	 * Dependencies: nanoTime
	 * Throws/Exceptions: n/a
	 */
	public static long getTime() {
		long tick = System.nanoTime();
		return tick;
	} // end getTime
	
	/*
	 * Method Name: sortTime Author: Fardin Abbassi 
	 * Creation Date: November 28, 2023
	 * Modified Date: December 14, 2023
	 * Description: times how long it takes to sort an integer array using either selection, quick, or merge sort
	 * @Parameters: int[] array, String sortType
	 * @Return Value: time it takes for sort 
	 * Data Type: Long 
	 * Dependencies: getTime, bubbleSort 
	 * Throws/Exceptions: n/a
	 */
	private static long sortTime(int[] array, String sortType) {
		long totalTime; // total time
		long startTime = getTime(); // start time
		switch(sortType) { // Sorts based on which type of sort is required
		case "Selection":
			searchSortLibrary.selectionSortInt(); // Selection Sort: Int
			break;
			
		case "Quick":
			searchSortLibrary.quickSortInt(0, array.length - 1); // Quick Sort: Int
			break;
			
		case "Merge":
			searchSortLibrary.MergeSortInt(0, array.length - 1); // Merge Sort: Int
			break;
		}
		long endTime = getTime(); // end time
		totalTime = (endTime - startTime);
		return totalTime;
	}

	/*
	 * Method Name: searchTime Author: Fardin Abbassi 
	 * Creation Date: November 28, 2023
	 * Modified Date: December 14 2023
	 * Description: times how long it takes to find an item in an integer array using either sequential search or binary search
	 * @Parameters: int[] array, int searchNum, String searchType
	 * @Return Value: time it takes for search 
	 * Data Type: Long 
	 * Dependencies: getTime, findElement 
	 * Throws/Exceptions: n/a
	 */
	public static long searchTime(int[] array, int searchNum, String searchType) {
		long totalTime; // total time
		long startTime = getTime(); // start time
		switch(searchType) {
		case "Sequential":
			searchSortLibrary.sequentialSearch(searchNum); // Sequential Search
			break;
			
		case "Binary":
			searchSortLibrary.binarySearch(0, array.length - 1, searchNum); // Binary Search
			break;
		}
		long endTime = getTime(); // end time
		totalTime = (endTime - startTime);
		return totalTime;
	} // end sequentialTime

	/*
	 * Method Name: searchTime Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 22, 2023
	 * Description: creates a report on multiple sorts and searches
	 * @Parameters: int[] array
	 * @Return Value: time it takes for search 
	 * Data Type: Long 
	 * Dependencies: getTime, findElement 
	 * Throws/Exceptions: n/a
	 */
	private static void sortTimeReport(int[] intArray) {
		searchSortLibrary.runs = 0;
		System.out.println();		
		int[] selectIntArray1 = intArray;
		int[] selectIntArray2 = intArray;
		int[] selectIntArray3 = intArray;
		int[] quickIntArray1 = selectIntArray1;
		int[] quickIntArray2 = selectIntArray2;
		int[] quickIntArray3 = selectIntArray3;
		int[] mergeIntArray1 = quickIntArray1;
		int[] mergeIntArray2 = quickIntArray2;
		int[] mergeIntArray3 = quickIntArray3;
		
		
		int[] sequentialArray1 = intArray;
		int[] sequentialArray2 = intArray;
		int[] sequentialArray3 = intArray;
		int[] binaryArray1 = sequentialArray1;
		int[] binaryArray2 = sequentialArray2;
		int[] binaryArray3 = sequentialArray3;
	
		
		System.out.println("Total elements: " + intArray.length);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println();

		
		
		/**Sort Times**/
		System.out.println("Sort Times");
		System.out.println("======================================================================================");
		// Selection Sort
		System.out.println("Current Sort; Selection Sort");
		long selectSort1 = sortTime(selectIntArray1, "Selection");
		long selectSortRuns = searchSortLibrary.runs;
		searchSortLibrary.runs = 0;
		long selectSort2 = sortTime(selectIntArray2, "Selection");
		long selectSort3 = sortTime(selectIntArray3, "Selection");
		
		long selectSortAverage = (selectSort1 + selectSort2 + selectSort3)/3;
		System.out.println("1st Run Time: " + selectSort1);
		System.out.println("2nd Run Time: " + selectSort2);
		System.out.println("3rd Run Time: " + selectSort3);
		System.out.println("Average Run Time: " + selectSortAverage);
		System.out.println("Amount of runs: " + selectSortRuns);
		System.out.println();
		
		
		// Quick Sort
		System.out.println("Current Sort; Quick Sort");
		long quickSort1 = sortTime(quickIntArray1, "Quick");
		long  quickSortRuns = searchSortLibrary.runs;
		searchSortLibrary.runs = 0;
		long quickSort2 = sortTime(quickIntArray2, "Quick");	
		long quickSort3 = sortTime(quickIntArray3, "Quick");
		
		long quickSortAverage = (quickSort1 + quickSort2 + quickSort3)/3;
		System.out.println("1st Run Time: " + quickSort1);
		System.out.println("2nd Run Time: " + quickSort2);
		System.out.println("3rd Run Time: " + quickSort3);
		System.out.println("Average Run Time: " + quickSortAverage);
		System.out.println("Amount of runs: " + quickSortRuns);
		System.out.println();
		
		//Merge Sort
		System.out.println("Current Sort; Merge Sort");
		long mergeSort1 = sortTime(mergeIntArray1, "Merge");
		long  mergeSortRuns = searchSortLibrary.runs;
		searchSortLibrary.runs = 0;
		long mergeSort2 = sortTime(mergeIntArray2, "Merge");
		long mergeSort3 = sortTime(mergeIntArray3, "Merge");
		
		long mergeSortAverage = (mergeSort1 + mergeSort2 + mergeSort3)/3;
		System.out.println("1st Run Time: " + mergeSort1);
		System.out.println("2nd Run Time: " + mergeSort2);
		System.out.println("3rd Run Time: " + mergeSort3);
		System.out.println("Average Run Time: " + mergeSortAverage);
		System.out.println("Amount of runs: " + mergeSortRuns);
		System.out.println("======================================================================================");
		System.out.println();
		
		
		
		/**Sort Analysis**/
		System.out.println();
		System.out.println("Sort Analysis");
		System.out.println("======================================================================================");
		System.out.println("Comparing average runtimes, we get the following podium placement:");
		System.out.println("Merge sort is the fastest sort with an average run time of " + mergeSortAverage+".");
		System.out.println("Quick sort comes second with an average time of " + quickSortAverage + ".");
		System.out.println("Selection sort comes last with an average of " + selectSortAverage + ".");
		System.out.println();

		System.out.println("This podium placement is the same for the largest amount of runs executed.");
		System.out.println("Merge sort has the most runs with " + mergeSortRuns + " runs.");
		System.out.println("Quick sort is second with " + quickSortRuns + " runs.");
		System.out.println("Selection sort has the least amount runs with " + selectSortRuns + " runs.");
		System.out.println();		

		System.out.println("Comparing speeds between each sort, we get a sense of how big O notation factors into speed.");
		System.out.println("The average run time for quick sort is " + (selectSortAverage / quickSortAverage) + " times faster than selection sorts' speeds.");
		System.out.println("Meanwhile, the average run time for merge sort is " + (selectSortAverage / mergeSortAverage) + " times faster than selection sorts' speeds.");
		System.out.println("Lastly, the average run time for merge sort is " + (quickSortAverage / mergeSortAverage) + " times faster than selection sorts' speeds.");
		System.out.println("We can infer from this that the time complexity for quick sort and merge sort (n log n) makes a faster run time.");
		System.out.println("======================================================================================");
		System.out.println();
		
		
				
		/**CONFIRM WITH OTHERS ON HOW TO DO THIS**/
		/**CONFIRM WITH OTHERS ON HOW TO DO THIS**/
		/**CONFIRM WITH OTHERS ON HOW TO DO THIS**/
		/**Search Times**/
		System.out.println();
		System.out.println("Search Times");
		System.out.println("======================================================================================");
		int randomNumber = (int) ((Math.random() * (1000 - 1)) + 1);		
		
		// Sequential Search
		System.out.println("Current Sort; Sequential Search");
		long sequentialSearch1 = searchTime(sequentialArray1, randomNumber, "Sequential");
		long sequentialSearchRuns = searchSortLibrary.runs;
		searchSortLibrary.runs = 0;
		long sequentialSearch2 = searchTime(sequentialArray2, randomNumber, "Sequential");
		long sequentialSearch3 = searchTime(sequentialArray3, randomNumber, "Sequential");
		
		long sequentialSearchAverage = (sequentialSearch1 + sequentialSearch2 + sequentialSearch3)/3;
		System.out.println("1st Run Time: " + sequentialSearch1);
		System.out.println("2nd Run Time: " + sequentialSearch2);
		System.out.println("3rd Run Time: " + sequentialSearch3);
		System.out.println("Average Run Time: " + sequentialSearchAverage);
		System.out.println("Amount of runs: " + sequentialSearchRuns);
		System.out.println();

		
		// Binary Search		
		System.out.println("Current Sort; Binary Search");
		long binarySearch1 = searchTime(binaryArray1, randomNumber, "Binary");
		long binarySearchRuns = searchSortLibrary.runs;
		searchSortLibrary.runs = 0;
		long binarySearch2 = searchTime(binaryArray2, randomNumber, "Binary");
		long binarySearch3 = searchTime(binaryArray3, randomNumber, "Binary");
		
		long binarySearchAverage = (binarySearch1 + binarySearch2 + binarySearch3)/3;
		System.out.println("1st Run Time: " + binarySearch1);
		System.out.println("2nd Run Time: " + binarySearch2);
		System.out.println("3rd Run Time: " + binarySearch3);
		System.out.println("Average Run Time: " + binarySearchAverage);
		System.out.println("Amount of runs: " + binarySearchRuns);
		System.out.println("======================================================================================");
		System.out.println();	
		

		
		/*Sort Analysis*/
		System.out.println();
		System.out.println("Search Analysis");
		System.out.println("======================================================================================");
		System.out.println("Comparing average runtimes, we see the following results when trying to find the number " + randomNumber + ":");
		System.out.println("Binary search has an average run time of " + binarySearchAverage +".");
		System.out.println("Sequential search follows with an average run time of " + sequentialSearchAverage + ".");
		System.out.println();

		System.out.println("In terms of runs, the positions stay the same.");
		System.out.println("Binary search runs " + binarySearchRuns + " times when searching.");
		System.out.println("Sequential search has a larger amount of runs with " + sequentialSearchRuns + " runs.");
		System.out.println("To compare the two, binary search is a faster search, about " + (sequentialSearchAverage / binarySearchAverage) + " times faster than sequential.");
		System.out.println();		

		System.out.println("As searches, both don't need to take too much time to finish.");
		System.out.println("However, binary search is a lot more efficient and faster than sequential.");
		if (randomNumber != 1)	System.out.println("It runs " + (sequentialSearchAverage / binarySearchAverage) + " times faster, and with " + (sequentialSearchRuns / binarySearchRuns) + " times less runs.");
		else System.out.println("That is if the number isn't one of the first elements of the array, as is seen here. \nAs such, sequential search will always be faster than binary search, owing to its starting position at the start of the array.");
		System.out.println("======================================================================================");
		System.out.println();
	}

}