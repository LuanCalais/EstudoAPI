<%@page import="modelo.Caixas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="Style.css">
        <title>Lista Caixas</title>
    </head>
    <body>

        <%
            ArrayList<Caixas> listaCaixas = (ArrayList<Caixas>) request.getAttribute("listaCaixas");
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

                <%
                    for (Caixas c : listaCaixas) {
                %>

                <tr>
                    <td><%=c.getId()%></td> 
                    <td><%=c.getDescricao()%></td>
                    <td><%=c.getQuantidade()%></td>
                    <td><%=c.getResponsavel()%></td>
                </tr>


                <%
                    }
                %>

            </tbody>

        </table>

        <a id="Link1" href="http://localhost:8080/Caixas/">Menu anterior</a>
        
        <img id="logoTipo3" src="Imagens/DSaaS_Logotipo.png">

    </body>
</html>
