<%-- 
    Document   : reserva
    Created on : 7/04/2019, 04:31:43 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservaciones</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/fonts.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">
        
    </head>
    <body>
        <%@include file="inc/menu.jsp" %>
        <jsp:useBean id="clie" scope="session" class="bean.clienteBean"/>
        <jsp:useBean id="reserva" scope="session" class="bean.habitacionBean"/>
        <%
            //obteniendo el paramentro del codigo de la pueza seleccionada
            String categoria = (String) request.getParameter("id");
            String salida1 = "";
            String mensajes="";
            String mensajeRegistroCliente="";
            if (categoria != null) {
                salida1 = reserva.listarHabitacionBuscada(categoria);
                if(salida1.equals("")){
                    mensajes="<h2>No hay habitaciones</h2>";
                    salida1="<div class='alert alert-info mt-5' id='alerta'>"
                            +"<p>Ups..!! En estos momentos no contamos con habitaciones de este tipo disponibles.</p>"
                            + "</div>";
                    
                }else{
                    mensajes="<h2>Hay Habitaciones Reserva Ya!!</h2>";
                }
                if (request.getParameter("guardar") != null) {
                    mensajeRegistroCliente = clie.insertarCliente(request);
                     
                            
               }

            }
        %>
        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col-5">
                    <%=mensajes%>
                    <%=salida1%>
                    <%=mensajeRegistroCliente%>
  
                    
                </div>
                <div class="col-7">
                    <div class="card">
                        <img src="vendor/img/cuarto3.jpg" class="card-image-top img-fluid" alt="">
                        <div class="card-block">
                            <h4 class="card-title text-center">Habitacion <%=categoria%></h4>
                            <p class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem, laudantium? Vel, ipsa esse mollitia!.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>
    </body>
</html>
