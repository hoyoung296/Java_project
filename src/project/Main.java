package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import YJ.*;
import Na.Service;
import sodam.Sodamgame_main;

import bang.Bang_Service;
import bang.Bang_ServiceImpl;


public class Main {
	public static void main(String[] args) {
		Service ser = new Service();
		Sodamgame_main s = new Sodamgame_main();
		Scanner sc = new Scanner(System.in);
		Bang_Service bs = new Bang_ServiceImpl();
		
		while(true) {
		System.out.println("미니 게임 천국에 오신걸 환영합니다.\n메뉴를 선택해주세요.");
		System.out.println("1. 숫자 맞추기\n2. 허은미\n3. 333 로또\n4. 끝말잇기\n5. 마피아 게임\n6. 프로그램 종료");
		int num = sc.nextInt();
		
			switch(num) {
			case 1 :
				bs.display();
				break;
			case 2:
				break;
			case 3:
				s.disp();
				break;
			case 4:
				Yj_DBService game = new Yj_DBServiceImpl();
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