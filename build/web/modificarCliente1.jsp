<%-- 
    Document   : modificarCliente1
    Created on : 27/09/2018, 08:06:57 PM
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
        <jsp:useBean id="clie" scope="session" class="bean.clienteBean"/>
        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col">
                    <h1>Modificar Cliente</h1>
                    <%
                        //obteniendo el parametro del codigo de la pieza seleccionada
                        String codPieza = request.getParameter("cd");
                        //relizando llamada añ metodo de vusqueda
                        clie.buscarCliente(codPieza);
                        if (request.getParameter("modificar") != null) {
                            String salida1 = clie.modificarCliente(request, codPieza);
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
                                <label for="idAdmin">id admin</label>
                                <input type="text" class="form-control" name="idAdmin" value="<%=clie.getClienteModificar().getId_admin()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="nombre">nombre</label>
                                <input type="text" class="form-control" name="nombre" value="<%=clie.getClienteModificar().getNom_clie()%>">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="paterno">paterno</label>
                                <input type="text" class="form-control" name="paterno" value="<%=clie.getClienteModificar().getPat_clie()%>">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12 col-md-4 mb-3">
                                <label for="materno">materno</label>
                                <input type="text" class="form-control" id="materno" name="materno" value="<%=clie.getClienteModificar().getMat_clie()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="telefono">telefono</label>
                                <input type="text" class="form-control" name="telefono" value="<%=clie.getClienteModificar().getTelf_clie()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="email">email</label>
                                <input type="text" class="form-control" name="email" value="<%=clie.getClienteModificar().getEmail_clie()%>">
                            </div>
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
                                <label for="dni">dni</label>
                                <input type="text" class="form-control" id="dni" name="dni" value="<%=clie.getClienteModificar().getDni_clie()%>">
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
