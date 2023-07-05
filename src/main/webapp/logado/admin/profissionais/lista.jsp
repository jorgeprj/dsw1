<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Profissionais</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Profissionais</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/profissionais/cadastro">Cadastrar novo profissional</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Profissionais</caption>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
                <th>CPF</th>
                <th>Telefone</th>
                <th>Sexo</th>
                <th>Data de nascimento</th>
                <th>Ações</th>

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
                            <a href="${pageContext.request.contextPath}/profissionais/edicao?id=${profissional.id}">Edição</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/profissionais/remocao?id=${profissional.id}" onclick="return confirm(getMessage());">
                                Remoção
                            </a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</body>
</html>