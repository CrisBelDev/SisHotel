<%-- 
    Document   : registroServicio
    Created on : 13-sep-2018, 22:12:03
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
        <%@include  file="inc/menu.jsp" %>
        <jsp:useBean id="servicio" scope="session" class="bean.servicioBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String salida=servicio.insertarServicio(request);
                out.print(salida);
            }
        %>
        <div class="container mt-5">
            <div class="row pt-5">
                <div class="col">
                    <h3>Registro de Servicio</h3>
                    <hr>
                    <form action="">
                        <div class="form-group row">
                            <div class="col-12 col-md-auto mb-3">
                                <label for="nom_servicio">Nombre</label>
                                <input type="text" class="form-control" placeholder="Nombre.." name="nom_servicio" id="nom_servicio">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="costo_servicio">Costo</label>
                                <input type="text" class="form-control" placeholder="Costo.." name="costo_servicio" id="costo_servicio">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="desc_servicio">Descripcion</label>
                                <input type="text" class="form-control" placeholder="Descripcion.." name="desc_servicio" id="desc_servicio">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
   
                                <input class="btn btn-primary btn-block" type="submit" value="Guardar" name="guardar">
                                <a href="listarServicio.jsp" class="btn btn-primary btn-block" >Ver lista</a>
                            </div>
                        </div>
                        </div>
                        <div class="w-100"></div>

                        

                    </form>
                </div>
            </div>
        </div>
         <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>
    </body>
</html>

