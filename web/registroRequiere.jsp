<%-- 
    Document   : registroRequiere
    Created on : 27/09/2018, 11:14:45 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Reservacion</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">

        <!-- Custom styles for this template -->
        <link href="vendor/css/full-slider.css" rel="stylesheet">
    </head>
    <body>
        <%@include  file="inc/menu.jsp" %>
        <jsp:useBean id="requiere" scope="session" class="bean.requiereBean"/>
        <jsp:useBean id="hab" scope="session" class="bean.habitacionBean"/>
        <jsp:useBean id="servicio" scope="session" class="bean.servicioBean"/>
        <%
            
            String mensaje="";
            if(request.getParameter("guardar")!=null){
                String salida=requiere.insertarRequiere(request);
                out.print(salida);
                mensaje="<div class='alert alert-info mt-5' id='alerta'>"
                            +"<p>Asignacion de servicio fue realizada correctamente.! </p>"
                            + "</div>";
            }
        %>
        <div class="container mt-5">
            <div class="row pt-5">
                <div class="col-6">
                    <h3>Requeriminetos de clientes</h3>
                    <hr>
                    <%=mensaje%>
                    <form method="get">
                        <div class="form-group row">
                            <div class="col-12 mb-3">
                                <select class="form-control" name="idHabitacion">
                                    <option value="-1">Habitaciones</option>
                                    <%=hab.listarHabitacionSelectOcupado()%>
                                 </select> 
                            </div>
                             
                            <div class="col-12 mb-3">
                                <select class="form-control" name="idServicio">
                                    <option value="-1">Servicios</option>
                                    <%=servicio.listarServicioSelect()%>
                                 </select>  
                            </div>
                            <div class="col-12 mb-3">
                                <label for="cantidad">cantidad</label>
                                <input type="number" class="form-control" placeholder="cantidad.." name="cantidad" id="cantidad">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-12  mb-3 ">
                                <input class="btn btn-primary btn-block form-control" type="submit" value="Guardar" name="guardar">
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
