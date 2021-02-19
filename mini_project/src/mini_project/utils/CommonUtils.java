package mini_project.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import mini_project.vo.Account;

public class CommonUtils {
	
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
		System.out.println("                                휴먼피시방에 오신 것을 환영합니다.                                   ");
	}
	
	public static void printWelcom(String id) {
		System.out.println("=====================================================================================================");
		System.out.printf("                                    %10s 님 환영합니다.\n", id);
	}
	
	public static void printInitialMenu() {
		System.out.println("=====================================================================================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │    1. 회원가입    │  │     2. 로그인     │  │      3. 종 료     │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}
	
	public static void printAdminMenu() {
		System.out.println("=====================================================================================================");
		System.out.println("                ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐                  ");
		System.out.println("                │    1. 회원가입    │  │     2. 로그인     │  │      3. 종 료     │                  ");
		System.out.println("                └───────────────────┘  └───────────────────┘  └───────────────────┘                  ");
		System.out.println("=====================================================================================================");
		System.out.print("메뉴를 선택하세요. > ");
	}

	/**
	 * 파일을 읽어올 ObjectInputStream을 생성
	 * @param fileName
	 * 			읽어올 파일명
	 * @return
	 * 			읽어올 파일명으로 생성된 ObjectInputStream 객체
	 */
	public static ObjectInputStream inputObjectFile(String fileName) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ois;
	}
	/**
	 * ArrayList배열을 파일로 저장
	 * @param fileName
	 * 			저장(생성)할 파일의 이름
	 * @param list
	 * 			파일에 저장할 ArrayList 배열
	 */
	public static void save(String fileName, ArrayList<?> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));			
			oos.writeObject(list);
			oos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save(String fileName, int num) {
		try {
			DataOutputStream fos = new DataOutputStream(new FileOutputStream(fileName));
			fos.writeInt(num);
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 목록에서 특정 id값을 가진 객체의 인덱스 값을 반환
	 * @param id
	 * 			배열의 객체에서 찾을 id값
	 * @param list
	 * 			id가 있는지 검색할 Account 배열
	 * @return
	 * 			찾는 id값을 가진 객체의 인덱스(존재하면 해당 인덱스 값, 존재하지 않으면 -1를 반환)
	 */
	public static int findById(String id, ArrayList<Account> list) {
		for(int i = 0; i < list.size(); i++) {
			if(id.equals(list.get(i).getId())) return i;
		}
		return -1;
	}
	
}
