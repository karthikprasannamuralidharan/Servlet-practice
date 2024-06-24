package servletpckage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginValidation {

	public static boolean validate(String name, String password) {
		boolean status = false;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql:///servletdb","root", "root");
			String query = "select * from register where username = ? and password = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			System.out.println(rs.next());
			status = rs.next();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
		
	}

}
