<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gerenciamento de Profissionais</title>
</head>
<body>
    <div align="center">
        <h1>Gerenciamento de Entrevistas</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/profissional">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/entrevistas-profissional/cadastro">Agendar Nova Entrevista</a> &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Entrevistas</caption>
            <tr>
                <th>Empresa</th>
                <th>Data</th>
                <th>Ações</th>
            </tr>
            <c:forEach items="${requestScope.listaEntrevistas}" var="entrevista" varStatus="status">
                <c:set var="empresa" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                <tr>
                    <td>${empresa}</td>
                    <td>${dataFormatada} às ${horaFormatada}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/entrevistas-profissional/edicao?id=${entrevista.id}">
                            Edição
                        </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/entrevistas-profissional/remocao?id=${entrevista.id}" onclick="return confirm('Tem certeza disso?');">
                            Remoção
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>