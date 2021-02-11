package mini_project;

public class Merchandise {
	private String name;
	private int price;
	
	public Merchandise() {
		this(null, 0);
	}
	
	public Merchandise(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}