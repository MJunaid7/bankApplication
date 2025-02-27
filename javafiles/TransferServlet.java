package com.sbi.browsing;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbi.registration.RegBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	int senderAccountNumber =Integer.parseInt(req.getParameter("senderAccountNumber"));
	int receiverAccountNumber = Integer.parseInt(req.getParameter("receiverAccountNumber"));
	int transferAmount= Integer.parseInt(req.getParameter("transferAmount"));
	
	if(RegBean.getRegBean().getAccountNumber()==senderAccountNumber && RegBean.getRegBean().getAccountNumber()!=receiverAccountNumber)
	{
	
	ResultSet sender=BalanceDetails.getDetails(senderAccountNumber);
	ResultSet receiver=BalanceDetails.getDetails(receiverAccountNumber);
	
	int senderAccount=0;int availableBalance=0;
	int receiverAccount=0;
	
	try {
		while(sender.next())
		{
			senderAccount=sender.getInt("accountnumber");
			availableBalance=sender.getInt("balance");
		}
		while(receiver.next())
		{
			receiverAccount=receiver.getInt("accountnumber");
		}
	} catch (SQLException e) {e.printStackTrace();}
	

	
	
		if(senderAccount!=0 && receiverAccount!=0 && availableBalance>=transferAmount && transferAmount>0 && senderAccount!=receiverAccount)
		{
			int insert=TransferDAO.transferAmount(senderAccountNumber, receiverAccountNumber, transferAmount);
			int transactionInsert=TransferDAO.updateTransaction(transferAmount,senderAccountNumber,receiverAccountNumber);
			if(insert==2 && transactionInsert>0)
			{
				RequestDispatcher rd = req.getRequestDispatcher("transactionSuccess.html");
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
