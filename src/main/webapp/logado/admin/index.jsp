<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin</title>
</head>
<body>
    <h1>Olá, ${sessionScope.usuarioLogado.nome}</h1>

    <li>
        <a href="${pageContext.request.contextPath}/empresas/lista.jsp"><fmt:message key="empresa.cruz" /></a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/profissionais/index.jsp"><fmt:message key="profissional.crud" /></a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="Sair" /></a>
    </li>
</body>
</html>