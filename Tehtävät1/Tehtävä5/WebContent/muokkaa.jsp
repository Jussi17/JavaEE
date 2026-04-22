<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="papu" class="luokat.AsiakasBean" scope="session"/>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Muokkaa asiakasta</title>
    </head>
    <body>
        <h1>Muokkaa asiakkaan tietoja</h1>
        <form action="MuokkausServlet" method="post">
            <input type="hidden" name="id" value="<jsp:getProperty name='papu' property='id'/>"/>
            <label>Nimi:</label>
            <input type="text" name="nimi" value="<jsp:getProperty name='papu' property='nimi'/>"/><br><br>
            <label>Osoite:</label>
            <input type="text" name="osoite" value="<jsp:getProperty name='papu' property='osoite'/>"/><br><br>
            <label>Puhelin:</label>
            <input type="text" name="puhelin" value="<jsp:getProperty name='papu' property='puhelin'/>"/><br><br>
            <label>Email:</label>
            <input type="text" name="email" value="<jsp:getProperty name='papu' property='email'/>"/><br><br>
            <label>Salasana:</label>
            <input type="text" name="salasana" value="<jsp:getProperty name='papu' property='salasana'/>"/><br><br>
            <input type="submit" value="Tallenna muutokset">
        </form>
        <a href="lomake.html">Palaa hakulomakkeeseen</a>
    </body>
</html>