package Huh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import project.Connect;

public class DAO {
	DTO dto=new DTO();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public DAO() {
		con=Connect.getConnect();
	}

	public void showList(String id) {
		String sql="select * from memberh where id='"+id+"'";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println("ID \tScore");
			System.out.println("__________________");
			while(rs.next()) {
				System.out.println(rs.getString("id")+"\t"+rs.getFloat("score"));
			}
		} catch (Exception e) {
			System.err.println("해당하는 사용자 없음");
//			e.printStackTrace();
		}
	}
	
	public void newUser(String id) {
		String sql="insert into memberh(id) values('"+id+"')";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println("아이디 생성 완료");
		} catch (Exception e) {
			System.err.println("사용 불가능한 아이디");
//			e.printStackTrace();
		}
	}
	
	public void setScore(String id,Float score) {

		String sql=String.format("update memberh set score=%.2f where id='%s'", score,id);
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println("점수 적용");
			showList(id);
		} catch (Exception e) {
			System.err.println("점수를 업데이트 할 수 없습니다.");
			e.printStackTrace();
		}
	}
	
	
}
