<%-- 
    Document   : listarOcupa
    Created on : 27/09/2018, 10:55:51 PM
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
        <jsp:useBean id="cliente" scope="session" class="bean.clienteBean"/>
        <jsp:useBean id="hab" scope="session" class="bean.habitacionBean"/>
        <%
            //obteniendo el paramentro del codigo de la pueza seleccionada
            String codPieza = (String) request.getParameter("cod");
            String op=(String)request.getParameter("op");
            String salida2="";
            if (codPieza != null) {
                String salida1 = cliente.eliminarCliente(request, codPieza);
                out.print(salida1);
            }
            if(op != null){
                 salida2=hab.modificarHabitacionLibre(request, op);
            }
        %>

        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col-12">
                    <%=salida2%>
                    <h2>cuartos ocupados</h2>
                    <table class="table table-bordered table-responsive table-hover">
                        <thead class="thead-default">
                        <th>Nombre</th>
                        <th>Paterno</th>
                        <th>Materno</th>
                        <th>Habitacion</th>
                        <th>Tipo</th>
                        <th>fecha inicio</th>
                        <th>fecha salida</th>
                        <th>Acciones</th>
                        </thead>
                        <tbody>
                            <%=cliente.listarClienteHabitacion()%>
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
