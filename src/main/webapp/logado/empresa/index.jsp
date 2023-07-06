<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="user.empresa" /></title>
</head>
<body>
    <h1>Olá, ${sessionScope.usuarioLogado.nome}</h1>

    <li>
        <a href="${pageContext.request.contextPath}/entrevistas-empresa/lista.jsp">Lista de Entrevistas</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="sair" /></a>
    </li>
</body>
</html>
