<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="il.ac.hit.model.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<style>

form, h1,h4 {
	margin-left: 30px;
}

h1 {
	color: #5f939a;
}

h4 {
	color: red;
}

table, td, th {
	border: 1px solid #ddd;
}

 th, td {
	padding: 10px;
}
 
th {
	background-color: #7eca9c;
	color: black;
}
table.center {
	margin-left: auto;
	margin-right: auto;
}

</style>
<title>Login</title>
    
</head>
<body>
	<!-- Login Page - This is the first page that the user sees -->
	
	<h1>Login Page</h1>
    <form action="http://localhost:8888/CostManagerPro/router/user/login" method="get" >
        User Name: <input type="text" name=username />
        <br />
        Password: <input type="password" name=password />
        <br />
        <input type="submit" value="Login" />
        <br />
        <h3>You don't have account? <a href="http://localhost:8888/CostManagerPro/router/user/register">Register</a></h3>
    </form>
            
     <%
 	//check if there is invalid input and if yes show message to the user 
    if(request.getAttribute("errorlogin")!= null){
    	out.println("<h4 align='center'>" +"invalid username or password" + "</h4>");
    }else{
    //get refference of the RequestDispatcher object
    RequestDispatcher dispatcher = null;
    //get the match attribute that indicates that the user successfully login
 	Login success = (Login) session.getAttribute("match");
    //get the no-match attribute that indicates that the user failed to login
 	Login fail = (Login) session.getAttribute("no-match");
    //check if there is a match
 	if(success != null){
 		//if there is a match forward the user to the home page
 		dispatcher = request.getRequestDispatcher("/views/user/home.jsp");
		dispatcher.forward(request, response);
 	
	}%>
 	<% 
    //check if there is a failure and if yes show a compatible message 
 	if(fail != null){ %>
 	<h4>Wrong username or password, please try again</h4><br>
 	
 	<% }%>
 	<% } %>

</body>
</html>
