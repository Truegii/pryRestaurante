<%-- 
    Document   : AgregaCarrito
    Created on : 07-jun-2023, 18:34:26
    Author     : Usuario
--%>

<%@page import="modelo.Usuario"%>
<%@page import="controlador.DAOProducto"%>
<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Producto p;
    String idpro= request.getParameter("id");
    DAOProducto daop = new DAOProducto();
    p = daop.buscaProducto(idpro);
    HttpSession sesion = request.getSession(false);
    Usuario usu = (Usuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Carrito</title>
        <link rel="stylesheet" href="../styles/style.css"/>
        <style>
            .footer {
                text-align: center;
                font-size: 0.9rem;
                font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            }
        </style>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
    </head>
    <body style="font-size: 22px; font-family: Roboto;">
        <nav class="navbar navbar-expand-lg bg-white navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="../index.html"><img style="width: 170px;" id="logo" src="../img/logorest.jpg" alt="alt"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../index.html">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">RESERVAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li>
                            <a class="nav-link" href="../FacturaListar?filtro=usuario">FACTURAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Login.jsp">CERRAR SESION</a>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <script>
            function validarCant() {
                let canti = document.frmCar.txtCantidad.value;
                if (canti == 0) {
                    alert("Ingresa la cantidad");
                    return 0;
                } else {
                    document.frmCar.submit();
                }
            }
        </script>
        <section class="container">
            <div class="d-flex justify-content-center align-items-center">
                <form name="frmCar" action="../AgregaCarrito" method="POST">
                    <div class="card" style="width: 18rem;">
                        <img src="../img/<%=p.getProimg()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getPronom()%></h5>
                            <p class="card-text">S/. <%=p.getPropre()%></p>
                            <span>Cantidad: </span><input type="number" class="form-control" id="ic" name="txtCantidad">
                            <br/>
                            <input type="hidden" value="<%=p.getProcod()%>" name="codigoProducto"/>

                            <button type="button" onclick="validarCant()" class="btn btn-primary">Añadir</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <footer class="footer py-4 fixed-bottom">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start">Copyright &copy; Nagoya Perú 2023</div>
                    <div class="col-lg-4 my-3 my-lg-0">
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="LinkedIn"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <div class="col-lg-4 text-lg-end">
                        <a class="link-dark text-decoration-none me-3" href="Reclamaciones.jsp">Libro de Reclamaciones</a>
                        <a class="link-dark text-decoration-none" href="#">Terminos de uso</a>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
