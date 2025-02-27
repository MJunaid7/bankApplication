package com.sbi.login;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/passwordChange")
public class PasswordChangeServlet extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int accountNumber=Integer.parseInt(req.getParameter("accountNumber"));
	String firstName=req.getParameter("firstName");
	String lastName= req.getParameter("lastName");
	String newPassword=req.getParameter("password");
	
		int changed=PasswordChangeDAO.changePassword(accountNumber, firstName, lastName, newPassword);
		if(changed>0)
		{
			PrintWriter pw = resp.getWriter();
			pw.print("Password Changed Successfully");
			RequestDispatcher rd= req.getRequestDispatcher("login.html");
			rd.include(req, resp);
		}
		else
		{
			RequestDispatcher rd= req.getRequestDispatcher("transactionFail.html");
			rd.forward(req, resp);
		}
}
}
