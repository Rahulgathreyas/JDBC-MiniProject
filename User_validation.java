package jdbc_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_validation {
	Connection usercon;
	public int check_username(String username) {
		// TODO Auto-generated method stub
		
		usercon=UserDBConnection.createUserDBConnection();
		String query="select * from users where Username= "+"'"+username+"'";
		try {
			Statement stmt=usercon.createStatement();
			ResultSet result=stmt.executeQuery(query);
			if(!result.isBeforeFirst()) {
				System.out.println("Invalid Username");
				return 0;
			}
			else {
				result.next();
				return 1;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int check_password(String password) {
		// TODO Auto-generated method stub
		
		usercon=UserDBConnection.createUserDBConnection();
		String query="select * from users where Password= "+"'"+password+"'";
		try {
			Statement stmt=usercon.createStatement();
			ResultSet result=stmt.executeQuery(query);
			if(!result.isBeforeFirst()) {
				System.out.println("Invalid Password");
				return 0;
			}
			else {
				result.next();
				return 1;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}