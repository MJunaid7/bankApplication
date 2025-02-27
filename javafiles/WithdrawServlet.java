package com.sbi.browsing;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbi.registration.RegBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 int accountNumber=Integer.parseInt(req.getParameter("accountNumber"));
	 int withdrawAmount = Integer.parseInt(req.getParameter("withdrawAmount"));
	 String password= req.getParameter("password");
	 if(RegBean.getRegBean().getAccountNumber()==accountNumber)
	 {
	 
	 int availableAmount=0;
	 try {
		ResultSet rs =BalanceDetails.getDetails(accountNumber);
		 while(rs.next())
		 {
			 availableAmount=rs.getInt("balance");
		 }
	     } catch (SQLException e) {e.printStackTrace();}
		
	 if(availableAmount>=withdrawAmount)
	 {
			int insert=WithdrawDAO.withdrawAmount(accountNumber, withdrawAmount,password);
			int transactionInsert= WithdrawDAO.updateTransaction(withdrawAmount, accountNumber);
			if(insert>0)
			{
				RequestDispatcher rd= req.getRequestDispatcher("transactionSuccess.html");
				rd.forward(req, resp);
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("transactionFail.html");
				rd.forward(req, resp);
			}
	 }
	 else
	 {
		 RequestDispatcher rd = req.getRequestDispatcher("transactionFail.html");
		 rd.forward(req, resp);
	 }
	 }
	 else
	 {
		 RequestDispatcher rd = req.getRequestDispatcher("transactionFail.html");
		 rd.forward(req, resp);
	 }
}
}
