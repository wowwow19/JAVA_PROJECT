package mini_project;

public class Account {
	private static int memNum = 0;
	private int num;
	private String id;
	private String pw;
	private String phone;
	private int remain_time;
	private boolean status;
	private boolean isMem;
	
	{
		memNum++;
	}
	
	// 비회원 가입시 생성자
	public Account() {
		this.num = memNum;
		this.id = "guest";
		this.pw = "0000";
		this.phone = "0000";
		this.remain_time = 0;
		this.status = false;
		this.isMem = false;
	}
	
	// 회원 가입시 생성자
	public Account(String id, String pw, String phone) {
		this.num = memNum;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.remain_time = 0;
		this.status = false;
		this.isMem = true;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRemain_time() {
		return remain_time;
	}

	public void setRemain_time(int remain_time) {
		this.remain_time = remain_time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isMem() {
		return isMem;
	}

	public void setMem(boolean isMem) {
		this.isMem = isMem;
	}

	@Override
	public String toString() {
		if (isMem) {
			return "계정정보 [회원번호=" + num + ", 아이디=" + id + ", 전화번호=" + phone + ", 남은시간=" + remain_time + ", 상태="
					+ status + ", 회원/비회원=회원]";
		} else {
			return "계정정보 [회원번호=" + num + ", 아이디=" + id + ", 전화번호=" + phone + ", 남은시간=" + remain_time + ", 상태="
					+ status + ", 회원/비회원=비회원]";
		}
	}

			
}
