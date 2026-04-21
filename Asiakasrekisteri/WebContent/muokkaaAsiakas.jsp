<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="papu" class="luokat.AsiakasBean" scope="session"/>
<html>
<head>
<title>Muokkaa asiakasta</title>
<link rel="stylesheet" href="css/tyyli.css" />
</head>
<%@ include file="valikko.jsp"%>
<body>
    <h3>Muokkaa asiakasta</h3>
    <form method="post" action="ServletTallennaAsiakas">
        <input type="hidden" name="id" value="<jsp:getProperty name='papu' property='id'/>"/>
        <table>
            <tr><td>Nimi:</td>
                <td><input type="text" name="nimi" value="<jsp:getProperty name='papu' property='nimi'/>"/></td></tr>
            <tr><td>Osoite:</td>
                <td><input type="text" name="osoite" value="<jsp:getProperty name='papu' property='osoite'/>"/></td></tr>
            <tr><td>Puhelin:</td>
                <td><input type="text" name="puhelin" value="<jsp:getProperty name='papu' property='puhelin'/>"/></td></tr>
            <tr><td>Email:</td>
                <td><input type="text" name="email" value="<jsp:getProperty name='papu' property='email'/>"/></td></tr>
            <tr><td>Salasana:</td>
                <td><input type="text" name="salasana" value="<jsp:getProperty name='papu' property='salasana'/>"/></td></tr>
            <tr><td><input type="submit" value="Tallenna muutokset"></td></tr>
        </table>
    </form>
</body>
</html>