package Model;

public class insta {
	private String name;
	private int follower;
	public insta(String name, int follower) {
		super();
		this.name = name;
		this.follower = follower;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFollower() {
		return follower;
	}
	public void setFollower(int follower) {
		this.follower = follower;
	}
	
}
