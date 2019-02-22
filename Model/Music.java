package Model;

public class Music {
	private int no;
	private String name;
	private String song;
	private String album;
	private String date;
	private String url;
	public Music(int no, String name, String song, String album, String date, String url) {
		super();
		this.no = no;
		this.name = name;
		this.song = song;
		this.album = album;
		this.date = date;
		this.url = url;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
