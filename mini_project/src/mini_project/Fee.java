package mini_project;

public class Fee extends Merchandise {
	/**
	 *	직렬화시 포함된 필드
	 *	time
	 */
	private static final long serialVersionUID = -7492384142269642113L;
	private int time;
	
	public Fee() {
		super();
		this.time = 0;
	}
	
	public Fee(String name, int itemNum, int price, int time) {
		super(name, itemNum ,price);
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
}
