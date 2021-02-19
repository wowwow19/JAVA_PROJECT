package mini_project.vo;

import static mini_project.utils.CommonUtils.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 직렬화시 포함된 필드
	 * 	memNum(static), num, id, pw, phone, remainTime, status, member
	 */
	private static final long serialVersionUID = -236989942385663711L;
	private static int memNum;		// 가입횟수(회원번호 발급에 참조, 자동증가)
	private int num;				// 회원번호(memNum에 의해 발급)
	private String id;				// 아이디
	private String pw;				// 비밀번호
	private String tmpPw;			// 가입시 확인용 비밀번호
	private String phone;			// 전화번호
	private int remainTime;			// 남은시간
	private boolean status;			// 이용상태
	private boolean member;			// 회원/비회원 여부
	
	static {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("memNum.ser"));
			memNum = dis.readInt();	
			dis.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	{
		memNum++;
		save("memNum.ser", memNum);
	}
	
	// 기본 생성자
	public Account() {}
	
	// 비회원 가입시 생성자
	public Account(String phone) {
		this.num = memNum;
		this.id = "guest";
		this.pw = "0000";
		this.phone = phone;
		this.remainTime = 0;
		this.status = false;
		this.member = false;
	}
	
	// 회원 가입시 생성자
	public Account(String id, String pw, String phone) {
		this.num = memNum;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.remainTime = 0;
		this.status = false;
		this.member = true;
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
	
	public String getTmpPw() {
		return tmpPw;
	}
	
	public void setTmpPw(String tmpPw) {
		this.tmpPw = tmpPw;
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
	
	public static int getMemNum() {
		return memNum;
	}

	public static void setMemNum(int memNum) {
		Account.memNum = memNum;
	}

	public int getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "계정정보 [회원번호=" + num + ", 아이디=" + id + ", 전화번호=" + phone + ", 남은시간=" + remainTime + ", 상태="
				+ status + ", 회원/비회원=" + (member ? "회원]" : "비회원]");
	}

}
