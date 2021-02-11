package mini_project;

public class Food extends Merchandise {
	private String kind;
	private int stock;
	
	public Food() {
		super();
		this.stock = 0;
	}
	
	public Food(String kind, String name, int price) {
		super(name, price);
		this.kind = kind;
		this.stock = 2;
	}
	
	public Food(String kind, String name, int price, int stock) {
		super(name, price);
		this.kind = kind;
		this.stock = stock;
	}
	
	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Food [name=" + this.getName() + ", price=" + this.getPrice() + ", kind=" + kind + ", stock=" + stock + "]";
	}
	
}
