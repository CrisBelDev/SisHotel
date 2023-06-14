<%-- 
    Document   : modificarServicio
    Created on : 13-sep-2018, 22:00:15
    Author     : kings
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
        <jsp:useBean id="servicio" scope="session" class="bean.servicioBean"/>
        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col">
                    <h1>Modificar pieza</h1>
                    <%
                        //obteniendo el parametro del codigo de la pieza seleccionada
                        String codPieza = request.getParameter("cd");
                        //relizando llamada añ metodo de vusqueda
                        servicio.buscarServicio(codPieza);
                        if (request.getParameter("modificar") != null) {
                            String salida1 = servicio.modificarServicio(request, codPieza);
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
                                <label for="nom_servicio">Nombre</label>
                                <input type="text" class="form-control" name="nom_servicio" value="<%=servicio.getServicioModificar().getNom_servicio()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="costo_servicio">Costo</label>
                                <input type="text" class="form-control" name="costo_servicio" value="<%=servicio.getServicioModificar().getCosto_servicio()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="desc_servicio">Descripcion</label>
                                <input type="text" class="form-control" name="desc_servicio" value="<%=servicio.getServicioModificar().getDescripcion()%>">
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
