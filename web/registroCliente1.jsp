<%@page import="bean.habitacion"%>
<%@page import="bean.habitacionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP de cliente registro</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">
        <link href="vendor/css/full-slider.css" rel="stylesheet">
    </head>
    <body>
        <%@include  file="inc/menu.jsp" %>
        <jsp:useBean id="clie" scope="session" class="bean.clienteBean"/>
        <jsp:useBean id="habitacion" scope="session" class="bean.habitacionBean"/>


        <div class="container mt-5">
            <div class="row pt-5">
                <div class="col">

                    <h3>Registro de clientes</h3>
                    <hr>
                    <%
                        habitacion obj=new habitacion();
                        String salida1="";
                        String salida="";
                        if (request.getParameter("guardar") != null) {
                            salida = clie.insertarCliente(request);
                             salida1=clie.costoNoche(request);
                            
                        }
                        if (request.getParameter("habitacion") != null && Integer.parseInt(request.getParameter("habitacion")) > 0) {
                            String codHab = request.getParameter("habitacion");
                            System.out.println("habitacion " + codHab);
                            obj = habitacion.buscarHabitacion(codHab);
                           String costo= clie.costoNoche(request);
                        }
                    %>
                  
                        <%=salida%>
                        
                    <form method="post">
                        <div class="row">
                            <div class="col-4">
                                <div class="form-group row">
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" placeholder="nombre.." name="nomclie" id="nomclie">
                                    </div>
                                    <div class="col-12 mb-3">
                                        <input type="text" class="form-control" placeholder="paterno.." name="patclie" id="patclie">
                                    </div>
                                    <div class="col-12  mb-3">
                                        <input type="text" class="form-control" placeholder="materno.." name="matclie" id="matclie">
                                    </div>
                                    <div class="col-12  mb-3">
                                        <input type="text" class="form-control" placeholder="telefono.." name="telfclie" id="telfclie">
                                    </div>
                                    <div class="col-12  mb-3">
                                        <input type="text" class="form-control" placeholder="email.." name="emailclie" id="emailclie">
                                    </div>
                                    <div class="col-12  mb-3">
                                        <input type="text" class="form-control" placeholder="dni.." name="dniclie" id="dniclie">
                                    </div>
                                </div> 
                            </div>
                            <div class="col-5">
                                <div class="form-group row">
                                    <div class="col-12 col-md-auto mb-3">
                                        <label for="costo">costo</label>
                                        <input type="text" class="form-control" placeholder="costo.." name="costo" id="costo">
                                    </div>
                                    <div class="col-12 col-md-auto mb-3">
                                        <label for="costo">fecha de Finalizacion</label>
                                        <input type="date" class="form-control" name="fechaSalidaHabitacion">
                                    </div>
                                    <div class="col-12 col-md-auto mb-3">
                                        <label for="costo">habitacion</label>
                                        <select class="form-control" name="habitacion">
                                            <option value="-1">Habitaciones</option>
                                            <%=habitacion.listarHabitacionSelect()%>
                                        </select>  
                                    </div>
                                    <div class="w-100"></div>
                                    <div class="col-12 col-md-4 mb-3">

                                        <input class="btn btn-primary btn-block" type="submit" value="Guardar" name="guardar">
                                    </div>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="card card-inverse card-primary">
                                    <div class="card-block">
                                        <h4 class="card-title"><u>Informacion</u></h4>
                                        
                                        <p> <%=(salida1)%></p>
                                    </div>
                                </div>
                            </div>
                                        

                        </div>



                        <div class="form-group row">
                            <div class="col-12 text-center">
                                <div class="row justify-content-start">
                                    <div class="col-12 col-sm-9 col-md-4">
                                        <a href="listarCliente1.jsp" class="btn btn-outline-danger">lista de clientes</a>
                                        <a href="listarOcupa.jsp" class="btn btn-outline-warning">lista de Ocupados</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
            <script src="vendor/js/jquery-3.3.1.min.js"></script>
            <script src="vendor/js/tether.min.js"></script>
            <script src="vendor/js/bootstrap.min.js"></script>
    </body>
</html>