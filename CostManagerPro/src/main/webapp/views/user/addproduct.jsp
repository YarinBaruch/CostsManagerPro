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
<title>Add Cost Item</title>

</head>
<body>
	<!-- In this page the user can add a new cost item -->
	<h1>Add New Cost Item</h1>
	
	<form
		action="http://localhost:8888/CostManagerPro/router/user/addproduct"
		method="get">
		Name: <input type="text" name="name" placeholder="Enter Name" />

		Price: <input type="text" name="price" placeholder="Enter price" />

		Description: <input type="text" name="description" placeholder="Write Description" /> 
		
		ID: <input type="text" name="ID" placeholder="Enter product ID" /> 
		
		<label for="category">Choose a Category:</label> 
		<select name="category">
			<option value="">--Please choose an option--</option>
			<option value="bills">Bills</option>
			<option value="education">Education</option>
			<option value="clothes">Clothes</option>
			<option value="house">House</option>
			<option value="health">Health</option>
			<option value="electronics">Electronics</option>
			<option value="food">Food</option>
			<option value="travel">Travel</option>
		</select> 
		
		Date: <input type="date" name="date" placeholder="select the cost date">
		
		<input type="submit" value="Add Cost Item" />
	</form>
	<p style="margin-left: 30px"><a href="http://localhost:8888/CostManagerPro/views/user/home.jsp">Home Page</a></p>	
	<%
	//check if there is invalid input and if yes show message to the user 
	if(request.getAttribute("erroradd")!= null){
    	out.println("<h4 align='center'>" +"invalid input.. try again" + "</h4>");
    }else{
    	
	
	//getting the cost item that added to the DB
	CostItem item = (CostItem) request.getAttribute("product");
	if (item != null) {
	%>

	<h4 style="margin-left: 30px; color:#5f939a">
		Cost Item
		<%=item.getName()%>
		Added Successfully
	</h4>
	<%
	}
	%>
	<% } %>


</body>
</html>