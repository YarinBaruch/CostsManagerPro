<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="il.ac.hit.model.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>

</head>
<body>
	<!-- In this page the user forward to the login page via the router after he click on logout -->
	
	<h1>Logout Page</h1>

	<%
	//forward the user to the login page via the router after the session was invalidate in the controller
	response.sendRedirect("http://localhost:8888/CostManagerPro/router/user/login");
	%>

</body>
</html>
