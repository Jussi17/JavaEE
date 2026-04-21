<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Haku</title>
<link rel="stylesheet" href="css/tyyli.css" />
</head>
<%@ include file="valikko.jsp"%>
<body>
    <h3>Hakutulokset</h3>
    <table>
        <tr>
            <th>id</th>
            <th>Nimi</th>
            <th>Osoite</th>
            <th>Puhelin</th>
            <th>Email</th>
            <th>Salasana</th>
        </tr>
        <c:forEach items="${requestScope.list}" var="item">
            <tr>
                <td><c:out value="${item.id}" /></td>
                <td><c:out value="${item.nimi}" /></td>
                <td><c:out value="${item.osoite}" /></td>
                <td><c:out value="${item.puhelin}" /></td>
                <td><c:out value="${item.email}" /></td>
                <td><c:out value="${item.salasana}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>