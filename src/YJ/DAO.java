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
			if (dto.getSystWord().isEmpty()) {
				throw new Exception();}
			
			System.out.print(dto.getSystWord());
			insertWord(dto.getSystWord());
		} catch (Exception e) {
			System.err.println(dto.getLastWord()+"으로 시작하는 단어가 없습니다.");
			dto.setGame("승리");
//			e.printStackTrace();
		}
		finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
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
		}finally { 
			try { 
			if(rs!=null) 
			{ 
			rs.close(); 
			
			} 
			if(ps!=null) 
			{ 
			ps.close(); 
			
			} 
			} catch (Exception ex) {} 
			}  
		}
	
	public void commit() {
		String sql="commit";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
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
		case 3:
			sql="select word from word where word like '"+inputSearch+"%'";
			break;
		case 4:
			sql="select word from word where word='"+inputSearch+"'";
			break;
		default:
			sql="select word from word where word='%"+inputSearch+"%'";
			break;
		}
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("word"));	}
			dto.setResult(ps.executeUpdate());
			
			if(dto.getResult()==0) {
//				System.out.println("해당 조건으로 등록된 단어가 없습니다.");
				return dto;}

			dto.setWordList(list);
			
		} catch (Exception e) {
			System.err.println("해당하는 단어를 찾을 수 없습니다.");
//			e.printStackTrace();
		}
		finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
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
		finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
			}  
		return dto;
	}
	
	public DTO rightWord(String inputWord){
		String sql="select word from word where word='"+inputWord+"' and used=1";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			dto.setResult(ps.executeUpdate());
			if (dto.getResult()!=0) {
				throw new Exception();
			}
		}catch (Exception e) {
			System.out.println();
			System.err.println("이미 사용한 단어입니다.");
			dto.setResult(1);
			dto.setGame("패배");
			return dto;
		}finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close();} 
			} catch (Exception ex) {}
			}
//		sql="select word from word where word='"+inputWord+"'";
//		try {
//			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
//			dto.setResult(ps.executeUpdate());
//			if (dto.getResult()==0) {
//				throw new Exception();
//			}
//		}catch (Exception e) {
//			System.out.println();
//			System.err.println("사전에 존재하지 않는 단어입니다.");
//			dto.setResult(1);
//			dto.setGame("패배");
//		}finally { 
//			try { 
//			if(rs!=null) 
//			{rs.close();} 
//			if(ps!=null) 
//			{ps.close();
////			} 
//			} catch (Exception ex) {}
//			}
		try {
			dto.setFirstWord(inputWord);
			dto.setLastWord(dto.getSystWord());
			dto=findWord(inputWord,4);
			if (dto.getFirstWord().equals(dto.getLastWord()) && inputWord.length()>1 && dto.getResult()!=0){
				dto.setResult(0);
			}
			else {
				if(!dto.getFirstWord().equals(dto.getLastWord())) {
					System.out.println();
					System.err.println("입력한 단어와 마지막 글자가 같지 않습니다.");
				}
				if(inputWord.length()<=1) {
					System.out.println();
					System.err.println("한글자는 입력이 불가합니다.");
				}
				if(dto.getResult()==0) {
					System.out.println();
					System.err.println("사전에 등록되지 않은 단어입니다.");
				}
				throw new Exception();
			}
		} catch (Exception e) {
			dto.setResult(1);
			dto.setGame("패배");
		}finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
			}  
		return dto;
		
	}
	

	
	public DTO insertWord(String inputWord){
		String sql=String.format("update word set used=1 where word='%s'",inputWord);
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
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
		finally { 
			try { 
			if(rs!=null) 
			{rs.close();} 
			if(ps!=null) 
			{ps.close(); } 
			} catch (Exception ex) {} 
			}  
		return dto;
	}
}
