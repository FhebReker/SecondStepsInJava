import java.util.ArrayList;
public class Hotel extends Reservation{
	private int amountRooms;
	private int ratingWanted;
	private String Date;
	public Hotel(int amountRooms,int ratingWanted, String Date ) {
		this.amountRooms = amountRooms;
		this.ratingWanted = ratingWanted;
		this.Date = Date;
	}
	public Hotel() {
		this.amountRooms = 0;
		this.ratingWanted = 0;
		this.Date = "";
	}
	public int getAmountRooms() {
		return amountRooms;
	}
	public void setAmountRooms(int amountRooms) {
		this.amountRooms = amountRooms;
	}
	public int getRatingWanted() {
		return ratingWanted;
	}
	public void setRatingWanted(int ratingWanted) {
		this.ratingWanted = ratingWanted;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
}


Car:
import java.util.ArrayList;
public class Car extends Reservation{
	private String Type;
	private long licenceNum;
	public Car(String Type, long licenceNum) {
		this.Type = Type;
		this.licenceNum = licenceNum;
	}
	public Car() {
		this.Type = "";
		this.licenceNum = 0;
	}
	public String getType() {
		return Type;
	}
	public long getLicenceNum() {
		return licenceNum;
	}
}



Airline:
import java.util.ArrayList;
public class Airline extends Reservation{
	private String airline;
	private int flightNum;
	private String Date;
	private int Time;
	public Airline(String airline, int flightNum, String Date, int Time) {
		this.airline = airline;
		this.flightNum = flightNum;
		this.Date = Date;
		this.Time = Time;
	}
	public Airline() {
		this.airline = "";
		this.flightNum = 0;
		this.Date = "";
		this.Time = 0;
		}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public int getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
}

