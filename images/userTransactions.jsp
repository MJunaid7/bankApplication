<%@page import="com.sbi.transactions.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        body{
            background-color: #00b5ef;
        }
        h1{
            font-family: sans-serif;
        }
        table {
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
            font-family: sans-serif;
            color: white;
        }

        table th {
            font-weight: 800;
        }

        table td,
        th {
            background-color: #292075;
            border: 2px solid white;
            padding: 20px;
            border-radius: 3px;
            width: 20vw;
            text-align: center;
        }
    </style>
</head>
<body>
 <h1>User Transactions</h1><br>
 <table>
 
 <tr>
 <th>Transaction Id</th>
 <th>Transaction Time</th>
 <th>Transaction Amount</th>
 <th>Debit_Credit</th>
 </tr>
 <%@ page import="java.io.*" %>
 <%@ page import="java.sql.*" %>
 <%@ page import="jakarta.servlet.*" %>
 <%@ page import="com.sbi.registration.RegBean" %>
 <%@ page import="jakarta.servlet.http.*" %>
 
 <%
 String from = request.getParameter("fromDate");
 String to= request.getParameter("toDate");
int accountNumber= Integer.parseInt(request.getParameter("accountNumber"));

if(RegBean.getRegBean().getAccountNumber()==accountNumber)
{
 
 ResultSet rs = UserTransactionDAO.getTransactions(accountNumber, from, to);
 
 while(rs.next())
 {%>
	 
	 <tr>
	 <td> <%out.println(rs.getInt(1));%> </td>
	 <td><%out.println(rs.getString(2));%></td>
	 <td><%out.println(rs.getInt(3));%></td>
	 <td> <%out.println(rs.getString(4));%></td>
	 </tr>
<% 
}
}
else
{%>
	<%@ include file="transactionFail.html" %>
<%
}
 %>
 

 
 </table>
</body>
</html>