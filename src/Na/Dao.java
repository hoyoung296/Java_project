package Na;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import project.Connect;

public class Dao {
	Scanner sc = new Scanner(System.in);
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public Dao() {
		con = Connect.getConnect();
	}

	public int insert(String id) {
		String sql1 = "insert into mafia(id) values(?)";
		String sql2 = "update mafia set pwd= ?, name=?,win=0,lose=0 where id=?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql1);
			ps.setString(1, id);
			ps.executeUpdate();
			System.out.print("저장 pwd : ");
			String pwd = sc.next();
			System.out.print("저장 name : ");
			String name = sc.next();
			ps = con.prepareStatement(sql2);
			ps.setString(1, pwd);
			ps.setString(2, name);
			ps.setString(3, id);
			result = ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("동일한 아이디 존재");
			// e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Dto> getlist(String id, ArrayList<Dto> list) {
		String sql = "select * from mafia where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Dto dto = new Dto();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setWin(rs.getInt("win"));
				dto.setLose(rs.getInt("lose"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void update(ArrayList<Dto> list) {
		for (int i = 0; i < list.size(); i++) {
			String sql = "update mafia set win=?,lose=? where id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, list.get(i).getWin());
				ps.setInt(2, list.get(i).getLose());
				ps.setString(3, list.get(i).getId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int delete(String id) {
		int result = 0;
		String sql = "delete from mafia where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}