package com.sbi.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistrationDAO 
{
 static int insert;
 public static int registerNewUser(RegBean b)
 {
	 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
		PreparedStatement pstmt= con.prepareStatement("insert into sbi_users_table values(?,?,?,?,?,?,?,?)");
		pstmt.setInt(1,b.getAccountNumber());
		pstmt.setString(2,b.getPassword());
		pstmt.setString(3,b.getBranchName());
		pstmt.setString(4,b.getFirstName());
		pstmt.setString(5,b.getLastName());
		pstmt.setInt(6,b.getAge());
		pstmt.setString(7,b.getGender());
		pstmt.setString(8,b.getAccountType());
		insert=pstmt.executeUpdate();
		PreparedStatement pstmt1= con.prepareStatement("insert into sbi_users_balance values(?,?)");
		pstmt1.setInt(1,b.getAccountNumber());
		pstmt1.setInt(2,0);
		pstmt1.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
	 return insert;
 }
}
