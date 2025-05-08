/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-11-03
 @Description: Enters a file with characters, lets user enter a different file, manipulate characters, print out characters, search for a specific character and/or quit the program.
 ***********************************************
*/

import java.io.*;
import java.util.Scanner;

public class Interface{
	static Scanner scan = new Scanner(System.in);  // scanner available for class
	static int recs = 0;
	public static void main(String[] args) throws IOException {
		char menuSelect = '0' /*for menu*/, binOrText = ' ' /*for character editor*/; 
		boolean finishedProgram = false; // to ensure loop 
		String textFile = null /*for text file*/, binaryFile = null /*for binary file*/, savedBinary = null /*for saved binary file*/, characterName /*for option 5 of menu*/;
		Character[] players = new Character[50];
		
		System.out.println("========== Welcome to Dungeons and Dragons! ==========");
		while (menuSelect == '0' || recs == 0 /*To make sure recs are read*/) {
			System.out.println("How would you like to read in a file?");
			menuOptions(2);
			menuSelect = scan.nextLine().charAt(0);
			switch (menuSelect) {
				case('1'): // text file input
					System.out.print("Please enter a text file name: ");
					textFile = scan.nextLine();
					if (inputData(textFile, players) <= 50) {
						recs = inputData(textFile, players);
						binOrText = 't';
					}
					else
						System.out.println("Too many characters, please input a different file");
					break;
				case('2'): // binary file input
					System.out.print("Please enter a binary file name: ");
					binaryFile = scan.nextLine();
					if (readNewBinFile(binaryFile, players) <= 50) {
						recs = readNewBinFile(binaryFile, players);
						binOrText = 'b';
					}
					else 
						System.out.println("Too many characters, please input a different file");
					break;
				default: // if you don't pick a valid option, loops until you do
					System.out.println("Please enter a valid key!");
					menuSelect = '0';
					break;
			}
			if (recs == 0) { // in case user reads in an empty file
				System.out.println("Input a correct file!");
			}
		}
		System.out.println("Successfully loaded file!");
		System.out.println();

		menuSelect = '0'; // setup for menu loop
		while(finishedProgram == false) {
			while(menuSelect == '0' && recs != 0) { // ensures there are valid recs to work with
				System.out.println("========== Dungeouns And Dragons ==========");
				System.out.println("What would you like to do?");
				menuOptions(12);
				menuSelect = scan.nextLine().charAt(0);
				switch (menuSelect) {
					case('1'): // text file input
						System.out.print("Please enter a file name: ");
						textFile= scan.nextLine();
						if (inputData(textFile, players) <= 50) {
							recs = inputData(textFile, players); //
							binOrText = 't';
						}
						else {
							System.out.println("Too many characters, please input a different file");
							break;
						}
						if (recs != 0)
							System.out.println("Successfully loaded file! Back to menu...");
						break;
					case('2'): // binary file input
						System.out.print("Please enter a Binary file name: ");
						binaryFile= scan.nextLine();
						if (readNewBinFile(binaryFile, players) <= 50) {
							recs = readNewBinFile(binaryFile, players);
							binOrText = 'b';
						}						
						else {
							System.out.println("Too many characters, please input a different file");
							break;
						}
						if (recs != 0)
							System.out.println("Successfully loaded file! Back to menu...");
						break;
					case('3'): // binary file creation using current data
						System.out.print("Please enter a Binary file name to save to: ");
						savedBinary = scan.nextLine();
						if (savedBinary == binaryFile || savedBinary == textFile) {
							System.out.println("File name is the same as an existing file. Returning to menu...");
							break;
						}
						writeNewBinFile(savedBinary, players, recs);
						System.out.println("Data saved! Back to menu...");
						break;
					case('4'): // current file output
						System.out.println("Printing players...");
						printOutData(players, recs);
						break;
					case('5'): // character edit
						System.out.print("Please enter a character you'd like to edit to enter editor mode: ");
						String character = scan.nextLine(), tempName;
						int characterID = -1; // -1 instead of 0 to check players[0]
						for(int i = 0; i<recs; i++) { //searches through file for character
							if (players[i] != null)
								tempName = players[i].getCharacterName();
							else {
								tempName = "";
								System.out.println(i);
							}
							if (tempName.equalsIgnoreCase(character)) // compares intended character to characters in file
								characterID = i;
						}
						if (characterID == -1) { // if intended character isn't found, goes back to menu
							System.out.println("Character not found, returning to menu...");
							break;
						} // otherwise, move onto character editor
						if (binOrText == 't')
							CharacterEditor(players, menuSelect, characterID, textFile);
						else
							CharacterEditor(players, menuSelect, characterID, binaryFile);							
						break;
					case('6'): // character searcher
						System.out.print("Enter your character's name: ");
						characterName = scan.nextLine();
						String testName; boolean found = false;
						for (int i = 0; i < recs; i++) {
							testName = players[i].getCharacterName(); // like character editor's search, looks for intended character
							if (testName.equalsIgnoreCase(characterName)) {
								printOutPlayer(players, i);
								found = true;
							}
							else if(i == recs - 1 && found == false) // if character wasn't found by the end of the file, will tell user
								System.out.println(characterName + " was not found");
						}
						break;
					case('7'): // quit program
						System.out.println("Are you sure you want to quit? (y/n)");
						menuSelect = scan.nextLine().toLowerCase().charAt(0);
						if (menuSelect == 'y') {
							finishedProgram = true;
							System.out.println("Quitting program...");
						}
						else {
							if (menuSelect != 'n') // in case of misinput
								System.out.println("Not a valid key");
							System.out.println("Returning to menu..."); // goes back to menu
						}
						break;
					default: // misinput
						System.out.println("Please enter a valid key!");
						menuSelect = '0';
						break;
				}
				System.out.println();
			}
			menuSelect = '0'; // sets loop up again
			
			if (recs == 0) { // in case empty file read in
				System.out.println("File not currently read in, please read in a file");
				System.out.println();
				while (recs == 0) { // will repeat until valid file read in
					System.out.println("========== Dungeouns And Dragons ==========");
					System.out.println("How would you like to read in a file?");
					menuOptions(2);
					menuSelect = scan.nextLine().charAt(0);
					switch (menuSelect) {
					case('1'): // text file input
						System.out.print("Please enter a text file name: ");
						textFile= scan.nextLine();
						if (inputData(textFile, players) <= 50) {
							recs = inputData(textFile, players); //
							binOrText = 't';
						}
						else 
							System.out.println("Too many characters, please input a different file");
						break;
					case('2'): // binary file input
						System.out.print("Please enter a binary file name: ");
						binaryFile= scan.nextLine();
						if (readNewBinFile(binaryFile, players) <= 50) {
							recs = readNewBinFile(binaryFile, players);
							binOrText = 'b';
						}
						else 
							System.out.println("Too many characters, please input a different file");
						break;
					default: // misinput
						System.out.println("Please enter a valid key!");
						menuSelect = '0';
						break;
					}
					if (recs == 0) { // empty file read in
						System.out.println("Input a correct file!");
					}
					System.out.println();
				}
				System.out.println("Successfully loaded file!");
			}
		}
		System.out.println("Thanks for playing!");
		scan.close();
	}
	
	/*
	* Method Name: menuOptions
	*Author: Fardin Abbassi
	* Creation Date; October 17, 2023
	* Modified Date: October 19, 2023
	*Description: Prints out up to 7 menu items
	* @Parameters: (int menuItems)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/
	public static void menuOptions(int menuItems) {
		if (menuItems > 7) // sets menu items to be printed to the length of the array
			menuItems = 7;
		String[] menu = {"1) Read an ASCII file", "2) Read a random‐access file", "3) Convert to Binary File", "4) Print All Characters",
						"5) Edit character(s)", "6) Search by Character Name", "7) Quit"};
		for (int i = 0; i<menuItems; i++) {
			System.out.println(menu[i]);
		}
	}
	
	/*
	* Method Name: menuOptions2
	*Author: Fardin Abbassi
	* Creation Date; October 20, 2023
	* Modified Date: October 20, 2023
	*Description: Prints out up to 6 submenu items
	* @Parameters: (int menuItems)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/
	public static void menuOptions2(int menuItems) {
		if (menuItems > 6) // sets menu items to be printed to the length of the array
			menuItems = 6;
		String[] menu = {"a) Add a new character", "b) Delete by Character Name", "c) Change Race", "d) Change Class", 
				"e) Update Level of Character", "f) Update Character Stat"};
		for (int i = 0; i<menuItems; i++) {
			System.out.println(menu[i]);
		}
	}
	
	/*
	* Method Name: CharacterEditor
	*Author: Fardin Abbassi
	* Creation Date; October 22, 2023
	* Modified Date: October 26, 2023
	*Description: Prints out up to 6 submenu items
	* @Parameters: (int menuItems)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/ 
	public static void CharacterEditor(Character[] players, char menuSelect, int characterID, String file) throws IOException {		
		System.out.println("How do you want to manipulate your character?");
		menuOptions2(6);
		menuSelect = scan.nextLine().toLowerCase().charAt(0);
		switch(menuSelect) {
			case('a'): // adding a character
				int nextCharacterSlot = recs;
				if(nextCharacterSlot == 50) {
					System.out.println("Character cannot be added due to the limit of characters being reached");
					break;
				}
				else {
					System.out.println("Adding new character...");
					newCharacter(file, players, nextCharacterSlot);
					System.out.println("Returning to menu...");
				}
				break;
			case('b'): // deleting a character
				System.out.print("Please enter a character you'd like to delete: ");
				String deletedCharacter = scan.nextLine();
				int deletedCharacterID = -1; // -1 instead of 0 to check players[0]
				for(int i = 0; i<recs; i++) { //searches through file for character
					String tempName = players[i].getCharacterName();
					if (tempName.equalsIgnoreCase(deletedCharacter)) // compares intended character to characters in file
						deletedCharacterID = i;
				}
				if (deletedCharacterID == -1) { // if intended character isn't found, goes back to menu
					System.out.println("Character not found, returning to menu...");
					break;
				} // otherwise, move onto character deleter
				System.out.println("Are you sure you want to delete " + deletedCharacter + "? (y/n)");
				menuSelect = scan.nextLine().toLowerCase().charAt(0);
				if (menuSelect == 'y') {
					System.out.println("Confirmation recieved. Deleting character...");
					deleteCharacter(file, players, deletedCharacterID);
				}
				else {
					if (menuSelect != 'n') // in case of misinput
						System.out.println("Not a valid key");
					System.out.println("Returning to menu..."); // goes back to menu
				}
				break;
			case('c'): //changing race
				System.out.print("Please enter your character's new race: ");
				String newRace = scan.nextLine();
				players[characterID].raceSwap(newRace);
				break;
			case('d'): // changing class
				System.out.print("Please enter your desired class: ");
				String newClass = scan.nextLine();
				players[characterID].classChange(newClass);
				break;
			case('e'): // edit character level
				System.out.print("Please enter how many levels you want to set your character to: ");
				int levelIncrease = 0;
				try {
					levelIncrease = Integer.parseInt(scan.nextLine());
				}catch(NumberFormatException e) { // in case of misinput
					System.out.println("Not a valid input, returning to menu...");
					break;
				}
				players[characterID].setLevel(0); // sets level to 0 before re-leveling
				for (int i = levelIncrease; i > 0; i--)
					players[characterID].levelUp(players[characterID].getCharacterClass());
				break;
			case('f'): //manually updating stat | IN PROGRESS
				/*5 – Update Character Stat: to edit each character stat field INDIVIDUALLY (sub menu)
				of a record then store to the array then update the random‐access field (see above
				for details).*/
				int statIncrease = 0;
				System.out.println("Which stat would you like to edit? \nStrength (s)\nConstitution (c)"
						+ "\nIntelligence (i)\nWisdom (w)\nDexterity (d)\nCharisma (r)");
				menuSelect = scan.nextLine().toLowerCase().charAt(0);
				boolean breakOut = false; // breaks out in case of misinput
				switch(menuSelect) {case('s'): case('c'): case('i'): case('w'): case('d'): case('r'): break;
					default:
						System.out.println("Invalid key, returning to menu..."); 
						breakOut = true; break;
				}
				if (breakOut) break;
				
				System.out.println("Please enter your new stat value: ");
					try {
						statIncrease = Integer.parseInt(scan.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Invalid input, returning to menu...");
						break;
					}
				switch(menuSelect) {
					case('s'):	players[characterID].setStrength(statIncrease); break;
					case('c'):	players[characterID].setConstitution(statIncrease); break;
					case('i'):	players[characterID].setIntelligence(statIncrease); break;
					case('w'):	players[characterID].setWisdom(statIncrease); break;
					case('d'):	players[characterID].setDexterity(statIncrease); break;
					case('r'):	players[characterID].setCharisma(statIncrease); break;
					default:
						System.out.println("Invalid key, returning to menu...");
						menuSelect = '0';
						break;
				}
				break;
			default:
				System.out.println("Please enter a valid key!");
				menuSelect = '0';
				break;
		}

	}
	
	/*
	* Method Name: inputData
	*Author: Fardin Abbassi
	* Creation Date; October 17, 2023
	* Modified Date: October 17, 2023
	*Description: Reads text file, populates character array based on information given in text file
	* @Parameters: (String fileName, Character[] players)
	* @Return Value: int
	* Data Type: int
	* Dependencies: String, Character[], BufferedReader, InputStreamReader, FileInputStream, IOException
	*Throws/Exceptions: IOException
	*/
	public static int inputData (String fileName, Character[] players) {
		int i=0;	
		try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while(in.ready()==true){                       
					players[i] = new Character();	
					players[i].setCharacterName (in.readLine());
					players[i].setRace (in.readLine());
					players[i].setCharacterClass (in.readLine());
					players[i].setLevel(Integer.parseInt(in.readLine()));
					players[i].setHitPoints(Integer.parseInt(in.readLine()));
					players[i].setStrength(Integer.parseInt(in.readLine()));
					players[i].setConstitution(Integer.parseInt(in.readLine()));
					players[i].setIntelligence(Integer.parseInt(in.readLine()));
					players[i].setWisdom(Integer.parseInt(in.readLine()));
					players[i].setDexterity(Integer.parseInt(in.readLine()));
					players[i].setCharisma(Integer.parseInt(in.readLine()));
					i++;
				}	// End loop
			in.close();
		} catch (IOException e) {};	// End try-catch statement.
		return i;
	}

	/*
	* Method Name: readNewBinFile
	*Author: Fardin Abbassi
	* Creation Date; October 17, 2023
	* Modified Date: October 20, 2023
	*Description: Reads file, populates character array based on information given in file
	* @Parameters: (String fileName, Character[] players)
	* @Return Value: int
	* Data Type: int
	* Dependencies: RandomAccessFile
	*Throws/Exceptions: IOException
	*/
	public static int readNewBinFile(String filename, Character players[]) throws IOException{
		RandomAccessFile raf = new RandomAccessFile(filename,"rw");
		int numrecs = (int)raf.length()/152;
		for (int i=0; i < numrecs; i++) {
			players[i] = new Character();
			players[i].readRec(raf, i);
    		} //end for
		raf.close();
		return numrecs;
	}

	/*
	* Method Name: printOutData
	*Author: Fardin Abbassi
	* Creation Date; October 17, 2023
	* Modified Date: October 25, 2023
	*Description: Prints name, race, class, level, hitpoints, strength, constitution, intelligence, wisdom, dexterity and charisma variables of each character in the array
	* @Parameters: (Character printPlayers[], int numrecs)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/
	public static void printOutData (Character printPlayers[], int numrecs) {
		System.out.println("Character Name\t" + "\tRace" + "\t\tClass" + "\t\tLvl" + "\tHP" + 
						"\tSTR" + "\tCON" + "\tINT" + "\tWIS" + "\tDEX" + "\tCHR");
		for(int i = 0; i<numrecs; i++) {
			if(printPlayers[i].getCharacterName() != "") {
				System.out.print(printPlayers[i].getCharacterName());
				if (printPlayers[i].getCharacterName().length()<20) {
					for (int k = printPlayers[i].getCharacterName().length(); k < 20; k++)
					System.out.print(" ");
				}
				
				System.out.print("\t" + printPlayers[i].getRace());
				if (printPlayers[i].getRace().length()<15) {
					for (int k = printPlayers[i].getRace().length(); k < 15; k++)
					System.out.print(" ");
				}
				
				System.out.print("\t" + printPlayers[i].getCharacterClass());
				if (printPlayers[i].getCharacterClass().length()<15) {
					for (int k = printPlayers[i].getCharacterClass().length(); k < 15; k++)
					System.out.print(" ");
				}
				
				System.out.println ("\t" + printPlayers[i].getLevel()+ "\t" + printPlayers[i].getHitPoints() + "\t" + printPlayers[i].getStrength()  + "\t" 
						+ printPlayers[i].getConstitution()  + "\t" + printPlayers[i].getIntelligence()  + "\t" + printPlayers[i].getWisdom()  + "\t" 
						+ printPlayers[i].getDexterity()  + "\t" + printPlayers[i].getCharisma());
			}
		}
	}

	/*
	* Method Name: printOutPlayer
	* Author: Fardin Abbassi
	* Creation Date; October 22, 2023
	* Modified Date: October 22, 2023
	* Description: Prints name, race, class, level, hitpoints, strength, constitution, intelligence, wisdom, dexterity and charisma variables of a single character in the array
	* @Parameters: (Character printPlayers[], int playerPosition)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/	
	public static void printOutPlayer (Character printPlayers[], int playerPosition) {
		System.out.println("Character Name\t" + "\tRace" + "\t\tClass" + "\t\tLvl" + "\tHP" + 
						"\tSTR" + "\tCON" + "\tINT" + "\tWIS" + "\tDEX" + "\tCHR");
		System.out.print(printPlayers[playerPosition].getCharacterName());
		if (printPlayers[playerPosition].getCharacterName().length()<20) {
			for (int k = printPlayers[playerPosition].getCharacterName().length(); k < 20; k++)
			System.out.print(" ");
		}
		System.out.print("\t" + printPlayers[playerPosition].getRace());
		if (printPlayers[playerPosition].getRace().length()<15) {
			for (int k = printPlayers[playerPosition].getRace().length(); k < 15; k++)
			System.out.print(" ");
		}
		System.out.print("\t" + printPlayers[playerPosition].getCharacterClass());
		if (printPlayers[playerPosition].getCharacterClass().length()<15) {
			for (int k = printPlayers[playerPosition].getCharacterClass().length(); k < 15; k++)
			System.out.print(" ");
		}	
		System.out.println ("\t" + printPlayers[playerPosition].getLevel()+ "\t" + printPlayers[playerPosition].getHitPoints() + "\t" + printPlayers[playerPosition].getStrength()  + "\t" 
				+ printPlayers[playerPosition].getConstitution()  + "\t" + printPlayers[playerPosition].getIntelligence()  + "\t" + printPlayers[playerPosition].getWisdom()  + "\t" 
				+ printPlayers[playerPosition].getDexterity()  + "\t" + printPlayers[playerPosition].getCharisma());
	}

	/*
	* Method Name: writeNewBinFile
	* Author: Fardin Abbassi
	* Creation Date; October 19, 2023
	* Modified Date: October 26, 2023
	* Description: Writes a binary file using the current set of players
	* @Parameters: (String filename, Character[] players, int numrecs)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: RandomAccessFile
	*Throws/Exceptions: IOException
	*/	
	public static void writeNewBinFile(String filename, Character[] players, int numrecs) throws IOException{
		RandomAccessFile raf = new RandomAccessFile(filename,"rw");
		for (int i=0; i < numrecs; i++) {			
			players[i].writeRec(raf, i);
		}
	    raf.close();
	}

	/*
	* Method Name: newCharacter
	*Author: Fardin Abbassi
	* Creation Date; October 22, 2023
	* Modified Date: October 23, 2023
	*Description: Creates a new character in the array at the given position
	* @Parameters: (String filename, Character[] players, int newCharacterPosition)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/ 
    public static void newCharacter(String filename, Character[] players, int newCharacterPosition){		
		players[newCharacterPosition] = new Character();
		System.out.print("Please write your new character's name: ");
    	String characterName = scan.nextLine();
    	System.out.print("Please write your new character's race: ");
    	String characterRace = scan.nextLine();
    	System.out.print("Please write your new character's class: ");
    	String characterClass = scan.nextLine();
    	players[newCharacterPosition].setCharacterName(characterName);
    	players[newCharacterPosition].setRace(characterRace);
    	players[newCharacterPosition].setCharacterClass(characterClass);
    	players[newCharacterPosition].statGen();
    	players[newCharacterPosition].levelUp(characterClass);
    	
    	System.out.println("Your character, " + characterName + ", has been created\nMake sure to save this file afterwards");
    	recs += 1;
    }
    
	/*
	* Method Name: deleteCharacter
	*Author: Fardin Abbassi
	* Creation Date; October 23, 2023
	* Modified Date: October 27, 2023
	*Description: Deletes a character in the array at the given position
	* @Parameters: (String filename, Character[] players, int deadCharacterPosition)
	* @Return Value: N/A
	* Data Type: N/A
	* Dependencies: N/A
	*Throws/Exceptions: N/A
	*/ 
    public static void deleteCharacter(String filename, Character[] players, int deadCharacterPosition){
    	String deadName = players[deadCharacterPosition].getCharacterName();
    	
    	players[deadCharacterPosition] = new Character();
    	players[deadCharacterPosition].setCharacterName("");
    	players[deadCharacterPosition].setRace("");
    	players[deadCharacterPosition].setCharacterClass("");
    	players[deadCharacterPosition].setLevel(0);
    	players[deadCharacterPosition].setHitPoints(0);
    	players[deadCharacterPosition].setStrength(0);
    	players[deadCharacterPosition].setConstitution(0);
    	players[deadCharacterPosition].setIntelligence(0);
    	players[deadCharacterPosition].setWisdom(0);
    	players[deadCharacterPosition].setDexterity(0);
    	players[deadCharacterPosition].setCharisma(0);
    	int lastPosition = -1;

    	for (int i = deadCharacterPosition + 1; i < players.length-1; i++) {
    		if (players[i+1] != null) {
    			if (players[i] != null && players[i].getCharacterName() == "") {
            		players[i] = null;
        			players[i] = players[i+1];
        			players[i+1] = null;
    			}
    			else if (players[i] == null){
    				players[i] = players[i+1];
    				players[i+1] = null;
    			}
    		}
    		lastPosition = i;
    	}
    	if (lastPosition != -1)
    		players[lastPosition + 1] = null;
    	System.out.println(deadName + " has been deleted");
    	recs -= 1;
    }
}
