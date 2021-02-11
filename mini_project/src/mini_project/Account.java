package mini_project;

public class Account {
	private String id;
	private String pw;
	private String phone;
	private int remain_time;
	private boolean status;
	private boolean isMem;
	
	public Account() {
		this.id = "guest";
		this.pw = "0000";
		this.phone = "0000";
		this.remain_time = 0;
		this.status = false;
		this.isMem = false;
	}
	
	public Account(String id, String pw, String phone) {
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.remain_time = 0;
		this.status = false;
		this.isMem = true;
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
			
}
