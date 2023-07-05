<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gerenciamento de Empresas</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Empresas</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/crud/cadastro">Cadastrar nova empresa</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Empresas</caption>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
                <th>CNPJ</th>
                <th>Cidade</th>
                <th>Ações</th>
            </tr>
            <c:forEach items="${requestScope.listaProfissionais}" var="profissinal" varStatus="status">
                <c:set var="nome" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="email" value="${requestScope.listaEmails[status.index]}" />
                <c:set var="senha" value="${requestScope.listaSenhas[status.index]}" />
                <c:set var="cnpj" value="${requestScope.listaCnpjs[status.index]}" />
                <c:set var="cidade" value="${requestScope.listaCidades[status.index]}" />

                <tr>
                    <td>${nome}</td>
                    <td>${email}</td>
                    <td>${senha}</td>
                    <td>${cnpj}</td>
                    <td>${cidade}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/crud/edicao?id=${profissional.id}">
                            Edição
                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/crud/remocao?id=${profissinal.id}" onclick="return confirm('Tem certeza disso?');">
                            Remoção
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>