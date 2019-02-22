package Model;

public class Singer {
	private String imagePath;
	private String name2;
	private String birth;
	private String agency;
	private String awards;
	private String topsing;
	public Singer(String imagePath, String name2, String birth, String agency, String awards, String topsing) {
		super();
		this.imagePath = imagePath;
		this.name2 = name2;
		this.birth = birth;
		this.agency = agency;
		this.awards = awards;
		this.topsing = topsing;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getTopsing() {
		return topsing;
	}
	public void setTopsing(String topsing) {
		this.topsing = topsing;
	}
	@Override
	public String toString() {
		return "Singer [imagePath=" + imagePath + ", name2=" + name2 + ", birth=" + birth + ", agency=" + agency
				+ ", awards=" + awards + ", topsing=" + topsing + "]";
	}

	
	
}
