package com.sbi.browsing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.sbi.registration.RegBean;

public class DepositDAO
{
	 static int insert;static int transactionInsert;
	 public static int depositAmount(int accountNumber,int depositAmount)
	 {
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
			PreparedStatement pstmt= con.prepareStatement("update sbi_users_balance set balance=balance+? where accountnumber=?");
			pstmt.setInt(1,depositAmount);
			pstmt.setInt(2,accountNumber);
			insert=pstmt.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
		 return insert;
	 }
	 
	 public static int updateTransaction(int amount,int accountNumber)
	 {
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
			PreparedStatement pstmt1= con.prepareStatement("insert into sbi_users_transactions values(sbi_transactionid.nextval,sysdate,?,?,?)");
			pstmt1.setInt(1,amount);
			pstmt1.setString(2,"Credited");
			pstmt1.setInt(3,accountNumber);
			transactionInsert=pstmt1.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
		 return insert; 
	 }
}
