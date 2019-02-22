package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.MainController;

public class AlbumDAO {
	public static ArrayList<Album> dbArrayList3 = new ArrayList<>();

	// 1. 나만의노래를 테이블뷰에 등록
	public static ArrayList<Album> selectAlbumData(String name) {
		String selectAlbum = "select * from albumtbl where name='" + name + "'";
		// 1-2 데이터베이스 커넥션을 가져와야한다.
		Connection con = null;
		// 1-3. 쿼리문을 실행해야할 Statement를 만든다.
		PreparedStatement psmt = null;
		// 2-4. 쿼리문을 실행하고나서 가져와야할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;

		try {
			con = DBUtill.getConnection();
			psmt = con.prepareStatement(selectAlbum);

			// executeQuery는 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
			// executeUpdateㄴ 쿼리문을 실행해서 테이블에 저장할때 사용하는 번개문
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("가져오기 실패 :  젠자앙..");
				return null;
			}
			while (rs.next()) {
				Album album = new Album(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				dbArrayList3.add(album);
			}
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 젠장.....");
		} finally {
			// 1-6. 자원객체를 닫는다.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				MainController.callAlert("닫기실패 : 실패");
			}
		}
		return dbArrayList3;
	}
}
