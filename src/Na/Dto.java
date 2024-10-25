package Na;

public class Dto {
	private String id, pwd, name;
	private int win,lose;
	public Dto(){
		
	}
	
	public Dto(String id, String pwd, String name,int win, int lose){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.win = win;
		this.lose = lose;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}
	
	public String toString() {
		return id + "\t" + name + "\t" + win + "\t" + lose;
	}
}