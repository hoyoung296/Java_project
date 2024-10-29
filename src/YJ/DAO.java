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
		String sql="select word from word where word like '"+lastWord+"%' and used=0";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			ran=new Random();
			ArrayList<String> list=new ArrayList<String>();
			while (rs.next()) {
//				String word=;
				list.add(rs.getString("word"));
				}
			dto.setSystWord(list.get(ran.nextInt(list.size())));
			System.out.print(dto.getSystWord());
			insertWord(dto.getSystWord());
		} catch (Exception e) {
			System.err.println(dto.getLastWord()+"로 시작하는 단어가 없습니다.");
//			e.printStackTrace();
		}
		return dto;
	}
	
	public void delWord(String inputWord){
		String sql="delete word where word='"+inputWord+"'";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println(inputWord+" 삭제 완료");
		} catch (Exception e) {
			System.err.println("삭제할 수 없는 단어입니다.");
//			e.printStackTrace();
		}}
	
	public void setWord(String inputWord){
		String sql=String.format("insert into word values('%s',0)", inputWord);
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println(inputWord+" 등록 완료");
		} catch (Exception e) {
			System.err.println("등록할 수 없는 단어입니다.");
//			e.printStackTrace();
		}}
	
	public void commit() {
		String sql="commit";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DTO findWord(String inputSearch,int menu) {
		String sql;
		ArrayList<String> list=new ArrayList<String>();
		switch (menu) {
		case 1:
			sql="select word from word where word like '%"+inputSearch+"%'";
			break;
		case 2:
			sql="select word from word where word like '%"+inputSearch+"'";
			break;
		default:
			sql="select word from word where word like '"+inputSearch+"%'";
			break;
		}
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("word"));	}
			dto.setResult(ps.executeUpdate());
			
			if(dto.getResult()==0) {
				System.out.println("해당 조건으로 등록된 단어가 없습니다.");
				return dto;}

			dto.setWordList(list);
			
		} catch (Exception e) {
			System.err.println("해당하는 단어를 찾을 수 없습니다.");
			e.printStackTrace();
		}
		return dto;
	}
	public DTO wordList() {
		String sql="select word from word";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			ran=new Random();
			ArrayList<String> list=new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString("word"));
				}
			dto.setSystWord(list.get(ran.nextInt(list.size())));
			insertWord(dto.getSystWord());
		} catch (Exception e) {
			System.err.println("초기화 실패");
//			e.printStackTrace();
		}
		return dto;
	}
	
	public DTO rightWord(String inputWord){
		String sql="select word from word where used=1 and word='"+inputWord+"'";
		dto.setFirstWord(inputWord);
		dto.setLastWord(dto.getSystWord());
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			dto.setResult(ps.executeUpdate());
			ps.close();
		}catch (Exception e) {
			System.err.println("등록 되지 않은 단어입니다.");
		}
		try {
			dto=findWord(inputWord, 1);
			if (dto.getFirstWord().equals(dto.getLastWord()) && inputWord.length()>1 &&dto.getResult()>=1){
//				System.out.println();
				dto.setResult(0);
			}
			else {
				throw new Exception();
			}
		} catch (Exception e) {
			dto.setResult(1);
//			System.err.println("ddd");
		}
		return dto;
		
	}
	public void rigthWord2(String firstWord, String lastWord) {
		
	
	}
	
	public DTO insertWord(String inputWord){
		String sql=String.format("update word set used=1 where word='%s'",inputWord);
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		commit();
		return dto;}
	
	
	public DTO gameSetting(){
		
		String sql="update word set used=0";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			wordList();
			System.out.println("게임 초기화 완료");
			System.out.println("");
			System.out.print(dto.getSystWord() +" >>> ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
