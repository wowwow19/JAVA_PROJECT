package miniProject.utils;

import static miniProject.utils.CommonUtils.*;

import java.util.ArrayList;

import miniProject.vo.Account;

public class Utils {
	/**
	 * 기본생성자
	 */
	public Utils() {}
	
	// 회원가입
		
	/**
	 * 회원가입을 위한 아이디, 패스워드, 전화번호 입력
	 * @author 민우
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
			
			if(checkInput(id, pw, pwCheck, phone, list)) {
				tmpUser.setId(id);
				tmpUser.setPw(pw);
				tmpUser.setPhone(phone);
				break;
			}
		}
		return tmpUser;
	}
	/**
	 * 회원가입 정보에 대한 유효성 검증을 실시하여 boolean값을 반환
	 * @author 민우
	 * @param id
	 * 			이미 존재하는 아이디인지 검증할 아이디 문자열
	 * @param pw
	 * 			5자 이상의 문자로 이루어진 비밀번호인지 검증할 비밀번호 문자열
	 * @param pwCheck
	 * 			비밀번호 확인을 위해 재입력된 비밀번호확인 문자열
	 * @param list
	 * 			이미 존재하는 아이디인지 검색할 회원목록 ArrayList
	 * @return
	 * 			모두 통과하면 true를 반환, 하나라도 통과하지 못할 경우 false를 반환
	 */
	public boolean checkInput(String id, String pw, String pwCheck, String phone, ArrayList<Account> list) {
		ArrayList<String> errMsg = new ArrayList<>();
		
		errMsg.add(checkId(id, list));
		errMsg.add(checkPwLength(pw));
		errMsg.add(checkPwMatch(pw, pwCheck));
		errMsg.add(checkPhone(phone));
		
		if(!printErr(errMsg)) return false;
		
		return true;
	}
	
	/**
	 * 
	 * @author 민우
	 * @param id
	 * @param list
	 * @return
	 */
	public String checkId(String id, ArrayList<Account> list) {
		for(int i = 0; i < list.size(); i++) {
			if(id.equals(list.get(i).getId())) {
				return id + "(은)는 이미 존재하는 아이디입니다.";
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @author 민우
	 * @param pw
	 * @return
	 */
	public String checkPwLength(String pw) {
		if(pw.length() < 5) {
			return "비밀번호는 5자 이상이어야 합니다.";
		}
		return null;
	}
	
	/**
	 * 
	 * @author 민우
	 * @param pw
	 * @param pwCheck
	 * @return
	 */
	public String checkPwMatch(String pw, String pwCheck) {
		if(pw.equals(pwCheck)) {
			return null;
		}
		return "입력한 비밀번호와 일치하지 않습니다.";
	}
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public String checkPhone(String phone) {
		for(int i = 0; i < phone.length(); i++) {
			if(phone.charAt(i) < '0' || phone.charAt(i) > '9') {
				return "전화번호는 10자리 또는 11자리의 숫자로 입력해야 합니다.";
			}
		}
		return null;
	}
	
	/**
	 * 회원가입 정보 입력 중 발생한 오류의 갯수와 내용을 출력
	 * @author 민우
	 * @param list
	 * 			오류 메시지 문자열을 담은 ArrayList
	 */
	public boolean printErr(ArrayList<String> list) {
		int num = 0;
		System.out.println("=====================================================================================================");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) != null) {
				System.out.println(list.get(i));
				num++;
			}
		}
		if(num != 0) {
			System.err.println("[" + num + "]" + "개의 에러 : 정보입력 실패");	
			return false;
		} else {
			return true;
		}
	}
	
	// 로그인
	public void printLoginMenu() {
		System.out.println("===========================================< 로 그 인 >==============================================");
		System.out.println("    ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐      ");
		System.out.println("    │      1. 회 원     │  │     2. 비회원     │  │     3. 관리자     │  │     4. 이 전      │      ");
		System.out.println("    └───────────────────┘  └───────────────────┘  └───────────────────┘  └───────────────────┘      ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 결제
	public void printPayMenu() {
		System.out.println("===========================================< 요금결제 >==============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │    1. 시간선택    │  │     2. 다 음      │  │    3. 로그아웃    │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 상품구매
	public void printPurchaseMenu() {
		System.out.println("=============================================<  구 매  >==============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │     1. 식사류     │  │     2. 사이드     │  │     3. 스 낵      │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("    ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐      ");
		System.out.println("    │      4. 음 료     │  │    5. 차/커피     │  │      6. 이 전     │  │    7. 로그아웃    │      ");
		System.out.println("    └───────────────────┘  └───────────────────┘  └───────────────────┘  └───────────────────┘      ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 이용상태관리
	public void printControlStatMenu() {
		System.out.println("===========================================< 이용상태관리 >=============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │    1. 이용시작    │  │    2. 일시정지    │  │    3. 이용종료    │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │      4. 이 전     │  │     5. 다 음      │  │    6. 로그아웃    │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 회원관리
	/**
	 * 
	 * @author 민우
	 */
	public void printMngMemMenu() {
		System.out.println("===========================================< 회원관리 >==============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │  1. 회원정보수정  │  │    2. 회원삭제    │  │     3. 이 전      │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	/**
	 * 
	 * @author 민우
	 * @param idx
	 * @param list
	 */
	public void updateInfo(int idx, ArrayList<Account> list) {
		boolean run = true;
		
		while(run) {
			System.out.println("=====================================================================================================");
			System.out.println("회원정보는 비밀번호, 전화번호, 남은시간만 수정가능합니다.");
			System.out.print("비밀번호(기존값:" +  list.get(idx).getPw() + ") > ");
			String pw = nextLine();
			System.out.print("비밀번호확인(기존값:" +  list.get(idx).getPw() + ") > ");
			String pwCheck = nextLine();
			System.out.print("전화번호(기존값:" +  list.get(idx).getPhone() + ") > ");
			String phone = nextLine();
			System.out.print("남은시간(기존값:" +  list.get(idx).getRemainTime() + ") > ");
			int remainTime = nextInt();
			
			if(checkInput(pw, pwCheck, phone)) {
				list.get(idx).setPw(pw);
				list.get(idx).setPhone(phone);
				list.get(idx).setRemainTime(remainTime);
				save("memberList.ser", list);
				System.out.println("수정이 완료되었습니다.");
				break;
			}			
		}
	}
	
	/**
	 * 
	 * @author 민우
	 * @param pw
	 * @param pwCheck
	 * @param phone
	 * @return
	 */
	public boolean checkInput(String pw, String pwCheck, String phone) {
		ArrayList<String> errMsg = new ArrayList<>();
		
		errMsg.add(checkPwLength(pw));
		errMsg.add(checkPwMatch(pw, pwCheck));
		errMsg.add(checkPhone(phone));
		
		if(!printErr(errMsg)) return false;
		
		return true;
	}
	
	/**
	 * 
	 * @author 민우
	 * @param list
	 */
	public void updateMem(ArrayList<Account> list) {
		boolean run = true;
		
		printMemberList(list);
		while(run) {
			System.out.print("수정할 회원번호를 입력하세요. > ");
			int idx = findByNum(nextInt(), list);
			
			printMemberInfo(idx, list);
			System.out.print("찾는 회원이 맞습니까? 1. 예 2. 아니요 3. 취소 > ");
			int choice = nextInt();
			
			if(choice == 1) {
				updateInfo(idx, list);
				System.out.println("수정이 완료되었습니다.");
				break;
			} else if(choice == 3) {
				return;
			}
		}
		return;
	}
	
	/**
	 * 
	 * @author 민우
	 * @param list
	 */
	public void deleteMem(ArrayList<Account> list) {
		boolean run = true;
		
		printMemberList(list);
		while(run) {
			System.out.print("삭제할 회원번호를 입력하세요. > ");
			int idx = findByNum(nextInt(), list);
			
			printMemberInfo(idx, list);
			System.out.print("찾는 회원이 맞습니까? 1. 예 2. 아니오 3. 취소 > ");
			int check = nextInt();
			
			if(check == 1) {
				System.out.print("정말로 삭제하시겠습니까?(삭제시 복구할 수 없습니다) 1. 예 2. 아니오 > ");
				int doubleCheck = nextInt();
				
				if(doubleCheck == 1) {
					list.remove(idx);
					save("memberList.ser", list);
					System.out.println("삭제가 완료되었습니다.");
					break;								
				}
			} else if(check == 3) return;
		}
	}
}