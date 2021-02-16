package mini_project;

import java.io.Serializable;

public class Merchandise implements Serializable {
	/**
	 *	직렬화시 포함된 필드
	 *	name, price, itemNum
	 */
	private static final long serialVersionUID = -4280881976853207054L;
	private String name;
	private int price;
	private int itemNum;
	
	public Merchandise() {
		this(null, 0, 0);
	}
	
	public Merchandise(String name, int itemNum, int price) {
		this.name = name;
		this.itemNum = itemNum;
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

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}	
	
}