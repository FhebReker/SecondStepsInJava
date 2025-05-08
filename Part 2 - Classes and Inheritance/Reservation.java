 public class Reservation{
	private int amountOfTravelers;
	public Reservation(int amountOfTravelers) {
		this.amountOfTravelers = amountOfTravelers;
	}
	public Reservation() {
		this.amountOfTravelers = 0;
		}
	public void setAmountOfTravelers(int amountOfTravelers) {
		this.amountOfTravelers = amountOfTravelers;		
	}
	public int getAmountOfTravelers() {
		return amountOfTravelers;
	}
}
