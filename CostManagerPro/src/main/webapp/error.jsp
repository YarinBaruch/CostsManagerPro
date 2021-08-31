<!-- Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323 
 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- This page will invoke when there is an error with the RouterServlet class -->
<h1>Error Message</h1>
<%=  request.getAttribute("errormessage").toString() %>
</body>
</html>
