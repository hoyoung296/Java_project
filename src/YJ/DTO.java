package YJ;

import java.util.ArrayList;
import java.util.Scanner;

public class DTO {
	Scanner input=new Scanner(System.in);
	private String inputWord,systWord,firstWord,lastWord,game;
	private int result;
	private ArrayList<String> list;
	
	public DTO() {}
	
	public DTO(String inputWord, String systWord,String firstWord, String lastWord, int result) {
		this.inputWord=inputWord;
		this.systWord=systWord;
		this.firstWord=firstWord;
		this.lastWord=lastWord;
		this.result=result;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game=game;
	}
	public String getLastWord() {
		return lastWord;
	}
	public void setLastWord(String lastWord) {
		this.lastWord = lastWord.substring(lastWord.length()-1);
	}
	public String getInputWord() {
		return inputWord;
	}
	public void setInputWord(String inputWord) {
		this.inputWord = inputWord;
	}
	public String getSystWord() {
		return systWord;
	}
	public void setSystWord(String systWord) {
		this.systWord = systWord;
	}
	public String getFirstWord() {
		return firstWord;
	}
	public void setFirstWord(String firstWord) {
		this.firstWord = firstWord.substring(0,1);
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public void setWordList(ArrayList<String> list){
		this.list=list;
	}
	public ArrayList<String> getWordList(){
		return list;
	}
}
