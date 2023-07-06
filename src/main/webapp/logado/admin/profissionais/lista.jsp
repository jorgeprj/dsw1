<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title><fmt:message key="user.profissional" /></title>
</head>
<body>
    <div align="center">
        <h1><fmt:message key="gerenciamento.profissionais" /></h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin"><fmt:message key="item.menu" /></a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/profissionais/cadastro">Cadastrar novo profissional</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption><fmt:message key="lista.profissional" /></caption>
            <tr>
                <th><fmt:message key="user.nome" /></th>
                <th><fmt:message key="user.email" /></th>
                <th><fmt:message key="user.password" /></th>
                <th><fmt:message key="profissional.cpf" /></th>
                <th><fmt:message key="profissional.telefone" /></th>
                <th><fmt:message key="profissional.sexo" /></th>
                <th><fmt:message key="profissional.data" /></th>
                <th><fmt:message key="admin.acoes" /></th>

                <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                    <tr>
                        <td>${profissional.nome}</td>
                        <td>${profissional.email}</td>
                        <td>${profissional.senha}</td>
                        <td>${profissional.cpf}</td>
                        <td>${profissional.telefone}</td>
                        <td>${profissional.sexo}</td>
                        <td>${profissional.dataNascimento}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/profissionais/edicao?id=${profissional.id}"><fmt:message key="user.edicao" /></a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/profissionais/remocao?id=${profissional.id}" onclick="return confirm(getMessage());">
                                <fmt:message key="user.remove" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</body>
</html>