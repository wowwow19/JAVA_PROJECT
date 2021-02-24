package miniProject.utils;

import static miniProject.utils.CommonUtils.*;

import java.util.ArrayList;

import miniProject.vo.Account;
import miniProject.vo.Food;
import miniProject.vo.Seat;

public class Utils {	
		
	
	
	
	// 회원관리
		
	
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