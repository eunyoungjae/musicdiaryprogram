package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.MainController;

public class MusicDAO {
	public static ArrayList<Music> dbArrayList=new ArrayList<>();
	//1. 나만의노래를 테이블뷰에 등록
	public static int insertMusicData(Music music) {
		String insertMusic = "insert into musictbl" +
	"(name,song,album,date,url)"+"values"+"(?,?,?,?,?)"	;
		
		//1-2데이터베이스 커넥션 가져오기
		Connection con=null;
		//1-3쿼리문을 실행해야하 Statement를 만든다.
		PreparedStatement psmt=null;
		int count=0;
		try {		
		con=DBUtill.getConnection();
		psmt=con.prepareStatement(insertMusic);
		//1-4 쿼리문에 데이터연결
		psmt.setString(1, music.getName());
		psmt.setString(2, music.getSong());
		psmt.setString(3, music.getAlbum());
		psmt.setString(4, music.getDate());
		psmt.setString(5, music.getUrl());
		
		//1-6 데이터를 연결한 쿼리문 실행
		count=psmt.executeUpdate();
		
		if(count==0) {
			MainController.callAlert("쿼리문 실패 : 또 젠자앙..");
			return count;
		}
	} catch (SQLException e) {
		MainController.callAlert("삽입 실패 : 젠장.....");
	} finally {
		//1-6. 자원객체를 닫는다.
		try {
		if(psmt !=null) {psmt.close();}
		if(con !=null) {con.close();}
			} catch (SQLException e) {
				MainController.callAlert("닫기실패 : 실패");
		}
	}
	return count;		
	}
	
	public static ArrayList<Music> getMusicData(){
		String selectMusic="select * from musictbl ";
		
		Connection con=null;
		
		PreparedStatement psmt=null;
		
		ResultSet rs=null;
		
		try {
			con=DBUtill.getConnection();
			psmt=con.prepareStatement(selectMusic);
			
			rs=psmt.executeQuery();
			if(rs==null) {
				MainController.callAlert("가져오기 실패 : 다시 확인해주세요");
				return null;
			}
			while(rs.next()) {
				Music music=new Music(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				dbArrayList.add(music);
			}
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 다시시도하세요");
		}
		return dbArrayList;
	}
	public static int deleteMusicData(int no) {
		String deleteMusic = "delete from musictbl where no =? ";
		Connection con=null;
		PreparedStatement psmt=null;
		int count =0;
		
		try {
			con=DBUtill.getConnection();
			psmt=con.prepareStatement(deleteMusic);
			psmt.setInt(1, no);
			
			count = psmt.executeUpdate();
			if(count==0) {
				MainController.callAlert("삭제 실패 : 다시 시도해주세요");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("삭제 실패 : 삭제 실패");
		}
		return count;
	}
	public static int updateMusicData(Music music) {
		String updataMusic = "update musictbl set "+
	"name=?,song=?,album=?,date=?,url=? ";
		
		Connection con=null;
		PreparedStatement psmt=null;
		int count=0;
		try {
			con=DBUtill.getConnection();
			psmt=con.prepareStatement(updataMusic);
			
			psmt.setString(1, music.getName());
			psmt.setString(2, music.getSong());
			psmt.setString(3, music.getAlbum());
			psmt.setString(4, music.getDate());
			psmt.setString(5, music.getUrl());
			
			count=psmt.executeUpdate();
			if(count==0) {
				MainController.callAlert("수정실패 : 확인후 다시시도해주세요");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("수정쿼리실패 : 다시 시도해주세요");
			e.printStackTrace();
		}
		return count;
	}
}
