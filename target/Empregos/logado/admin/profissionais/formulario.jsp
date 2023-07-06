<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="message">
<head>
    <fmt:bundle basename="message">
        <title><fmt:message key="user.profissional" /></title>
    </fmt:bundle>
</head>
<body>
    <fmt:bundle basename="message">
        <div align="center">
            <h1><fmt:message key="gerenciamento.profissional" /></h1>
            <h2>
                <a href="lista"><fmt:message key="user.profissional" /></a>
            </h2>
        </div>
    
        <div align="center">
            <c:choose>
                <c:when test="${profissional != null}">
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
    </fmt:bundle>
</body>
</fmt:bundle>