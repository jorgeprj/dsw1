<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profissional</title>
</head>
<body>
    <h1>Olá, ${sessionScope.usuarioLogado.nome}</h1>

    <li>
        <a href="${pageContext.request.contextPath}/entrevistas-profissional/cadastro">Agendar Nova Entrevista</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/entrevistas-profissional/lista.jsp">Lista de Entrevistas</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
    </li>
</body>
</html>
