package sodam;

import java.util.Random;
import java.util.Scanner;

public class Sodamgame_main {

	public void disp() {

		Scanner input = new Scanner(System.in);
		Random rdnum = new Random();
		int num, num1, num2, num3, and = 0;
		int rdnum1 = rdnum.nextInt(5) + 1;
		int rdnum2 = rdnum.nextInt(5) + 1;
		int rdnum3 = rdnum.nextInt(5) + 1;

		System.out.println("\n" + "▶ 333 로또 게임 ◀");
		System.out.println("1. 게임시작");
		System.out.println("2. 초기메뉴로 돌아가기");
		System.out.print(">>>> ");
		num = input.nextInt();
		System.out.println("\n");

		switch (num) {
		case 1:

			System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
			System.out.println("작성한 숫자 3개가 랜덤숫자와 모두 일치하면 당첨입니다.");
			System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★" + "\n");

			while (true) {
				System.out.println("1에서 5 사이의 숫자를 3개 입력해주세요.");
				System.out.print("1번 숫자 >> ");
				num1 = input.nextInt();
				System.out.print("2번 숫자 >> ");
				num2 = input.nextInt();
				System.out.print("3번 숫자 >> ");
				num3 = input.nextInt();
				if (num1 == 0 || num1 > 10) {
					System.out.println("1에서 5 사이의 숫자만 입력 가능합니다!" + "\n");
					continue;
				} else if (num2 == 0 || num2 > 5) {
					System.out.println("1에서 5 사이의 숫자만 입력 가능합니다!" + "\n");
					continue;
				} else if (num2 == 0 || num2 > 5) {
					System.out.println("1에서 5 사이의 숫자만 입력 가능합니다!" + "\n");
					continue;
				} else if (num3 == 0 || num3 > 5) {
					System.out.println("1에서 5 사이의 숫자만 입력 가능합니다!" + "\n");
					continue;
				}
				break;
			}
			System.out.println("입력하신 숫자는 " + num1 + ", " + num2 + ", " + num3 + "입니다." + "\n");

			System.out.println("=======숫자 추첨을 시작합니다.=======");

			for (int i = 0; i < 1; i++) {

				System.out.print("\n" + "[" + rdnum1 + "]");

				if (rdnum1 == num1 || rdnum1 == num2 || rdnum1 == num3) {
					System.out.print(" >>>> 입력하신 숫자 " + rdnum1 + "과(와) 일치합니다!!");

				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}

				System.out.print("\n" + "[" + rdnum2 + "]");

				if (rdnum2 == num1 || rdnum2 == num2 || rdnum2 == num3) {
					System.out.print(" >>>> 입력하신 숫자 " + rdnum2 + "과(와) 일치합니다!!");

				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}

				System.out.print("\n" + "[" + rdnum3 + "]");

				if (rdnum3 == num1 || rdnum3 == num2 || rdnum3 == num3) {
					System.out.print(" >>>> 입력하신 숫자 " + rdnum3 + "과(와) 일치합니다!!");

				}

				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}

			}

			if ((rdnum1 == num1 || rdnum1 == num2 || rdnum1 == num3)
					&& (rdnum2 == num1 || rdnum2 == num2 || rdnum2 == num3)
					&& (rdnum3 == num1 || rdnum3 == num2 || rdnum3 == num3)) {
				System.out.println("\n\n" + "☆★☆★☆★☆★☆★☆★☆" + "\n" + "당첨을 축하합니다!!" + "\n" + "☆★☆★☆★☆★☆★☆★☆");
			} else
				System.out.println("\n\n" + "☆☆☆☆☆GameOver☆☆☆☆☆" + "\n");

			return;

		case 2:
			return;
		}

	}
}
