package com.sbi.browsing;

import java.io.IOException;
import java.io.PrintWriter;

import com.sbi.registration.RegBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
	int accountNumber=Integer.parseInt(req.getParameter("accountNumber"));
	int depositAmount = Integer.parseInt(req.getParameter("depositeAmount"));
	
	if(RegBean.getRegBean().getAccountNumber()==accountNumber)
	{
	
		int insert=DepositDAO.depositAmount(accountNumber, depositAmount);
		int transactionInsert=DepositDAO.updateTransaction(depositAmount,accountNumber);
		
		if(insert>0 && transactionInsert>0 )
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

}

