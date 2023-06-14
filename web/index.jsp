<%-- 
    Document   : index
    Created on : 06-sep-2018, 21:56:10
    Author     : kings
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/fonts.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">
        <link rel="stylesheet" href="vendor/css/edition.css">
    </head>
    <body>
        <%@include  file="inc/menu.jsp" %>
        <header>
            <!-- banner slide -->
            <div id="banner" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#banner" data-slide-to="0" class="active"></li>
                    <li data-target="#banner" data-slide-to="1"></li>
                    <li data-target="#banner" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="carousel-item active ">
                        <img class="d-block w-100" src="vendor/img/bg.jpg" alt="First slide">
                        <div class="carousel-caption d-none d-md-block textos">
                            <h2 class="primera_linea">Lorem ipsum dolor sit amet, consectetur adipisicing elit</h2>
                            <h3 class="segunda_linea">Duis aute irure dolor in reprehenderit</h3>
                            <div class="contenedor-btns">
                                <a href="#" class="">lorem</a>
                                <a href="#">ipsum</a>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100 img-fluid" src="vendor/img/f2.jpg" alt="Second slide">
                        <div class="carousel-caption d-none d-md-block textos">
                            <h2 class="primera_linea">Lorem ipsum dolor sit amet, consectetur adipisicing elit</h2>
                            <h3 class="segunda_linea">Duis aute irure dolor in reprehenderit</h3>
                            <div class="contenedor-btns">
                                <a href="#" class="">lorem</a>
                                <a href="#">ipsum</a>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="vendor/img/f3.jpg" alt="Third slide">
                        <div class="carousel-caption d-none d-md-block textos">
                            <h2 class="primera_linea">Lorem ipsum dolor sit amet, consectetur adipisicing elit</h2>
                            <h3 class="segunda_linea">Duis aute irure dolor in reprehenderit</h3>
                            <div class="contenedor-btns">
                                <a href="#" class="">lorem</a>
                                <a href="#">ipsum</a>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#banner" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#banner" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>	
            <!-- banner slide-->
        </header>
        <!-- main -->
        <div class="container">
            <div class="row mt-3">
                <div class="col-7 align-self-center">
                    <h1 class="titulo text-center">Welcome!</h1>
                    <p class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quae architecto similique deleniti dicta! Suscipit maxime vero rerum consequuntur quaerat, dignissimos minus accusantium eaque hic reiciendis.</p>
                </div>
                <div class="col-5 ">
                    <img src="vendor/img/hotel.jpg" class="img-fluid">
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-5 ">
                    <img src="vendor/img/hotel1.jpg" class="img-fluid">
                </div>
                <div class="col-7 align-self-center">
                    <p class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quae architecto similique deleniti dicta! Suscipit maxime vero rerum consequuntur quaerat, dignissimos minus accusantium eaque hic reiciendis.</p>

                </div>

                <div class="w-100"></div>

            </div>
            <!-- servicios -->
            <div class="row mt-5">
                <div class="col-12">
                    <h3 class="text-center">SERVICES</h3>
                    <div class="card-group mt-4">
                        <div class="card">
                            <img src="vendor/img/restoran.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title text-center">Restauracion</h4>
                                <p class="text-center">Disponemos de restaurante propio, con zona para desayunos, comidas, cenas y cafeteria con terraza exterior donde poder disfrutar y relajarse en un entorno agradable.</p>
                            </div>
                        </div>

                        <div class="card">
                            <img src="vendor/img/comida.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title text-center">Servicio a Cuartos</h4>
                                <p class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem, laudantium? Vel, ipsa esse mollitia!.</p>
                            </div>
                        </div>

                        <div class="card">
                            <img src="vendor/img/f3.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title text-center">Piscina</h4>
                                <p class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil, ipsam.</p>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
            <!-- fin servicios -->
            <div class="row mt-3">
                <div class="col-12 ">
                    <h3 class="titulo text-center">PLANES DE HABITACION<span class="icon-shop"></span></h3>
                    <hr>
                    <div class="card-group mt-3">
                        <div class="card">
                            <img src="vendor/img/cuarto1.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title">Basic Individual</h4>
                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt natus, odit eius </p>
                                <div class="contenedor">
                                    <a class="btn btn-outline-primary btn-sm " href="reserva.jsp?id=normal"><i class="icon-shopping-cart"></i>Reservar</a>
                                    <a class="btn btn-outline-warning btn-sm " href="reserva.jsp?id=normal"><i class="icon-info"></i>Detalle</a>	
                                </div>
                            </div>		
                        </div>
                        <div class="card">
                            <img src="vendor/img/cuarto2.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title">Doble/Twin(2 personas)</h4>
                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt natus, odit eius </p>
                                <div class="contenedor">
                                    <a class="btn btn-outline-primary btn-sm " href="reserva.jsp?id=doble"  ><i class="icon-shopping-cart"></i>Reservar</a>
                                    <a class="btn btn-outline-warning btn-sm " href="reserva.jsp?id=doble"  ><i class="icon-info"></i>Detalle</a>	
                                </div>	
                            </div>			
                        </div>
                        <div class="card">
                            <img src="vendor/img/f2.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title">Matrimonial</h4>
                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt natus, odit eius </p>
                                <div class="contenedor">
                                    <a class="btn btn-outline-primary btn-sm " href="reserva.jsp?id=matrimonial"  ><i class="icon-shopping-cart"></i>Reservar</a>
                                    <a class="btn btn-outline-warning btn-sm " href="reserva.jsp?id=matrimonial"  ><i class="icon-info"></i>Detalle</a>	
                                </div>
                            </div>		
                        </div>
                        <div class="card">
                            <img src="vendor/img/cuarto2.jpg" class="card-image-top img-fluid" alt="">
                            <div class="card-block">
                                <h4 class="card-title">Confort</h4>
                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nesciunt natus, odit eius </p>
                                <div class="contenedor">
                                    <a class="btn btn-outline-primary btn-sm " href="reserva.jsp?id=suit"   ><i class="icon-shopping-cart"></i>Reservar</a>
                                    <a class="btn btn-outline-warning btn-sm " href="reserva.jsp?id=suit"  ><i class="icon-info"></i>Detalle</a>	
                                </div>

                            </div>		
                        </div>
                    </div>		

                </div>

            </div>
            
        </div>
        <section class="logo">
                <div class="row ">
                        <div class="col-auto header_titulo">
                                <h5>lorem ggg dolor</h5>
                                <p class="">Lorem ipsum dolor sit amet, consectetur.</p>
                                <p class="">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magni voluptatum et, alias vero, reprehenderit doloremque neque dignissimos voluptas, quam placeat ipsum esse nesciunt sapiente. Veritatis corporis quisquam veniam, expedita laborum!</p>
                                <div class="d-flex justify-content-center">
                                        <button class="btn btn-outline-primary ">ver</button>
                                    <button class="btn btn-outline-primary ">info</button>
                                </div>

                        </div>
                        <div class="logo_imagen"></div>
                </div>
            </section>
        <!-- main -->




        <%@include  file="inc/footer.jsp" %>


        <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>
    </body>
</html>
