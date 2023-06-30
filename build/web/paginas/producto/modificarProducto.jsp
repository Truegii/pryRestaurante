<%@page import="modelo.Usuario"%>
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
        <title>Modifica Producto</title>
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
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
                            <a class="nav-link" href="ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="paginas/Login.jsp">CERRAR SESION</a>
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
                </div>
            </div>
        </nav>
        <%
            Producto prod = (Producto) request.getAttribute("product");

        %>
        <div class="container">
            <div class="abs-center">

                <form class="form" method="GET" action="ProductoListar">
                    <div class="row g-3">
                        <div>
                            <h1>
                                Modificar Producto
                            </h1>
                            <br>
                        </div>
                        <div class="col-md-5 form-floating mb-2">
                            <input type="text" class="form-control bg-dark-subtle" id="floatingInput" name="pronom" value="<%=prod.getPronom()%>">
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="col-md-7 form-floating mb-2">
                            <input type="number" class="form-control bg-dark-subtle"  id="floatingInput" name="propre" value=<%=prod.getPropre()%>>
                            <label for="floatingInput">Precio</label>
                        </div>
                        <div class="form-floating">
                            <select class="form-select" id="floatingSelect" aria-label="Floating label select example" name="protipo">
                                <option selected><%=prod.getProtipo()%></option>
                                <option value="Ceviches">Ceviches</option>
                                <option value="Piqueos">Piqueos</option>
                                <option value="Makis">Makis</option>
                                <option value="Tradicionales">Tradicionales</option>
                                <option value="Sopas">Sopas</option>
                                <option value="Entradas">Entradas</option>
                                <option value="Mariscos">Mariscos</option>
                                <option value="Postres">Postres</option>
                            </select>
                            <label for="floatingSelect">Tipo</label>
                        </div>

                        <input name="filtro" type="hidden" value="actualizar"/>
                        <input name="codigo" type="hidden" value="<%=prod.getProcod()%>"/>
                        <br>
                        <div class="d-grid gap-2">
                            <input type="submit" class="btn btn-outline-dark" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
