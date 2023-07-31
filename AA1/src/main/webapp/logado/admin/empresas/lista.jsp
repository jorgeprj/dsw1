<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:bundle basename="message">
<head>
    <title><fmt:message key="gerenciamento.empresa" /></title>
</head>
<body>
    <div align="center">
        <h1><fmt:message key="gerenciamento.empresa" /></h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin"><fmt:message key="item.menu" /></a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/empresas/cadastro"><fmt:message key="cadastrar.empresa" /></a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption><fmt:message key="lista.empresa" /></caption>
            <tr>
                <th><fmt:message key="user.nome" /></th>
                <th><fmt:message key="user.email" /></th>
                <th><fmt:message key="user.password" /></th>
                <th><fmt:message key="empresa.cnpj" /></th>
                <th><fmt:message key="empresa.cidade" /></th>
                <th><fmt:message key="admin.acoes" /></th>
            </tr>
            <c:forEach var="empresa" items="${requestScope.listaEmpresas}">
                    <tr>
                        <td>${empresa.nome}</td>
                        <td>${empresa.email}</td>
                        <td>${empresa.senha}</td>
                        <td>${empresa.cnpj}</td>
                        <td>${empresa.cidade}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/empresas/edicao?id=${empresa.id}"><fmt:message key="user.edicao" /></a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/empresas/remocao?id=${empresa.id}" onclick="return confirm(getMessage());">
                                <fmt:message key="user.remove" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</body>
</fmt:bundle>