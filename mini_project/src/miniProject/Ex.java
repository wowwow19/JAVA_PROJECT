package miniProject;

import static miniProject.utils.CommonUtils.*;

import miniProject.service.Service;

public class Ex {
	public static void main(String[] args) {
		
		Service s = new Service();
		boolean run = true;
		
		while (run) {
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
					s.end = (int)System.currentTimeMillis();
					System.out.println(((s.end - s.start)/1000f) + "초");
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
