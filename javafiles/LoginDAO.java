package com.sbi.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
static ResultSet rs;
public static ResultSet isUserExist(int accountNumber,String password)
{
	 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
		PreparedStatement pstmt= con.prepareStatement("select * from sbi_users_table where accountnumber=? and password=?");
		pstmt.setInt(1,accountNumber);
		pstmt.setString(2,password);
		rs=pstmt.executeQuery();
		}catch(Exception e) {System.out.println(e);}
	 return rs;
}
}
