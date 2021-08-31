<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="il.ac.hit.model.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
    
</head>
<body>
	<!-- In this page the user can register to the system if he does not have an username -->
	
	<h1>Register Page</h1>
    <form action="http://localhost:8888/CostManagerPro/router/user/register" method="get" >
        User Name: <input type="text" name=username />
        <br />
        Password: <input type="password" name=password />
        <br />
        <input type="submit" value="Sign Up" />
        <br/>
        <h3>You already have account? <a href="http://localhost:8888/CostManagerPro/router/user/login">Login</a></h3>
    </form>
            
     <%
 	//check if there is invalid input and if yes show message to the user 
     if(request.getAttribute("errorregister")!= null){
     	out.println("<h4 align='center'>" +"invalid username or password" + "</h4>");
     }else{
    	 	//get the register attribute from the request and check if it is not null
    		Login login = (Login) request.getAttribute("register");
    	 	if(login != null){ %>
    	 		<h4>The user <%= login.getUsername() %> added Successfully</h4>
    	 		
    	 	<% } %>
    	 
     <% } %>
 

</body>
</html>
