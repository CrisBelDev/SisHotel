<%-- 
    Document   : registroHabitacion
    Created on : 27/09/2018, 04:52:49 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de habitacion</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">

        <!-- Custom styles for this template -->
        <link href="vendor/css/full-slider.css" rel="stylesheet">
    </head>
    <body>
        <%@include  file="inc/menu.jsp" %>
        <jsp:useBean id="habitacion" scope="session" class="bean.habitacionBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String salida=habitacion.insertarHabitacion(request);
                out.print(salida);
            }
        %>
        <div class="container mt-5">
            <div class="row pt-5">
                <div class="col">
                    <h3>Registro de habitacion</h3>
                    <hr>
                    <form method="get">
                        <div class="form-group row">
                            <div class="col-12 col-md-auto mb-3">
                                <label for="nomHabitacion">Nombre</label>
                                <input type="text" class="form-control" placeholder="Nombre.." name="nomHabitacion" id="nomHabitacion">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="tipo">Tipo</label>
                                <input type="text" class="form-control" placeholder="tipo.." name="tipo" id="tipo">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="costoHabitacion">costo</label>
                                <input type="text" class="form-control" placeholder="costoHabitacion.." name="costoHabitacion" id="costoHabitacion">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12 col-md-4 mb-3">
                                <label for="estado">Estado</label>
                                <input type="text" class="form-control" placeholder="estado.." name="estado" id="estado">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="descripcion">Descripcion</label>
                                <input type="text" class="form-control" placeholder="descripcion.." name="descripcion" id="descripcion">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
   
                                <input class="btn btn-primary btn-block" type="submit" value="Guardar" name="guardar">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12 text-center">
                                <div class="row justify-content-start">
                                    <div class="col-12 col-sm-9 col-md-4">
                                        
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