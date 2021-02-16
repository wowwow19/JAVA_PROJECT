package mini_project;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	
	private static Scanner scanner = new Scanner(System.in);

	public static String nextLine() {
		return scanner.nextLine();
	}
	
	public static int nextInt() {
		return Integer.parseInt(scanner.nextLine());
	}
	
	public static void printLogo() {
		System.out.println("██╗  ██╗██╗   ██╗███╗   ███╗ █████╗ ███╗   ██╗    ██████╗  ██████╗██████╗  █████╗ ███╗   ██╗ ██████╗ \n" + 
				"██║  ██║██║   ██║████╗ ████║██╔══██╗████╗  ██║    ██╔══██╗██╔════╝██╔══██╗██╔══██╗████╗  ██║██╔════╝ \n" + 
				"███████║██║   ██║██╔████╔██║███████║██╔██╗ ██║    ██████╔╝██║     ██████╔╝███████║██╔██╗ ██║██║  ███╗\n" + 
				"██╔══██║██║   ██║██║╚██╔╝██║██╔══██║██║╚██╗██║    ██╔═══╝ ██║     ██╔══██╗██╔══██║██║╚██╗██║██║   ██║\n" + 
				"██║  ██║╚██████╔╝██║ ╚═╝ ██║██║  ██║██║ ╚████║    ██║     ╚██████╗██████╔╝██║  ██║██║ ╚████║╚██████╔╝\n" + 
				"╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝    ╚═╝      ╚═════╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ");
		System.out.println("=====================================================================================================");
		System.out.println("                                     휴먼피시방에 오신 것을 환영합니다.                                      ");
	}
	
	public static void printInitialMenu() {
		System.out.println("=====================================================================================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │     1. 회원가입     │  │      2. 로그인      │  │      3. 종 료      │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	public static void printLoginMenu() {
		System.out.println("=============================================< 로 그 인 >==============================================");
		System.out.println("    ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐      ");
		System.out.println("    │      1. 회 원      │  │      2. 비회원      │  │      3. 관리자     │  │       4. 이 전      │      ");
		System.out.println("    └───────────────────┘  └───────────────────┘  └───────────────────┘  └───────────────────┘      ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	public static void printPayMenu() {
		System.out.println("=============================================< 요금결제 >==============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │     1. 시간선택     │  │      2. 다 음      │  │     3. 로그아웃      │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	public static void printControlStatMenu() {
		System.out.println("===========================================< 이용상태관리 >=============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │     1. 이용시작     │  │     2. 일시정지     │  │      3. 이용종료     │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │      4. 이 전      │  │      5. 다 음      │  │     6. 로그아웃     │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	public static void printPurchaseMenu() {
		System.out.println("=============================================<  구 매  >==============================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │      1. 식사류      │  │     2. 사이드      │  │      3. 스 낵      │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("    ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐      ");
		System.out.println("    │      4. 음 료      │  │     5. 차/커피      │  │      6. 이 전      │  │     7. 로그아웃     │      ");
		System.out.println("    └───────────────────┘  └───────────────────┘  └───────────────────┘  └───────────────────┘      ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}

	
	public static void printWelcom(String id) {
		System.out.println("=====================================================================================================");
		System.out.printf("                                    %10s 님 환영합니다.\n", id);
	}

	public static void save(String fileName, ArrayList<?> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));			
			oos.writeObject(list);
			oos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
