package jdbc_project;

//Import MySQL
import java.util.*;
import java.sql.*;
public class DBConnection {
		static Connection con;
	public static Connection createDBConnection() {
		try {
			//Getting Connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_schema","root","Rahul@14");
			
		}catch(Exception e) {
			  
			e.printStackTrace();
		}
		return con;
	}
}
