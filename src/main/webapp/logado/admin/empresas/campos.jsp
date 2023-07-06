<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:bundle basename="message">
    <table border="1">
        <caption>
            <c:choose>
                <c:when test="${empresa != null}">
                    <h1><fmt:message key="user.edicao" /></h1>
                </c:when>
                <c:otherwise>
                    <h1><fmt:message key="user.cadastro" /></h1>
                </c:otherwise>
            </c:choose>
        </caption>

        <c:if test="${empresa != null}">
            <input type="hidden" name="id" value="${empresa.id}" />
        </c:if>
        <tr>
            <td><label for="nome"><fmt:message key="user.nome" /></label></td>
            <td><input type="text" id="nome" name="nome" size="45" required value="${empresa.nome}" /></td>
        </tr>

        <tr>
            <td><label for="email"><fmt:message key="user.email" /></label></td>
            <td><input type="text" id="email" name="email" size="45" required value="${empresa.email}" /></td>
        </tr>

        <tr>
            <td><label for="senha"><fmt:message key="user.password" /></label></td>
            <td><input type="text" id="senha" name="senha" size="45" required value="${empresa.senha}" /></td>
        </tr>

        <tr>
            <td><label for="cnpj"><fmt:message key="empresa.cnpj" /></label></td>
            <td><input type="text" id="cnpj" name="cnpj" size="45" required value="${empresa.cnpj}" /></td>
        </tr>

        <tr>
            <td><label for="cidade"><fmt:message key="empresa.cidade" /></label></td>
            <td><input type="text" id="cidade" name="cidade" size="45" required value="${empresa.cidade}" /></td>
        </tr>
        
        
        <tr>
            <td colspan="2" align="center"><input type="submit" value="<fmt:message key="salvar" />"/></td>
        </tr>
    </table>
</fmt:bundle>