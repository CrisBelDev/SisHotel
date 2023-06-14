<%-- 
    Document   : listarReservacion
    Created on : 27/09/2018, 10:07:14 PM
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
        <jsp:useBean id="reservacion" scope="session" class="bean.reservacionBean"/>


        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col-12">
                    <h2>lista Reservaciones</h2>
                    <div class="alert alert-info mt-5" id="alerta">
                        <%
                            //obteniendo el paramentro del codigo de la pueza seleccionada
                            String codPieza = (String) request.getParameter("cod");
                            if (codPieza != null) {
                                String salida1 = reservacion.eliminarReservacion(request, codPieza);
                                out.print(salida1);
                            }
                        %>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <table class="table table-bordered table-responsive table-hover">
                        <thead class="thead-default">
                        <th>id</th>
                        <th>id cliente</th>
                        <th>Costo</th>
                        <th>fecha inicio</th>
                        <th>fecha salida</th>
                        </thead>
                        <tbody>
                            <%=reservacion.listarReservacion()%>
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
