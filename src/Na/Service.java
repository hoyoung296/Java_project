package Na;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {
	private Dao dao;

	public Service() {
		dao = new Dao();
	}

	public static int max(int... arr) {
		int max = 0;
		for (int a : arr) {
			if (a >= max) {
				max = a;
			}
		}
		return max;
	}

	static Scanner sc = new Scanner(System.in);

	public void GameInfo() {
		System.out.println("마피아 게임에 오신걸 환영합니다.\n최소 4명에서 최대 8명까지 가능합니다.\n대화는 5턴 동안 진행되며, 투표를 통해 마피아를 색출하세요.\n"
				+ "시민들의 수가 마피아랑 같아지면 마피아 승리 이전에 마피아 색출에 성공하면 시민의 승리입니다.\n회원가입 및 로그인 먼저 진행해주시고 게임참여 눌러주세요.");
	}

	public static void Join(Dao dao) {
		System.out.print("저장 id : ");
		String id = sc.next();
		int result = dao.insert(id);
		if (result == 1) {
			System.out.println("회원 가입 완료!!!");
		}
	}

	public void Login(Dao dao, ArrayList<Dto> list) {
		System.out.print("id 입력 : ");
		String id = sc.next();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(id)) {
				System.out.println("해당 id는 이미 로그인중입니다.");
				return;
			}
		}
		dao.getlist(id, list);
		if (list.size() == 0) {
			System.out.println("해당 아이디는 존재하지 않습니다.");
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(id)) {
				System.out.print("pwd 입력 : ");
				String pwd = sc.next();
				if (list.get(i).getPwd().equals(pwd))
					System.out.println("로그인 완료");

				else
					System.out.println("비밀번호가 틀렸습니다.");

				break;
			}

			if (i == list.size() - 1)
				System.out.println("해당 아이디는 존재하지 않습니다.");
		}
	}

	public void GameJoin(ArrayList<Dto> list, Dao dao) {
		int citizen = 0, mafia = 0;
		boolean[] realmafia = new boolean[list.size()];
		ArrayList<String> nickname = new ArrayList<String>();
		ArrayList<Integer> vote = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			nickname.add(list.get(i).getName());
			vote.add(0);
		}

		if (list.size() > 8)
			System.out.println("인원수가 8명 초과입니다.");

		else if (list.size() < 4)
			System.out.println("인원수가 4명 미만입니다.");

		else {
			System.out.print("참가자 : ");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getName() + "\t");
			}
			System.out.println();
			System.out.println("마피아를 정하겠습니다.");
			ArrayList<Integer> mafianum = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				int a = (int) (Math.random() * list.size()) + 1;
				if (mafianum.contains(a))
					i--;
				else
					mafianum.add(a);
			}

			if (4 <= list.size() && list.size() < 6) {
				for (int i = 0; i < mafianum.size(); i++) {
					if (mafianum.get(i) == list.size() - 1) {
						System.out.println(list.get(i).getName() + "님이 마피아입니다.");
						realmafia[i] = true;
					}
				}
				mafia = 1;
			}

			else if (6 <= list.size() && list.size() <= 8) {
				for (int i = 0; i < mafianum.size(); i++) {
					if (mafianum.get(i) == list.size() - 1 || mafianum.get(i) == list.size() - 3) {
						System.out.println(list.get(i).getName() + "님이 마피아입니다.");
						realmafia[i] = true;
					}
				}
				mafia = 2;
			}

			citizen = list.size() - mafia;
			while (true) {
				for (int turn = 1; turn < 6; turn++) {
					System.out.println("턴 수 : " + turn);
					for (int i = 0; i < nickname.size(); i++) {
						System.out.print(nickname.get(i) + " : ");
						sc.next();
					}

					if (turn == 5) {
						System.out.println("투표 시작하겠습니다.\n각자 마피아라고 생각하는 사람의 번호를 입력해주세요.");
						for (int i = 0; i < nickname.size(); i++) {
							System.out.print(i + 1 + ". " + nickname.get(i) + "\t");
						}

						System.out.println();

						for (int i = 0; i < nickname.size(); i++) {
							System.out.print(nickname.get(i) + "님 입력 : ");
							int cho = sc.nextInt();
							vote.set(cho - 1, vote.get(cho - 1) + 1);
						}
					}
				}

				for (int i = 0; i < vote.size(); i++) {
					System.out.println(i + 1 + ". " + nickname.get(i) + "님의 득표수 : " + vote.get(i));
				}

				int[] arr = new int[vote.size()];
				for (int i = 0; i < vote.size(); i++) {
					arr[i] = vote.get(i);
				}
				int max = max(arr);

				for (int i = 0; i < nickname.size(); i++) {
					boolean next = false;
					if (max == vote.get(i)) {
						for (int j = 0; j < nickname.size(); j++) {
							if (i == j)
								continue;

							if (vote.get(i) == vote.get(j)) {
								System.out.println("투표수 동률이 발생했습니다. 다음 단계로 바로 넘어갑니다.");
								next = true;
								break;
							}

							else if (j == nickname.size() - 1) {
								System.out.println(nickname.get(i) + "님이 마피아로 지목되었습니다.");
								if (realmafia[i] == true) {
									System.out.println(nickname.get(i) + "님은 마피아가 맞습니다.");
									nickname.remove(i);
									vote.remove(i);
									mafia--;
									next = true;
								}

								else {
									System.out.println(nickname.get(i) + "님은 마피아가 아닙니다. 무고한 시민이 죽었습니다.");
									nickname.remove(i);
									vote.remove(i);
									citizen--;
									next = true;
								}
							}
						}
					}

					if (next)
						break;
				}

				if (mafia == 0) {
					System.out.println("모든 마피아가 죽었습니다. 시민의 승리입니다.");
					for (int i = 0; i < list.size(); i++) {
						if (realmafia[i] == true)
							list.get(i).setLose(list.get(i).getLose() + 1);
						else
							list.get(i).setWin(list.get(i).getWin() + 1);
					}
					break;
				} else {
					System.out.println("밤이 찾아왔습니다.\n마피아는 죽이고 싶은 사람의 번호를 입력해주세요");
					for (int i = 0; i < nickname.size(); i++) {
						System.out.print(i + 1 + ". " + nickname.get(i) + "\t");
						System.out.println();
					}
					System.out.print("번호 입력 : ");
					int num = sc.nextInt();
					nickname.remove(num - 1);
					vote.remove(num - 1);
					citizen--;
					System.out.println("간밤에 무고한 시민이 죽었습니다.");
				}

				if (mafia >= citizen) {
					System.out.println("시민들이 더 이상 마피아를 죽일 수 없습니다.\n마피아의 승리입니다.");
					for (int i = 0; i < list.size(); i++) {
						if (realmafia[i] == true)
							list.get(i).setWin(list.get(i).getWin() + 1);
						else
							list.get(i).setLose(list.get(i).getLose() + 1);
					}
					break;
				}
			}
		}
		dao.update(list);
	}

	public void WinLoseSearch(ArrayList<Dto> list) {
		if (list.size() == 0)
			System.out.println("로그인 먼저 진행해주세요");

		else {
			System.out.println("id\tname\twin\tlose");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}

	public void Logout(ArrayList<Dto> list, Dao dao) {
		if (list.size() == 0)
			System.out.println("로그인 먼저 진행해주세요");
		else {
			System.out.print("로그아웃할 id 입력 : ");
			String id = sc.next();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId().equals(id)) {
					System.out.println("로그아웃 완료.");
					list.remove(i);
					break;
				}

				if (i == list.size() - 1)
					System.out.println("해당 id는 로그인 되어 있지 않거나 존재하지 않는 id입니다.");
			}
		}
	}

	public void InfoDelete(ArrayList<Dto> list, Dao dao) {
		int a = 0;
		System.out.print("삭제할 id 입력 : ");
		String id = sc.next();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(id)) {
				System.out.println("로그인 되어있습니다. 로그아웃 먼저 진행헤주세요.");
				a = 1;
				break;
			}
		}
		if (a == 1) {
			return;
		}
		int result = dao.delete(id);
		if (result == 0)
			System.out.println("해당 id는 존재하지 않습니다.");

		else
			System.out.println("삭제완료");
	}

	public void mafia() {
		ArrayList<Dto> list = new ArrayList<Dto>();
		GameInfo();
		while (true) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 게임 참여\n4. 전적 조회\n5. 로그아웃\n6. 회원정보 삭제\n7. 메인메뉴로 돌아가기");
			System.out.print("번호 입력 : ");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				Join(dao);
				break;
			case 2:
				Login(dao, list);
				break;
			case 3:
				GameJoin(list, dao);
				break;
			case 4:
				WinLoseSearch(list);
				break;
			case 5:
				Logout(list, dao);
				break;
			case 6:
				InfoDelete(list, dao);
				break;
			case 7:
				System.out.println("메인메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}
}