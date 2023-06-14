<%-- 
    Document   : dashboard
    Created on : 18-sep-2018, 18:45:28
    Author     : kings
--%>


<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>JSP Page</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        
        <link rel="stylesheet" href="vendor/css/dashboard.css">
        <link rel="stylesheet" href="vendor/css/fonts.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <%@include file="inc/menuDashboard.jsp" %>
                <main class="main col">
                    <div class="row">
                        <div class="columna col-lg-7">
                            <div class="widget nueva_entrada">
                                <h3 class="titulo">Nueva Entrada</h3>
                                <form action="">
                                    <input type="text" placeholder="Titulo de la entrada">
                                    <textarea placeholder="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."></textarea>
                                    <div class="d-flex justify-content-end">
                                        <button><i class="icon icon-edit"></i> Enviar</button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="columna col-lg-5">
                            <div class="widget estadisticas">
                                <h3 class="titulo">Estadisticas</h3>
                                <div class="contenedor d-flex flex-wrap">
                                    <div class="caja">
                                        <h3>15,236</h3>
                                        <p>Visitas</p>
                                    </div>
                                    <div class="caja">
                                        <h3>1,831</h3>
                                        <p>Registros</p>
                                    </div>
                                    <div class="caja">
                                        <h3>$160,548</h3>
                                        <p>Ingresos</p>
                                    </div>
                                </div>
                            </div>

                            <div class="widget comentarios">
                                <h3 class="titulo">Comentarios</h3>
                                <div class="contenedor">
                                    <div class="comentario d-flex flex-wrap">
                                        <div class="foto">
                                            <a href="#">
                                                <img src="img/persona1.jpg" width="100" alt="">
                                            </a>
                                        </div>
                                        <div class="texto">
                                            <a href="#">Jhon Doe</a>
                                            <p>en <a href="#">Mi primer entrada</a></p>
                                            <p class="texto-comentario">
                                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis natus ex inventore provident modi id distinctio non minus, magni quia officiis, vel debitis doloremque ratione, consequuntur omnis hic voluptatem asperiores?
                                            </p>
                                        </div>
                                        <div class="botones d-flex justify-content-start flex-wrap w-100">
                                            <button class="aprobar"><i class="icono icon-ok"></i>Aprobar</button>
                                            <button class="eliminar"><i class="icono icon-cancel"></i>Eliminar</button>
                                            <button class="bloquear"><i class="icono icon-flag"></i>Bloquear Usuario</button>
                                        </div>
                                    </div>  

                                    <div class="comentario d-flex flex-wrap">
                                        <div class="foto">
                                            <a href="#">
                                                <img src="img/persona2.jpg" width="100" alt="">
                                            </a>
                                        </div>
                                        <div class="texto">
                                            <a href="#">Jhon Doe</a>
                                            <p>en <a href="#">Mi primer entrada</a></p>
                                            <p class="texto-comentario">
                                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis natus ex inventore provident modi id distinctio non minus, magni quia officiis, vel debitis doloremque ratione, consequuntur omnis hic voluptatem asperiores?
                                            </p>
                                        </div>
                                        <div class="botones d-flex justify-content-start flex-wrap w-100">
                                            <button class="aprobar"><i class="icono icon-ok"></i>Aprobar</button>
                                            <button class="eliminar"><i class="icono icon-cancel"></i>Eliminar</button>
                                            <button class="bloquear"><i class="icono icon-flag"></i>Bloquear Usuario</button>
                                        </div>
                                    </div>

                                    <div class="comentario d-flex flex-wrap">
                                        <div class="foto">
                                            <a href="#">
                                                <img src="img/persona3.jpg" width="100" alt="">
                                            </a>
                                        </div>
                                        <div class="texto">
                                            <a href="#">Jhon Doe</a>
                                            <p>en <a href="#">Mi primer entrada</a></p>
                                            <p class="texto-comentario">
                                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis natus ex inventore provident modi id distinctio non minus, magni quia officiis, vel debitis doloremque ratione, consequuntur omnis hic voluptatem asperiores?
                                            </p>
                                        </div>
                                        <div class="botones d-flex justify-content-start flex-wrap w-100">
                                            <button class="aprobar"><i class="icono icon-ok"></i>Aprobar</button>
                                            <button class="eliminar"><i class="icono icon-cancel"></i>Eliminar</button>
                                            <button class="bloquear"><i class="icono icon-flag"></i>Bloquear Usuario</button>
                                        </div>
                                    </div>        
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
