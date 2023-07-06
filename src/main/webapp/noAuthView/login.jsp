<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="vagas.emprego" /></title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <h1><fmt:message key="login.page" />:</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li>${erro}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="index.jsp">
            <table>
                <tr>
                    <th><fmt:message key="user.login" />:</th>
                    <td><input type="text" name="email" value="${param.email}"/></td>
                </tr>
                <tr>
                    <th><fmt:message key="user.password" />:</th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="<fmt:message key="user.login"/>">
                    </td>
                </tr>
            </table>
        </form>

        <div align="center">
            <h1><fmt:message key="lista.empresa" /></h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="user.nome" /></th>
                <th><fmt:message key="user.email" /></th>
                <th><fmt:message key="empresa.cidade" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="empresa" items="${listaEmpresas}">
                <tr>
                    <td>${empresa.id}</td>
                    <td>${empresa.nome}</td>
                    <td>${empresa.email}</td>
                    <td>${empresa.cidade}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
        </div>
        <form method="get" action="noAuth/lista-empresas.jsp">
            <input type="submit" name="<fmt:message key="listar">" value="">
        </form>

    </body>
</fmt:bundle>
</html>
