package mini_project.vo;

/**
 *	직렬화시 포함된 필드
 *	time
 */
public class Fee extends Merchandise {
	private static final long serialVersionUID = -7492384142269642113L;
	private int time;		// 시간값(요금결제시 추가될 시간)
	private boolean member;	// 회원/비회원 요금구분
	
	public Fee() {
		super();
		this.time = 0;
	}
	
	public Fee(String name, int itemNum, int price, int time, boolean member) {
		super(name, itemNum ,price);
		this.time = time;
		this.member = member;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}
	
}
