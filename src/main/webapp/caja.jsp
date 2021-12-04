<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Caja"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Caja> lista = (List<Caja>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CAJA</title>
    </head>
    <body>
              
        <table border="1">
            <tr>
                <th>ID</th>
                <th>FECHA</th>
                <th>ID RESERVA</th>
                <th>MONTO</th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.id}</td>
                <td>${item.fecha}</td>
                <td>${item.idr}</td>
                <td>${item.monto}</td>
            </tr>
            </c:forEach>
        </table>
        <a href="MainController">RESERVAS</a>
    </body>
</html>
