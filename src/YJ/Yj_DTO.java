package YJ;

public class Yj_DTO {
	private String inputWord,nextWord,firstWord;

	public String getFirstWord() {
		return firstWord;
	}

	public void setFirstWord(String firstWord) {
		this.firstWord = firstWord.substring(firstWord.length()-1);
	}

	public String getInputWord() {
		return inputWord;
	}

	public void setInputWord(String inputWord) {
		this.inputWord = inputWord;
	}

	public String getNextWord() {
		return nextWord;
	}

	public void setNextWord(String nextWord) {
		this.nextWord = nextWord;
	}
	

	
}
