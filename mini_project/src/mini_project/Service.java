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
		String pw_check;
		String phone;
		boolean run = true;
		
		System.out.println("회원가입 정보를 입력해주세요.");
		do {
			boolean checkId = true;
			do {
				System.out.print("아이디 > ");
				id = nextLine();
				
				for (int i = 0; i < members.size(); i++) {
					if (members.get(i).getId().equals(id)) {
						checkId = true;
						System.out.println("사용할 수 없는 아이디입니다. 다시 입력하세요.");
					} else {
						checkId = false;
					}
					
				}
			} while (checkId);
			
			boolean checkPw = true;
			do {
				System.out.print("비밀번호 > ");
				pw = nextLine();
				System.out.print("비밀번호확인 > ");
				pw_check = nextLine();
				
				if (pw.equals(pw_check)) {
					checkPw = false;
				} else {
					checkPw = true;
					System.out.println("입력한 비밀번호와 일치하지 않습니다.");
				}
			} while (checkPw);
			
			System.out.print("전화번호 > ");
			phone = nextLine();
			
			System.out.println("────── 입력정보 ──────");
			System.out.println("아이디 : " + id);
			System.out.print("비밀번호 : ");
			for (int i = 0; i < pw.length(); i++) {
				System.out.print("*");
			}
			System.out.println();
			System.out.println("전화번호 : " + phone);
			System.out.print("입력한 정보가 맞습니까? 1. 예 2. 아니오 > ");
			int choice = nextInt();
			if (choice == 1) {
				members.add(new Account(id, pw, phone));
				save("members.ser", members);
				System.out.println("회원가입이 완료되었습니다.");
				return;
			}
		} while(run);
	}
	
	// 보경
	public void login() {
		
	}
	
	// 찬희
	public void pay() {
		
	}
	
	// 소연
	public void purchase() {
		
	}
	
	// 보경
	public void controlStat() {
		
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
