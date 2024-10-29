package project;


import Na.Service;
import YJ.service;

import bang.Bang_Service;
import bang.Bang_ServiceImpl;
import java.util.Scanner;
import sodam.Sodamgame_main;

public class Main {
	public static void main(String[] args) {
		Huh.Service huh = new Huh.Service();
		service game = new service();
		Service ser = new Service();
		Sodamgame_main s = new Sodamgame_main();
		Bang_Service bs = new Bang_ServiceImpl();
		Scanner sc = new Scanner(System.in);

		System.out.println("미니 게임 천국에 오신걸 환영합니다.\n메뉴를 선택해주세요.");
		while (true) {
			System.out.println("1. 숫자 맞추기\n2. 가위바위보 게임\n3. 333 로또\n4. 끝말잇기\n5. 마피아 게임\n6. 프로그램 종료");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				bs.display();
				break;
			case 2:
				huh.display();
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