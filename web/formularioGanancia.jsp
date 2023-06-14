<%-- 
    Document   : formularioGanancia
    Created on : 15/11/2018, 11:06:52 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP de cliente registro</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">
        <link href="vendor/css/full-slider.css" rel="stylesheet">
    </head>
    <body>
        <%@include  file="inc/menu.jsp" %>
        <jsp:useBean id="clie" scope="session" class="bean.clienteBean"/>
        <jsp:useBean id="habitacion" scope="session" class="bean.habitacionBean"/>
        <div class="container mt-5 pt-5">
            <div class="row justify-content-center mt-5">
                <div class="col-2">
                    <h3>Reporte de ganancias</h3>
                    <form action="reporteGanancia.jsp">
                        <div class="form-group">
                            <label for="">fecha inicio</label>
                            <input type="text" name="fechaInicio" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="">fecha final</label>
                            <input type="text" name="fechaFinal" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="enviar" value="buscar" class="btn btn-outline-primary btn-block">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>