package mini_project;

import static mini_project.Utils.*;

public class Ex {
	public static void main(String[] args) {
		
		Service s = new Service();
		boolean run = true;
		
		while (run) {
			int input = nextInt();
			
			switch (input) {
			case 1:
				s.register();
				break;
			case 2:
				s.login();
				break;
			case 3:
				s.pay();
				break;
			case 4:
				s.purchase();
				break;
			case 5:
				s.controlStat();
				break;
			default:
				return;	// 메인 메서드 종료					
			}
		}
	}
}
