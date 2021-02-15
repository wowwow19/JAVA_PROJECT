package mini_project;

import static mini_project.Service.*;
import static mini_project.Utils.*;

public class RegisterTest {
	
	// 관리자계정 생성
	static {
		getMembers().add(new Account("admin", "9999", "0000"));
	}
		
	public static void register() {
		
		boolean run = true;
		String id = "";
		
		System.out.println("회원가입 정보를 입력해주세요.");

		do {
			System.out.print("아이디 > ");
			id = nextLine();
			
			for (int i = 0; i < getMembers().size(); i++) {
				if (getMembers().get(i).getId().equals(id)) {
					run = false;
					System.out.println("사용할 수 없는 아이디입니다. 다시 입력하세요.");
				} else {
					run = true;
				}
			
			}
		} while (!run);
		
		System.out.print("비밀번호 > ");
		String pw = nextLine();
		System.out.print("전화번호 > ");
		String phone = nextLine();
		
		getMembers().add(new Account(id, pw, phone));
		System.out.println("회원가입이 완료되었습니다.");
		
//		for (int i = 0; i < getMembers().size(); i++) {
//			System.out.println(getMembers().get(i));
//		}
				
	}
	
	public static void main(String[] args) {
		register();
	}
}
