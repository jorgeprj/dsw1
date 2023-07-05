<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table border="1">
    <fmt:bundle basename="message">
        <caption>
            <c:choose>
                <c:when test="${profissional != null}">
                    <h1>Edição</h1>
                </c:when>
                <c:otherwise>
                    <h1>Cadastro</h1>
                </c:otherwise>
            </c:choose>
        </caption>

<form action="atualizacao" method="post">
        <c:if test="${profissional != null}">
            <input type="hidden" name="id" value="${profissional.id}" />
        </c:if>
        <tr>
            <td><label for="nome">Nome</label></td>
            <td><input type="text" id="nome" name="nome" size="45" required value="${profissional.nome}"/></td>
        </tr>
    
        <tr>
            <td><label for="email">Email</label></td>
            <td><input type="text" id="email" name="email" size="45" required value="${profissional.email}"></td>
        </tr>
    
        <tr>
            <td><label for="senha">Senha</label></td>
            <td><input type="text" id="senha" name="senha" size="45" required value="${profissional.senha}"/></td>
        </tr>
    
        <tr>
            <td><label for="cpf">CPF</label></td>
            <td><input type="text" id="cpf" name="cpf" size="45" required value="${profissional.cpf}"/></td>
        </tr>
    
        <tr>
            <td><label for="telefone">Telefone</label></td>
            <td><input type="text" id="telefone" name="telefone" size="45" required value="${profissional.telefone}"/></td>
        </tr>
    
        <tr>
            <td><label for="sexo">Sexo</label></td>
            <td>
                <select id="sexo" name="sexo" required>
                    <option value="M" ${profissional.sexo == 'M' ? 'selected' : ''}>M</option>
                    <option value="F" ${profissional.sexo == 'F' ? 'selected' : ''}>F</option>
                    <option value="Outro" ${profissional.sexo == 'Outro' ? 'selected' : ''}>Outro</option>
                </select>
            </td>
        </tr>
    
        <tr>
            <td><label for="dataNascimento">Data De Nascimento</label></td>
            <td><input type="date" id="dataNascimento" name="dataNascimento" size="45" required value="${profissional.dataNascimento}"/></td>
        </tr>
    
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Salvar"/></td>
        </tr>
    </form>
    </fmt:bundle>
</table>