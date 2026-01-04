<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Commune" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Session</title></head>
<body>
<%
    String prenom = (String) session.getAttribute("prenom");
    String nom = (String) session.getAttribute("nom");
    Integer count = (Integer) session.getAttribute("count");
    Commune commune = (Commune) session.getAttribute("commune");
    String badinput = (String) session.getAttribute("badinput");
    List<String> communesFile = (List<String>) request.getAttribute("communesFile");
%>

<% if (prenom != null && nom != null) { %>
<h1>Vous êtes <%= prenom %> <%= nom %></h1>
<p>Requete Numero : <%= (count == null ? 0 : count) %></p>
<% if (commune != null) { %>
<p>Dernière Commune saisie : <%= commune.getCodePostal() %> <%= commune.getName() %></p>
<% } %>
<% } %>

<form method="post" action="commune-session">
    <p><b>Saisissez une commune :</b> <%= badinput == null ? "" : badinput %></p>
    <p>Code Postal <input type="text" name="codePostal"></p>
    <p>Nom Commune <input type="text" name="name"></p>
    <input type="submit" value="Envoyer">
    <button type="submit" name="action" value="list">Voir toutes les communes</button>
</form>

<% if (communesFile != null) { %>
<h2>commune.txt</h2>
<pre>
<% for (String line : communesFile) { %><%= line %>
<% } %>
</pre>
<% } %>
</body>
</html>
