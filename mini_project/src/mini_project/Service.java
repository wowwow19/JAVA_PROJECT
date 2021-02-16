package mini_project;

import static mini_project.Utils.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Service {
	private static int sales = 0;	// 총매출
	private static ArrayList<Account> members = new ArrayList<Account>();	// 회원목록
	private static ArrayList<Food> mealList = new ArrayList<Food>();		// 음식목록
	private static ArrayList<Food> sideList = new ArrayList<Food>();		// 사이드목록
	private static ArrayList<Food> snackList = new ArrayList<Food>();		// 과자목록
	private static ArrayList<Food> beverageList = new ArrayList<Food>();	// 음료목록
	private static ArrayList<Food> teaList = new ArrayList<Food>();			// 커피/차목록
	private static ArrayList<Fee> feeListNoMem = new ArrayList<Fee>();		// 비회원요금목록
	private static ArrayList<Fee> feeListMem = new ArrayList<Fee>();		// 회원요금목록
	
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("members.ser")));			
			members = (ArrayList<Account>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("mealList.ser")));			
			mealList = (ArrayList<Food>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("sideList.ser")));			
			sideList = (ArrayList<Food>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("snackList.ser")));			
			snackList = (ArrayList<Food>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("beverageList.ser")));			
			beverageList = (ArrayList<Food>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("teaList.ser")));			
			teaList = (ArrayList<Food>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("feeListNoMem.ser")));			
			feeListNoMem = (ArrayList<Fee>) ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("feeListMem.ser")));			
			feeListMem = (ArrayList<Fee>) ois.readObject();
			ois.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 민우
	public void register() {
		String id;
		String pw;
		String tmpPw;
		String phone;
		boolean run = true;
		
		System.out.println("회원가입 정보를 입력해주세요.");
		do {
			boolean existId = true;
			do {
				System.out.print("아이디 > ");
				id = nextLine();
				
				for (int i = 0; i < members.size(); i++) {
					if (members.get(i).getId().equals(id)) {
						existId = true;
						System.out.println("사용할 수 없는 아이디입니다. 다시 입력하세요.");
					} else {
						existId = false;
					}
					
				}
			} while (existId);
			
			boolean equalPw = true;
			do {
				boolean checkPw = true;
				do {
					System.out.print("비밀번호(5자이상) > ");
					pw = nextLine();
					if (pw.length() > 4) {
						checkPw = true;
					} else {
						System.out.println("비밀번호는 5자이상이어야 합니다.");
						checkPw = false;
					}
				} while(!checkPw);
				System.out.print("비밀번호확인 > ");
				tmpPw = nextLine();
				
				if (pw.equals(tmpPw)) {
					equalPw = true;
				} else {
					equalPw = false;
					System.out.println("입력한 비밀번호와 일치하지 않습니다. 다시 입력해주세요.");
				}
			} while (!equalPw);
			
			System.out.print("전화번호 > ");
			phone = nextLine();
			
			System.out.println("────── 입력정보 ──────");
			System.out.println("아이디 : " + id);
			System.out.print("비밀번호 : ");
			for (int i = 0; i < pw.length(); i++) {
				if (i == 0 || i == pw.length()-1) {
					System.out.print(pw.charAt(i));
				} else {
					System.out.print("*");					
				}
			}
			System.out.println();
			System.out.println("전화번호 : " + phone);
			System.out.print("입력한 정보가 맞습니까? 1. 예 2. 아니오 3. 종료 > ");
			int choice = nextInt();
			if (choice == 1) {
				members.add(new Account(id, pw, phone));
				save("members.ser", members);
				System.out.println("회원가입이 완료되었습니다.");
				return;
			} else if (choice == 3) {
				return;
			}
		} while(run);
	}
	
	// 보경
	public void login() {
		boolean run = true;
		
		while(run) {
			try {
				int idx = 0;
				printLoginMenu();
				int input = nextInt();
				
				switch (input) {
				case 1:
					System.out.print("아이디 > ");
					String id = nextLine();
					for(int i = 0 ; i < members.size() ; i++) {
						if(members.get(i).getId().equals(id)) {
							idx = i;
						}
					}
					System.out.print("비밀번호 > ");
					String pw = nextLine();
					if(members.get(idx).getPw().equals(pw)) {
						printWelcom(id);
						pay();
					}
					else {
						System.out.println("아이디나 비밀번호 확인");
					}
					break;
					
				case 2:
					System.out.print("전화번호 입력 > ");
					String phone = nextLine();
					members.add(new Account(phone));		// 비회원 생성자 호출
					printWelcom("guest");
					pay();
					break;
				case 3:
					System.out.println("관리자로그인");
					break;
				default:
					return;
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
	
	// 찬희
	public void pay() {
		boolean run = true;
		
		while(run) {
			try {
				printPayMenu();
				int input = nextInt();
				
				switch(input) {
				case 1:	// 요금선택
						// id가 "guest"일 때
							// 1. 비회원 요금목록(feeListNoMem) 출력
							// 2. Fee객체의 itemNum으로 요금 선택
							// 3. 선택한 요금 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 이용상태관리(ControlStat())로 넘어가기
						// 회원일 때
							// 1. 회원 요금목록(feeListMem) 출력
							// 2. Fee객체의 itemNum으로 요금 선택
							// 3. 선택한 요금 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 이용상태관리(ControlStat())로 넘어가기
				case 2:	// 다음 : 이용상태관리
					controlStat();
				case 3:	// 로그아웃 : 초기화면으로 돌아감
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
	
	// 보경
	public void controlStat() {
//		printControlStatMenu();
		purchase();
	}
	
	// 소연
	public void purchase() {
		boolean run = true;
		
		while(run) {
			try {
				printPurchaseMenu();
				int input = nextInt();
				
				switch(input) {
				case 1:	// 식사류
							// 1. mealList의 식사메뉴 하나씩 출력하기
							// 2. Food객체의 itemNum으로 메뉴 선택
							// 3. 선택한 메뉴 갯수와 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
				case 2:	// 사이드
							// 1. sideList의 사이드메뉴 하나씩 출력하기
							// 2. Food객체의 itemNum으로 메뉴 선택
							// 3. 선택한 메뉴 갯수와 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
				case 3:	// 스낵
							// 1. snackList의 스낵메뉴 하나씩 출력하기
							// 2. Food객체의 itemNum으로 메뉴 선택
							// 3. 선택한 메뉴 갯수와 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
				case 4:	// 음료
							// 1. beverageList의 음료메뉴 하나씩 출력하기
							// 2. Food객체의 itemNum으로 메뉴 선택
							// 3. 선택한 메뉴 갯수와 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
				case 5:	// 차/커피
							// 1. teaList의 차메뉴 하나씩 출력하기
							// 2. Food객체의 itemNum으로 메뉴 선택
							// 3. 선택한 메뉴 갯수와 결제여부 묻기
							// 4. 결제시 금액(price)만큼 총매출(sales)에 추가
							// 5. 결제시 구매한 갯수만큼 Food객체의 재고(stock)값 감소시키기
				case 6:	// 이전(이용상태관리)
					controlStat();
				case 7:	// 로그아웃(초기화면으로 이동)
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
	
	
	// 민우
	public void manageMember() {
		
	}
	
	// 소연
	public void manageStock() {
		
	}
	
	// 찬희
	public void manageSales() {
		
	}

	
	// 총 매출 getter, setter
	public static int getSales() {
		return sales;
	}
	
	public static void setSales(int sales) {
		Service.sales = sales;
	}
	
	// 회원목록 getter
	public static ArrayList<Account> getMembers() {
		return members;
	}	
	
}
