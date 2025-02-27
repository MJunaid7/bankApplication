package com.sbi.registration;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
RegBean userDetails=RegBean.getRegBean();
ServletContext sc= req.getServletContext();

userDetails.setAccountNumber(Integer.parseInt(req.getParameter("accountNumber")));
userDetails.setPassword(req.getParameter("password"));
userDetails.setBranchName(req.getParameter("branchNames"));
userDetails.setFirstName(req.getParameter("firstName"));
userDetails.setLastName(req.getParameter("lastName"));
userDetails.setAge(Integer.parseInt(req.getParameter("age")));
userDetails.setGender(req.getParameter("gender"));
userDetails.setAccountType(req.getParameter("accountType"));
sc.setAttribute("accountNum",userDetails.getAccountNumber());
int insert =RegistrationDAO.registerNewUser(userDetails);
if(insert>0)
{
	RequestDispatcher rd= req.getRequestDispatcher("login.html");
	rd.forward(req, resp);
}
else
{
	PrintWriter pw = resp.getWriter();
	pw.print("RegistrationFail");
	RequestDispatcher rd= req.getRequestDispatcher("register.html");
	rd.include(req, resp);
}
}
}
