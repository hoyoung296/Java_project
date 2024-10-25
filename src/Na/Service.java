package Na;

import java.util.ArrayList;
import java.util.Scanner;

// 마피아 게임 만들거임

public class Service {
	private Dao dao;

	public Service() {
		dao = new Dao();
	}

	public void mafia() {
		int case1 = 0;
		ArrayList<Dto> list = new ArrayList<Dto>();
		Scanner sc = new Scanner(System.in);
		System.out.println("마피아 게임에 오신걸 환영합니다.\n이 게임은 최소 4명에서 최대 8명까지 가능합니다.\n회원가입 및 로그인 먼저 진행해주시고 게임참여 눌러주세요.");
		while (true) {
			System.out.println("메뉴를 선택해주세요.\n1. 회원가입\n2. 로그인\n3. 게임 참여\n4. 전적 조회\n5.회원 삭제\n6. 메인메뉴로 돌아가기");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.print("저장 id : ");
				String id = sc.next();
				System.out.print("저장 pwd : ");
				String pwd = sc.next();
				System.out.print("저장 name : ");
				String name = sc.next();
				int result = dao.insert(id, pwd, name);
				if (result == 1) {
					System.out.println("회원 가입 완료!!!");
					list = dao.join();
				}
				break;
			case 2:
				if (list.size() == 0)
					System.out.println("회원가입 먼저 진행해주세요");
				else {
					System.out.print("id 입력 : ");
					id = sc.next();
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getId().equals(id)) {
							System.out.print("pwd 입력 : ");
							pwd = sc.next();
							if (list.get(i).getPwd().equals(pwd))
								System.out.println("로그인 완료");
							else
								System.out.println("비밀번호가 틀렸습니다.");
							break;
						}

						if (i == list.size() - 1)
							System.out.println("해당 id는 존재하지 않습니다.");
					}
				}
				break;
			case 3:

				break;
			case 4:
				if (list.size() == 0)
					System.out.println("회원가입 먼저 진행해주세요");
				else {
					System.out.println("id\tname\twin\tlose");
					for (int i = 0; i < list.size(); i++)
						System.out.println(list.get(i));
				}
				break;
			case 5:
				if (list.size() == 0)
					System.out.println("회원가입 먼저 진행해주세요");
				else {
					System.out.print("id 입력 : ");
					id = sc.next();
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getId().equals(id)) {
							System.out.println("회원정보가 삭제되었습니다.");
							list.remove(i);
							break;
						}

						if (i == list.size() - 1)
							System.out.println("해당 id는 존재하지 않습니다.");
					}
				}
				break;

			case 6:
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			}
		}
	}
}