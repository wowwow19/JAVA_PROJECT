package mini_project.vo;

public class Food extends Merchandise {
	/**
	 * 직렬화시 포함된 필드
	 * kind, stock
	 */
	private static final long serialVersionUID = -6863341935051929057L;
	private String kind;	// 음식종류
	private int stock;		// 재고수
	
	public Food() {
		super();
		this.stock = 0;
	}
	
	public Food(String kind, int itemNum, String name, int price) {
		super(name, itemNum, price);
		this.kind = kind;
		this.stock = 2;
	}
	
	public Food(String kind, int itemNum, String name, int price, int stock) {
		super(name, itemNum, price);
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
