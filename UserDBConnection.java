package jdbc_project;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserDBConnection {
	static Connection usercon;
	public static Connection createUserDBConnection() {	
		try {
			//load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//get connection 
			usercon=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_details","root","Rahul@14");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return usercon;
	}
}