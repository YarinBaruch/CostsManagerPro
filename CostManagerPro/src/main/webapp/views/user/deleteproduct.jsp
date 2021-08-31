<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="il.ac.hit.model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Cost Item</title>

</head>
<body>
	<!-- In this page the user can delete a cost item by id -->
	<h1>Delete Cost Item</h1>
	
	<form action="http://localhost:8888/CostManagerPro/router/user/deleteproduct"
		method="get">
		ID: <input type="text" name="ID" placeholder="Enter cost item ID to delete" /> 
		<input type="submit" value="Delete Cost Item"/>
	</form>
	<p style="margin-left: 30px"><a href="http://localhost:8888/CostManagerPro/views/user/home.jsp">Home Page</a></p>	
	
	<%
	//check if there is invalid input and if yes show message to the user 
	if(request.getAttribute("errordel")!= null){
    	out.println("<h4 align='center'>" +"invalid id.. try again" + "</h4>");
    }else{
    	//getting the cost item that the user choose to delete 
    	CostItem item = (CostItem) request.getAttribute("delete");
    	//if the attribute exists show the user message that the cost item was delete 
    	if(item != null){
    		out.println("<p align='center'>"+ "Cost Item" + " " + item.getName() + " Deleted Successfully" + "</p>");
    	}
    }
	
	%>

</body>
</html>