<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="henkilo" class="beans.HenkiloBean" scope="application"/>
<jsp:setProperty name="henkilo" property="*"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Muokkaa henkilötietoja</title>
</head>
<body>
<% out.println("Session id: " + session.getId()); %>
<% out.println("Nimi: " + henkilo.getNimi()); %>
    <h1>Muokkaa henkilötietoja</h1>
    <form action="henkilomuokkaus.jsp" method="post">
        <label>Nimi:</label>
        <input type="text" name="nimi" value="<jsp:getProperty name='henkilo' property='nimi'/>"><br><br>
        <label>Osoite:</label>
        <input type="text" name="osoite" value="<jsp:getProperty name='henkilo' property='osoite'/>"><br><br>
        <label>Kotikunta:</label>
        <input type="text" name="kotikunta" value="<jsp:getProperty name='henkilo' property='kotikunta'/>"><br><br>
        <label>Email:</label>
        <input type="text" name="email" value="<jsp:getProperty name='henkilo' property='email'/>"><br><br>
        <input type="submit" value="Tallenna muutokset">
    </form>
    <% if(henkilo.getNimi() != null && !henkilo.getNimi().isEmpty()) { %>
        <h2>Tallennetut tiedot:</h2>
        <p>Nimi: <jsp:getProperty name="henkilo" property="nimi"/></p>
        <p>Osoite: <jsp:getProperty name="henkilo" property="osoite"/></p>
        <p>Kotikunta: <jsp:getProperty name="henkilo" property="kotikunta"/></p>
        <p>Email: <jsp:getProperty name="henkilo" property="email"/></p>
    <% } %>
    <a href="henkilo.jsp">Lisää henkilö</a>
</body>
</html>