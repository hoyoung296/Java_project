package bang;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

import project.Connect;

public class Bang_DAO {

	Bang_DTO dto = new Bang_DTO(); 
	private int num, num1=0,num2=0,num3=0;
	public void random() {
		Random random = new Random();
		num = random.nextInt(100)+1;
		dto.setNum1(num);
	}
	public int result() {
		Scanner input = new Scanner(System.in);
		System.out.println("=======================");
		try {
			num = input.nextInt();
			num1 = dto.getNum1();
			if(num1 > num && num <= 100) {
				System.out.println("정수보다 크다");
				System.out.println("===================");
				num=0;
			}else if(num1 < num && num >= 1){
				System.out.println("정수보다 작다");
				System.out.println("===================");
				num=0;
			}
			if(num1==num) {
				num=1;
			}

		} catch (Exception e) {
			System.out.println("정수를 입력해주세요");
			System.out.println("===================");
		}
		return num;
	}
	public void answer() {
		if(num == 1) {
			System.out.println("숫자 맞추기 성공");
			++num3;
			dto.setNum(num3);
		}
		if(num == 0) {
			System.out.println("숫자 맞추기 실패");
			++num2;
			dto.setNum2(num2);
			System.out.println(dto.getNum());
			System.out.println(dto.getNum2());
		}
		System.out.println("정답은 : " + dto.getNum1());
		System.out.println("===================");
	}
	public Bang_DTO count() {	
		return dto;
	}
	Connection con = Connect.getConnect();
	public void update(int t, int f) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE look SET 성공 = ?, 실패 = ?";
		try {
			con = Connect.getConnect();
			ps = con.prepareStatement(sql);
			ps.setInt(1, t); //값 넣기
			ps.setInt(2, f);
			int push = ps.executeUpdate(); // 업데이트
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//	public void update(int t, int f) {
//	    Connection con = null;
//	    PreparedStatement ps = null;
//	    String sql = "INSERT INTO look(성공, 실패) VALUES(?, ?)";
//
//	    try {
//	        con = Connect.getConnect();
//	        ps = con.prepareStatement(sql);
//	        ps.setInt(1, t);
//	        ps.setInt(2, f);
//	        ps.executeUpdate();
//	        System.out.println("데이터가 성공적으로 입력되었습니다.");
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } 
