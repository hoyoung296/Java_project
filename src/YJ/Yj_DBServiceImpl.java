package YJ;

//import java.sql.Connection;
import java.util.Scanner;
import project.*;

public class Yj_DBServiceImpl implements Yj_DBService {
	Yj_DAO dao;
	
	public Yj_DBServiceImpl() {
		dao=new Yj_DAO();
	}
	public void display(){
		Scanner input=new Scanner(System.in);
		while (true) {
			System.out.println("[1]	게임 시작");
			System.out.println("[2] 게임 설명");
			System.out.println("");
			System.out.println();
			int menu=input.nextInt();
			switch (menu) {
			case 1:
				dao.newGame();
				boolean game=true;
				Yj_DTO dto=new Yj_DTO();
				
				while (game) {
					System.out.print(">>>");
					dto.setInputWord(input.next());
					if (dao.checkUsed(dto.getInputWord())!=0){
						System.out.println("패배");
						game=false;
					}else{
						dao.getNext(dto.getInputWord());
					}}
				
				break;
			case 2:
				System.out.println("끝말잇기는 단어를 말하면 그 단어의 맨 끝 글자에 이어서 말하는 놀이이다.\n"
						+ "전에 말한 단어 금지 국어사전에 등재되지 않은 단어는 금지 등 여러 규칙들이 있다.\n"
						+ "명사인 단어만 가능하고 고유명사는 안되며, 지명은 예외이다.");
				break;
			case 3:
				break;
			default:
				break;
			}
		}
	}
}