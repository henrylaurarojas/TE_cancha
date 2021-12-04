<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Reserva"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Reserva> lista = (List<Reserva>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INICIO</title>
    </head>
    <body>
        <h1>Registros de Reservas</h1>
        <table>           
            <tr><th><a href="MainControllerCanchas">Registro de Canchas</a></th></tr>
            <tr><th><a href="MainControllerClientes">Registro de Clientes</a></th></tr>
            <tr><th><a href="MainControllerEmpleados">Registro de Empleados</a></th></tr>
            <tr><th></th></tr>
            <tr><th></th></tr>
            <tr><th></th></tr>
            <tr><th></th></tr>
            <tr><th></th></tr>
            <tr>
                <th><a href="MainController?op=nuevo">Nuevo</a></th>
            </tr>
        </table>       
        <table border="1">
            <tr>
                <th>ID</th>
                <th>FECHA</th>
                <th>DESDE</th>
                <th>HASTA</th>
                <th>ID_CLIENTE</th>
                <th>ID_EMPLEADO</th>
                <th>ID_CANCHA</th>
                <th>OBSERVACION</th>
                <th>MODIFICAR</th>
                <th>ELIMINAR</th>
                <th>CAJA</th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.idr}</td>
                <td>${item.fecha}</td>
                <td>${item.desde}</td>
                <td>${item.hasta}</td>
                <td>${item.idcl}</td>
                <td>${item.ide}</td>
                <td>${item.idcan}</td>
                <td>${item.obs}</td>
                <td><a href="MainController?op=editar&idr=${item.idr}">Modificar</a></td>
                <td><a href="MainController?op=eliminar&idr=${item.idr}"
                       onclick="return(confirm('Está seguro de eliminar'))">Eliminar</a></td>  
                <td><a href="MainControllerCaja?op=nuevo&idr=${item.idr}">CAJA</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
