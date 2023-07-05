<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:bundle basename="message">
        <title>Gerenciar Profissionais</title>
    </fmt:bundle>
</head>
<body>
    <fmt:bundle basename="message">
        <div align="center">
            <h1>Gerenciar Profissionais</h1>
            <h2>
                <a href="lista">Profissionais</a>
            </h2>
        </div>
    
        <div align="center">
            <c:choose>
                <c:when test="${profissinal != null}">
                    <form action="atualizacao" method="post">
                        <%@include file="campos.jsp"%>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="insercao" method="post">
                        <%@include file="campos.jsp"%>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    
        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagens}" var="mensagem">
                    <li>${mensagem}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fmt:bundle>
</body>
</html>