package miniProject.utils;

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

import miniProject.service.Service;
import miniProject.vo.Account;
import miniProject.vo.Merchandise;

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
		System.out.println("==========================================< 관리자메뉴 >=============================================");
		System.out.println("    ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐  ┌───────────────────┐      ");
		System.out.println("    │    1. 회원관리    │  │    2. 재고관리    │  │    3. 매출관리    │  │     4. 종 료      │      ");
		System.out.println("    └───────────────────┘  └───────────────────┘  └───────────────────┘  └───────────────────┘      ");
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
	
	/**
	 * 회원 목록에서 특정 num값을 가진 객체의 인덱스 값을 반환
	 * @param num
	 * 			배열의 객체에서 찾을 num값
	 * @param list
	 * 			id가 있는지 검색할 Account 배열
	 * @return
	 * 			찾는 id값을 가진 객체의 인덱스(존재하면 해당 인덱스 값, 존재하지 않으면 -1를 반환)
	 */
	public static int findByNum(int num, ArrayList<Account> list) {
		for(int i = 0; i < list.size(); i++) {
			if(num == list.get(i).getNum()) return i;
		}
		return -1;
	}	
	
	/**
	 * 글자 하나당 2byte로 계산할지 1byte로 계산할지를 알려주는 메서드, 파라미터가 한글일 경우 2byte를 그렇지 않을 경우는
	 * 1byte로 반환한다.
	 * 
	 * @param c
	 *            입력할 글자
	 * @return 반환될 숫자
	 */
	private static int getCharCount(char c) {
		int cnt = 0;
		if (c >= '가' && c <= '힣') {
			cnt++;
		}
		cnt++;
		return cnt;
	}

	/**
	 * 문자열을 입력받아 바이트 갯수를 리턴해주는 메서드, 한글이 포함된경우 2를 그렇지 않은 경우는 1을 가산한다.
	 * 
	 * @param str
	 *            세야할 글자
	 * @return int 타입의 리턴
	 */
	public static int getStringByteCount(String str) {
		char[] words = str.toCharArray();
		int cnt = 0;
		for (char c : words) {
			cnt += getCharCount(c);
		}
		return cnt;
	}

	/**
	 * 콘솔에 출력할 문자열의 길이가 부적절하게 길 경우 지정된 크기만큼을 자른다.
	 * 
	 * @param strs
	 *            잘라야할 원문
	 * @param lastIdx
	 *            잘라야할 길이
	 * @return 잘라서 완성된 문자열을 반환
	 */
	public static String subContent(String str, int len) {
		StringBuilder ret = new StringBuilder();
		char[] ori = str.toCharArray();
		int cnt = 0;
		for (char c : ori) {
			if (len / 2 * 2 - 3 < cnt) {
				ret = new StringBuilder(ret.substring(0, ret.length() - 2) + "..");
				break;
			}
			cnt += getCharCount(c);
			ret.append(c);
		}
		return ret.toString();
	}

	/**
	 * 출력시 필요한 문자열 배열과 각 배열의 한계길이값을 지정한 정수 배열을 받아와 하나의 스트링을 반환
	 * 
	 * @param strs
	 *            출력할 텍스트 배열
	 * @param len
	 *            출력될 텍스트 포맷의 한계 길이
	 * @return 조합후 완성된 문자열을 반환한다.
	 */
	public static String format(String[] strs, int[] len) {
		String ret = "";

		for (int i = 0; i < strs.length; i++) {
			int cnt = len[i];
			strs[i] = subContent(strs[i], cnt);
			cnt = cnt - getStringByteCount(strs[i]) + strs[i].length();
			ret += "%-" + cnt + "s";
		}
		return String.format(ret, (Object[]) strs);
	}
	
	public static void printMemberList(ArrayList<Account> list) {
		String[] menus = {"회원번호", "아이디", "비밀번호", "전화번호", "남은시간", "이용상태", "회원여부"};
		String[] str = new String[7];
		int[] len = {10, 20, 20, 20, 10, 10, 10};
		
		System.out.println("==========================================< 회원목록 >===============================================");
		System.out.println(format(menus, len));
		for(int i = 0; i < list.size(); i++) {
			str[0] = Integer.toString(list.get(i).getNum());
			str[1] = list.get(i).getId();
			str[2] = list.get(i).getPw();
			str[3] = list.get(i).getPhone();
			str[4] = Integer.toString(list.get(i).getRemainTime());
			str[5] = Boolean.toString(list.get(i).isStatus());
			str[6] = Boolean.toString(list.get(i).isMember());				
			System.out.println(format(str, len));
		}
		System.out.println("=====================================================================================================");
	}
	
	/**
	 * 패스워드 문자열을 입력받아 첫글자와 마지막 글자를 제외하고 *(별표)로 출력
	 * @author 민우
	 * @param pw
	 * 			출력할 패스워드 문자열
	 */
	public static String printPw(String pw) {
		String pwTmp = "";
		pwTmp += pw.charAt(0);
		for (int i = 1; i < pw.length()-1; i++) {
			pwTmp += '*';
		}
		pwTmp += pw.charAt(pw.length()-1);
		return pwTmp;
	}
	
	public static void printMemberInfo(Account user) {
		String[] menus = {"회원번호", "아이디", "비밀번호", "전화번호", "남은시간", "이용상태", "회원여부"};
		String[] str = new String[7];
		int[] len = {10, 20, 20, 20, 10, 10, 10};
		
		System.out.println("==========================================< 회원정보 >===============================================");
		System.out.println(format(menus, len));
		str[0] = Integer.toString(user.getNum());
		str[1] = user.getId();
		str[2] = printPw(user.getPw());
		str[3] = user.getPhone();
		str[4] = Integer.toString(user.getRemainTime());
		str[5] = Boolean.toString(user.isStatus());
		str[6] = Boolean.toString(user.isMember());				
		System.out.println(format(str, len));
		System.out.println("=====================================================================================================");
	}
	
	public static void printMemberInfo(int idx, ArrayList<Account> list) {
		String[] menus = {"회원번호", "아이디", "비밀번호", "전화번호", "남은시간", "이용상태", "회원여부"};
		String[] str = new String[7];
		int[] len = {10, 20, 20, 20, 10, 10, 10};
		
		System.out.println("==========================================< 회원정보 >===============================================");
		System.out.println(format(menus, len));
		str[0] = Integer.toString(list.get(idx).getNum());
		str[1] = list.get(idx).getId();
		str[2] = list.get(idx).getPw();
		str[3] = list.get(idx).getPhone();
		str[4] = Integer.toString(list.get(idx).getRemainTime());
		str[5] = Boolean.toString(list.get(idx).isStatus());
		str[6] = Boolean.toString(list.get(idx).isMember());				
		System.out.println(format(str, len));
		System.out.println("=====================================================================================================");
	}
	
	public static int findByItemNum(int itemNum, ArrayList<Merchandise> list) {
		int idx = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getItemNum() == itemNum) idx = i;
			return idx;
		}
		return -1;
	}
		
}
