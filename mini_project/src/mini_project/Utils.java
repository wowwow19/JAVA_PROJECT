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
		System.out.println("=====================================================================================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │      1. 회 원      │  │      2. 비회원      │  │     3. 뒤로가기     │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
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
