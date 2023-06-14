<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Reservacion</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">
        <link href="vendor/css/full-slider.css" rel="stylesheet">
    </head>
    <body>
        <%@include  file="inc/menu.jsp" %>
        <jsp:useBean id="reservacion" scope="session" class="bean.reservacionBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String salida=reservacion.insertarReservacion(request);
                out.print(salida);
            }
        %>
        <div class="container mt-5">
            <div class="row pt-5">
                <div class="col">
                    <h3>Registro de Reservacion</h3>
                    <hr>
                    <form method="get">
                        <div class="form-group row">
                            <div class="col-12 col-md-auto mb-3">
                                <label for="idCliente">id cliente</label>
                                <input type="text" class="form-control" placeholder="cliente.." name="idCliente" id="idCliente">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="costo">costo</label>
                                <input type="text" class="form-control" placeholder="costo.." name="costo" id="costo">
                            </div>
                            <div class="col-12 col-md-auto mb-3">
                                <label for="f_inicio">inicio</label>
                                <input type="date" class="form-control" name="f_inicio" id="f_inicio">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12 col-md-4 mb-3">
                                <label for="f_salida">final</label>
                                <input type="date" class="form-control" name="f_salida" id="f_salida">
                            </div>
                            <div class="w-100"></div>
                            
                            <div class="w-100"></div>
                            <div class="col-12 col-md-4 mb-3">
   
                                <input class="btn btn-primary btn-block" type="submit" value="Guardar" name="guardar">
                                <a href="listarReservacion.jsp" class="btn btn-primary btn-block" >Ver lista</a>
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
