package Model;

public class Album {
	private String albumImage;
	private String albumName;
	private String singer;
	private String birth;
	private String albumSong;
	public Album(String albumImage, String albumName, String singer, String birth, String albumSong) {
		super();
		this.albumImage = albumImage;
		this.albumName = albumName;
		this.singer = singer;
		this.birth = birth;
		this.albumSong = albumSong;
	}
	public String getAlbumImage() {
		return albumImage;
	}
	public void setAlbumImage(String albumImage) {
		this.albumImage = albumImage;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAlbumSong() {
		return albumSong;
	}
	public void setAlbumSong(String albumSong) {
		this.albumSong = albumSong;
	}
	
	
}
