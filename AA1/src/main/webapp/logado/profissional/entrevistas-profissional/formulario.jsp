<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="message">
<head>
    <title><fmt:message key="user.professional" /></title>
</head>
<body>
    <div align="center">
        <h1><fmt:message key="gerenciamento.entrevista" /></h1>
        <h2>
            <a href="lista"><fmt:message key="lista.entrevista" /></a>
        </h2>
    </div>

    <div align="center">
        <c:choose>
            <c:when test="${entrevista != null}">
                <form action="atualizacao" method="post">
                    <%@include file="campos-edicao.jsp"%>
                </form>
            </c:when>
            <c:otherwise>
                <form action="insercao" method="post">
                    <%@include file="campos-insercao.jsp"%>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</fmt:bundle>