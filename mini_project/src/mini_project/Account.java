package mini_project;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 직렬화시 포함된 필드
	 * 	memNum(static), num, id, pw, phone, remainTime, status, isMem
	 */
	private static final long serialVersionUID = -236989942385663711L;
	private static int memNum = 0;	// 가입횟수(회원번호 발급에 참조, 자동증가)
	private int num;				// 회원번호(memNum에 의해 발급)
	private String id;				// 아이디
	private String pw;				// 비밀번호
	private String phone;			// 전화번호
	private int remainTime;			// 남은시간
	private boolean status;			// 이용상태
	private boolean isMem;			// 회원/비회원 여부
	
	{
		memNum++;					// 생성자 호출(계정추가)마다 가입횟수 증가
	}
	
	// 비회원 가입시 생성자
	public Account(String phone) {
		this.num = memNum;
		this.id = "guest";
		this.pw = "0000";
		this.phone = phone;
		this.remainTime = 0;
		this.status = false;
		this.isMem = false;
	}
	
	// 회원 가입시 생성자
	public Account(String id, String pw, String phone) {
		this.num = memNum;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.remainTime = 0;
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
		return remainTime;
	}

	public void setRemain_time(int remainTime) {
		this.remainTime = remainTime;
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
			return "계정정보 [회원번호=" + num + ", 아이디=" + id + ", 전화번호=" + phone + ", 남은시간=" + remainTime + ", 상태="
					+ status + ", 회원/비회원=회원]";
		} else {
			return "계정정보 [회원번호=" + num + ", 아이디=" + id + ", 전화번호=" + phone + ", 남은시간=" + remainTime + ", 상태="
					+ status + ", 회원/비회원=비회원]";
		}
	}
}
