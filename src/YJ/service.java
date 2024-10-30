package YJ;

import java.util.Scanner;

public class service {
	Scanner input=new Scanner(System.in);
	DAO dao=new DAO();
	DTO dto;
	
	
	public void display() {
		dto=new DTO();
		boolean display=true;
		while (display) {

		System.out.println("[1] 게임 시작");
		System.out.println("[2] 게임 설명");
		System.out.println("[3] 단어 관리");
		System.out.println("[4] 게임 종료");
		System.out.println();
		System.out.print(">>>");
		
		int menu=input.nextInt();
		switch (menu) {
		case 1:
			dto=dao.gameSetting();
			System.out.flush();
			int score=0;
			boolean game=true;
			
			while (game) {
				dto.setInputWord(input.next());
				dto=dao.rightWord(dto.getInputWord());
				dao.insertWord(dto.getInputWord());
				
				if (dto.getResult()<1){
					dto.setLastWord(dto.getInputWord());
					dto=dao.findWord(dto.getLastWord(),3);
					if (dto.getResult()>=1){
						dto=dao.wordList(dto.getLastWord());
						System.out.print(" >>> ");
						score++;
						System.out.print("");
					}
					else {dto.setGame("승리");System.out.println();
					System.out.println(dto.getGame()); game=false;}
		
					}
				else {System.out.println(dto.getGame()); game=false;}
				
			}
			
			System.out.println();
			System.out.println(String.format("%d회 동안 진행되었습니다.", score));

			
			break;
		case 2:
			System.out.println("끝말잇기란?"
					+ "\n여러 사람이 하는 말놀이의 하나입니다."
					+ "\n한 사람이 먼저 한 낱말을 말하면 다음 사람이 그 말의 끝 음절을 첫소리로 하는 낱말을 말하는 식으로 다음 사람으로 계속 이어 가는 놀이입니다."
					+ "\n예를 들어, `장미-미인-인간성-성장…'과 같이 이어 나갈 수 있습니다."
					+ "\n");
			System.out.println("해당 게임에서 두음법칙은 적용되지 않습니다.");
			break;
		case 3:
			
			System.out.println("[1] 단어 삭제");
			System.out.println("[2] 단어 등록");
			System.out.println("[3] 단어 검색");
			System.out.println("[4] 관리 종료");
			System.out.print(">>>");
			menu=input.nextInt();
			
			switch (menu) {
			case 1:
				System.out.println("무작위의 단어가 출력됩니다.");
				System.out.println("");
				while (true) {
					System.out.println();
					for (int i=0;i<10;i++) {
						dto= dao.wordList();
						System.out.println(dto.getSystWord());}
					
					System.out.println("삭제할 단어를 입력하십시오.");
					System.out.println("다른 단어들을 보려면 '다음', 나가려면 '종료'를 입력하십시오.");
					System.out.print(">>>");
					dto.setInputWord(input.next());
					
					if(dto.getInputWord().equals("다음")){
						System.out.flush();
						continue;
					}
					if (dto.getInputWord().equals("종료")) {
						System.out.flush();
						dao.commit();
						break;
					}
					else {
						dao.delWord(dto.getInputWord());}
				}
				break;
			case 2:
				System.out.println("새롭게 등록할 단어를 입력하십시오.");
				System.out.print(">>>");
				dto.setInputWord(input.next());
				dao.setWord(dto.getInputWord());
				break;
			case 3:
				dto=new DTO();
				System.out.println("검색할 단어를 입력하십시오.");
				System.out.print(">>>");
				dto.setInputWord(input.next());
				
				System.out.println("[1] "+dto.getInputWord()+"을/를 포함하는 단어");
				System.out.println("[2] "+dto.getInputWord()+"으로 끝나는 단어");
				System.out.println("[3] "+dto.getInputWord()+"으로 시작하는 단어");
				System.out.println("[4] "+dto.getInputWord()+"을/를 검색");
				System.out.println("[5] 취소");
				System.out.print(">>>");
				menu=input.nextInt();
				System.out.flush();
				System.out.println("\t\t검색 결과");
				System.out.println("");
				dto=dao.findWord(dto.getInputWord(),menu);

				try {
					for(int i=0;i<dto.getWordList().size();i++) {
						System.out.println(dto.getWordList().get(i));
					}
					System.out.println();
				} catch (Exception e) {
					System.err.println("조건에 맞는 단어를 찾을 수 없습니다.");
				}

				break;
			default:
				break;
			}
			break;
		default:
			display=false;
			break;
			}
		System.out.flush();
		}
}
	
}

