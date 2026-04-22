<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="noppa" scope="session" class="luokat.Noppa" />

<c:if test="${param.heita != null}">
    <c:set var="heita" value="true" />
</c:if>

<%
    if (request.getParameter("heita") != null) {
        noppa.heita();
    } else if (request.getParameter("nollaa") != null) {
        noppa.nollaa();
    }
%>

<!DOCTYPE HTML>
<html>
    <head><title>Noppa.jsp</title></head>
    <body>
        <c:choose>
            <c:when test="${param.heita != null}">
                <p>Silmäluvuksi tuli: <c:out value="${noppa.tulos}"/></p>
            </c:when>
            <c:otherwise>
                <p>Noppaa ei ole heitetty</p>
            </c:otherwise>
        </c:choose>

        <p>Heittojen summa on nyt: <c:out value="${noppa.summa}"/></p>
        <p>Heittojen lukumäärä: <c:out value="${noppa.lukumaara}"/></p>
        <p>Heittojen keskiarvo: <fmt:formatNumber value="${noppa.keskiarvo}" maxFractionDigits="2"/></p>

        <a href="Noppa.jsp?heita=ok">Heitä noppaa</a><br>
        <a href="Noppa.jsp?nollaa=ok">Nollaa</a><br>
    </body>
</html>