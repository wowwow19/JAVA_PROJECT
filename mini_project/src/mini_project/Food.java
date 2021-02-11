package mini_project;

public class Food extends Merchandise {
	private int stock;
	
	public Food() {
		super();
		this.stock = 0;
	}
	
	public Food(String name, int price, int stock) {
		super(name, price);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
