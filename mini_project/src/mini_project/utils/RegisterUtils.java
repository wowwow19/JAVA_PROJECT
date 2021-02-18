package mini_project.utils;

import static mini_project.utils.CommonUtils.*;
import java.util.ArrayList;

import mini_project.vo.Account;
/**
 * 
 * @author 민우
 *
 */
public class RegisterUtils {
	
	/**
	 * 기본생성자
	 */
	public RegisterUtils() {}
	
	/**
	 * 패스워드 문자열을 입력받아 첫글자와 마지막 글자를 제외하고 *(별표)로 출력
	 * @param pw
	 * 			출력할 패스워드 문자열
	 */
	public void printPw(String pw) {
		for (int i = 0; i < pw.length(); i++) {
			if (i == 0 || i == pw.length()-1) {
				System.out.print(pw.charAt(i));
			} else {
				System.out.print("*");					
			}
		}
	}
	
	/**
	 * 회원가입 정보 입력 후 입력된 정보 확인하기 위해 출력
	 * @param tmpUser
	 * 				정보가 입력된 임시 Account 객체
	 */
	public void printInputInfo(Account tmpUser) {
		System.out.println("────── 입력정보 ──────");
		System.out.println("아이디 : " + tmpUser.getId());
		System.out.print("비밀번호 : ");
		printPw(tmpUser.getPw());
		System.out.println();
		System.out.println("전화번호 : " + tmpUser.getPhone());
	}
	
	/**
	 * 회원가입을 위한 아이디, 패스워드, 전화번호 입력
	 * @param tmpUser
	 * 				가입정보가 담길 임시 Account 객체
	 * @param list
	 * 				입력된 아이디가 이미 존재하는지 검색할 Account 배열
	 * @return
	 * 				가입정보가 담긴 임시 Account 객체
	 */
	public Account insertInfo(Account tmpUser, ArrayList<Account> list) {
		boolean run = true;
		
		while(run) {
			System.out.println("=====================================================================================================");
			System.out.println("회원가입 정보를 입력합니다.");
			System.out.print("아이디 > ");
			String id = nextLine();
			System.out.print("비밀번호(5자이상) > ");
			String pw = nextLine();
			System.out.print("비밀번호확인 > ");
			String pwCheck = nextLine();
			System.out.print("전화번호 > ");
			String phone = nextLine();
			
			if(checkInput(id, pw, pwCheck, list)) {
				tmpUser.setId(id);
				tmpUser.setPw(pw);
				tmpUser.setPhone(phone);
				break;
			}
		}
		return tmpUser;
	}
	
	public boolean checkInput(String id, String pw, String pwCheck, ArrayList<Account> list) {
		boolean checkId = true;
		boolean checkPwLength = true;
		boolean checkPwMatch = true;
		ArrayList<String> errMsg = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(id.equals(list.get(i).getId())) {
				errMsg.add(id + "은(는) 이미 존재하는 아이디입니다.");
				checkId = false;
			}
		}
		if(pw.length() < 5) {
			errMsg.add("비밀번호는 5자 이상이어야 합니다.");
			checkPwLength = false;
		}
		if(!pw.equals(pwCheck)) {
			errMsg.add("입력한 비밀번호와 일치하지 않습니다.");
			checkPwLength = false;
		}
		if(errMsg.size() != 0) {
			printErr(errMsg);			
		}
		
		return checkId && checkPwLength && checkPwMatch;
	}
	
	public void printErr(ArrayList<String> list) {
		System.out.println("=====================================================================================================");
		System.out.println("[" + list.size() + "]" + "개의 에러 : 회원가입 실패");
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + "." + list.get(i));
		}
	}
}