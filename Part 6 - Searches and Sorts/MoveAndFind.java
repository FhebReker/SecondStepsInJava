/**
 ***********************************************
 * @Author : Fardin Abbassi
 * @Originally made : November 30, 2023
 * @Last Modified: December 19, 2023
 * @Description: Sort/search library for int arrays and string arrays
 ***********************************************
 */

public class MoveAndFind {
	public static int[] intArray;
	public static  String[] strArray;
	public static long runs = 0;
	
	public MoveAndFind() {
		intArray = null;
		strArray = null;
	}	
	public MoveAndFind(int[] array) {
		intArray = array;
		strArray = null;
	}
	public MoveAndFind(String[] array) {
		intArray = null;
		strArray = array;
	}	
	
	
	/* Method Name: binarySearch 
	 * Author: Fardin Abbassi 
	 * Creation Date: November 28, 2023
	 * Modified Date: December 19, 2023
	 * Description: recursively finds an integer element in an array using binary search
	 * @Parameters: int low, int high, int key
	 * @Return Value: int
	 * Data Type: int 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
	public int binarySearch(int low, int high, int key) {
		runs++;
		int mid = low + (high - low) / 2; // middle of lowest and highest indexes
		// recursively search through array with binary search
		if (low <= high) {
			if (intArray[mid] == key) { // if middle is the key, return
				return mid;
			} else if (intArray[mid] > key) { // if more than the key, search lesser half
				return binarySearch(low, mid-1, key);
			} else { // otherwise search greater side
				return binarySearch(mid+1, high, key);
			}
		}
		return -1; // Return -1 if key is not found
	} // end binarySearch  	
	
	/* Method Name: binarySearch 
	 * Author: Fardin Abbassi 
	 * Creation Date: November 28, 2023
	 * Modified Date: December 19, 2023
	 * Description: recursively finds a string element in an array using binary search
	 * @Parameters: int low, int high, String target
	 * @Return Value: int
	 * Data Type: int, String
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
	public int binarySearch(int low, int high, String target) {
		runs++;
        int mid = low + (high - low) / 2; // middle of lowest and highest indexes
    	
		// recursively search through array with binary search
        if (low <= high) {
            int comparison = strArray[mid].compareTo(target);
            if (comparison == 0)  {// if middle is the key, return
                return mid;
            } else if (comparison < 0) { //if greater than the key, search greater half
                return binarySearch(mid+1, high, target);
            } else { // otherwise search lesser half
                return binarySearch(low, mid-1, target);
            }            
        }
        return -1;  // Return -1 if target is not found
    }
	
	
	
	/* Method Name: sequentialSearch 
	 * Author: Fardin Abbassi
	 * Creation Date: November 30, 2023
	 * Modified Date: December 19, 2023 
	 * Description: finds an integer using sequential search
	 * @Parameters: int key
	 * @Return Value: int 
	 * Data Type: int 
	 * Dependencies: none
	 * Throws/Exceptions: n/a
	 */
	public int sequentialSearch(int key) {
		for (int i = 0; i < intArray.length; i++) {
			runs++;
			if (intArray[i] == key) {
				return i; /* the position where the element was found */
			} // end if
		} // end for
		return -1; /* return -1 if key not found in the array */
	} // end findElement		
	
	/* Method Name: sequentialSearch 
	 * Author: Fardin Abbassi 
	 * Creation Date: November 30, 2023
	 * Modified Date: December 19, 2023
	 * Description: finds a string using sequential search
	 * @Parameters: String target
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
	public int sequentialSearch(String target) {
        for (int i = 0; i < strArray.length; i++) {
    		runs++;
            if (strArray[i].equals(target)) {
                return i;  /* the position where the element was found */
            }
        }
        return -1;  // Return -1 if the target string is not found
    }
    

	
	/*Method Name: selectionSortInt 
	 * Author: Fardin Abbassi 
	 * Creation Date: November 28, 2023
	 * Modified Date: December 19, 2023
	 * Description: sorts an integer array using selection sort
	 * @Parameters: n/a
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
	public void selectionSortInt() {
		for (int i = intArray.length - 1; i > 0; i--) {
			runs++;
			int maxLoc = 0;
			for (int j = 1; j <= i; j++) {
				if (intArray[j] > intArray[maxLoc]) {
					maxLoc = j;
				} // end if
			} // end for
			int temp = intArray[maxLoc];
			intArray[maxLoc] = intArray[i];
			intArray[i] = temp;
		} // end for
	} // end selectionSort	
       
	/* Method Name: selectionSortString 
	 * Author: Fardin Abbassi 
	 * Creation Date: November 28, 2023
	 * Modified Date: December 19, 2023
	 * Description: sorts a string array using selection sort
	 * @Parameters: n/a
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
	public void selectionSortString() {
        int n = strArray.length;
        for (int i = 0; i < n - 1; i++) {
    		runs++;
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (strArray[j].compareTo(strArray[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = strArray[minIndex];
            strArray[minIndex] = strArray[i];
            strArray[i] = temp;
        }
    }

	
	
	/* Method Name: quickSortInt 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: sorts an integer array using quick sort
	 * @Parameters: int low, int high
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: partitionInt
	 * Throws/Exceptions: n/a
	 */
    public void quickSortInt(int low, int high) {
		runs++;
    	if (low < high) {
    		// recursively sort array before and after partition
            int pi = partitionInt(low, high);
            quickSortInt(low, pi - 1);
            quickSortInt(pi + 1, high);
        }
    }
    /* Method Name: partitionInt 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: sets a pivot, then swaps elements into place based on pivot
	 * @Parameters: int low, int high
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
    private static int partitionInt(int low, int high) {
        // pick a pivot
    	int pivot = intArray[high];
        int i = (low - 1);
        
        // from low to high, if current element is less than the pivot, increment i and swap
        for (int j = low; j < high; j++) {
            if (intArray[j] < pivot) {
                i++;
                int temp = intArray[i];
                intArray[i] = intArray[j];
                intArray[j] = temp;
            }
        }
        
        // final swap to put pivot into place
        int temp = intArray[i + 1];
        intArray[i + 1] = intArray[high];
        intArray[high] = temp;
        return i + 1;
    }
    
    /* Method Name: quickSortString 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: sorts an integer array using quick sort
	 * @Parameters: int low, int high
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: printArray
	 * Throws/Exceptions: n/a
	 */
    public void quickSortString(int low, int high) {
		runs++;
        if (low < high) {
    		// recursively sort array before and after partition
            int pi = partitionString(low, high);
            quickSortString(low, pi - 1);
            quickSortString(pi + 1, high);
        }
    }
    /* Method Name: partitionString 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: sets a pivot, then swaps elements into place based on pivot
	 * @Parameters: int low, int high
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: printArray
	 * Throws/Exceptions: n/a
	 */
    private static int partitionString(int low, int high) {
        // pick a pivot
        String pivot = strArray[high];
        int i = (low - 1);

        // from low to high, if current element is less than the pivot, increment i and swap
        for (int j = low; j < high; j++) {
            if (strArray[j].compareTo(pivot) < 0) {
                i++;
                String temp = strArray[i];
                strArray[i] = strArray[j];
                strArray[j] = temp;
            }
        }
        
        // final swap to put pivot into place
        String temp = strArray[i + 1];
        strArray[i + 1] = strArray[high];
        strArray[high] = temp;
        return i + 1;
    }

    
    
    /* Method Name: MergeSortInt 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: sorts an integer array using merge sort
	 * @Parameters: int left, int right
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: ???
	 * Throws/Exceptions: n/a
	 */
    public void MergeSortInt(int left, int right) {
		runs++;
        if (left < right) {
        	// recursively sort 2 halves of the array
            int middle = (left + right) / 2;
            MergeSortInt(left, middle);
            MergeSortInt(middle + 1, right);
            
            // merge the two arrays
            mergeInt(left, middle, right);
        }
    } 
    /* Method Name: mergeInt 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: Takes two sides of the integer array to merge in order
	 * @Parameters: int left, int middle, int right
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
    private static void mergeInt(int left, int middle, int right) {
        // find sizes of sub arrays
    	int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        int leftSub[] = new int[leftSize];
        int rightSub[] = new int[rightSize];

        // copying data to temporary sub arrays
        int i = 0, j = 0;
        for (i = 0; i < leftSize; i++)
        	leftSub[i] = intArray[left + i];
        for (j = 0; j < rightSize; j++)
        	rightSub[j] = intArray[middle + 1 + j];

        // merge into original array in sorted order
        i = 0; j = 0;
        int k = left;
        while (i < leftSize && j < rightSize) {
            if (leftSub[i] <= rightSub[j]) {
            	intArray[k] = leftSub[i];
                i++;
            } else {
            	intArray[k] = rightSub[j];
                j++;
            }
            k++;
        }

        // copy any remaining elements
        while (i < leftSize) {
        	intArray[k] = leftSub[i];
            i++;
            k++;
        }
        while (j < rightSize) {
        	intArray[k] = rightSub[j];
            j++;
            k++;
        }
    }
   
    /* Method Name: MergeSortString 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: sorts a string array using merge sort
	 * @Parameters: int left, int right
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: ??
	 * Throws/Exceptions: n/a
	 */
    public void MergeSortString(int left, int right) {
 		runs++;
         if (left < right) {
         	// recursively sort 2 halves of the array
             int middle = (left + right) / 2;
             MergeSortString(left, middle);
             MergeSortString(middle + 1, right);

             // merge the two arrays
             mergeString(left, middle, right);
         }
     }
    /* Method Name: mergeString 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 14, 2023
	 * Modified Date: December 14, 2023
	 * Description: Takes two sides of the string array to merge in order
	 * @Parameters: int[] data: array to be sorted
	 * @Return Value: n/a 
	 * Data Type: void 
	 * Dependencies: n/a
	 * Throws/Exceptions: n/a
	 */
    private static void mergeString(int left, int middle, int right) {
         // find sizes of sub arrays
     	int leftSize = middle - left + 1;
         int rightSize = right - middle;
         
         
         String leftSub[] = new String[leftSize];
         String rightSub[] = new String[rightSize];

         // copying data to temporary sub arrays
         int i = 0, j = 0;
         for (i = 0; i < leftSize; i++)
         	leftSub[i] = strArray[left + i];
         for (j = 0; j < rightSize; j++)
         	rightSub[j] = strArray[middle + 1 + j];

         // merge into original array in sorted order
         i = 0; j = 0;
         int k = left;
         while (i < leftSize && j < rightSize) {
             if (leftSub[i].compareTo(rightSub[j]) <= 0) {
             	strArray[k] = leftSub[i];
                 i++;
             } else {
             	strArray[k] = rightSub[j];
                 j++;
             }
             k++;
         }
         
         // copy any remaining elements
         while (i < leftSize) {
         	strArray[k] = leftSub[i];
             i++;
             k++;
         }
         while (j < rightSize) {
         	strArray[k] = rightSub[j];
             j++;
             k++;
         }
     }
} // end class