package Na;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.Connect;

public class Dao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public Dao() {
		con = Connect.getConnect();
	}

	public int insert(String id, String pwd, String name) {
		Dto dto;
		String sql = "insert into mafia values(?,?,?,?,?)";
		ResultSet rs;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, name);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("동일한 아이디 존재");
			// e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Dto> join() {
		String sql = "select * from mafia";
		ArrayList<Dto> arr = new ArrayList<Dto>();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Dto dto = new Dto();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setWin(rs.getInt("win"));
				dto.setLose(rs.getInt("lose"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
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
}