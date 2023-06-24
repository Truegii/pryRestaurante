
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    Usuario usu = (Usuario) sesion.getAttribute("usuario");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
        <title>Admin</title>
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
                            <a class="nav-link" href="Reserva.jsp">RESERVAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li>
                            <a class="nav-link" href="../FacturaListar?filtro=usuario">FACTURAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../CerrarSesion">CERRAR SESION</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Carrito.jsp">CARRITO</a>
                        </li>
                    </ul>


                    <div class="nav-item d-flex">
                        <span class="nav-link"><% if (usu == null) {
                                request.getRequestDispatcher("index.html").forward(request, response);
                            } else {
                                out.println(usu.getNombre());
                            }
                            %></span>

                    </div>

                </div>
            </div>
        </nav>
        <main>
            <section class="container">
                <div style="font-size: 22px;" class="card bg-dark bg-gradient border rounded-pill mb-3">
                    <div class="card-body text-body-light">
                        <span class="text-white">Agregar Productos</span>
                    </div>
                    <button onclick="window.location.href = 'producto/agregarProducto.jsp'" class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                </div>

                <div style="font-size: 22px;" class="card bg-dark bg-gradient border rounded-pill mb-3">
                    <div class="card-body text-body-light">
                        <span class="text-white">Listar Productos</span>
                    </div>
                    <button onclick="window.location.href = '../ProductoListar?filtro=crud'" class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                </div>

                <div style="font-size: 22px;" class="card bg-dark bg-gradient border rounded-pill mb-2">
                    <div class="card-body text-body-light">
                        <span class="text-white">Listar Facturas</span>
                    </div>
                    <button onclick="window.location.href = '../FacturaListar?filtro=todos'" class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                </div>

                <div style="font-size: 22px;" class="card bg-dark bg-gradient border rounded-pill mb-2">
                    <div class="card-body text-body-light">
                        <span class="text-white">Listar Reservas</span>
                    </div>
                    <button onclick="window.location.href = '../ReservaListar?filtro=todos'" class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                </div>
                <div style="font-size: 22px;" class="card bg-dark bg-gradient border rounded-pill mb-2">
                    <div class="card-body text-body-light">
                        <span class="text-white">Listar Reclamos</span>
                    </div>
                    <button onclick="window.location.href = '../ReclamosListar'" class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                </div>
                <br>
                <div class="d-grid gap-2 mb-5 bg-light">
                    <a style="font-size: 22px;" class="btn btn-outline-dark " href="Login.jsp">Cerrar sesión</a>
                </div>

            </section>

        </main>
    </body>
</html>
