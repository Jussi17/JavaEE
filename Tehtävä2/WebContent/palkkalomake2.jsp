<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Palkkalomake</title>
</head>
<body>
    <h1>Palkkalomake</h1>
    <form action="PalkkaServlet2" method="post">
        <label>Tehdyt tunnit:</label>
        <input type="text" name="tunnit"><br><br>
        <label>Tuntipalkka:</label>
        <input type="text" name="tuntipalkka"><br><br>
        <label>Veroprosentti:</label>
        <input type="text" name="veroprosentti"><br><br>
        <input type="submit" value="Laske palkka">
    </form>
    
    <!-- Tulokset näytetään tässä -->
    <% if(request.getAttribute("bruttopalkka") != null) { %>
        <h2>Tulokset:</h2>
        <p>Bruttopalkka: <%= request.getAttribute("bruttopalkka") %> €</p>
        <p>Nettopalkka: <%= request.getAttribute("nettopalkka") %> €</p>
    <% } %>
</body>
</html>