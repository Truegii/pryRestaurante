

<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();

    Usuario usu = (Usuario) sesion.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
        <title>Carta</title>
        <style>
            .footer {
                text-align: center;
                font-size: 0.9rem;
                font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            }
        </style>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body style="font-size: 22px; font-family: Roboto;">
        <nav class="navbar navbar-expand-lg bg-white navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.html"><img style="width: 170px;" id="logo" src="img/logorest.jpg" alt="alt"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="index.html">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="paginas/Reserva.jsp">RESERVAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="#">CARTA</a>
                        </li>
                        <li>
                            <a class="nav-link" href="FacturaListar?filtro=usuario">FACTURAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="paginas/Carrito.jsp">CARRITO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CerrarSesion">CERRAR SESION</a>
                        </li>
                        <%
                            if (usu.getNombre().equals("admin@nagoya.com")) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="paginas/admin.jsp">ADMIN</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                    <div class="nav-item d-flex">
                        <span class="nav-link"><%out.println(usu.getNombre());
                            %></span>

                    </div>
                </div>
            </div>
        </nav>

        <main class="section_all">
            <section class="container">

                <div class="container-productos">
                    <h1 align="center" style="color: black;">PRODUCTOS</h1>

                    <h1>Tipo:</h1>
                    <form action="ProductoListar" method="get" class="carrito-item">
                        <select name="filtro" onchange="this.form.submit()" class="form-select" style="width: 40%; text-align: center">
                            <option value="todos">Seleccionar</option>
                            <option value="todos">Mostrar todos</option>
                            <option value="Ceviche">Ceviches</option>
                            <option value="Piqueos">Piqueos</option>
                            <option value="Makis">Makis</option>
                            <option value="Tradicionales">Tradicionales</option>
                            <option value="Sopas">Sopas</option>
                            <option value="Entradas">Entradas</option>
                            <option value="Mariscos">Mariscos</option>
                            <option value="Postres">Postres</option>
                        </select>
                    </form>


                    <div class="productos mt-5">
                        <%
                            List<Producto> listPro = (List<Producto>) request.getAttribute("listaProd");
                            for (int i = 0; i < listPro.size(); i++) {
                                Producto p = listPro.get(i);
                        %>
                        <div class="card mb-4 m-2 mx-4 bg-transparent border-0" style="max-width: 500px;">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img style="max-width: 140px;" src="img/<%out.print(p.getProimg());%>" class="img-fluid rounded-circle border border-5 border-dark" alt="...">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-text"><%out.print(p.getProtipo());%><small class="text-muted float-end">S/. <%out.print(p.getPropre());%></small></h5>

                                        <p class="card-text"></p>

                                        <p class="card-text"><%out.print(p.getPronom());%></p>
                                        <input type="hidden" name="codigoProducto" value="<%= p.getProcod()%>"/>
                                        <div class="position-absolute bottom-0 end-0"><button type="button" id="añadir" onclick="window.location = 'paginas/AgregaCarrito.jsp?id=<%=p.getProcod()%>'" class="btn btn-primary">Agregar</button></div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <%
                            }
                        %>
                    </div>

                </div>
            </section>
        </main>
        <footer class="footer py-4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start">Copyright &copy; Nagoya Perú 2023</div>
                    <div class="col-lg-4 my-3 my-lg-0">
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="LinkedIn"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <div class="col-lg-4 text-lg-end">
                        <a class="link-dark text-decoration-none me-3" href="paginas/Reclamaciones.jsp">Libro de Reclamaciones</a>
                        <a class="link-dark text-decoration-none" href="#">Terminos de uso</a>
                    </div>
                </div>
            </div>
        </footer>

    </body>
</html>
