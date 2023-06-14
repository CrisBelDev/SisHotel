<%-- 
    Document   : registroCliente
    Created on : 11-sep-2018, 16:29:25
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
        <jsp:useBean id="admin" scope="session" class="bean.administradorBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String salida=admin.insertarCliente(request);
                out.print(salida);
            }
        %>
        <div class="container mt-5">
            <div class="row pt-5">
                <div class="col">
                    <h3>Registro de Administradores</h3>
                    <hr>
                    <form method="get">
                        <div class="form-group row">
                            <div class="col-12 col-md-auto mb-3">
                                <label for="nombre">Nombre</label>
                                <input type="text" class="form-control" placeholder="Nombre.." name="nombre" id="nombre">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="paterno">Paterno</label>
                                <input type="text" class="form-control" placeholder="paterno.." name="paterno" id="paterno">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="materno">Materno</label>
                                <input type="text" class="form-control" placeholder="materno.." name="materno" id="materno">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12 col-md-4 mb-3">
                                <label for="telefono">Telefono</label>
                                <input type="text" class="form-control" placeholder="telefono.." name="telefono" id="telefono">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="cargo">cargo</label>
                                <input type="text" class="form-control" placeholder="cargo.." name="cargo" id="cargo">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="dni">Dni</label>
                                <input type="text" class="form-control" placeholder="dni.." name="dni" id="dni">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="expedido">Expedido</label>
                                <input type="text" class="form-control" placeholder="expedido.." name="expedido" id="expedido">
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

