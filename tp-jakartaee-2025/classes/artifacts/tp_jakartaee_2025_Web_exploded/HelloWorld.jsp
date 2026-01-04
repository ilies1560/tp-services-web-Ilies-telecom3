<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HelloWorld JSP</title>
</head>
<body>
<%
    for (int i = 0; i < 5; i++) {
%>
<p>Hello World nous sommes le : <%= new Date() %></p>
<%
    }
%>
</body>
</html>
