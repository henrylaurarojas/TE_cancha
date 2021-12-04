<%@page import="com.emergentes.modelo.Empleado"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Empleado emp = (Empleado)request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFICAR</title>
    </head>
    <body>
        <h1><c:if test="${emp.ide==0}">
             Nuev@ Emplead@
            </c:if>
        <h1><c:if test="${emp.ide!=0}">
             Modificar Emplead@
            </c:if>
            </h1>
        <form action="MainControllerEmpleados" method="post">
            <input type="hidden" name="ide" value="${emp.ide}">
            <table>
                <tr>
                    <td>NOMBRES:</td>
                    <td><input type="text" name="nombre" value="${emp.nombre}"></td>
                </tr>
                <tr>
                    <td>CEDULA IDENTIDAD:</td>
                    <td><input type="text" name="ci" value="${emp.ci}"></td>
                </tr>
                <tr>
                    <td>CONTRASEÑA:</td>
                    <td><input type="text" name="pass" value="${emp.pass}"></td>                  
                </tr>
                <tr>
                    <td>CELULAR:</td>
                    <td><input type="text" name="celular" value="${emp.celular}"></td>                  
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Enviar"></td>
                </tr>
            </table>
            <a href="MainControllerEmpleados">Inicio</a>
        </form>
    </body>
</html>
