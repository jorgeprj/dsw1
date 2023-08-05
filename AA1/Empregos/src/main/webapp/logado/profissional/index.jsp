<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:bundle basename="message">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="user.profissional" /></title>
</head>
<body>
    <h1><fmt:message key="user.ola" />, ${sessionScope.usuarioLogado.nome}</h1>

    <li>
        <a href="${pageContext.request.contextPath}/entrevistas-profissional/cadastro"><fmt:message key="agendar.entrevista" /></a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/entrevistas-profissional/lista.jsp"><fmt:message key="lista.empresa" /></a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="sair" /></a>
    </li>
</body>
</fmt:bundle>
