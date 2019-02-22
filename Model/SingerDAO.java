package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Controller.MainController;

public class SingerDAO {
	public static ArrayList<Singer> dbArrayList2 = new ArrayList<>();
	public static ArrayList<insta> dbArrayList4 = new ArrayList<>();
	// 1. 나만의노래를 테이블뷰에 등록
	public static ArrayList<Singer> selectMusicData(String name) {
		String selectSinger = "select * from singertbl where name='" + name + "'";
		// 1-2 데이터베이스 커넥션을 가져와야한다.
		Connection con = null;
		// 1-3. 쿼리문을 실행해야할 Statement를 만든다.
		PreparedStatement psmt = null;
		// 2-4. 쿼리문을 실행하고나서 가져와야할 레코드를 담고있는 보자기 객체
		ResultSet rs = null;

		try {
			con = DBUtill.getConnection();
			psmt = con.prepareStatement(selectSinger);

			// executeQuery는 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
			// executeUpdateㄴ 쿼리문을 실행해서 테이블에 저장할때 사용하는 번개문
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("가져오기 실패 :  젠자앙..");
				return null;
			}
			while (rs.next()) {
				Singer singer = new Singer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				dbArrayList2.add(singer);
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
		return dbArrayList2;
	}
	
	//4. 테이블뷰에서 수정한 레코드를 데이터베이스 테이블에 수정하는 함수
		public static int updateSingerData(Singer singer, String name2) {
			//4-1 데이터베이스에 수정
			String updateSinger = "update singertbl set "+
					"image=?, birth=?, agency=?, prize=?, topsing=? where name=?";
					System.out.println("함수시작"+singer.toString());
					System.out.println(name2);
					singer.toString();
					//1-2 데이터베이스 커넥션을 가져와야한다.
					Connection con=null;
					//1-3. 쿼리문을 실행해야할 Statement를 만든다.
					PreparedStatement psmt=null;
					int count=0;
					try {
						con=DBUtill.getConnection();			
						psmt=con.prepareStatement(updateSinger);
						//1-4. 쿼리문에 데이터를 연결
						
						psmt.setString(1, singer.getImagePath());
						psmt.setString(2, singer.getBirth());
						psmt.setString(3, singer.getAgency());
						psmt.setString(4, singer.getAwards());
						psmt.setString(5, singer.getTopsing());
						psmt.setString(6, name2);
						
						//1-5. 데이터를 연결한 쿼리문 실행
						count=psmt.executeUpdate();
						
						System.out.println("쿼리문 실행결과 :"+count);
						if(count==0) {
							MainController.callAlert("수정쿼리 실패 : 또 젠자앙..");
							return count;
						}else {
							
						}
					} catch (SQLException e) {
						MainController.callAlert("수정쿼리 실패 : 젠장.....");
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

		public static int insertStudentData(Singer singer) {
			int count = 0;

			// 1-1. 데이터베이스에 학생테이블에 입력하는 쿼리문
			StringBuffer insertSinger = new StringBuffer();
			insertSinger.append("insert into singertbl ");
			insertSinger.append("values ");
			insertSinger
					.append("(image,name,birth,agency,prize,topsing) ");
			insertSinger.append("(?,?,?,?,?,?) ");

			// 1-2. 데이터베이스 Connection을 가져와야 한다.
			Connection con = null;

			// 1-3. 쿼리문을 실행해야할 Statement를 만들어야 한다.
			PreparedStatement psmt = null;
			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(insertSinger.toString());
				// 1-4. 쿼리문에 실제 데이터를 연결한다.
				psmt.setString(1, singer.getImagePath());
				psmt.setString(2, singer.getName2());
				psmt.setString(3, singer.getBirth());
				psmt.setString(4, singer.getAgency());
				psmt.setString(5, singer.getAwards());
				psmt.setString(6, singer.getTopsing());
				

				// 1-5. 실제데이터를 연결한 쿼리문을 실행한다.
				// executeUpdate(); 쿼리문을 실행해서 테이블에 저장을 할때 사용하는 번개문
				count = psmt.executeUpdate();
				if (count == 0) {
					MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문에 문제가 있습니다.");
					return count;
				}
			} catch (SQLException e) {
				MainController.callAlert("삽입 실패 : 데이터베이스 삽입에 문제가 있습니다.");
			} finally {
				// 1-6. 자원객체를 닫아야한다.
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					MainController.callAlert("자원 닫기 실패 : 자원 닫기에 문제가 있습니다.");
				}
			}
			return count;
		}

		// 2. 테이블에서 전체 내용을 모두 가져오는 함수
		public static ArrayList<Singer> getSingerTotalData() {

			// 2-1. 데이터베이스 학생테이블에 있는 레코드를 모두 가져오는 쿼리문
			String selectSinger = "select * from singertbl";

			// 2-2. 데이터베이스 Connection을 가져와야 한다.
			Connection con = null;

			// 2-3. 쿼리문을 실행해야할 Statement를 만들어야 한다.
			PreparedStatement psmt = null;

			// 2-4. 쿼리문을 싱행하고나서 가져와야할 레코드를 담고있는 보자기 객체
			ResultSet rs = null;
			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(selectSinger);

				// 2-5. 실제데이터를 연결한 쿼리문을 실행한다.(번개를 치는것)
				// executeQuery(); 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
				rs = psmt.executeQuery();
				if (rs == null) {
					MainController.callAlert("select 실패 : select에 문제가 있습니다.");
					return null;
				}
				while (rs.next()) {
					Singer singer = new Singer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6));
					dbArrayList2.add(singer);
				} // end of while
			} catch (SQLException e) {
				MainController.callAlert("삽입 실패 : 데이터베이스 삽입에 문제가 있습니다.");
			} finally {
				// 1-6. 자원객체를 닫아야한다.
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					MainController.callAlert("자원 닫기 실패 : 자원 닫기에 문제가 있습니다.");
				}
			}
			return dbArrayList2;
		}
		
		public static int deleteStudentData(String no) {

			// 3-1. 데이터베이스 학생테이블에 있는 레코드를 삭제하는 쿼리문
			String deleteSinger = "delete from singertbl where name = ? ";

			// 3-2. 데이터베이스 Connection을 가져와야 한다.
			Connection con = null;

			// 3-3. 쿼리문을 실행해야할 Statement를 만들어야 한다.
			PreparedStatement psmt = null;

			// 3-4. 쿼리문을 싱행하고나서 가져와야할 레코드를 담고있는 보자기 객체
			int count = 0;
			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(deleteSinger);
				psmt.setString(1, no);
				// 3-5. 실제데이터를 연결한 쿼리문을 실행한다.(번개를 치는것)
				// executeQuery(); 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
				count = psmt.executeUpdate();
				if (count == 0) {
					MainController.callAlert("delete 실패 : delete에 문제가 있습니다.");
					return count;
				}

			} catch (SQLException e) {
				MainController.callAlert("delete 실패 : delete에 문제가 있습니다.");
			} finally {
				// 3-6. 자원객체를 닫아야한다.
				try {
					if (psmt != null) {
						psmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					MainController.callAlert("자원 닫기 실패 : 자원 닫기에 문제가 있습니다.");
				}
			}
			return count;
		}
		public static ArrayList<insta> selectFollowerData() {
			String selectFollower = "select * from charttbl ";
			// 1-2 데이터베이스 커넥션을 가져와야한다.
			Connection con = null;
			// 1-3. 쿼리문을 실행해야할 Statement를 만든다.
			PreparedStatement psmt = null;
			// 2-4. 쿼리문을 실행하고나서 가져와야할 레코드를 담고있는 보자기 객체
			ResultSet rs = null;

			try {
				con = DBUtill.getConnection();
				psmt = con.prepareStatement(selectFollower);

				// executeQuery는 쿼리문을 실행해서 결과를 가져올때 사용하는 번개문
				// executeUpdateㄴ 쿼리문을 실행해서 테이블에 저장할때 사용하는 번개문
				rs = psmt.executeQuery();
				if (rs == null) {
					MainController.callAlert("가져오기 실패 :  젠자앙..");
					return null;
				}
				while (rs.next()) {
					insta insta= new insta(rs.getString(1), rs.getInt(2));
					dbArrayList4.add(insta);
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
			return dbArrayList4;
		}
}
