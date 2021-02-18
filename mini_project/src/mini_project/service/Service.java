package mini_project.service;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import static mini_project.utils.CommonUtils.*;
import mini_project.utils.*;
import mini_project.vo.*;

@SuppressWarnings("unchecked")
public class Service {
	private int sales = 0;											// 총매출
	private ArrayList<Account> members = new ArrayList<Account>();	// 회원목록
	private ArrayList<Food> menuList = new ArrayList<Food>();		// 메뉴목록
	private ArrayList<Fee> feeList = new ArrayList<Fee>();			// 요금목록
	
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 민우
	 */
	public void register() {
		boolean run = true;
		Account tmpUser = new Account();
		RegisterUtils ru = new RegisterUtils();
		
		while(run) {
			tmpUser = ru.insertInfo(tmpUser, members);
			ru.printInputInfo(tmpUser);
			
			System.out.print("입력한 정보가 맞습니까? 1. 예 2. 아니오 3. 종료 > ");
			int choice = nextInt();
			
			if(choice == 1) {
				members.add(tmpUser);
				save("memberList", members);
				return;
			} else if (choice == 3) {
				return;
			}
		}
	}
	
	/**
	 * @author 보경
	 */
	public void login() {
	}
	
	/**
	 * @author 찬희
	 */
	public void pay() {
		boolean run = true;
		
		while(run) {
			try {
//				printPayMenu();
				int input = nextInt();
				
				switch(input) {
				case 1:	// 요금선택
						// isMem이 false(비회원)일 때
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
	
	/**
	 * @author 보경
	 */
	public void controlStat() {
//		printControlStatMenu();
		purchase();
	}
	
	/**
	 * @author 소연
	 */
	public void purchase() {
		boolean run = true;
		
		while(run) {
			try {
//				printPurchaseMenu();
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
	
	/**
	 * @author 민우
	 */
	public void manageMember() {
		
	}
	
	/**
	 * @author 소연
	 */
	public void manageStock() {
		
	}
	
	
	/**
	 * @author 찬희
	 */
	public void manageSales() {
		
	}

}