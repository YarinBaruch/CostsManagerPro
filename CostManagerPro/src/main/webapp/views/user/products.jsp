<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="il.ac.hit.model.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cost Items Table</title>
</head>
<body>
	<!-- In this page the user see all of is cost items -->

	<h1 style="text-align: center">Cost Items Table</h1>
	<table class="center">

		<%
			//get all the cost items 
			List <CostItem> products = (List <CostItem>) request.getAttribute("data");
		%>
		
		<% //check if the list is not null or empty and if all is ok show the table
		if(products != null || products.size() > 0){%>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Category</th>
			<th>Date</th>
			
		</tr>

		<%
		for (CostItem p : products) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getDescription()%></td>
			<td><%=p.getPrice()%></td>
			<td><%=p.getCategory()%></td>
			<td><%=p.getDate()%></td>
		</tr>
		<%
		}
		%>
	<% } else {
		out.println("No Data");
	}%>

	</table>
	<% // this var use for display the total costs
		double sum = (double) request.getAttribute("total"); 
	%>
	<h4 style="color:blue; text-align: center">Total Sum: <%= sum %></h4>
	
		
	<p style="margin-left: 30px"><a href="http://localhost:8888/CostManagerPro/views/user/home.jsp">Home Page</a></p>	
	
</body>
</html>