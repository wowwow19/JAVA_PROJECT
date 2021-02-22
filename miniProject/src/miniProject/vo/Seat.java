package miniProject.vo;

/**
 * 주문기록 작성을 위한 좌석 클래스
 * @author 민우
 *
 */
public class Seat {
	private int num;			// 좌석번호
	private Account user;		// 점유중인 사용자
	private Food food;			// 주문한 음식
	private String orderDate;	// 주문일자
	
	public Seat() {}
	
	public Seat(int num, Account user) {
		this.num = num;
		this.user = user;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
