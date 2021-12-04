<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Cliente> lista = (List<Cliente>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CLIENTES</title>
    </head>
    <body>
        <h1>Registros de Clientes</h1>
        <table>
            <tr>
                <th><a href="MainControllerClientes?op=nuevo">Nuevo</a></th>
            </tr>
        </table>       
        <table border="1">
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>APELLIDO</th>
                <th>CEDULA IDENTIDAD</th>
                <th>CELULAR</th>
                <th>MODIFICAR</th>
                <th>ELIMINAR</th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.idcl}</td>
                <td>${item.nombre}</td>
                <td>${item.apellido}</td>
                <td>${item.ci}</td>
                <td>${item.celular}</td>
                <td><a href="MainControllerClientes?op=editar&idcl=${item.idcl}">Modificar</a></td>
                <td><a href="MainControllerClientes?op=eliminar&idcl=${item.idcl}"
                       onclick="return(confirm('Está seguro de eliminar'))">Eliminar</a></td>  
            </tr>
            </c:forEach>
        </table>
        <a href="MainController">RESERVAS</a>
    </body>
</html>
