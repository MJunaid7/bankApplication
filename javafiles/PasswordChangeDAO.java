package com.sbi.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PasswordChangeDAO 
{
	static int changed;
 public static int changePassword(int accountNumber,String firstName,String lastName,String password)
 {
	 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
		PreparedStatement pstmt= con.prepareStatement("update sbi_users_table set password=? where accountnumber=? and firstname=? and lastname=?");
		pstmt.setString(1,password);
		pstmt.setInt(2, accountNumber);
		pstmt.setString(3,firstName);
		pstmt.setString(4,lastName);
		changed=pstmt.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
	 return changed;
 }
}
