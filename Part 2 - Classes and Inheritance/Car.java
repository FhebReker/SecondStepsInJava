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
