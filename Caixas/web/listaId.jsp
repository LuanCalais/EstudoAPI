<%-- 
    Document   : listaId
    Created on : 09/06/2021, 19:42:02
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="modelo.Caixas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="Style.css">
        <title>Buscador de Id</title>
    </head>
    <body>

        <%
            Caixas c = (Caixas) request.getAttribute("listaId");
        %>

        <table border="1">
            <legend id="LegendaLista" >Lista de Caixas</legend>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descrição</th>
                    <th>Quantidade</th>
                    <th>Responsável</th>
                </tr>
            </thead>

            <tbody>

                <tr>
                    <td><%=c.getId()%></td> 
                    <td><%=c.getDescricao()%></td>
                    <td><%=c.getQuantidade()%></td>
                    <td><%=c.getResponsavel()%></td>
                </tr>

            </tbody>

        </table>

        <a id="Link1" href="http://localhost:8080/Caixas/">Menu anterior</a>

        <img id="logoTipo3" src="Imagens/DSaaS_Logotipo.png">


    </body>
</html>
