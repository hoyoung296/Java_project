package Huh;

public class DTO {
	private String id, name;
	private float winRate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setWinRate(float winRate) {
		this.winRate=winRate;
	}
	public float getWinRate() {
		return winRate;
	}

}
