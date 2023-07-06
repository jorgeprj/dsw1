<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:bundle basename="message">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin</title>
</head>
<body>
    <h1><fmt:message key="user.ola" />, ${sessionScope.usuarioLogado.nome}</h1>

    <li>
        <a href="${pageContext.request.contextPath}/empresas/lista.jsp"><fmt:message key="empresa.crud" /></a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/profissionais/index.jsp"><fmt:message key="profissional.crud" /></a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="sair" /></a>
    </li>
</body>
</fmt:bundle>