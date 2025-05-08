/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-10-16
 @Description: Creates an RPG character, with a name, class, and race, with methods to manipulate the other variables.
***********************************************
*/
import java.io.*;

public class Character{
	private String characterName;		// character name = 20 * 2 bytes
	private String race;				// character race = 20 * 2 bytes
	private String characterClass;		// character class = 20 * 2 bytes
	private int level;					// character level  = 4 bytes
	private int hitPoints;				// character hp = 4 bytes
	private int strength;				// character str = 4 bytes
	private int constitution;			// character con = 4 bytes
	private int intelligence;			// character int = 4 bytes
	private int wisdom;					// character wis = 4 bytes
	private int dexterity;				// character dex = 4 bytes
	private int charisma;				// character chr = 4 bytes
	private final long recLength = 152;
	
	public Character(){
		characterName = "";
		race = "";
		characterClass = "";
		level = 0;
		hitPoints = 0;
		strength = 0;
		constitution = 0;
		intelligence = 0;
		wisdom = 0;
		dexterity = 0;
		charisma = 0;
	}
	public Character(String name, String race, String characterClass, int level,
			int hp, int str, int con, int intelligence, int wis, int dex, int chr) {
		this.characterName = name;	
		this.race = race;
		this.characterClass = characterClass;	
		this.level = level;	
		this.hitPoints = hp;				
		this.strength = str;				
		this.constitution = con;			
		this.intelligence = intelligence;			
		this.wisdom = wis;				
		this.dexterity = dex;				
		this.charisma = chr;			
	}

	// getters and setters for strings
	public String getCharacterName() {return characterName;}
	public String getRace() {return race;}
	public String getCharacterClass() {return characterClass;}
	public void setCharacterName(String characterName) {this.characterName = characterName;}
	public void setRace(String race) {this.race = race;}
	public void setCharacterClass(String characterClass) {this.characterClass = characterClass;}

	// getters and setters for internally changed ints
	public int getLevel() {return level;}
	public int getHitPoints() {return hitPoints;}
	public void setLevel(int level) {this.level = level;}
	public void setHitPoints(int hitPoints) {this.hitPoints = hitPoints;}

	// getters and setters for externally changed ints
	public int getStrength() {return strength;}
	public int getConstitution() {return constitution;}
	public int getIntelligence() {return intelligence;}
	public int getWisdom() {return wisdom;}
	public int getDexterity() {return dexterity;}
	public int getCharisma() {return charisma;}
	public void setStrength(int strength) {this.strength = strength;}
	public void setConstitution(int constitution) {this.constitution = constitution;}
	public void setIntelligence(int intelligence) {this.intelligence = intelligence;}
	public void setWisdom(int wisdom) {	this.wisdom = wisdom;}
	public void setDexterity(int dexterity) {this.dexterity = dexterity;}
	public void setCharisma(int charisma) {this.charisma = charisma;}
	
	/*
	* Method Name: diceRoll
	*Author: Fardin Abbassi
	* Creation Date; October 10, 2023
	* Modified Date: October 16, 2023
	*Description: Rolls 4 six-sided dice, returns the 3 highest rolls as an integer
	* @Parameters: (int stat)
	* @Return Value: int
	* Data Type: int
	* Dependencies: n/a
	*Throws/Exceptions: n/a
	*/
	private int diceRoll(int stat) {
		int[] diceResults = {(int)(6*Math.random() + 1), (int)(6*Math.random() + 1), (int)(6*Math.random() + 1), (int)(6*Math.random() + 1)};
		int lowest = 0;
		for (int i = 0; i<2; i++) {
			if (diceResults[i] < diceResults[i+1]) {
				lowest = diceResults[i];
			}
			else if (diceResults[i] > diceResults[i+1]){
				lowest = diceResults[i+1];
			}
		}
		stat = diceResults[0] + diceResults[1] + diceResults[2] + diceResults[3] - lowest;
		return stat;
	}
	
	/*
	* Method Name: statGen
	*Author: Fardin Abbassi
	* Creation Date; October 10, 2023
	* Modified Date: October 16, 2023
	*Description: Generates user stats based on the diceRoll method above.
	* @Parameters: n/a
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: n/a 
	*Throws/Exceptions: n/a 
	*/
	public void statGen() {
		this.strength = diceRoll(this.strength);
		this.constitution = diceRoll(this.constitution);
		this.intelligence = diceRoll(this.intelligence);
		this.wisdom = diceRoll(this.wisdom);
		this.dexterity = diceRoll(this.dexterity);
		this.charisma = diceRoll(this.charisma);
	}
	
	/*
	* Method Name: levelUp
	*Author: Fardin Abbassi
	* Creation Date; October 10, 2023
	* Modified Date: October 16, 2023
	*Description: Increases hit points based on a die roll with faces depending on the class.
	* @Parameters: (String characterClass)
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: n/a 
	*Throws/Exceptions: n/a 
	*/
 	public void levelUp (String characterClass) {
		int sidesOfDie = 0;
		int roll = 0;
		switch (characterClass) {
			case "Warrior":
				sidesOfDie = 10;
			case "Cleric":
				sidesOfDie = 8;
			case "Mage":
				sidesOfDie = 4;
			default:
				sidesOfDie = 6;
		}
		roll = (int)(sidesOfDie * Math.random()+1);
		System.out.println("Your " + characterClass + " has levelled up! Your hitpoints has increased from " + this.hitPoints + " to  " + (this.hitPoints+roll) + "!");
		this.hitPoints += roll;
		this.level += 1;
	}

 	/*
 	* Method Name: raceSwap
 	*Author: Fardin Abbassi
	* Creation Date; October 10, 2023
	* Modified Date: October 16, 2023
 	*Description: Defaults race to human by using the humanify method below, then adds stat modifiers based on race
 	* @Parameters: (String race)
 	* @Return Value: void
 	* Data Type: n/a
 	* Dependencies: n/a 
 	*Throws/Exceptions: n/a 
 	*/
	public void raceSwap(String race) {
		humanify();
		switch(race) {
			case "Halfling":
				this.race = "Halfling";
				this.constitution += 1;
				this.dexterity += 2;
				break;
			case "Elf":
				this.race = "Elf";
				this.dexterity += 3;
				this.constitution -= 2;
				break;
			case "Dwarf":
				this.race = "Dwarf";
				this.constitution += 3;
				break;
			case "Orc":
				this.race = "Orc";
				this.strength += 3;
				this.constitution += 1;
				break;
			case "Gnome":
				this.race = "Gnome";
				this.intelligence += 2;
				this.strength -= 3;
				this.wisdom += 1;
				break;
			case "Human":
				break;
			default:
				this.race = race;
				break;
		}
	}
	
	/*
	* Method Name: humanify
	*Author: Fardin Abbassi
	* Creation Date; October 10, 2023
	* Modified Date: October 16, 2023
	*Description: Removes stat modifiers based on race, then turns current character into a human with no stat changes.
	* @Parameters: n/a
	* @Return Value: n/a
	* Data Type: n/a
	* Dependencies: n/a 
	*Throws/Exceptions: n/a
	*/
	private void humanify() {
		switch(this.race) {
			case "Human":
				break;
			case "Halfling":
				this.race = "Human";
				this.constitution -= 1;
				this.dexterity -= 2;
				break;
			case "Elf":
				this.race = "Human";
				this.dexterity -= 3;
				this.constitution += 2;
				break;
			case "Dwarf":
				this.race = "Human";
				this.constitution -= 3;
				break;
			case "Orc":
				this.race = "Human";
				this.strength -= 3;
				this.constitution -= 1;
				break;
			case "Gnome":
				this.race = "Human";
				this.intelligence -= 2;
				this.strength += 3;
				this.wisdom -= 1;
				break;
		}
	}
	
	/*
	* Method Name: classChange
	*Author: Fardin Abbassi
	* Creation Date; October 10, 2023
	* Modified Date: October 16, 2023
	*Description: Changes the character's class, resets hit points, then rerolls hit points based on class
	* @Parameters: (String desiredClass)
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: n/a
	*Throws/Exceptions: n/a
	*/
	public void classChange(String desiredClass) {
		this.hitPoints = 0;
		this.characterClass = desiredClass;
		int currentLevel = this.level;
		this.level = 0;
		System.out.println("Adjusting level based on new class...");
		for (int i = 0; i < currentLevel; i++) {
			levelUp(desiredClass);
		}
		
	}
	
	/*
	* Method Name: writeRec
	*Author: Fardin Abbassi
	* Creation Date; October 16, 2023
	* Modified Date: October 16, 2023
	*Description: writes a record of a character to the RandomAccessFile
	* @Parameters: (RandomAccessFile raf, int recordNumber)
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: RandomAccessFile
	*Throws/Exceptions: IOException
	*/
     public void writeRec (RandomAccessFile raf, int recordNumber) throws IOException {
        raf.seek (recordNumber * recLength); 	// move pointer to position in file
        int nameLen = characterName.length ();	// determine if there are more than 20 characters
		int padLen = 0;						// calculate the number of blanks that need to be 
        if (nameLen > 20)					//  added to maintain a length of 20
            nameLen = 20;
        else
            padLen = 20 - nameLen;
        for (int i = 0 ; i < characterName.length () ; i++)	// write the character name
            raf.writeChar (characterName.charAt (i));
        if (padLen > 0)	{					// write the extra blanks
            for (int i = 0 ; i < padLen ; i++)
                raf.writeChar (' ');
        }
        // repeat for race
        nameLen = race.length ();
		padLen = 0;						
        if (nameLen > 20)					
            nameLen = 20;
        else
            padLen = 20 - nameLen;
        for (int i = 0 ; i < race.length () ; i++)	
            raf.writeChar (race.charAt (i));
        if (padLen > 0)	{				
            for (int i = 0 ; i < padLen ; i++)
                raf.writeChar (' ');
        }        
        // repeat for characterClass
        nameLen = characterClass.length ();
		padLen = 0;						
        if (nameLen > 20)					
            nameLen = 20;
        else
            padLen = 20 - nameLen;
        for (int i = 0 ; i < characterClass.length () ; i++)	
            raf.writeChar (characterClass.charAt (i));
        if (padLen > 0)	{				
            for (int i = 0 ; i < padLen ; i++)
                raf.writeChar (' ');
        }   
        
        // write the ints to the file
        raf.writeInt (level);
        raf.writeInt (hitPoints);
        raf.writeInt (strength);
        raf.writeInt (constitution);
        raf.writeInt (intelligence);
        raf.writeInt (wisdom);
        raf.writeInt (dexterity);
        raf.writeInt (charisma);
   }
 
	/*
	* Method Name: readRec
	*Author: Fardin Abbassi
	* Creation Date; October 16, 2023
	* Modified Date: October 16, 2023
	*Description: writes a record of a character to the RandomAccessFile
	* @Parameters: (RandomAccessFile raf, int recNum)
	* @Return Value: void
	* Data Type: n/a
	* Dependencies: RandomAccessFile
	*Throws/Exceptions: IOException
	*/
    public void readRec (RandomAccessFile raf, int recNum) throws IOException   {
        raf.seek (recNum * recLength);
        String temp = "";
        for (int i = 0 ; i < 20 ; i++)
            temp = temp + raf.readChar();
        characterName = temp.trim();
        temp = "";
        for (int i = 0 ; i < 20 ; i++)
            temp = temp + raf.readChar();
        race = temp.trim();
        temp = "";
        for (int i = 0 ; i < 20 ; i++)
            temp = temp + raf.readChar();
        characterClass = temp.trim();
        // read the long and doubles from the file
        level = raf.readInt();
        hitPoints = raf.readInt();
        strength = raf.readInt();
        constitution = raf.readInt();
        intelligence = raf.readInt();
        wisdom = raf.readInt();
        dexterity = raf.readInt();
        charisma = raf.readInt();
    }  // end readRec
}
