/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-10-08
 @Description: Asks the user for information to display a booking for a reservation.
***********************************************
*/

import java.util.Scanner;

public class BookingInterface{
	static Scanner scan = new Scanner(System.in);
	public static void main (String[] args)    {
		// Initializing variables with empty values
		String name = "", hotelDate = "", flyingWith = "", flightDate = "";
		int numberOfTravelers = 0, numberOfRooms = 0, hotelRating = 0, flightNum = 0, hourOfFlight = 0, carSelect = 0;
		// String array for car types, long array for licence numbers
		long[] licenceNums = {129323, 332112, 21313, 23123, 5664456, 4435435};
		String [] carType = {"Audi A6", "Mazda CX-30", "Dodge Challenger", "Tesla Model 3", "KIA EV6", "Ford F-150"};
		char yesOrNo = 'a';
		
		// setting name and numberOfTravelers
		name = showMeTheValue(name, "What's your name?");
		numberOfTravelers = showMeTheValue(numberOfTravelers, "How many people are travelling today?");

		// Asking if user wants to book a hotel
		while (yesOrNo == 'a') {
			System.out.println("Would you like to book a hotel? \n(Type either Y or N)");
			yesOrNo = scan.nextLine().toUpperCase().charAt(0);
			switch (yesOrNo) {
				case 'Y':
					numberOfRooms = showMeTheValue(numberOfRooms, "How many rooms would you like to book?");
					// Entered value must be between 1 and 5
					while (hotelRating <= 0) {
						hotelRating = showMeTheValue(hotelRating, "What rating would you like your hotel to be?");
						if (hotelRating <= 0 || hotelRating > 5) {
							System.out.println("Type in a number between 1 and 5, please.");
							hotelRating = 0;
						}
					}
					hotelDate = showMeTheValue(hotelDate, "When would you like to book your hotel? \n(Use yyyy/mm/dd format)");
					break;
				case 'N':
					break;
				default :
				// must receive a yes or no response
					System.out.println("Please type either Y or N to proceed.");
					yesOrNo = 'a';
					break;
			}
		}
		// resets yesOrNo to ask for car rental
		yesOrNo = 'a';	
		
		// Asking if user wants to rent a car
		while (yesOrNo == 'a') {
			System.out.println("Would you like to rent a car? \n(Type either Y or N)");
			yesOrNo = scan.nextLine().toUpperCase().charAt(0);
			switch (yesOrNo) {
				case 'Y':
					System.out.println("Which type of car are you looking for?");
					// creating the index for the car and licence number arrays
					carSelect = showMeTheValue(carSelect, "Which type of car are you looking for?"
							+ "\n1) Sedan \n2) SUV \n3) Coupe "
							+ "\n4) Electric Sedan\n5) Electric SUV \n6) Pickup Truck "
							+ "\nPick any other option to cancel") - 1;
					break;
				case 'N':
					break;
				default :
				// must receive a yes or no response
					System.out.println("Please type either Y or N to proceed.");
					yesOrNo = 'a';
					break;
			}
		}
		// resets yesOrNo to ask for flight
		yesOrNo = 'a';	

		// Asking user if they are arriving by plane
		while (yesOrNo == 'a') {
			System.out.println("Are you arriving by air? \n(Type either Y or N)");
			yesOrNo = scan.nextLine().toUpperCase().charAt(0);
			switch (yesOrNo) {
				case 'Y':
					flyingWith = showMeTheValue(flyingWith, "Which airline are you flying with?");
					flightNum = showMeTheValue(flightNum, "What is your flight number?");
			        flightDate = showMeTheValue(flightDate, "What date is the flight? \n(Use yyyy/mm/dd format)");
					hourOfFlight = showMeTheValue(hourOfFlight, "What hour is your flight? \n(24 hour clock)");
			        break;
				case 'N':
					break;
				default :
				// must receive a yes or no response
					System.out.println("Please type either Y or N to proceed.");
					yesOrNo = 'a';
					break;
			}

		}	
		// closes scanner
		scan.close();
		System.out.println();
		
		// Initializes objects
		Airline a = new Airline(flyingWith, flightNum, flightDate, hourOfFlight);
		Car c = new Car();
		// initializes car object if index is valid
		if (carSelect >= 0 && carSelect <= 5) {
			c = new Car(carType[carSelect], licenceNums[carSelect]);
		}
		Hotel h = new Hotel(numberOfRooms, hotelRating, hotelDate);
		h.setAmountOfTravelers(numberOfTravelers);
		c.setAmountOfTravelers(numberOfTravelers);
		a.setAmountOfTravelers(numberOfTravelers);
		primaryClient pc = new primaryClient();
		pc.setName(name);
		
		// Always displays name and number of travelers
		System.out.println("Name: " + pc.getName());
		System.out.println("Number of travelers today: " + h.getAmountOfTravelers());
		System.out.println("---------------------------------------");
		
		// displays hotel booking if properly initialized
		if (h.getAmountRooms() != 0) {
			System.out.println("Number of rooms: " + h.getAmountRooms());
			System.out.println("Hotel rating: " + h.getRatingWanted());
			System.out.println("Check-in date: " + h.getDate());
			System.out.println("---------------------------------------");
		}
		// displays car rented if properly initialized
		if (c.getLicenceNum() != 0) {
			System.out.println("Car type: " + c.getType());
			System.out.println("Licence number: " + c.getLicenceNum());
			System.out.println("---------------------------------------");
		}
		// displays flight information if properly initialized
		if (a.getFlightNum() != 0) {
			System.out.println("Airline: " + a.getAirline());
			System.out.println("Flight number: " + a.getFlightNum());
			System.out.println("Date of flight: " + a.getDate());
			System.out.println("Time of flight: " + a.getTime() + ":00");
			System.out.println("---------------------------------------");
		}
	}
	
/* Method Name: showMeTheValue
* @Author: Fardin Abbassi 
* @Date: 10 04 2023
* @Modified: 10 08 2023
* @Description: purpose of method is to have the user enter an integer through a scanner
* @Parameters: int checkedValue, String phrase
* @Returns: int
* Dependencies: N/A
* Throws/Exceptions: NumberFormatException 
*/
	public static int showMeTheValue(int checkedValue, String phrase){
		while (checkedValue == 0) {			
			System.out.println(phrase);
			try{
				checkedValue = Integer.parseInt(scan.nextLine());
			}
			catch (NumberFormatException ex){
				System.out.println("Invalid entry, please type in a number.");
			}
		}
		return checkedValue;
	} 

/* Method Name: showMeTheValue
* @Author: Fardin Abbassi 
* @Date: 10 04 2023
* @Modified: 10 08 2023
* @Description: purpose of method is to have the user enter a string through a scanner
* @Parameters: String checkedValue, String phrase
* @Returns: String
* Dependencies: N/A
* Throws/Exceptions: N/A 
*/
	public static String showMeTheValue(String checkedValue, String phrase){
		while (checkedValue == "") {
			System.out.println(phrase);
			checkedValue = scan.nextLine();
		}
		return checkedValue;
	} 
}
