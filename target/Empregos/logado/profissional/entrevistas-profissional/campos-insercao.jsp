<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
    <caption>
        Cadastro
    </caption>

    <c:if test="${entrevista != null}">
        <input type="hidden" name="id" value="${entrevista.id}" />
    </c:if>
    
    <tr>
        <td><label for="cnpj">Empresas</label></td>
        <td>
            <select id="cnpj" name="cnpj">
                <c:forEach items="${listaEmpresas}" var="empresa">
                    <option value="${empresa.cnpj}" <c:if test="${empresa.cnpj == requestScope.empresaSelecionada}">selected</c:if>>${empresa.nome} - ${empresa.cidade}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    
    <tr>
        <td><label for="data">Data</label></td>
        <td>
            <input type="date" id="data" name="data" required value="${requestScope.dataSelecionada}" />
        </td>        
    </tr>
    
    <tr>
        <td><label for="hora">Hora:</label></td>
        <td>
            <select id="hora" name="hora">
                <script>
                    var selectHora = document.getElementById("hora");
            
                    var horaInicial = 8; 
                    var horaFinal = 18; 
                    var intervaloMinutos = 30; 
                    var horaSelecionada = "${requestScope.horaSelecionada}";
            
                    for (var hora = horaInicial; hora <= horaFinal; hora++) {
                        for (var minuto = 0; minuto < 60; minuto += intervaloMinutos) {
                            var horaFormatada = hora.toString().padStart(2, '0');
                            var minutoFormatado = minuto.toString().padStart(2, '0');
                            var option = document.createElement("option");
                            option.value = horaFormatada + ":" + minutoFormatado;
                            option.text = horaFormatada + ":" + minutoFormatado;
            
                            if (option.value === horaSelecionada) {
                                option.selected = true;
                            }
            
                            selectHora.appendChild(option);
                        }
                    }
                </script>
            </select>
            
        </td>
    </tr>
    
    <tr>
        <td colspan="2" align="center">
            <c:if test="${requestScope.disponibilidade == false}">
                <h3 style="color:red;">Data/horário indisponível. Escolha outra data ou horário para se entrevistar.</h3>
            </c:if>
            <input type="submit" value="Agendar" />
        </td>
    </tr>
</table>