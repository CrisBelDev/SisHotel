<%-- 
    Document   : menu
    Created on : 06-sep-2018, 22:52:14
    Author     : kings
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- navegacion -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top ">
    <div class="container">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="http://localhost:8080/SISHOTEL/"><span class="icon-home"></span>Inicio</a>
                </li>
                <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="menu-categorias" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="icon-user"></span>Administrador</a>
                        <div class="dropdown-menu" arialabelledby="menu-categorias">
                            <a href="registroCliente.jsp" class="dropdown-item">Registrar</a>
                            <a href="listarUsuarios.jsp" class="dropdown-item">Listar</a>
                            <a href="registroServicio.jsp" class="dropdown-item">Registrar Servicio</a>
                            <a href="listarServicio.jsp" class="dropdown-item">Listar Servicio</a>
                            <a href="registroHabitacion.jsp" class="dropdown-item">Registrar Habitacion</a>
                            <a href="listarHabitacion.jsp" class="dropdown-item">Listar Habitacion</a>
                            <a href="registroCliente1.jsp" class="dropdown-item">Registrar Cliente</a>
                            <a href="listarCliente1.jsp" class="dropdown-item">Listar Cliente</a>
                           
                            <a href="listarReservacion.jsp" class="dropdown-item">Listar Reservacion</a>
                        </div>
                    </li>
                <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="menu-categorias" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="icon-users"></span>Cliente</a>
                        <div class="dropdown-menu justify-content-center" arialabelledby="menu-categorias">
                            
                            <a href="listarOcupa.jsp" class="dropdown-item">listar habitaciones ocupadas</a>
                            <a href="registroRequiere.jsp" class="dropdown-item">requerimentos</a>
                            
                            
                        </div>
                    </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Disabled</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<!-- navegacion -->