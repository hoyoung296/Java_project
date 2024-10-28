package project;

import Na.Service;
import YJ.Yj_DBService;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Yj_DBService game = new Yj_DBService();
		Service ser = new Service();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("미니 게임 천국에 오신걸 환영합니다.\n메뉴를 선택해주세요.");
		System.out.println("1. 방준혁\n2. 허은미\n3. 박소담\n4. 끝말잇기 \n5. 마피아 게임\n6. 프로그램 종료");
		int num = sc.nextInt();
		while (true) {
			switch (num) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				game.display();
				break;
			case 5:
				ser.mafia();
				break;
			case 6:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}
}