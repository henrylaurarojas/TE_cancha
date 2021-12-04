<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Empleado> lista = (List<Empleado>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EMPLEADOS</title>
    </head>
    <body>
        <h1>Registros de Empleados</h1>
        <table>
            <tr>
                <th><a href="MainControllerEmpleados?op=nuevo">Nuevo</a></th>
            </tr>
        </table>       
        <table border="1">
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>CEDULA IDENTIDAD</th>
                <th>CONTRASEÑA</th>
                <th>CELULAR</th>
                <th>MODIFICAR</th>
                <th>ELIMINAR</th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.ide}</td>
                <td>${item.nombre}</td>
                <td>${item.ci}</td>
                <td>${item.pass}</td>
                <td>${item.celular}</td>
                <td><a href="MainControllerEmpleados?op=editar&ide=${item.ide}">Modificar</a></td>
                <td><a href="MainControllerEmpleados?op=eliminar&ide=${item.ide}"
                       onclick="return(confirm('Está seguro de eliminar'))">Eliminar</a></td>  
            </tr>
            </c:forEach>
        </table>
        <a href="MainController">RESERVAS</a>
    </body>
</html>
