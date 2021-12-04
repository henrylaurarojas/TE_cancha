<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Cancha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Cancha> lista = (List<Cancha>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CANCHAS</title>
    </head>
    <body>
        <h1>Registros de Canchas</h1>
        <table>
            <tr>
                <th><a href="MainControllerCanchas?op=nuevo">Nuevo</a></th>
            </tr>
        </table>       
        <table border="1">
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>PRECIO</th>
                <th>OBSERVACION</th>
                <th>MODIFICAR</th>
                <th>ELIMINAR</th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.idcan}</td>
                <td>${item.nombre}</td>
                <td>${item.precio}</td>
                <td>${item.obs}</td>
                <td><a href="MainControllerCanchas?op=editar&idcan=${item.idcan}">Modificar</a></td>
                <td><a href="MainControllerCanchas?op=eliminar&idcan=${item.idcan}"
                       onclick="return(confirm('Está seguro de eliminar'))">Eliminar</a></td>  
            </tr>
            </c:forEach>
        </table>
        <a href="MainController">RESERVAS</a>
    </body>
</html>
