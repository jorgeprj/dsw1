<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="message">
<head>
    <title><fmt:message key="gerenciamento.profissionail" /></title>
</head>
<body>
    <div align="center">
        <h1><fmt:message key="gerenciamento.entrevista" /></h1>
        <h2>
            <a href="${pageContext.request.contextPath}/profissional"><fmt:message key="item.menu" /></a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/entrevistas-profissional/cadastro">Agendar Nova Entrevista</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption><fmt:message key="lista.entrevista" /></caption>
            <tr>
                <th><fmt:message key="user.empresa" /></th>
                <th><fmt:message key="profissional.data" /></th>
                <th><fmt:message key="admin.acoes" /></th>
            </tr>
            <c:forEach items="${requestScope.listaEntrevistas}" var="entrevista" varStatus="status">
                <c:set var="empresa" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                <tr>
                    <td>${empresa}</td>
                    <td>${dataFormatada} <fmt:message key="as" /> ${horaFormatada}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/entrevistas-profissional/edicao?id=${entrevista.id}">
                            <fmt:message key="user.edicao" />
                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/entrevistas-profissional/remocao?id=${entrevista.id}" onclick="return confirm('Tem certeza disso?');">
                            <fmt:message key="user.remove" />
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</fmt:bundle>