public class primaryClient{
	private String name;
	private Reservation res;
	
	public primaryClient(String name, Reservation spot) {
		this.setName(name);
		res = spot;
	}
	public primaryClient() {
		this.setName("");
		res = new Reservation();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
