package com.sbi.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import com.sbi.registration.RegBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 PrintWriter pw = resp.getWriter();
	int accountNumber=Integer.parseInt(req.getParameter("accountNumber"));
	String password=req.getParameter("password");
	RegBean.getRegBean().setAccountNumber(accountNumber);
	
	try {
	ResultSet rs = LoginDAO.isUserExist(accountNumber, password);
	if(rs.next())
	{
	  RequestDispatcher rd= req.getRequestDispatcher("browsing.html");
	  rd.forward(req, resp);
	}
	else
	{
		pw.print("Login Fail");
		RequestDispatcher rd= req.getRequestDispatcher("login.html");
		rd.include(req, resp);
	}
	}catch(Exception e) {e.printStackTrace();}
}
}
