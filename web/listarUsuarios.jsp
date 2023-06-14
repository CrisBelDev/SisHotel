<%-- 
    Document   : listarUsuarios
    Created on : 09-jun-2018, 18:42:05
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
        <jsp:useBean id="usuario" scope="session" class="bean.administradorBean"/>
        <%
            //obteniendo el paramentro del codigo de la pueza seleccionada
            String codPieza = (String) request.getParameter("cod");
            if (codPieza != null) {
                String salida1 = usuario.eliminarAdministrador(request, codPieza);
                out.print(salida1);
            }
        %>

        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col-12">
                    <h2>lista usuario</h2>
                    <table class="table table-bordered table-responsive table-hover">
                        <thead class="thead-default">
                        <th>Codigo Usr</th>
                        <th>codigo rol</th>
                        <th>Nombre</th>
                        <th>Paterno</th>
                        <th>Materno</th>
                        <th>Cargo</th>
                        <th>Cedula</th>
                        <th>Expedido</th>
                        <th>Password</th>
                        <th>Opciones</th>
                        </thead>
                        <tbody>
                            <%=usuario.listarUsuario()%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>
        <!-- modales -->
        <button class="btn btn-primary" data-toggle="modal" data-target="#fm-modal-grid">Abrir Modal con Grid</button>
        <div class="modal fade" id="fm-modal-grid" tabindex="-1" role="dialog" aria-labelledby="fm-modal-grid" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="">Modificar Persona</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <form action="">
                                        <form action="">
                                            <div class="form-group">
                                                <label for="nombre">Nombre</label>
                                                <input type="text" class="form-control" placeholder="Nombre" name="nombre" id="nombre">
                                            </div>

                                            <div class="form-group">
                                                <label for="pass">Contraseña</label>
                                                <input type="password" class="form-control" placeholder="Contraseña" name="pass" id="pass">
                                            </div>

                                            <div class="form-group">
                                                <label for="mensaje">Mensaje</label>
                                                <textarea name="mensaje" id="mensaje" class="form-control"></textarea>
                                            </div>

                                            <div class="form-group">
                                                <label for="pais">Pais</label>
                                                <select name="pais" id="pais" class="form-control">
                                                    <option value="mexico">Mexico</option>
                                                    <option value="espana">España</option>
                                                    <option value="colombia">Colombia</option>
                                                </select>
                                            </div>

                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" name="sexo" id="hombre" class="form-check-input mr-2">Hombre
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" name="sexo" id="mujer" class="form-check-input mr-2">Mujer
                                                </label>
                                            </div>

                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="checkbox" name="terminos" id="terminos" class="form-check-input mr-2">Acepto Terminos y Condiciones
                                                </label>
                                            </div>

                                            <button class="btn btn-primary" type="submit">Enviar</button>
                                        </form>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-success">Aceptar</button>
                        <button class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>