<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<fmt:bundle basename="message">>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="lista.empresa" /></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
        <h1><fmt:message key="lista.empresa" /></h1>
        <div align="center">
            <table border="1">
                <caption><fmt:message key="lista.empresa" /></caption>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="user.nome" /></th>
                    <th><fmt:message key="empresa.cnpj" /></th>
                    <th><fmt:message key="empresa.cidade" /></th>
                </tr>
                <c:forEach var="empresa" items="${requestScope.listaEmpresasCidade}">
                    <tr>
                        <td>${empresa.nome}</td>
                        <td>${empresa.email}</td>
                        <td>${empresa.cnpj}</td>
                        <td>${empresa.cidade}</td>
                    </tr>
                </c:forEach>
            </table>
            
            <div>
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="voltar"></a>
            </div>    
        </div>
</body>
</fmt:bundle>