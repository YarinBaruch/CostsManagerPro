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

<title>Report</title>
</head>
<body>
	<!-- In this page the user can choose period of time and get a report of all the cost items between this range of dates -->
	<h1>Report</h1>
	<form
		action="http://localhost:8888/CostManagerPro/router/user/report" method="get">		
		Start Date: <input type="date" name="startdate" placeholder="start date">
		End Date: <input type="date" name="enddate" placeholder="end date">
		
		<input type="submit" value="Generate Report" />
	</form>
	<table class="center">

		<%	
			//check if there is invalid input and if yes show message to the user 
			if(request.getAttribute("errorreport")!= null){
	    		out.println("<h4 align='center'>" +"invalid input.. try again" + "</h4>");
	    	}
			//get the report attribute andd check if is not null 
			List <CostItem> report = (List <CostItem>)request.getAttribute ("report");
		
		    if(report != null) { %>
			
			<% if(report.size() == 0){ %>
			<h4>No results for this dates</h4>
				
			<% } else {%>
			
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Category</th>
			<th>Date</th>
			
		</tr>

		<%
		// if the report is not null iterate over the report and display it as table
		for (CostItem p : report) {
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
		// this var use for display the total costs
		double sum = (double) request.getAttribute("reportsum");
		%>
		<h4 style="color:blue; text-align: center">Total Sum: <%= sum %></h4>
		
	<% } %>
	
	<% } %>

	</table>
	<p style="margin-left: 30px"><a href="http://localhost:8888/CostManagerPro/views/user/home.jsp">Home Page</a></p>	
	
</body>
</html>