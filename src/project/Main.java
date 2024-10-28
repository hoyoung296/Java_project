package project;

import java.util.Scanner;

import Na.Service;
import YJ.service;
import sodam.Sodamgame_main;

public class Main {
	public static void main(String[] args) {
		YJ.service game =new service();
		Service ser = new Service();
		Sodamgame_main s = new Sodamgame_main();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("미니 게임 천국에 오신걸 환영합니다.\n메뉴를 선택해주세요.");
		while (true) {
			System.out.println("1. 방준혁\n2. 허은미\n3. 333 로또\n4. 끝말잇기 \n5. 마피아 게임\n6. 프로그램 종료");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				s.disp();
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