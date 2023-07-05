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
        <a href="${pageContext.request.contextPath}/empresas/lista.jsp">CRUD de Empresas</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/profissionais/index.jsp">CRUD de Profissionais</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
    </li>
</body>
</html>