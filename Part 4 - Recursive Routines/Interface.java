/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-11-25
 @Description: Lets the user choose between 4 recursive subroutines, a palindrome checker, fractal pattern maker, shape counter, or a mock stock trader
***********************************************
*/

import java.util.*;
import java.io.*;

public class Interface{
	static 	Scanner scan = new Scanner(System.in); // scanner for all methods
	
	public static void main(String[] args) {
		while(true) { // endless menu loop until user wants to leave
			System.out.println("What would you like to do?");
			System.out.println("1 - Palindrome Checker \n2 - Fractal Pattern \n3 - Count Shapes \n4 - Mock Stock Market \nQ - Quit Menu");
			char menuSelect = scan.nextLine().toLowerCase().charAt(0);			
			switch(menuSelect) {
				case '1':
				    passwordChecker();
					break;
				case '2':
				    patternMaker();
					break;
				case '3':
					shapeCounter();
					break;
				case '4':
					mockStocks();
					break;
				case 'q':
					System.out.println("Exiting the menu...");
					break;
				default:
					System.out.println("Not a valid choice!\nReturning to menu...");
					break;
			}
			if (menuSelect == 'q') break; // leaves loop
    		System.out.println();
		}
		scan.close();
	}

	/*
	* Method Name: passwordChecker
	*Author: Fardin Abbassi
	* Creation Date; October 25, 2023
	* Modified Date: November 16, 2023
	*Description: Asks for user input, then checks if the input can turn into a palindrome using possiblePal()
	* @Parameters: n/a
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: Scanner scan, possiblePal(charArray, 0, 1, 0)
	*Throws/Exceptions: n/a
	*/
	public static void passwordChecker() {
		System.out.print("Enter the word you'd like to check here: ");
	    String possiblePass = scan.nextLine();
		char[] charArray = possiblePass.toCharArray(); // creates char[] for method using input string
		
	    boolean works = possiblePal(charArray, 0, 1, 0);
	    // If it could be rearranged as a palindrome, print yes, otherwise print no
	    if (works)	System.out.println("YES - " + possiblePass + " would work as a password!");
	    else		System.out.println("No - " + possiblePass + " wouldn't work as a password");
	}
	/*
	* Method Name: passwordChecker
	*Author: Fardin Abbassi
	* Creation Date; October 25, 2023
	* Modified Date: November 16, 2023
	*Description: Recursively checks if a char array has the potential to become a palindrome
	* @Parameters: char[] possibility, int index1, int index2, int counter
	* @Return Value: boolean?
	* Data Type: boolean
	* Dependencies: n/a
	*Throws/Exceptions: n/a
	*/
	private static boolean possiblePal(char[] possibility, int index1, int index2, int counter){
        if (index1 >= possibility.length){ // base case: did the array reach the last letter?
    		// if the array was an odd length, there should only be 1 unique character
        	if (possibility.length % 2 == 1) return (possibility.length - counter == 1);
        	
        	// if the array was an even length, there should not be any characters uncounted
	        else return (possibility.length - counter == 0);  
        }
        
        // already counted, moving onto next letter for index1
        if (possibility[index1] == ' ') { 
	        return possiblePal(possibility, index1 + 1, 1, counter);
	    }
        
        // if not counted and the same, count and increase counter
        if (possibility[index1] == possibility[index2] && possibility[index1] != ' '){ 
            counter += 2;
            possibility[index1] = ' ';
            possibility[index2] = ' ';
        }
        
        // has index2 reached the end of the array?
        String temp = String.valueOf(possibility);
        // if so, increment index1, set index2 to 1, recursively call method
	    if (index2 + 1 >= temp.substring(index1 + 1).length()) return possiblePal(possibility, index1 + 1, 1, counter);
        // otherwise, increment index2, recursively call method
	    else return possiblePal(possibility, index1, index2 + 1, counter);
    }
	
	/*
	* Method Name: patternMaker
	*Author: Fardin Abbassi
	* Creation Date; November 19, 2023
	* Modified Date: November 19, 2023
	*Description: Asks user for integer input, then calls on pattern() to print the stars out
	* @Parameters: n/a
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: Scanner scan, pattern(starAmt, columnStart)
	*Throws/Exceptions: NumberFormatException
	*/
    public static void patternMaker() {
    	try {
    		System.out.println("Enter the largest amount of stars in a line: ");
    		int starAmt = Integer.parseInt(scan.nextLine());
    		System.out.println("Enter the column to start in: ");
    		int columnStart = Integer.parseInt(scan.nextLine());
    	    pattern(starAmt,columnStart);
    	}
    	catch(NumberFormatException e) { //In case someone decides to use letters for digits
    		System.out.println("Not a number, please try again.");
    	}
    }    
	/*
	* Method Name: pattern
	*Author: Fardin Abbassi
	* Creation Date; November 19, 2023
	* Modified Date: November 19, 2023
	*Description: Recursively creates a fractal pattern using stars, going from small to big back to small
	* @Parameters: int n, int i
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: n/a
	*Throws/Exceptions: n/a
	*/
    private static void pattern(int n, int i) {
        if (n <= 1) {// base case: printing single star
            for (int j = 0; j < i; j++) { //creates spaces to print after the ith column
                System.out.print(" ");
            }
            System.out.println("*"); //prints a single star
            return;
        }
        pattern(n / 2, i); // recursive call: halves size of amount of stars, keeps same indent
        for (int j = 0; j < i; j++) { //creates spaces to print after the ith column
            System.out.print(" ");
        }
        for (int j = 0; j < n; j++) { //prints full amount of stars
            System.out.print("*");
        }
        System.out.println();
        pattern(n / 2, n / 2 + i); // recursive call: halves amount of stars, increases indent by half of the amount of stars
    }
    
    // rows and columns are defined here to lessen amount of parameters for the method
    private static int totalRows;
	private static int totalColumns;
	/*
	* Method Name: shapeCounter
	*Author: Fardin Abbassi
	* Creation Date; November 22, 2023
	* Modified Date: November 24, 2023
	*Description: Asks user for file name, then reads file for X and . characters in the text file. Counts shapes per rectangle using getShapeSize()
	* @Parameters: n/a
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: Scanner scan, java.io.BufferedReader, java.io.FileReader, java.io.File, getShapeSize(i, j, touristLocation, box)
	*Throws/Exceptions: IOException, FileNotFoundException, NumberFormatException
	*/
    public static void shapeCounter() {
    	System.out.print("Enter your file's name here: ");
    	String line = scan.nextLine();
    	try(BufferedReader br = new BufferedReader(new FileReader(new File(line)))){
    		int count, currentChar, shapeNumber = 1;
        	char [][] box;
        	boolean [][] touristLocation;
    		while (br.ready() ) { // loops until all shapes are read in
    			totalColumns = Integer.parseInt(br.readLine());
        		totalRows = Integer.parseInt(br.readLine());
        		count = 0;
            	box = new char[totalRows][totalColumns];
            	touristLocation = new boolean[totalRows][totalColumns];
    			for (int i = 0; i<totalRows; i++) {
    				for (int j = 0; j<totalColumns+2; j++) {
    					currentChar = br.read();
    					// if current character is . or X, add to char array, set boolean array to false
    					if (currentChar == 88 || currentChar == 46) {
    		        		box[i][j] = (char)currentChar;
    		        		touristLocation[i][j] = false;
    					}
		        		// reset to 0 for next character
		        		currentChar = 0;
    				}
    			}
    			
    			// count increments based on number of shapes
    			for (int i = 0; i<totalRows; i++)
    				for (int j = 0; j<totalColumns; j++) 
    					if (getShapeSize(i, j, touristLocation, box) > 0) count++;
    			
    			System.out.println("Amount of shapes in figure " + shapeNumber + ": " + count);
    			shapeNumber++;
    		}
    	}catch(Exception e) { // if there are problems with misinputs, misread files or whatever may it be
    		e.printStackTrace();
    	}
    }
	/*
	* Method Name: getShapeSize
	*Author: Fardin Abbassi
	* Creation Date; November 22, 2023
	* Modified Date: November 24, 2023
	*Description: Recursively checks if a shape is formed by Xs directly tpo the side or above/below
	* @Parameters: int row, int column, boolean[][] found, char[][] box
	* @Return Value: int
	* Data Type: int, boolean [][], char[][]
	* Dependencies: int totalRows, int totalColumns
	*Throws/Exceptions: n/a
	*/
    private static int getShapeSize(int row, int column, boolean[][] found, char[][] box) {
        // if outside borders defined by totalRows and totalColumns, return 0
        if (row < 0 || row >= totalRows || column < 0 || column >= totalColumns)
        	return 0;

        // if char is a . or has already been counted, return 0
        if (found[row][column] || box[row][column] == '.')
        	return 0;
        
        found[row][column] = true;  // mark current char as found for future reference
        
        // 	count current square, then recursively call method to add onto 
        //	current count based on the chars above, below, to the right and left
        int size = 1;			   
        size += getShapeSize(row-1, column, found, box);
        size += getShapeSize(row+1,column, found, box);
        size += getShapeSize(row,column-1, found, box);
        size += getShapeSize(row,column+1, found, box);
        return size;
    }

    /*
	* Method Name: mockStocks
	*Author: Fardin Abbassi
	* Creation Date; November 24, 2023
	* Modified Date: November 25, 2023
	*Description: Asks for a number between 1 and 7, finds the maximum return on investment for the randomly generated stock prices
	* @Parameters: n/a
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: Scanner scan
	*Throws/Exceptions: NumberFormatException
	*/
    public static void mockStocks(){
    	int[] pricesThisWeek = new int[7];
    	int maximumValue, days = 0;
    	
    	// filling up array with random numbers between 1 and 9
    	for(int i = 0; i<pricesThisWeek.length; i++) pricesThisWeek[i] = (int)(9*Math.random()) + 1; 
    	System.out.print("This Week's Prices: ");
    	for(int j = 0; j<pricesThisWeek.length; j++) System.out.print("$"+pricesThisWeek[j] + " ");
    	System.out.println();
    	
    	while(true) { //looping until recieving a valid response from user
    		try {
        		System.out.print("Enter the maximum amount of days needed to sell: ");
            	days = Integer.parseInt(scan.nextLine());
        	}catch(NumberFormatException e) { // if user inputs anything that isn't a number
        		System.out.println("Invalid entry, please enter a number between 1 and 7");
        	}    
    		
    		// if within range, return max amount of profit
    		if(days <= 7 && days > 0) {
        		maximumValue = maxProfit(pricesThisWeek, 0, 0, 10, days);
            	System.out.println("The most you can get this week is $" + maximumValue);
            	break;
    		}
    		// if user inputs anything that isn't a number between 1 and 7
        	else System.out.println("Invalid entry, please enter a number between 1 and 7");
        	
    	}
    }
    /*
	* Method Name: mockStocks
	*Author: Fardin Abbassi
	* Creation Date; November 24, 2023
	* Modified Date: November 25, 2023
	*Description: Asks for a number between 1 and 7, finds the maximum return on investment for the randomly generated stock prices
	* @Parameters: int[] prices, int n, int maxProfit, int minimumPrice, int lastDay
	* @Return Value: int
	* Data Type: int
	* Dependencies: n/a
	*Throws/Exceptions: n/a
	*/
    private static int maxProfit(int[] prices, int n, int maxProfit, int minimumPrice, int lastDay) {
    	//	base case: if n is the last day, return whatever the profit is
    	if (n >= lastDay) return maxProfit;
        
        //	if minimum price is less than the current day's price, 
    	//	set the minimum to the current price
        if (minimumPrice > prices[n])  minimumPrice = prices[n];
        
        // 	if the current profit is greater than the previous maximum profit, 
        //	set the max profit to the current profit
        int currentProfit = prices[n] - minimumPrice;
        if (currentProfit > maxProfit)  maxProfit = currentProfit;
        
        //	recursively call whatever the profit is for tomorrow
        return maxProfit(prices, n+1, maxProfit, minimumPrice, lastDay);
    }
}
