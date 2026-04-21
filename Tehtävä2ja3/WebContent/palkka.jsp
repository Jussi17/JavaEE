<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Palkan laskenta</title>
</head>
<body>
    <h1>Palkan laskenta</h1>
    <%
        double tunnit = Double.parseDouble(request.getParameter("tunnit"));
        double tuntipalkka = Double.parseDouble(request.getParameter("tuntipalkka"));
        double veroprosentti = Double.parseDouble(request.getParameter("veroprosentti"));
        
        double bruttopalkka = tunnit * tuntipalkka;
        double nettopalkka = bruttopalkka * (1 - veroprosentti / 100);
    %>
    <p>Bruttopalkka: <%= bruttopalkka %> €</p>
    <p>Nettopalkka: <%= nettopalkka %> €</p>
</body>
</html>