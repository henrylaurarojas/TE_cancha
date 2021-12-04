<%@page import="com.emergentes.modelo.Caja"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Caja caj = (Caja)request.getAttribute("caj");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FACTURAR</title>
    </head>
    <body>
        <h1><c:if test="${caj.id==0}">
             Nuev@ Caja
            </c:if>
        <h1><c:if test="${caj.id!=0}">
             Modificar Caja
            </c:if>
            </h1>
        <form action="MainControllerCaja" method="post">
            <input type="hidden" name="id" value="${caj.id}">
            <table>
                <tr>
                    <td>FECHA:</td>
                    <td><input type="date" name="fecha" value="${caj.fecha}"></td>
                </tr>
                <tr>
                    <td>ID RESERVA:</td>
                    <td><input type="text" name="idr" value="${caj.idr}"></td>
                </tr>
                <tr>
                    <td>MONTO:</td>
                    <td><input type="text" name="monto" value="${caj.monto}"></td>                  
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Enviar"></td>
                </tr>
            </table>
            <a href="MainControllerCaja">Inicio</a>
        </form>
    </body>
</html>
