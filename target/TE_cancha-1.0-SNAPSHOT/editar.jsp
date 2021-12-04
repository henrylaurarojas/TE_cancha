<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Reserva res = (Reserva)request.getAttribute("res");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFICAR</title>
    </head>
    <body>
        <h1><c:if test="${res.idr==0}">
             Nueva Reserva  
            </c:if>
        <h1><c:if test="${res.idr!=0}">
             Modificar Reserva  
            </c:if>
            </h1>
        <form action="MainController" method="post">
            <input type="hidden" name="idr" value="${res.idr}">
            <table>
                <tr>
                    <td>FECHA:</td>
                    <td><input type="date" name="fecha" value="${res.fecha}"></td>
                </tr>
                <tr>
                    <td>DESDE:</td>
                    <td><input type="text" name="desde" value="${res.desde}"></td>
                </tr>
                <tr>
                    <td>HASTA:</td>
                    <td><input type="text" name="hasta" value="${res.hasta}"></td>
                </tr>
                <tr>
                    <td>ID CLIENTE:</td>
                    <td><input type="number" name="idcl" value="${res.idcl}"></td>                  
                </tr>
                <tr>
                    <td>ID EMPLEADO:</td>
                    <td><input type="number" name="ide" value="${res.ide}"></td>                  
                </tr>
                <tr>
                    <td>ID CANCHA:</td>
                    <td><input type="number" name="idcan" value="${res.idcan}"></td>                  
                </tr>
                <tr>
                    <td>OBSERVACIÓN:</td>
                    <td><input type="text" name="obs" value="${res.obs}"></td>                  
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Enviar"></td>
                </tr>
            </table>
            <a href="MainController">Inicio</a>
        </form>
    </body>
</html>
