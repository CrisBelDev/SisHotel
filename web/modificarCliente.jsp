<%-- 
    Document   : modificarPieza
    Created on : 26-may-2018, 18:39:10
    Author     : Incos
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
        <jsp:useBean id="admin" scope="session" class="bean.administradorBean"/>
        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col">
                    <h1>Modificar pieza</h1>
                    <%
                        //obteniendo el parametro del codigo de la pieza seleccionada
                        String codPieza = request.getParameter("cd");
                        //relizando llamada añ metodo de vusqueda
                        admin.buscarPieza(codPieza);
                        if (request.getParameter("modificar") != null) {
                            String salida1 = admin.modificarAdministrador(request, codPieza);
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
                                <label for="nombre">Nombre</label>
                                <input type="text" class="form-control" name="nombre" value="<%=admin.getAdministradorModificar().getNombre()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="paterno">Paterno</label>
                                <input type="text" class="form-control" name="paterno" value="<%=admin.getAdministradorModificar().getPaterno()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="materno">Materno</label>
                                <input type="text" class="form-control" name="materno" value="<%=admin.getAdministradorModificar().getMaterno()%>">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12 col-md-4 mb-3">
                                <label for="telefono">Telefono</label>
                                <input type="text" class="form-control" id="telefono" name="telefono" value="<%=admin.getAdministradorModificar().getTelefono()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="cargo">cargo</label>
                                <input type="text" class="form-control" name="cargo" value="<%=admin.getAdministradorModificar().getCargo()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="dni">Dni</label>
                                <input type="text" class="form-control" name="dni" value="<%=admin.getAdministradorModificar().getDni()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="expedido">Expedido</label>
                                <input type="text" class="form-control" id="expedido" name="expedido" value="<%=admin.getAdministradorModificar().getExpedido()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="expedido">Password</label>
                                <input type="text" class="form-control" name="password" value="<%=admin.getAdministradorModificar().getPassword()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">

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
