package YJ;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import project.Connect;

public class DAO {
	DTO dto=new DTO();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Random ran;
	
	public DAO() {
		con=Connect.getConnect();
	}
	
	public DTO wordList(String lastWord) {
		String spl="select word from word where word like '"+lastWord+"%' and used=0";
		try {
			ps=con.prepareStatement(spl);
			rs=ps.executeQuery();
			ran=new Random();
			ArrayList<String> list=new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString("word"));
				}
			dto.setSystWord(list.get(ran.nextInt(list.size())));
			System.out.print(dto.getSystWord());
		} catch (Exception e) {
			System.out.println("단어가 없습니다.");
			e.printStackTrace();
		}
		return dto;
	}
	
	public DTO wordList() {
		String spl="select word from word";
		try {
			ps=con.prepareStatement(spl);
			rs=ps.executeQuery();
			ran=new Random();
			ArrayList<String> list=new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString("word"));
				}
			dto.setSystWord(list.get(ran.nextInt(list.size())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public boolean rightWord(String inputWord){
		boolean used=true;
		
		dto.setFirstWord(inputWord);
		dto.setLastWord(dto.getSystWord());
		
		if(!dto.getFirstWord().equals(dto.getLastWord()))
		{System.out.println("단어가 일치하지 않습니다!");
			used=false;
			return used;}
		
		String spl="select used from word where word='"+inputWord+"'";
		try {
			ps=con.prepareStatement(spl);
			rs=ps.executeQuery();
			}
		catch (Exception e) {
			System.out.println("아직 등록되지 않은 단어입니다.");
			return used;
//			e.printStackTrace();
		}
		return used;
		
		
	}
	public DTO insertWord(String inputWord, String sysoWord){
		String spl=String.format("update word set used=1 where word='%s' or word='%s'",inputWord,sysoWord);
		try {
			ps=con.prepareStatement(spl);
			rs=ps.executeQuery();
		} catch (Exception e) {
			System.out.println("아직 등록되지 않은 단어입니다.");
			e.printStackTrace();
		}
		return dto;
	}
	
	public void gameSetting(){
		String spl="update word set used=0";
		try {
			ps=con.prepareStatement(spl);
			rs=ps.executeQuery();
			wordList();
			System.out.print(dto.getSystWord() +" >>> ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
