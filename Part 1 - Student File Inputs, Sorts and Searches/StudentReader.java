/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-09-22
 @Description: Reads a file, then allows the user to either read in another file, sort the file by last name, print out the file, write out the sorted file, search for student number, or end the proogram.
***********************************************
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	public static void main(String args[]) {
		Scanner userinput = new Scanner(System.in);
		String fname;
    int recs;

    String [][] ArrayOfFile;
    char menuSelect;
    boolean isFileRead = false, wasSorted = false, finishedProgram = false;
    String searchedWord = "";
    
    while (true){
      // reading in file
      System.out.println("Enter filename to read from");
      fname = userinput.nextLine();
      recs = CheckSize(fname);
      System.out.println("Number of records to be read" + recs);
      ArrayOfFile = new String[recs/3][3];
      System.out.println("Number of records read: " + recs);
      /* put into menu*/
      ArrayOfFile = readIn(fname, ArrayOfFile);
      if (recs != 0)
        isFileRead = true;
      // finished reading in 
      if (isFileRead == false){
        System.out.println("You must enter a valid filename!");
      }
      else
        break;
    }
    while (finishedProgram == false){
        System.out.println("Which would you like to perform?\n1. Read in a file\n2. Sort the current file by last name\n3. Print out current file\n4. Write out sorted file\n5. Search for specific student number\n6. Finish");
      menuSelect = userinput.nextLine().charAt(0);   
      switch (menuSelect){
        case '1':
          while (true){
            // reading in file
            isFileRead = false;
            System.out.println("Enter filename to read from");
            fname = userinput.nextLine();
            recs = CheckSize(fname);
            System.out.println("Number of records to be read" + recs);
            ArrayOfFile = new String[recs/3][3];
            System.out.println("Number of records read: " + recs);
            /* put into menu*/
            ArrayOfFile = readIn(fname, ArrayOfFile);
            if (recs != 0)
              isFileRead = true;
            // finished reading in 
            if (isFileRead == false){
              System.out.println("You must enter a valid filename!");
            }
            else
              break;
          }
          break;
        case '2':
          sortOut(ArrayOfFile, 2);
          sortOut(ArrayOfFile, 1);
          wasSorted = true;
          break;
        case '3':
          System.out.println("This is the array being printed out");
      		/* put into menu*/
      		printOut(ArrayOfFile);
      		/* put into menu*/
          break;
        case '4':
          if (wasSorted == true){
            System.out.println("Writing out sorted file");
            writeOut("stsort.dat", ArrayOfFile); 
          }
          else
            System.out.println("You need to sort the file first!");
          break;
          
          // not done //
        case '5':
          while (searchedWord.length() != 9){
            System.out.println("What is the student number you want to search by?");   
            searchedWord = userinput.nextLine();
            if (searchedWord.length() != 9){
              System.out.println("Pick a valid student number!");
            }
          }
          for (int i = 0; i<ArrayOfFile.length; i++){
            if (ArrayOfFile[i][0].compareTo(searchedWord) == 0) {
              searchedWord = "found";
              System.out.println("Found the student:");
              System.out.println(ArrayOfFile[i][0]);
              System.out.println(ArrayOfFile[i][1]);
              System.out.println(ArrayOfFile[i][2]);              
          }
          }
          if (searchedWord != "found")
            System.out.println("Couldn't find that student number.");
          break;
        case '6':
          finishedProgram = true; 
          break;
          // not done //

          
        default:
          System.out.println("Pick a valid number this time!");
          break;
      }		
      System.out.println();
    }
		userinput.close();
	}
	public static int CheckSize(String filename) {
		int numRec = 0;
		String dataItem;		
		try {
			// Pointer
			BufferedReader FileInputPointer = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			while (FileInputPointer.ready() == true) {
				dataItem = FileInputPointer.readLine();// Just takes a look at what is there
				numRec++;
			} // end while
		} // end of try
		catch (FileNotFoundException e) {
			System.out.println("Error - this file does not exist");
		}
		catch(IOException e) {
			System.out.println("Error - Opps" + e.toString());
		}
		return numRec;//The count of the number of records found.
	}
	/*
	* Method Name: readIn Author: K. McKay Creation Date; 01 12 2021 Modified Date:
	 * 09 22 2023 Description: purpose of the method is to read in the contents of a
	 * file
	 * 
	 * @Parameters: A string that represents the name of the file being read in.
	 * 
	 * @Return Value: Number of records read in Data Type: example: Integer
	 * Dependencies:java.io.* Throws/Exceptions: Error code will present if file
	 * cannot be read
	 */
	public static String[][] readIn(String filename, String[][] theArray) {
		String dataItem;
		try {
			BufferedReader FileInputPointer = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			
			int i=0;
			while (FileInputPointer.ready() == true) {
				for (i = 0; i<14; i++) {
					for (int j = 0; j<3; j++) {
						dataItem = FileInputPointer.readLine();
						theArray[i][j]= dataItem;
					}
				}
			} // end while
			
			
			FileInputPointer.close();
		} // end try

		catch (FileNotFoundException e) {
			System.out.println("Error - this file does not exist");
		}

		catch (IOException e) {
			System.out.println("error=" + e.toString());
		}
		return theArray;

	}
	/*
	 * Method Name: writeOut Author: K. McKay Creation Date; 01 12 2021 Modified
	 * Date: 09 22 2023 Description: purpose of the method is to read in the
	 * contents of a file
	 * 
	 * @Parameters: A string that represents the name of the file being read in.
	 * 
	 * @Return Value: Number of records read in Data Type: example: Integer
	 * Dependencies:java.io.* Throws/Exceptions: Error code will present if file
	 * cannot be read
	 */
	public static void writeOut(String filename, String[][] theArray) {
		try {
			PrintWriter outputfile = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(int i=0; i < theArray.length;i++) {
				for (int j = 0; j <3; j++) {
					outputfile.println(theArray[i]);
				}
			}
			
			
			
			outputfile.close();
		} catch (Exception e) {
			System.out.println("error=" + e.toString());
		}
	}
  /** Method Name: printOut
* @Author: K. McKay 
* @Date: 01 12 2023
* @Modified: 09 22 2023
* @Description: purpose of method is to print a 2d array using the values inside
* @Parameters: String[][] array
* @Returns: N/A
* Dependencies: System.out
* Throws/Exceptions: N/A 
*/
	public static void printOut(String[][] theArray) {
		for(int i=0;i < theArray.length; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(theArray[i][j]);
			}
		}
		System.out.println();
	}	
  /** Method Name: sortOut
* @Author: Fardin Abbassi 
* @Date: 09 12 2023
* @Modified: 09 22 2023
* @Description: purpose of method is to print a 2d array using its size and column given
* @Parameters: String[][] array, int column
* @Returns: N/A
* Dependencies: N/A
* Throws/Exceptions: N/A 
*/
	public static void sortOut (String[][] array, int column) {
		String[] temp = {array[0][0], array[0][1], array[0][2]};		  
    boolean swapped = true;
    for (int h = 0; h < array.length; h++){
      while (swapped){
        for (int i = 0; i < array.length - 1; i++) {
        swapped = false;    
          if (array[i][column].compareTo(array[i+1][column]) > -1) {
            temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
            swapped = true;
          }
        }
      }
      swapped = true;
    }
  }
  }
