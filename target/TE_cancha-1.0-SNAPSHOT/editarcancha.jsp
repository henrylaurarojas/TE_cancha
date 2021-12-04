<%@page import="com.emergentes.modelo.Cancha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Cancha can = (Cancha)request.getAttribute("can");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Canchas</title>
    </head>
    <body>
        <h1><c:if test="${can.idcan==0}">
             Nueva Cancha
            </c:if>
        <h1><c:if test="${can.idcan!=0}">
             Modificar Cancha  
            </c:if>
            </h1>
        <form action="MainControllerCanchas" method="post">
            <input type="hidden" name="idcan" value="${can.idcan}">
            <table>
                <tr>
                    <td>NOMBRE DE CANCHA:</td>
                    <td><input type="text" name="nombre" value="${can.nombre}"></td>
                </tr>
                <tr>
                    <td>PRECIO:</td>
                    <td><input type="number" name="precio" value="${can.precio}"></td>                  
                </tr>
                <tr>
                    <td>OBSERVACIÓN:</td>
                    <td><input type="text" name="obs" value="${can.obs}"></td>                  
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Enviar"></td>
                </tr>
            </table>
            <a href="MainControllerCanchas">Inicio</a>
        </form>
    </body>
</html>
