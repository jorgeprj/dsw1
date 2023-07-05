<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin</title>
</head>
<body>
    <h1>Olá, ${sessionScope.usuarioLogado.nome}</h1>

    <li>
        <a href="${pageContext.request.contextPath}/crud/cadastro">Adicionar Profissional</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/crud/lista.jsp">Lista de Profissionais</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
    </li>
</body>
</html>