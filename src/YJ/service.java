package YJ;

import java.util.Scanner;

public class service {
	Scanner input=new Scanner(System.in);
	DAO dao=new DAO();
	DTO dto;
	
	public void display() {
		System.out.println("[1] 게임 시작");
		System.out.println("[2] 게임 설명");
		System.out.println("[3] 게임 종료");
		System.out.println();
		System.out.print(">>>");
		
		int menu=input.nextInt();
		switch (menu) {
		case 1:
			
			dao.gameSetting();
			int score=0;
			boolean game=true;
			while (game) {
				dto=new DTO();
				dto.setInputWord(input.next()); //유저 대답 입력
				
				if (dao.rightWord(dto.getInputWord())){
					dto.setLastWord(dto.getInputWord());//마지막 글자 추출
					dao.wordList(dto.getLastWord()); //마지막 글자로 시작하는 단어 출력
					System.out.print(" >>> ");
					dao.insertWord(dto.getInputWord(), dto.getSystWord());
					score++;
					}
				else {
					System.out.println("게임 종료");
					System.out.println(String.format("%d 번 이어나갔습니다.", score));
					game=false;
				}

			}

			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		default:
			break;
		}
		while(true) {
		
		}}
	
	public void gameSetting() {
		
	}
}

