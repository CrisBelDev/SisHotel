<%-- 
    Document   : login
    Created on : 18-sep-2018, 18:28:23
    Author     : kings
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="vendor/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/css/estilos.css">
        <link rel="stylesheet" href="vendor/css/login.css">
    </head>
    <%@include  file="inc/menu.jsp"%>

    <body background="vendor/img/login.jpg">

        <div class="container estatico">
            <div class="row mt-5 justify-content-center">
                <div class="col-12 col-sm-7 col-md-4  mt-5">
                    <div class="loginbox">
                        <img src="vendor/img/img-1.jpg" class="avatar">
                        <h1>Login here</h1>
                        <form action="">
                            <p>User name</p>
                            <input type="text" placeholder="Enter Username">
                            <p>Passsword</p>
                            <input type="password" name="" placeholder="Enter password">
                            <a href="dashboard.jsp"  class="boton">Login</a>
                            <a href="#">Lost you Password?</a>
                            <a href="#">Dont't have an account?</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%@include  file="inc/footer.jsp"%>
        <script src="vendor/js/jquery-3.3.1.min.js"></script>
        <script src="vendor/js/tether.min.js"></script>
        <script src="vendor/js/bootstrap.min.js"></script>

    </body>
</html>
