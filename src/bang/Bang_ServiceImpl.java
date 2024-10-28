package bang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

import project.Connect;


public class Bang_ServiceImpl implements Bang_Service{
	Bang_DAO dao = new Bang_DAO();
	Connection con = Connect.getConnect();
	private int t=0,f=0;
	Scanner input = new Scanner(System.in);
	int num = 0;
	boolean bool;
	public void display() {
		bool = true;
		while (bool) {
			System.out.println("1.숫자 맞추기 게임 시작");
			System.out.println("2.결과저장하고 출력하기");
			System.out.println("3.종료");
			num = input.nextInt();
			switch (num) {
			case 1:
				//랜덤 지정
				dao.random();
				for(int i = 0; i < 7; i++) {
					System.out.println("정수를 입력하세요");
					num = dao.result();
					if(num == 1) {
						break;
					}
				}
				dao.answer();
				dao.count();
				break;
			case 2:
				Bang_DTO dto = dao.count(); 
				t = dto.getNum();
				f = dto.getNum2();
				dao.update(t, f);
				String sql = "select * from look";
				String dle = "DELETE from look";
				System.out.println("=======================");
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						System.out.print("성공 : ");
						System.out.print(rs.getInt("성공"));
						System.out.print("\t실패 : ");
						System.out.println(rs.getInt("실패"));
					} else {
						System.out.println("데이터가 없습니다.");
					}
					System.out.println("=======================");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				bool = false;
				break;
			default:
				System.out.println("잘못된 숫자 입력");
				break;
			}
		}
	}
}
