package com.sbi.browsing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransferDAO {
	static int insert;static int transactionInsert;
	 public static int transferAmount(int senderAccountNumber,int receiverAccountNumber,int transferAmount)
	 {
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
			PreparedStatement sender= con.prepareStatement("update sbi_users_balance set balance=balance-? where accountnumber=?");
			sender.setInt(1,transferAmount);
			sender.setInt(2,senderAccountNumber);
			insert=sender.executeUpdate();
			
			PreparedStatement receiver= con.prepareStatement("update sbi_users_balance set balance=balance+? where accountnumber=?");
			receiver.setInt(1,transferAmount);
			receiver.setInt(2,receiverAccountNumber);
			insert=insert+receiver.executeUpdate();
			
			}catch(Exception e) {System.out.println(e);}
		 return insert;
	 }
	 public static int updateTransaction(int transferAmount, int senderAccountNumber,int  receiverAccountNumber)
	 {
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","LION");
			PreparedStatement pstmt1= con.prepareStatement("insert into sbi_users_transactions values(sbi_transactionid.nextval,sysdate,?,?,?)");
			pstmt1.setInt(1,transferAmount);
			pstmt1.setString(2,"Debited");
			pstmt1.setInt(3, senderAccountNumber);
			transactionInsert=pstmt1.executeUpdate();
			
			PreparedStatement pstmt2= con.prepareStatement("insert into sbi_users_transactions values(sbi_transactionid.nextval,sysdate,?,?,?)");
			pstmt2.setInt(1,transferAmount);
			pstmt2.setString(2,"Credited");
			pstmt2.setInt(3, receiverAccountNumber);
			transactionInsert=pstmt2.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
		 return insert; 
	 }
}

