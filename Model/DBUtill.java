package Model;

import java.sql.Connection;
import java.sql.DriverManager;

import Controller.MainController;

public class DBUtill {
	public static Connection getConnection() {
	Connection con=null;
	//1. MySql database class 로드한다.
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//2. 주소,ID,비밀번호를 통해서 접속요청
		con=DriverManager.getConnection("jdbc:mysql://192.168.0.173/musicdb", "root", "123456");
	} catch (Exception e) {
		
	}
	return con;
	}
}
