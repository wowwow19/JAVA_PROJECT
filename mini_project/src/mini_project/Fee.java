package mini_project;

public class Fee extends Merchandise {
	private int time;
	
	public Fee() {
		super();
		this.time = 0;
	}
	
	public Fee(String name, int price, int time) {
		super(name, price);
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
}
