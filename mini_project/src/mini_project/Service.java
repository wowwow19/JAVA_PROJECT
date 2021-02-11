package mini_project;

import java.util.ArrayList;

public class Service {
	
	static private int sales = 0;
	static private ArrayList<Account> members = new ArrayList<Account>();
	static private ArrayList<Account> tmp_members = new ArrayList<Account>();
	static private Data data = new Data();
	
	// 민우
	public void register() {
		
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

	public static ArrayList<Account> getTmp_members() {
		return tmp_members;
	}
	
	
	
}
