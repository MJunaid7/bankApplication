package com.sbi.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserTransactionDAO 
{
	static ResultSet rs;
	public static ResultSet  getTransactions(int accountNumber,String fromDate,String toDate)
	{
	 try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
		PreparedStatement pstmt= con.prepareStatement("select * from sbi_users_transactions where accountnumber=?");
		pstmt.setInt(1,accountNumber);
		rs=pstmt.executeQuery();
	
		}catch(Exception e) {System.out.println(e);}
	 return rs;
	}

}
