<%@page import="com.emergentes.modelo.Cliente"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Cliente cli = (Cliente)request.getAttribute("cli");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFICAR</title>
    </head>
    <body>
        <h1><c:if test="${can.idcl==0}">
             Nueva Cliente
            </c:if>
        <h1><c:if test="${can.idcl!=0}">
             Modificar Cliente
            </c:if>
            </h1>
        <form action="MainControllerClientes" method="post">
            <input type="hidden" name="idcl" value="${cli.idcl}">
            <table>
                <tr>
                    <td>NOMBRES:</td>
                    <td><input type="text" name="nombre" value="${cli.nombre}"></td>
                </tr>
                <tr>
                    <td>APELLIDOS:</td>
                    <td><input type="text" name="apellido" value="${cli.apellido}"></td>
                </tr>
                <tr>
                    <td>CEDULA DE IDENTIDAD:</td>
                    <td><input type="text" name="ci" value="${cli.ci}"></td>                  
                </tr>
                <tr>
                    <td>CELULAR:</td>
                    <td><input type="text" name="celular" value="${cli.celular}"></td>                  
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Enviar"></td>
                </tr>
            </table>
            <a href="MainControllerClientes">Inicio</a>
        </form>
    </body>
</html>
