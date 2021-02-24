package miniProject.service;

import static miniProject.utils.CommonUtils.*;
import static miniProject.utils.Utils.insertOrderInfo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import miniProject.vo.*;

@SuppressWarnings("unchecked")
public class Service {
	public long start;												// 로그인(사용시작)시의 시간
	public long end;												// 사용종료시의 시간
	private int sales;												// 매출
	private int purchase;											// 매입
	private Account loginUser = null;								// 현재 로그인된 계정의 정보를 담을 객체
	private Seat[] seats = new Seat[5];								// 현재좌석현황
	private ArrayList<Account> members = new ArrayList<Account>();	// 회원목록
	private ArrayList<Food> menuList = new ArrayList<Food>();		// 메뉴목록
	private ArrayList<Fee> feeList = new ArrayList<Fee>();			// 요금목록
	private ArrayList<Seat> orderLog = new ArrayList<Seat>();		// 좌석별 사용/주문기록
	
	{
		try {
			ObjectInputStream ois = null;
			ois = inputObjectFile("memberList.ser");			
			members = (ArrayList<Account>) ois.readObject();
			ois.close();
			
			ois = inputObjectFile("menuList.ser");
			menuList = (ArrayList<Food>) ois.readObject();
			ois.close();
			
			ois = inputObjectFile("feeList.ser");			
			feeList = (ArrayList<Fee>) ois.readObject();
			ois.close();
			
			DataInputStream dis = new DataInputStream(new FileInputStream("transaction.ser"));	// 장부에 저장된 매출/매입액 불러오기
			sales = dis.readInt();	
			purchase = dis.readInt();
			dis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < seats.length; i++) {		// 좌석목록의 좌석객체 초기화 수행(null값으로 채우지 않기 위함)
			seats[i] = new Seat();
		}
		
	}
	
	// 회원가입
	/**
	 * @author 민우
	 */
	public void register() {
		while(true) {
			Account tmpUser = insertInfo();
			printMemberInfo(tmpUser);
			try {
				System.out.print("입력한 정보가 맞습니까? 1. 예 2. 아니오 3. 종료 > ");
				int choice = nextInt();
				
				switch(choice) {
				case 1:
					members.add(tmpUser);
					save("memberList.ser", members);
					return;
				case 2:
					decMemNum();
					break;
				case 3:
					decMemNum();
					return;
				default:
					decMemNum();
					System.out.println("잘못 입력했습니다.");
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Account findBy(String id) {
		for(int i = 0; i < members.size(); i++) {
			if(id.equals(members.get(i).getId())) return members.get(i);
		}
		return null;
	}
	
	/**
	 * 회원가입 정보를 입력받아 해당 정보로 생성된 Account 객체를 반환
	 * @author 민우
	 * @return
	 * 		입력받은 정보로 생성된 Account 객체
	 */
	Account insertInfo() {
		System.out.println("=====================================================================================================");
		System.out.println("회원가입 정보를 입력합니다.");
		String id = insertId();
		String pw = insertPw();
		String phone = insertPhone();
		
		Account tmpUser = new Account(id, pw, phone);
		return tmpUser;
	}
	
	/**
	 * 아이디를 입력받아 검사 후 통과시 아이디 문자열을 반환
	 * @author 민우
	 * @return
	 * 		아이디 문자열
	 */
	String insertId() {
		while(true) {
			System.out.print("아이디 > ");
			String id = nextLine();

			if(checkId(id)) {
				return id;
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean checkId(String id) {
		if(!isOnlyChar(id)) {
			System.out.println("[오류] 아이디에는 공백이 포함될 수 없습니다.");
			return false;
		}
		if(isIdExist(id)) {
			System.out.println("[오류] " + id + "은(는) 이미 존재하는 아이디입니다.");
			return false;
		}
		return true;
	}
	
	/**
	 * 비밀번호를 입력받아 검사 후 통과시 비밀번호 문자열을 반환
	 * @author 민우
	 * @return
	 * 		비밀번호 문자열
	 */
	String insertPw() {
		while(true) {
			System.out.print("비밀번호(5자이상) > ");
			String pw = nextLine();
			System.out.print("비밀번호확인 > ");
			String pwck = nextLine();
			
			if(checkPw(pw, pwck)) {
				return pw;
			}
		}
	}
	
	/**
	 * 
	 * @param pw
	 * @param pwck
	 * @return
	 */
	boolean checkPw(String pw, String pwck) {
		if(!isOnlyChar(pw)) {
			System.out.println("[오류] 비밀번호에는 공백이 포함될 수 없습니다.");
			return false;
		}
		if(!isPwLengthEnough(pw)) {
			System.out.println("[오류] 비밀번호는 5자 이상이어야 합니다.");
			return false;
		}
		if(!isPwMatch(pw, pwck)) {
			System.out.println("[오류] 입력한 비밀번호와 일치하지 않습니다.");
			return false;
		}
		return true;
	}
	
	/**
	 * 전화번호를 입력받아 검사 후 통과시 전화번호 문자열을 반환
	 * @author 민우
	 * @return
	 * 		전화번호 문자열
	 */
	String insertPhone() {
		while(true) {
			System.out.print("전화번호(숫자로만입력) > ");
			String phone = nextLine();

			if(checkPhone(phone)) {
				return phone;
			}
		}
	}
	/**
	 * 
	 * @param phone
	 * @return
	 */
	boolean checkPhone(String phone) {
		if(!isPhoneOnlyNum(phone) || !isPhoneLengthEnough(phone)) {
			System.out.println("[오류] 전화번호는 10자리 또는 11자리의 숫자로만 입력해야 합니다.");
			return false;
		}
		return true;
	}
		
	/**
	 * 입력받은 문자열에 공백이 포함되어있는지 검사한 후 boolean값을 반환
	 * @param str
	 * 			검사할 문자열
	 * @return
	 * 			공백이 포함되었을 경우 false 반환, 포함되지 않았을 경우 true 반환
	 */	
	boolean isOnlyChar(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 32) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 아이디가 회원목록에 이미 존재하는지 검사한 후 boolean값을 반환
	 * @author 민우
	 * @param id
	 * 			존재하는지 찾을 id 문자열
	 * @return
	 * 			이미 존재할경우 true 반환, 존재하지 않을경우 false 반환
	 */
	boolean isIdExist(String id) {
		Account tmp = findBy(id);
		
		if(tmp != null) {
			return true;			
		}
		
		return false;
	}
				
	
	/**
	 * 비밀번호가 5자 이상인지 검사한 후 boolean값을 반환
	 * @author 민우
	 * @param pw
	 * 			검사할 pw 문자열
	 * @return
	 * 			5자 미만일 경우 false 반환, 5자 이상일 경우 true 반환
	 */
	boolean isPwLengthEnough(String pw) {
		if(pw.length() < 5) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 입력한 비밀번호와 비밀번호확인 문자열이 동일한지 검사한 후 boolean값을 반환
	 * @author 민우
	 * @param pw
	 * 			최초에 입력한 비밀번호 문자열
	 * @param pwCheck
	 * 			비밀번호 확인 문자열
	 * @return
	 * 			일치하지 않을 경우 false , 일치할 경우 true 반환
	 */
	boolean isPwMatch(String pw, String pwCheck) {
		if(pw.equals(pwCheck)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 입력한 전화번호 문자열이 숫자로만 이루어져있는지 검사한 후 boolean값을 반환
	 * @param phone
	 * 			입력한 전화번호 문자열
	 * @return
	 * 			숫자 외의 문자가 포함되었을 경우 false 반환, 숫자만으로 이루어진 경우 true 반환
	 */
	boolean isPhoneOnlyNum(String phone) {
		for(int i = 0; i < phone.length(); i++) {
			if(phone.charAt(i) < '0' || phone.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	boolean isPhoneLengthEnough(String phone) {
		if(phone.length() < 10 || phone.length() > 11) return false;
		return true;
	}
		
	// 로그인
	/**
	 * @author 보경
	 */
	public void login() {
		String id = "";
		String pw = "";
		String phone = "";
		while(true) {
			printLoginMenu();
			int input = nextInt();
			switch (input) {
			case 1: // 회원 로그인
				System.out.print("아이디 > ");
				id = nextLine();
				System.out.print("비밀번호 > ");
				pw = nextLine();
				if(findBy(id, pw) != null) {
					loginUser = findBy(id, pw);
					System.out.println("로그인성공");
					System.out.println(loginUser.isStatus());
					if(loginUser == members.get(0)) {
						adminMenu();
					}
					else {
						memberMenu();
					}
				}
				else if(findBy(id) == null) {
					System.out.println("아이디 확인 후 다시 입력");
				}
				else {
					System.out.println("비밀번호 확인 후 다시 입력");
				}
				break;
				
			case 2: // 비회원 로그인
				System.out.print("전화번호 입력 > ");
				phone = nextLine();
				loginUser = new Account(phone); // 비회원 생성자 호출
				System.out.println("임시회원번호: " + loginUser.getNum());
				System.out.println(loginUser);
				memberMenu();
				break;
				
			case 3: // 이전
				return;
			default:
				System.out.println("1~3값으로 다시 입력");
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * 			사용자가 입력한 id 값
	 * @return 회원정보에 일치하는 값이 있으면 해당 계정정보를 반환
	 */
	Account findBy(String id, String pw) {
		if(findBy(id) != null) {
			for(int i = 0 ; i < members.size(); i++) {
				if(id.equals(members.get(i).getId()) && pw.equals(members.get(i).getPw())) {
					return members.get(i);
				}
			}
		}
		return null;
	}
	
	void printLoginMenu() {
		System.out.println("============================================ 홈 > 로그인 ============================================");
		System.out.println("                ┌─────────┐  ┌─────────┐  ┌─────────┐               ");
		System.out.println("                │    1. 회 원      │  │    2. 비회원     │  │     3. 이 전     │               ");
		System.out.println("                └─────────┘  └─────────┘  └─────────┘               ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 회원메뉴
	/**
	 * 일반회원 로그인시 탐색가능한 메뉴를 출력
	 * @author 민우
	 */
	public void memberMenu() {
		boolean run = true;
		
		try {
			while(run) {
				printMemberMenu();
				int input = nextInt();
				
				switch(input) {
				case 1: // 좌석선택
					selectSeat();
					break;
				case 2:	// 요금결제
					pay();
					break;
				case 3:	// 음식구매
					purchase();
					break;
				case 4:	// 이용상태관리
					controlStat();
					break;
				case 5:	// 정보수정
					updateInfo();
					break;
				case 6:	// 로그아웃
					logout();
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			}
		} catch(NumberFormatException e) {
			System.out.println("숫자로 입력하세요.");
		}
	}
	
	// 좌석선택
	/**
	 * @author 민우
	 */
	public void selectSeat() {
		boolean run = true;
		
		while(run) {
			for(int i = 0; i < seats.length; i++) {
				if(seats[i].getUser() == loginUser) {
					System.out.println(loginUser.getId() + "님은 이미 " + (i+1) + "번 좌석을 사용중입니다.");
					return;
				}
			}
			printSelectSeatMenu(seats);
			int input = nextInt()-1;
			
			if(seats[input].getUser() == null) {
				seats[input].setUser(loginUser);
				break;					
			} else {
				System.out.println("이미 이용중인 좌석입니다.");
			}
		}
	}
	
	/**
	 * 
	 * @param seats
	 */	void printSelectSeatMenu(Seat[] seats) {
		System.out.println("======================================= 회원메뉴 > 좌석선택 =========================================");
		printEmptySeat();
		System.out.println("=====================================================================================================");
		System.out.print("이용할 좌석번호를 입력하세요. > ");
	}
	
	/**
	 * 현재 좌석현황 배열을 입력받아 비어있는 좌석을 출력
	 * @param seats
	 * 			현재 좌석현황 배열
	 */
	void printEmptySeat() {
		for(int i = 0; i < seats.length; i++) {
			if(seats[i].getUser() == null) {
				System.out.println((i+1)+ "번 좌석" + " : " + "◻︎ 이용가능");				
			} else {
				System.out.println((i+1) + "번 좌석" + " : " + "☒ 이용중");
			}
		}
	}
	
	// 요금결제
	
	// 요금결제
	/**
	 * @author 찬희
	 */
	public void pay() {
		boolean run = true;
		while(run) {
			try {

				printPayMenu();
				int input = nextInt();
				System.out.println(loginUser.isMember());
				switch(input) {
				case 1:	
//					for(int i = 0; i < feeList.size() ; i++) {
//						
//						if(loginUser.isMember() == feeList.get(i).isMember()) {
//							System.out.println(printFeeList());
//						}
//					}
					printFeeList();
					// 입력 받음
					// 
					System.out.println("사용할 요금제 선택");
					input = nextInt();
					Fee fe = findBy(input);
					System.out.println(fe);
					System.out.println("요금을 결제하시겠습니까?");
					
					System.out.println("1.네 | 2.아니오");
					input = nextInt();
					if(input == 1) {
						sales += fe.getPrice();
						int ff = fe.getTime();
						loginUser.setRemainTime(ff + loginUser.getRemainTime());
						System.out.println(sales + "원");
						System.out.println(loginUser.getRemainTime() + "분");
						save("transaction.ser", sales,purchase);
						save("memberList.ser", members);
						break;
					}else if(input == 2) {
						memberMenu(); 
					}
					
//					int increseTime = feeList.get(input).getTime(); // << 증가될 시간
//					System.out.println(increseTime + "분");
//					int price = fe.getItemNum().getPrice(); // << 매출에 추가될 요금
//					System.out.println(price + "원");
//					System.out.println("요금을 결제하시겠습니까?");
//					System.out.println("1.네 | 2.아니오");
//					input = nextInt();
					
					
					
					
//					switch(input) {
//					case 1 :sales += price;
//					 System.out.println("결제가 완료되었습니다.");
//					case 2 :memberMenu();
//					}
					// 요금선택
						// isMem이 false(비회원)일 때\
							// 1. 비회원 요금목록(feeListNoMem) 출력
							// 2. Fee객체의 itemNum으로 요금 선택
							// 3. 선택한 요금 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 이용상태관리(ControlStat())로 넘어가기
						// isMem이 true(회원)일 때
							// 1. 회원 요금목록(feeListMem) 출력
							// 2. Fee객체의 itemNum으로 요금 선택
							// 3. 선택한 요금 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 이용상태관리(ControlStat())로 넘어가기
				case 2:	// 다음 : 이용상태관리
					return;	
				default:
					System.out.println("다시 입력하세요");
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
	void printFeeList() {
        String[] menus = {"품번", "상품명", "판매가격"};
        String[] str = new String[3];
        int[] len = {10, 20, 10};

        System.out.println("==========================================< 요금목록 >===============================================");
        System.out.println(format(menus, len));
        for(int i = 0; i < feeList.size(); i++) {
        	if(loginUser.isMember() == feeList.get(i).isMember()) {
            str[0] = Integer.toString(feeList.get(i).getItemNum());
            str[1] = feeList.get(i).getName();
            str[2] = Integer.toString(feeList.get(i).getPrice());
            System.out.println(format(str, len));
        	}
        }
        System.out.println("=====================================================================================================");
    }
	
	Fee findBy(int num) {
        for(int i = 0; i < feeList.size(); i++) {
        	if(num==feeList.get(i).getItemNum() && loginUser.isMember() == feeList.get(i).isMember())  
        return feeList.get(i);
        }
        return null;
    }
	
	void printPayMenu() {
		System.out.println("=======================================  회원메뉴 > 요금결제  ========================================");
		System.out.println("                            ┌─────────┐  ┌─────────┐                            ");
		System.out.println("                            │   1. 시간선택    │  │     2. 이 전     │                            ");
		System.out.println("                            └─────────┘  └─────────┘                            ");
		System.out.println("======================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}

	// 이용상태관리
	/**
	 * @author 보경
	 */
	@SuppressWarnings("deprecation")
	public void controlStat() {
//		start = (int)System.nanoTime();
//		System.out.println("1.일시정지 2. 이용종료 3. 이전(요금결제) 4. 다음(음식구매)");
//		int input = nextInt();
//		switch(input) {
//		case 1:
//			pauseOfUse();
//			break;
//		case 2:
//			end = (int)System.nanoTime();
//			System.out.println("이용 종료됨. 총 이용시간: " + (end-start));
//			System.out.println("남은시간: ");
//			loginUser = null;// 로그아웃
//			break;
//		case 3: case 4:
//			nestedSwitch();
//		}
		boolean run = true;
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat totalUsingTime = new SimpleDateFormat("HH:mm:ss");
		ArrayList<Long> times = new ArrayList<>();
		
		while(run) {
			printControlStatMenu();
			int input = nextInt();
			
			switch(input) {
			case 1:
				if(!loginUser.isStatus()) {
					times.add(System.currentTimeMillis());
					loginUser.setStatus(true);
				} else {
					System.out.println("이미 이용중입니다.");
				}
				break;
			case 2:
				if(loginUser.isStatus()) {		// 이용중상태
					times.add(System.currentTimeMillis());
					loginUser.setStatus(false);
				} else {
					System.out.println("이용중인 상태가 아닙니다.");
				}
				break;					
			case 3:
				if(loginUser.isStatus()) {
					long usingTime = 0;
					times.add(System.currentTimeMillis());
					for(int i = 1; i < times.size(); i+=2) {
						System.out.println("시작시간 : " + totalUsingTime.format(new Date(times.get(i-1))));
						System.out.println("종료시간 : " + totalUsingTime.format(new Date(times.get(i))));
						usingTime += (times.get(i) - times.get(i-1));
					}
//					System.out.println("사용시간 : " + (int)usingTime / 1000);
					Date date = new Date(usingTime);
					date.setHours(0);
					System.out.println("사용시간 : " + totalUsingTime.format(date));
					loginUser = null;
					times = null;
					return;
				} else {
					System.out.println("이용중인 상태가 아닙니다.");
					break;
				}
			case 4:
				return;
			}
		}
	}
	
	void printControlStatMenu() {
		System.out.println("====================================  회원메뉴 > 이용상태관리  ======================================");
		System.out.println("    ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐   ");
		System.out.println("    │   1. 이용시작    │  │   2. 일시정지    │  │   3. 이용종료    │  │    4. 이 전      │   ");
		System.out.println("    └─────────┘  └─────────┘  └─────────┘  └─────────┘   ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	void pauseOfUse() {
		int pauseTime = (int)System.nanoTime();
		System.out.println("일시정지됨. 현재까지 이용시간: " + (pauseTime - start));
		System.out.println("1. 이용재개 2. 이용종료 3. 이전(요금결제) 4. 다음(음식구매)");
		int input = nextInt();
		switch(input) {
		case 1:
			resumeOfUse();
			break;
		case 2:
			System.out.println("이용 종료됨. 총 이용시간: " + (pauseTime-start));
			System.out.println("남은시간: ");
			loginUser = null;// 로그아웃
			break;
		case 3: case 4:
			nestedSwitch();
		}
	}
	
	void resumeOfUse() {
		int resumeTime = (int)System.nanoTime();
		System.out.println("이용이 재개되었습니다.");
		controlStat();
	}
	
	void nestedSwitch() {
		System.out.println("3. 이전(요금결제) 4. 다음(음식구매)");
		int reInput = nextInt();
		switch(reInput) {
		case 3:
			pay();
			break;
		case 4:
			purchase();
			break;
		default:
			System.out.println("올바른 값 입력.");
		}
	}
	
	// 회원정보수정(회원메뉴)
	void updateInfo() {
		printMemberInfo(loginUser);
		System.out.println("============================== 회원메뉴 > 정보수정 =================================");
		System.out.println("회원정보는 비밀번호, 전화번호만 수정가능합니다.");
		String pw = updatePw();
		String phone = updatePhone();
		
		loginUser.setPw(pw);
		loginUser.setPhone(phone);			
	}

	String updatePw() {
		while(true) {
			System.out.print("비밀번호(기존값:" +  loginUser.getPw() + ") > ");
			String pw = nextLine();
			System.out.print("비밀번호확인(기존값:" +  loginUser.getPw() + ") > ");
			String pwck = nextLine();
			
			if(checkPw(pw, pwck)) {
				return pw;
			}
		}
	}
	
	String updatePhone() {
		while(true) {
			System.out.print("전화번호(기존값:" +  loginUser.getPhone() + ") > ");
			String phone = nextLine();

			if(checkPhone(phone)) {
				return phone;
			}
		}
	}
		
	void logout() {
		if(loginUser.isStatus()) {
			System.out.println("로그아웃 전 이용을 종료해주세요.");
			return;
		}
		int idx = findSeatByUser(loginUser);
		if(idx != -1) {
			seats[idx] = new Seat();			
		}
		loginUser = null;
	}
	
	int findSeatByUser(Account user) {
		for(int i = 0; i < seats.length; i++) {
			if(seats[i].getUser() == user) return i;
		}
		return -1;
	}

	
	/**
	 * @author 소연
	 */
	public void purchase() {
		int itemNum = 0;
		String menuName = "";
		int menuStock = 0;
		int Price = 0;

		boolean run = true;

		while (run) {
			try {
				// printPurchaseMenu();
				System.out.println("주문코너 입니다. 카테고리의 숫자를 입력하십시오.");
				System.out.println("1. 식사 2, 사이드 3. 스낵 4. 음료 5. 커피/차");
				int input = nextInt();
				boolean menuChk = true;
				
				switch (input) {
				case 1: // 식사류
						// 1. mealList의 식사메뉴 하나씩 출력하기
					System.out
							.println("	" + "주문번호" + "\t" + "메뉴" + "				" + "가격" + "\t   " + "재고량" + "\t");
					System.out.println("---------------------------------------------------------------------------------------------");
					for (int i = 0; i < 24; i++) { // menuList 출력
						System.out.println("\t" + menuList.get(i).getItemNum() + "\t" + menuList.get(i).getName()
								+ "\t		" + menuList.get(i).getPrice() + "\t	" + menuList.get(i).getStock());
					}
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("주문하실 메뉴의 번호를 입력해주세요 >");

					// 2. Food객체의 itemNum으로 메뉴 선택
			
					
					// 3. 선택한 메뉴 갯수와 결제여부 묻기


	                  
					// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
					
					// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
					break;
					
				case 2: // 사이드
						// 1. sideList의 사이드메뉴 하나씩 출력하기
					System.out
							.println("	" + "주문번호" + "\t" + "메뉴" + "				" + "가격" + "\t   " + "재고량" + "\t");
					System.out.println("---------------------------------------------------------------------------------------------");
					for (int i = 24; i <=35 ; i++) { // menuList 출력
						System.out.println("\t" + menuList.get(i).getItemNum() + "\t" + menuList.get(i).getName()
								+ "\t		" + menuList.get(i).getPrice() + "\t	" + menuList.get(i).getStock());
					}
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("주문하실 메뉴의 번호를 입력해주세요 >");
					// 2. Food객체의 itemNum으로 메뉴 선택
					//itemNum = nextInt(); // 상품번호 입력
					// 3. 선택한 메뉴 갯수와 결제여부 묻기
	
					// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
					
					// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
					
					break;
				case 3: // 스낵
						// 1. snackList의 스낵메뉴 하나씩 출력하기
					System.out
							.println("	" + "주문번호" + "\t" + "메뉴" + "				" + "가격" + "\t   " + "재고량" + "\t");
					System.out.println("---------------------------------------------------------------------------------------------");
					for (int i = 36; i <= 49; i++) { // menuList 출력
						System.out.println("\t" + menuList.get(i).getItemNum() + "\t" + menuList.get(i).getName()
								+ "\t		" + menuList.get(i).getPrice() + "\t	" + menuList.get(i).getStock());
					}
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("주문하실 메뉴의 번호를 입력해주세요 >");
					// 2. Food객체의 itemNum으로 메뉴 선택
					//itemNum = nextInt(); // 상품번호 입력
					// 3. 선택한 메뉴 갯수와 결제여부 묻기

					// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
					
					// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
					
					break;
				case 4: // 음료
						// 1. beverageList의 음료메뉴 하나씩 출력하기
					System.out
							.println("	" + "주문번호" + "\t" + "메뉴" + "				" + "가격" + "\t   " + "재고량" + "\t");
					System.out.println("---------------------------------------------------------------------------------------------");
					for (int i = 50; i <=66; i++) { // menuList 출력
						System.out.println("\t" + menuList.get(i).getItemNum() + "\t" + menuList.get(i).getName()
								+ "\t		" + menuList.get(i).getPrice() + "\t	" + menuList.get(i).getStock());
					}
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("주문하실 메뉴의 번호를 입력해주세요 >");
					// 2. Food객체의 itemNum으로 메뉴 선택
					//itemNum = nextInt(); // 상품번호 입력
					// 3. 선택한 메뉴 갯수와 결제여부 묻기

					// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
					
					// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
					
					break;
				case 5: // 차/커피
						// 1. teaList의 차메뉴 하나씩 출력하기
					System.out
							.println("	" + "주문번호" + "\t" + "메뉴" + "				" + "가격" + "\t   " + "재고량" + "\t");
					System.out.println("---------------------------------------------------------------------------------------------");
					for (int i = 67; i <=96; i++) { // menuList 출력
						System.out.println("\t" + menuList.get(i).getItemNum() + "\t" + menuList.get(i).getName()
								+ "\t		" + menuList.get(i).getPrice() + "\t	" + menuList.get(i).getStock());
					}
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("주문하실 메뉴의 번호를 입력해주세요 >");
					// 2. Food객체의 itemNum으로 메뉴 선택
					//itemNum = nextInt(); // 상품번호 입력
					// 3. 선택한 메뉴 갯수와 결제여부 묻기

					// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
					
					// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
					break;
				case 6: // 이전(이용상태관리)
					controlStat();
				case 7: // 로그아웃(초기화면으로 이동)
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
	
	void printPurchaseMenu() {
		System.out.println("========================================  회원메뉴 > 구 매  =========================================");
		System.out.println("                ┌─────────┐  ┌─────────┐  ┌─────────┐               ");
		System.out.println("                │    1. 식사류     │  │    2. 사이드     │  │     3. 스 낵     │               ");
		System.out.println("                └─────────┘  └─────────┘  └─────────┘               ");
		System.out.println("                ┌─────────┐  ┌─────────┐  ┌─────────┐               ");
		System.out.println("                │     4. 음 료     │  │    5. 차/커피    │  │     6. 이 전     │               ");
		System.out.println("                └─────────┘  └─────────┘  └─────────┘               ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 관리자메뉴
	/**
	 * 관리자계정(admin)으로 로그인했을 경우 각 관리자 기능을 호출하는 메뉴를 출력
	 * @author 민우
	 */
	public void adminMenu() {
		boolean run = true;
		
		try {
			while(run) {
				printAdminMenu();
				int input = nextInt();
				
				switch(input) {
				case 1:	// 회원관리
					manageMember();
					break;
				case 2:	// 재고관리
					manageStock();
					break;
				case 3:	// 매출관리
					manageSales();
					break;
				case 4:	// 종료
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			}
		} catch(NumberFormatException e) {
			System.out.println("숫자로 입력하세요.");
		}
	}
	
	void printAdminMenu() {
		System.out.println("===================================== 홈 > 로그인 > 관리자메뉴 ======================================");
		System.out.println("    ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐   ");
		System.out.println("    │   1. 회원관리    │  │   2. 재고관리    │  │   3. 매출관리    │  │   4. 로그아웃    │   ");
		System.out.println("    └─────────┘  └─────────┘  └─────────┘  └─────────┘   ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 회원관리
	
	/**
	 * @author 민우
	 */
	public void manageMember() {
		boolean run = true;
		
		try {
			while(run) {
				printMngMemMenu();
				int input = nextInt();
				
				switch(input) {
				case 1: // 회원정보수정
					updateMem();
					break;
				case 2: // 회원삭제
					deleteMem();
					break;
				case 3: // 이전(관리자메뉴출력화면)
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			}
		} catch(NumberFormatException e) {
			System.out.println("숫자로 입력하세요.");
		}
	}
	
	void printMngMemMenu() {
		System.out.println("======================================== 관리자메뉴 > 회원관리 ======================================");
		System.out.println("                ┌─────────┐  ┌─────────┐  ┌─────────┐               ");
		System.out.println("                │ 1. 회원정보수정  │  │   2. 회원삭제    │  │     3. 이 전     │               ");
		System.out.println("                └─────────┘  └─────────┘  └─────────┘               ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	// 회원관리 > 회원정보수정
	void updateMem() {
		printMemberList(members);
		while(true) {
			try {
				System.out.print("수정할 회원번호를 입력하세요. > ");
				int idx = findByNum(nextInt());
				if(idx == -1) {
					System.out.println("찾을 수 없습니다.");
				} else {
					printMemberInfo(members.get(idx));
					System.out.print("찾는 회원이 맞습니까? 1. 예 2. 아니요 3. 취소 > ");
					int check = nextInt();
					
					if(check == 1) {
						updateInfo(idx);
						System.out.println("수정이 완료되었습니다.");
						break;
					} else if(check == 3) {
						return;
					}		
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
		return;
	}
	
	int findByNum(int num) {
		for(int i = 0; i < members.size(); i++) {
			if(num == members.get(i).getNum()) return i;
		}
		return -1;
	}	
	
	void updateInfo(int idx) {
		System.out.println("================================ 관리자메뉴 > 회원관리 > 회원정보수정 ===============================");
		System.out.println("회원정보는 비밀번호, 전화번호, 남은시간만 수정가능합니다.");
		String pw = updatePw(idx);
		String phone = updatePhone(idx);
		int total = updateRemainTime(idx);
		
		members.get(idx).setPw(pw);
		members.get(idx).setPhone(phone);
		members.get(idx).setRemainTime(total);
	}
	
	String updatePw(int idx) {
		while(true) {
			System.out.print("비밀번호(기존값:" +  members.get(idx).getPw() + ") > ");
			String pw = nextLine();
			System.out.print("비밀번호확인(기존값:" +  members.get(idx).getPw() + ") > ");
			String pwck = nextLine();
			
			if(checkPw(pw, pwck)) {
				return pw;
			}
		}
	}
	
	String updatePhone(int idx) {
		while(true) {
			System.out.print("전화번호(기존값:" +  members.get(idx).getPhone() + ") > ");
			String phone = nextLine();

			if(checkPhone(phone)) {
				return phone;
			}
		}
	}

	int updateRemainTime(int idx) {
		while(true) {
			int remainTime = members.get(idx).getRemainTime();
			System.out.println("남은시간을 늘리려면 양수로, 줄이려면 음수로 입력하세요.");
			System.out.print("남은시간(기존값:" + remainTime + ", 단위:분) > ");
			int addTime = nextInt();
			int total = remainTime + addTime;

			if(checkRemainTime(remainTime, total)) {
				return total;
			} 
		}
	}
	
	boolean checkRemainTime(int remainTime, int total) {
		if(!isRemainTimeOnlyNum(remainTime)) {
			System.out.println("[오류] 남은시간은 양수 또는 음수의 정수만 입력해야 합니다.");
			return false;
		}
		if(isTimeUnderZero(total)) {
			System.out.println("[오류] 남은시간은 0보다 작을 수 없습니다.");
			return false;
		}
		return true;
	}
	
	boolean isRemainTimeOnlyNum(int remainTime) {
		if(remainTime < 0) {
			remainTime = -remainTime;
		}
		String str = Integer.toString(remainTime);		
		for(int i = 0; i < str.length(); i++) {
			if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}
	
	boolean isTimeUnderZero(int total) {
		if(total < 0) return true;
		return false;
	}
	
	// 회원관리 > 회원삭제
	/**
	 * 회원 목록에서 회원 객체의 삭제를 수행
	 * @author 민우
	 * @param list
	 * 			회원 목록
	 */
	void deleteMem() {
		printMemberList(members);
		while(true) {
			try {
				System.out.println("================================ 관리자메뉴 > 회원관리 > 회원삭제 ===================================");
				System.out.print("삭제할 회원번호를 입력하세요. > ");
				int idx = findByNum(nextInt());
				if(idx == -1) {
					System.out.println("찾을 수 없습니다.");
				} else {
					printMemberInfo(members.get(idx));
					System.out.print("찾는 회원이 맞습니까? 1. 예 2. 아니오 3. 취소 > ");
					int check = nextInt();
					
					if(check == 1) {
						checkDeleteMem(idx);
						break;					
					} else if(check == 3) {
						return;
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}		
		}
	}
	
	/**
	 * 회원 객체 삭제 전, 다시 한 번 삭제 여부를 물음
	 * @param idx
	 * 			회원목록 내 삭제할 회원 객체의 인덱스
	 * @param list
	 * 			회원 목록
	 */
	void checkDeleteMem(int idx) {
		System.out.print("정말로 삭제하시겠습니까?(삭제시 복구할 수 없습니다) 1. 예 2. 아니오 > ");
		int check = nextInt();
		if(check == 1) {
			members.remove(idx);
			save("memberList.ser", members);
			System.out.println("삭제가 완료되었습니다.");
		}
	}
	
	/**
	 * @author 소연
	 */
	public void manageStock() {
		boolean run = true;
		int[] arr = {0, 24, 36, 50, 67, 97};
		
		try {
			while(run) {
				printManageStockMenu();
				int input = nextInt();
				
				
				if(input == 6) return;
				purchase += stockOrder(arr[input-1], arr[input]);
				save("transaction.ser", sales, purchase);				
			}
		} catch(NumberFormatException e) {
			System.out.println("숫자로 입력하세요.");
		}
	}
	
	void printManageStockMenu() {
		System.out.println("=====================================  관리자메뉴 > 재고관리  =======================================");
		System.out.println("                ┌─────────┐  ┌─────────┐  ┌─────────┐               ");
		System.out.println("                │    1. 식사류     │  │    2. 사이드     │  │     3. 스 낵     │               ");
		System.out.println("                └─────────┘  └─────────┘  └─────────┘               ");
		System.out.println("                ┌─────────┐  ┌─────────┐  ┌─────────┐               ");
		System.out.println("                │     4. 음 료     │  │    5. 차/커피    │  │     6. 이 전     │               ");
		System.out.println("                └─────────┘  └─────────┘  └─────────┘               ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	int stockOrder(int start, int end) {
		int purchase = 0;
		boolean run = true;
		
		while(run) {
			int idx = 0;
			
			printFoodList(start, end, menuList);
			System.out.println("추가 주문할 물품의 품번을 입력하세요. > ");
			int itemNum = nextInt();
			
			idx = findByItemNum(start, end, itemNum, menuList);
			
			if(idx != -1) {
				printFoodInfo(idx, menuList);
				System.out.println("찾는 물품이 맞습니까? 1. 예 2. 아니오 3. 취소 > ");
				int check = nextInt();
				
				switch(check) {
				case 1:
					purchase = insertOrderInfo(idx, menuList);
					return purchase;
				case 2:
					break;
				case 3:
					return purchase;
				default:
					System.out.println("다시 입력하세요.");
				}
			}
		}
		return purchase;
	}
	
	Food findItemByNum(int start, int end, int itemNum) {
		for(int i = start; i < end; i++) {
			if(menuList.get(i).getItemNum() == itemNum) return menuList.get(i);
		}
		return null;
	}
	
	
	/**
	 * @author 찬희
	 */
	public void manageSales() {
		// 임시
		System.out.print("매출 : ");
		printNumPerThou(sales);
		System.out.println();
		System.out.print("매입 : ");
		printNumPerThou(purchase);
		System.out.println();
		System.out.print("영업이익 : ");
		printNumPerThou(getTotal());
		System.out.println();
	}

	public ArrayList<Account> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Account> members) {
		this.members = members;
	}
	
	/**
	 * 매출액(sales)과 매입액(purchase)의 차인 영업이익(total)을 반환하는 getter
	 * @return
	 * 		매출액(sales)과 매입액(purchase)의 차인 영업이익(total)
	 */
	public int getTotal() {
		return sales - purchase;
	}
}
