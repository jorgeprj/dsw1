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
            <a href="${pageContext.request.contextPath}/empresas/cadastro">Cadastrar nova empresa</a> &nbsp;&nbsp;&nbsp;
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
            <c:forEach var="empresa" items="${requestScope.listaEmpresas}">
                    <tr>
                        <td>${empresa.nome}</td>
                        <td>${empresa.email}</td>
                        <td>${empresa.senha}</td>
                        <td>${empresa.cnpj}</td>
                        <td>${empresa.cidade}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/empresas/edicao?id=${empresa.id}">Edição</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/empresas/remocao?id=${empresa.id}" onclick="return confirm(getMessage());">
                                Remoção
                            </a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</body>
</html>