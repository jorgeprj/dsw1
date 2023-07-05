<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gerenciamento de Profissionais</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Profissionais</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/admin">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/crud/cadastro">Cadastrar novo profissinal</a> &nbsp;&nbsp;&nbsp;
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
            </tr>
            <c:forEach items="${requestScope.listaProfissionais}" var="profissinal" varStatus="status">
                <c:set var="nome" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="email" value="${requestScope.listaEmails[status.index]}" />
                <c:set var="senha" value="${requestScope.listaSenhas[status.index]}" />
                <c:set var="cpf" value="${requestScope.listaCpfs[status.index]}" />
                <c:set var="telefone" value="${requestScope.listaTelefones[status.index]}" />
                <c:set var="sexo" value="${requestScope.listaSexos[status.index]}" />
                <c:set var="dataNascimento" value="${requestScope.listaData[status.index]}" />

                <tr>
                    <td>${nome}</td>
                    <td>${email}</td>
                    <td>${senha}</td>
                    <td>${cpf}</td>
                    <td>${telefone}</td>
                    <td>${sexo}</td>
                    <td>${dataNascimento}</td>
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