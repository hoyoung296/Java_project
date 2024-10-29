package Huh;

import java.sql.Connection;
import java.util.Random;
import java.util.Scanner;

public class Service {
	Scanner input = new Scanner(System.in);
	Random ran = new Random();
	int win, lose, tie;
	private DAO dao;
	private DTO dto;
	private Scanner scan;

	public Service() {
		dao = new DAO();
	}

	public void display() {
		int num;
		boolean display=true;
		while (display) {
			System.out.println("1.사용자 이름 입력");
			System.out.println("2.가위바위보 게임 시작");
			System.out.println("3.사용자 목록 확인");
			System.out.println("4.나가기");
			System.out.print(">>>");
			num = input.nextInt();
			switch (num) {
			case 1:
				registerGame();
				break;
			case 2:
				startGame();
				break;
			case 3:
				showUser();
				break;
			default:
				display=false;
				break;

			}
		}
	}

	// 사용자 이름 입력
	public void registerGame() {
		System.out.print("참여자 아이디 입력 : ");
		dao.newUser(input.next());
	}
	
	public void showUser() {
		System.out.print("참여자 아이디 입력 : ");
		dao.showList(input.next());
		}
	

	// 사용자의 선택 입력
	public void startGame() {
		win = lose = tie = 0;
		boolean game=true;
		while (game) {
			String[] choice = { "가위", "바위", "보" };
			System.out.println("가위, 바위, 보 중 하나를 선택하세요 (종료하려면 exit 입력): ");
			scan = new Scanner(System.in);
			String userChoice = scan.next();

			if (userChoice.equals("exit")) {
				System.out.print("참여자 아이디 입력 : ");
				String id=input.next();
				dto=resultGame();
				System.out.println("점수 저장중...");
				dao.setScore(id,dto.getWinRate());
				System.out.println("점수 저장 완료!");
				game=false;
				break;
			}

			if (!userChoice.equals("가위") && !userChoice.equals("바위") && !userChoice.equals("보")) {
				System.out.println("잘못된 선택입니다.");
				continue;
			}
			// 컴퓨터의 선택
			String computerChoice = choice[ran.nextInt(3)];// 가위, 바위, 보 중에 하나 랜덤으로 선택
			System.out.println("컴퓨터의 선택: " + computerChoice);

			// 승패 결정
			if (userChoice.equals(computerChoice)) {
				System.out.println("무승부");
				tie++;
			} else if ((userChoice.equals("가위") && computerChoice.equals("보"))
					|| (userChoice.equals("바위") && computerChoice.equals("가위"))
					|| (userChoice.equals("보") && computerChoice.equals("바위"))) {
				System.out.println("승리!!!");
				win++;
			} else {
				System.out.println("패배");
				lose++;
			}
		}
	}

	// 승률 계산
	public DTO resultGame() {
		dto=new DTO();
		int totalGames = win + lose + tie;
		float winRate = (((float) win / totalGames) * 100);// 실수 입력
		System.out.println("==========================");// 이름과 총 게임 수 , 승 패 무승부 몇번인지 승률 소수점 둘째자리까지 출력
		System.out.println();
		System.out.println("총 게임 수: " + totalGames);
		System.out.println("승리 : " + win + ", 패배 : " + lose + ", 무승부 : " + tie);
		System.out.printf("승률: %.2f %% \n", winRate);// %.2f 소수점 둘째짜리까지 표기
		System.out.println("==========================");
		dto.setWinRate(winRate);
		return dto;
	}
	
}
