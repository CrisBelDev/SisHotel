<%-- 
    Document   : listarServicio
    Created on : 13-sep-2018, 21:54:28
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
        <script type="text/javascript">
            function confirmarEliminacion() {
                var varConfirm = confirm("Esta seguro de eliminar?");
                console.log('varConfirm: ' + varConfirm)
                return varConfirm;
            }
        </script>
    </head>
    <body>
        <%@include file="inc/menu.jsp" %>
        <jsp:useBean id="servicio" scope="session" class="bean.servicioBean"/>
        <%
            //obteniendo el paramentro del codigo de la pueza seleccionada
            String codPieza = (String) request.getParameter("cod");
            if (codPieza != null) {
                String salida1 = servicio.eliminarServicio(request, codPieza);
                out.print(salida1);
            }
        %>

        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col-12">
                    <h2>lista Servicio</h2>
                    <table class="table table-bordered table-responsive table-hover">
                        <thead class="thead-default">
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Costo</th>
                        <th>Descripcion</th>
                        <th>Opciones</th>
                        </thead>
                        <tbody>
                            <%=servicio.listarServicio()%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>
    </body>

</html>