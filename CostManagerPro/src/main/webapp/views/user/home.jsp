<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="il.ac.hit.model.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
    
</head>
<body>
	<!-- This is the page that the user see when he login successfully -->

	<p style="margin-left: 30px"><a href="http://localhost:8888/CostManagerPro/router/user/logout">Logout</a> </p>
	<h1 style="text-align : center">Home Page</h1>
    <%
    //check if the match attribute that represent the current login user is not empty 
    if(session.getAttribute("match") == null || session.getAttribute("match")== ""){
    	response.sendRedirect("http://localhost:8888/CostManagerPro/router/user/login");
    }
    //get the current login user into Login object
 	Login login = (Login) session.getAttribute("match"); 
 	%>
 	<!-- Main Menu -->
 	<h4 style="color:#5f939a; text-align : center">Welcome <%= login.getUsername() %> !</h4> 
 	<h4 style="color:#5f939a; text-align : center">You Have Successfully Logged in to the Cost Manager App</h4>
 	<p style="text-align : center"><a href="http://localhost:8888/CostManagerPro/router/user/products">Show Cost Items</a></p>	
 	<p style="text-align : center"><a href="http://localhost:8888/CostManagerPro/router/user/getproduct">Get Cost Item</a></p>
	<p style="text-align : center"><a href="http://localhost:8888/CostManagerPro/router/user/addproduct">Add Cost Item</a></p>
	<p style="text-align : center"><a href="http://localhost:8888/CostManagerPro/router/user/deleteproduct">Delete Cost Item</a></p>
	<p style="text-align : center"><a href="http://localhost:8888/CostManagerPro/router/user/report">Generate Report</a></p>
</body>
</html>
