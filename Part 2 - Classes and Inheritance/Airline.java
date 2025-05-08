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
