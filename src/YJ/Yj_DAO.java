package YJ;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import project.Connect;

public class Yj_DAO {
	Yj_DTO dto=new Yj_DTO();
	Random ran=new Random();
	PreparedStatement ps;
	Connection con;
	ResultSet rs;

	public Yj_DAO(){
		con=Connect.getConnect();}
	
	public void game1(){
//		System.out.println(dto.getInputWord().substring(1));
		System.out.println(dto.getFirstWord());
		System.out.println("시스템 답안 생성 중");
		ArrayList<String> list=new ArrayList<String>();
//		String word="가";
		String sql="select * from word where word like '"+dto.getFirstWord()+"%'";
		try {
			ps=con.prepareStatement(sql);
//			ps.setString(1, dto.getFirstWord());
			rs=ps.executeQuery();
			while (rs.next()) {list.add(rs.getString("word"));}
			System.out.println(list);
			do{
				dto.setNextWord(list.get(ran.nextInt(list.size())));
				}
			while(game3()!=0);
			System.out.println(dto.getNextWord());
			System.out.println("시스템 답안 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	public int game2() {
		String sql="select * from usedword where usedword='"+dto.getInputWord()+"'";
		System.out.println("유저 답안 중복 체크");
		System.out.println(dto.getInputWord());
		int result=0;
		try {
			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
			result=ps.executeUpdate();
			System.out.println("유저 답안 중복 체크 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}System.out.println(result); return result;}
	
	public int game3(){
		System.out.println("시스템 답안 중복 체크");
		int result=0;
		String sql="select * from usedword where usedword='"+dto.getNextWord()+"'";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void gameSetting(){
		System.out.println("게임 초기화 중");
		String sql="delete usedword";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	public int usedWord(String word){ //중복 체크
//		int result=0;
//		String sql="select * from usedword where usedword='"+word+"'";
//		try {
//			ps=con.prepareStatement(sql);
////			 
//			result=ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;}
	
//	public int usedWord(){
//		int result=0;
//		String sql="select * from usedword where usedword='"+dto.getInputWord()+"'";
//		try {
//			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
//			result=ps.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

//	
//	public void insert(){
//		String sql="insert into usedword values('"+word+"')";
//		try {
//			ps=con.prepareStatement(sql);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}}
	
	
	

}