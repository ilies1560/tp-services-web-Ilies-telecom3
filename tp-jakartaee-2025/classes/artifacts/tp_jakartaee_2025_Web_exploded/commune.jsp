<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Commune" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Commune</title></head>
<body>
<%
    String bad = (String) request.getAttribute("badinput");
    Commune c = (Commune) request.getAttribute("commune");
%>

<% if (bad != null) { %>
<p><b>Erreur:</b> <%= bad %></p>
<% } %>

<% if (c != null) { %>
<p>Code Postal: <%= c.getCodePostal() %></p>
<p>Nom: <%= c.getName() %></p>
<% } %>

</body>
</html>
