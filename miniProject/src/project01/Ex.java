package project01;

import static project01.utils.CommonUtils.*;

import project01.service.Service;

public class Ex {
	public static void main(String[] args) {
		
		Service s = new Service();
		
		while (true) {
			try {
				printLogo();
				printInitialMenu();
				int input = nextInt();
				
				switch (input) {
				case 1:
					s.register();
					break;
				case 2:
					s.login();
					break;
				case 3:
					return;
				default:
					System.out.println("다시 입력하세요.");
				}
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하세요.");
			}
		}
	}
}
