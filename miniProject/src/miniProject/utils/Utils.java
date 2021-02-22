package miniProject.utils;

import static miniProject.utils.CommonUtils.*;

import java.util.ArrayList;

import miniProject.vo.Account;
import miniProject.vo.Food;
import miniProject.vo.Seat;

public class Utils {	
		
	
	
	
	// 회원관리
		
	/**
	 * 회원목록에서 회원정보의 수정을 수행
	 * @author 민우
	 * @param list
	 * 			회원목록
	 */
	public static void updateMem(ArrayList<Account> list) {
		boolean run = true;
		
		printMemberList(list);
		while(run) {
			try {
				System.out.print("수정할 회원번호를 입력하세요. > ");
				int idx = findByNum(nextInt(), list);
				if(idx == -1) {
					System.out.println("찾을 수 없습니다.");
				} else {
					printMemberInfo(idx, list);
					System.out.print("찾는 회원이 맞습니까? 1. 예 2. 아니요 3. 취소 > ");
					int check = nextInt();
					
					if(check == 1) {
						updateInfo(idx, list);
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
	
	/**
	 * 특정 인덱스를 입력받아 해당 인덱스의 객체를 수정
	 * @author 민우
	 * @param idx
	 * 			수정할 객체의 인덱스
	 * @param list
	 * 			회원목록
	 */
	public static void updateInfo(int idx, ArrayList<Account> list) {
		boolean run = true;
		
		while(run) {
			System.out.println("============================== 관리자메뉴 > 회원관리 > 회원정보수정 =================================");
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
				break;
			}			
		}
	}
	
	/**
	 * 수정내용에 대한 유효성 검증을 수행하여 통과하지 못할경우 에러메시지를 출력
	 * @author 민우
	 * @param pw
	 * 			수정된 비밀번호 문자열
	 * @param pwCheck
	 * 			비밀번호 확인 문자열 
	 * @param phone
	 * 			수정된 전화번호 문자열
	 * @return
	 * 			유효성 검증 통과여부
	 */
	public static boolean checkInput(String pw, String pwCheck, String phone) {
		ArrayList<String> errMsg = new ArrayList<>();
		
		errMsg.add(checkPwLength(pw));
		errMsg.add(checkPwMatch(pw, pwCheck));
		errMsg.add(checkPhone(phone));
		
		if(!printErr(errMsg)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 회원 목록에서 회원 객체의 삭제를 수행
	 * @author 민우
	 * @param list
	 * 			회원 목록
	 */
	public static void deleteMem(ArrayList<Account> list) {
		boolean run = true;
		
		printMemberList(list);
		while(run) {
			try {
				System.out.println("================================ 관리자메뉴 > 회원관리 > 회원삭제 ===================================");
				System.out.print("삭제할 회원번호를 입력하세요. > ");
				int idx = findByNum(nextInt(), list);
				if(idx == -1) {
					System.out.println("찾을 수 없습니다.");
				} else {
					printMemberInfo(idx, list);
					System.out.print("찾는 회원이 맞습니까? 1. 예 2. 아니오 3. 취소 > ");
					int check = nextInt();
					
					if(check == 1) {
						checkDeleteMem(idx, list);
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
	public static void checkDeleteMem(int idx, ArrayList<Account> list) {
		System.out.print("정말로 삭제하시겠습니까?(삭제시 복구할 수 없습니다) 1. 예 2. 아니오 > ");
		int check = nextInt();
		if(check == 1) {
			list.remove(idx);
			save("memberList.ser", list);
			System.out.println("삭제가 완료되었습니다.");
		}
	}
	
	// 정보수정(회원메뉴)
	public static void updateMem(Account user, ArrayList<Account> list) {
		boolean run = true;
		
		printMemberInfo(user);
		while(run) {
			System.out.println("============================== 회원메뉴 > 정보수정 =================================");
			System.out.println("회원정보는 비밀번호, 전화번호만 수정가능합니다.");
			System.out.print("비밀번호(기존값:" +  user.getPw() + ") > ");
			String pw = nextLine();
			System.out.print("비밀번호확인(기존값:" +  user.getPw() + ") > ");
			String pwCheck = nextLine();
			System.out.print("전화번호(기존값:" +  user.getPhone() + ") > ");
			String phone = nextLine();
			
			if(checkInput(pw, pwCheck, phone)) {
				user.setPw(pw);
				user.setPhone(phone);
				save("memberList.ser", list);
				System.out.println("수정이 완료되었습니다.");
				break;
			}			
		}
	}
	
	// 재고관리
	public static void printManageStockMenu() {
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
	
	public static int stockOrder(int start, int end, ArrayList<Food> list) {
		int purchase = 0;
		boolean run = true;
		
		while(run) {
			int idx = 0;
			
			printFoodList(start, end, list);
			System.out.println("추가 주문할 물품의 품번을 입력하세요. > ");
			int itemNum = nextInt();
			
			idx = findByItemNum(start, end, itemNum, list);
			
			if(idx != -1) {
				printFoodInfo(idx, list);
				System.out.println("찾는 물품이 맞습니까? 1. 예 2. 아니오 3. 취소 > ");
				int check = nextInt();
				
				switch(check) {
				case 1:
					purchase = insertOrderInfo(idx, list);
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
	
	public static int insertOrderInfo(int idx, ArrayList<Food> list) {
		int purchase = 0;
		System.out.println("주문할 물품의 갯수를 입력하세요. > ");
		int num = nextInt();
		
		list.get(idx).setStock(list.get(idx).getStock() + num);
		save("menuList.ser", list);
		purchase += list.get(idx).getPurchasePrice() * num;
		
		return purchase;
	}
	
}