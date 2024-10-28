package project;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 명령어 사용을 위한 준비 단계
			String id, pwd, url;
			id = "c##ghdud024";
			pwd = "ghdud1532";
			url = "jdbc:oracle:thin:@192.168.51.92:1521:xe";// orcl
			con = DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}