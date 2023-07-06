<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vagas de Emprego</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <fmt:bundle basename="message">
            <h1>Lista de Empresas</h1>
            <div align="center">
                <table border="1">
                    <caption>Lista das empresas</caption>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Cidade</th>
                    </tr>
                    <c:forEach var="empresa" items="${requestScope.listaEmpresas}">
                        <tr>
                            <td>${empresa.nome}</td>
                            <td>${empresa.email}</td>
                            <td>${empresa.cidade}</td>
                        </tr>
                    </c:forEach>
                </table>
                
                <div>
                    <a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="Voltar"/></a>
                </div>    
            </div>    
        </fmt:bundle>
    </body>
</fmt:bundle>
</html>