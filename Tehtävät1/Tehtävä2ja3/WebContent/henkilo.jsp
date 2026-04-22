<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="henkilo" class="beans.HenkiloBean" scope="application"/>
<jsp:setProperty name="henkilo" property="*"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Henkilötiedot</title>
</head>
<body>
<% out.println("Session id: " + session.getId()); %>
<% out.println("Nimi: " + henkilo.getNimi()); %>
    <h1>Henkilötiedot</h1>
    <form action="henkilo.jsp" method="post">
        <label>Nimi:</label>
        <input type="text" name="nimi"><br><br>
        <label>Osoite:</label>
        <input type="text" name="osoite"><br><br>
        <label>Kotikunta:</label>
        <input type="text" name="kotikunta"><br><br>
        <label>Email:</label>
        <input type="text" name="email"><br><br>
        <input type="submit" value="Tallenna">
    </form>

        <% if(henkilo.getNimi() != null && !henkilo.getNimi().isEmpty()) { %>
        <h2>Tallennetut tiedot:</h2>
        <p>Nimi: <jsp:getProperty name="henkilo" property="nimi"/></p>
        <p>Osoite: <jsp:getProperty name="henkilo" property="osoite"/></p>
        <p>Kotikunta: <jsp:getProperty name="henkilo" property="kotikunta"/></p>
        <p>Email: <jsp:getProperty name="henkilo" property="email"/></p>
    <% } %>
    <a href="henkilomuokkaus.jsp">Muokkaa tietoja</a>
</body>
</html>