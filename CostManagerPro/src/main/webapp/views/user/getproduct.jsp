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
<title>Get Cost Item</title>

</head>
<body>
	<!-- In this page the user can find cost item by id -->
	
	<h1>Find Cost Item</h1>
	<form
		action="http://localhost:8888/CostManagerPro/router/user/getproduct"
		method="get">

		ID: <input type="text" name="ID" placeholder="Enter ID" /><br> <br>
		<input type="submit" value="Find Cost Item" />
	</form>
	<p style="margin-left: 30px"><a href="http://localhost:8888/CostManagerPro/views/user/home.jsp">Home Page</a></p>	
	

	
	<%
	//check if there is invalid input and if yes show message to the user 
	if(request.getAttribute("errorid")!= null){
    	out.println("<h4 align='center'>" +"invalid id.. try again" + "</h4>");
    }else{
    	
	//check if this is the first time that the jsp load
	if(request.getParameter("ID") != null){
    	//getting the cost item id that the user choose to display 
		CostItem item = (CostItem) request.getAttribute("idproduct");
    	//if this cost item exists display it to the user
		if (item != null) { 
			out.println("<h4 style='color:black' align='center'>" + item + "</h4>");
		//The cost item is not found 	
		}else {
			out.println("<h4 style='color:red' align='center'>" + "Cost Item Not Found !" + "</h4>");
		}
	
	}
    }
	
	%>

</body>
</html>