<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Empresa</title>
</head>
<body>
    <div align="center">
        <h1>Lista de Entrevistas</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/empresa">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <tr>
                <th>Profissional</th>
                <th>Data</th>
                <th>Ações</th>
            </tr>
            <c:forEach items="${requestScope.listaEntrevistas}" var="entrevista" varStatus="status">
                <c:set var="profissional" value="${requestScope.listaNomes[status.index]}" />
                <c:set var="dataFormatada" value="${requestScope.listaData[status.index]}" />
                <c:set var="horaFormatada" value="${requestScope.listaHora[status.index]}" />
                <tr>
                    <td>${profissional}</td>
                    <td>${dataFormatada} às ${horaFormatada}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/entrevistas-empresa/remocao?id=${entrevista.id}" onclick="return confirm('Tem certeza disso?');">
                            Cancelar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>