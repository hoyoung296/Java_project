package YJ;

import java.sql.*;

import java.util.ArrayList;
import java.util.Random;
import project.*;

public class Yj_DAO {
	Random ran=new Random();
	PreparedStatement ps;
	Connection con;
	ResultSet rs;
	
	String nextWord;

	public Yj_DAO(){
		con=Connect.getConnect();
	}
	
	
	public void getNext(String inputWord){
		ArrayList<String> list=new ArrayList<String>();
		String lastWord=inputWord.substring((inputWord.length())-1);
		String sql="select * from word where word like '"+lastWord+"%'";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {list.add(rs.getString("word"));}
			do{
				nextWord=list.get(ran.nextInt(list.size()));
				}
			while(checkUsed(nextWord)!=0);
			System.out.println(nextWord);
			
//			if (rs.next()) {
//				System.out.println(rs.getString("word"));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	public void newGame(){
		String sql="delete usedword";
		try {
			ps=con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	public int checkUsed(String word){
		int result=0;
		String sql="select * from usedword where usedword='"+word+"'";
		try {
			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;}
	
	public void insert(String word){
		String sql="insert into usedword values('"+word+"')";
		try {
			ps=con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}}
	
	
	

}
//	
//	public void getFirst(){
//		String sql="select * from word";
//		ArrayList<String> word=new ArrayList<String>();
//		try {
//			PreparedStatement ps=con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			int result=ps.executeUpdate();
//			while(rs.next()) {
//				Yj_DTO dto= new Yj_DTO();
//				System.out.println("b");
//				//dto.setNextWord(rs.getString("word"));
//				word.add(rs.getString("word"));}
//			nextWord=word.get(ran.nextInt(result));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(nextWord);
//	}

//	public String getNext(String input) {
//		String sql="select * from word where  like='%?'";
//		try {
//			ArrayList<String> word=new ArrayList<String>();
//			ps= con.prepareStatement(sql);
//			ps.setString(1,input.substring(input.length()-1));
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				word.add(rs.getString("word"));
//			}
//			nextWord=word.get(ran.nextInt(word.size()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return nextWord;}
	
//	public int usedword(String input){
//		int result=0;
//		String sql="select * from usedword where  like='?'";
//		try {
//			ps= con.prepareStatement(sql);
//			ps.setString(1,input);
//			result=ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;

