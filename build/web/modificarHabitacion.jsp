<%-- 
    Document   : modificarHabitacion
    Created on : 27/09/2018, 06:52:58 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">

        <!-- Custom styles for this template -->
        <link href="vendor/css/full-slider.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="inc/menu.jsp" %>
        <jsp:useBean id="hab" scope="session" class="bean.habitacionBean"/>
        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col">
                    <h1>Modificar pieza</h1>
                    <%
                        //obteniendo el parametro del codigo de la pieza seleccionada
                        String codPieza = request.getParameter("cd");
                        //relizando llamada añ metodo de vusqueda
                        hab.buscarHabitacion(codPieza);
                        if (request.getParameter("modificar") != null) {
                            String salida1 = hab.modificarHabitacion(request, codPieza);
                            out.print(salida1);
                        }
                    %>
                </div>
            </div>
        </div>
        
        
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form method="post">
                        <div class="form-group row">
                            <div class="col-12 col-md-auto mb-3">
                                <label for="nomHab">Nombre</label>
                                <input type="text" class="form-control" name="nomHab" value="<%=hab.getHabitacionModificar().getNom_habitacion()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="tipoHab">tipo</label>
                                <input type="text" class="form-control" name="tipoHab" value="<%=hab.getHabitacionModificar().getTipo()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="costoHab">costo</label>
                                <input type="text" class="form-control" name="costoHab" value="<%=hab.getHabitacionModificar().getCosto()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="estadoHab">estado</label>
                                <input type="text" class="form-control" name="estadoHab" value="<%=hab.getHabitacionModificar().getEstado()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="descripcionHab">descripcion</label>
                                <input type="text" class="form-control" name="descripcionHab" value="<%=hab.getHabitacionModificar().getDescripcion()%>">
                            </div>
                        </div>

                        

                        <div class="form-group row">
                            <div class="col-12 text-center">
                                <div class="row justify-content-start">
                                    <div class="col-12 col-sm-9 col-md-4">
                                       <input type="submit" value="Modificar" class="btn btn-success btn-block" name="modificar">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        
        <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>
    </body>
</html>
