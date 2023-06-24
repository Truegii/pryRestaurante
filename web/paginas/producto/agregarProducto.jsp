<%-- 
    Document   : agregarProducto
    Created on : 26-may-2023, 23:52:49
    Author     : Usuario
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    Usuario usu = (Usuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registra Producto</title>
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
    </head>
    <body style="font-size: 22px; font-family: Roboto;">
        <nav class="navbar navbar-expand-lg bg-white navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="../../index.html"><img style="width: 170px;" id="logo" src="../../img/logorest.jpg" alt="alt"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../../index.html">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../Reserva.jsp">RESERVAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../../ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../Login.jsp">CERRAR SESION</a>
                        </li>
                        <%
                            if (usu.getNombre().equals("admin@nagoya.com")) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="../admin.jsp">ADMIN</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>
        <script>
            function validaProd() {
                let nom = document.frmProd.pronom.value;
                let pre = document.frmProd.propre.value;
                let tip = document.frmProd.protipo.value;
                let imag = document.frmProd.proimg.value;
                if (nom == 0) {
                    alert("El nombre del producto es necesario");
                    return 0;
                } else if (pre == 0) {
                    alert("El precio es obligatorio");
                    return 0;
                } else if (tip == 0) {
                    alert("El tipo es obligatorio");
                    return 0;
                } else if (imag == 0) {
                    alert("La imagen es obligatoria");
                    return 0;
                } else {
                    alert("Producto agregado con exito");
                    document.frmProd.submit();
                }
            }
        </script>
        <div class="container">
            <div class="abs-center">

                <form name="frmProd" class="form" method="POST" action="../../ProductoConexion">
                    <div class="row g-3">
                        <div>
                            <h1>
                                Agregar Producto
                            </h1>
                            <br>
                        </div>
                        <div class="col-md-5 form-floating mb-2">
                            <input type="text" class="form-control bg-dark-subtle" id="floatingInput" name="pronom">
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="col-md-7 form-floating mb-2">
                            <input type="number" class="form-control bg-dark-subtle" id="floatingInput" name="propre">
                            <label for="floatingInput">Precio</label>
                        </div>
                        <div class="form-floating">
                            <select class="form-select" id="floatingSelect" aria-label="Floating label select example" name="protipo">
                                <option selected>---Seleccionar---</option>
                                <option value="Ceviche">Ceviches</option>
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

                        <div class="input-group mb-3">
                            <input type="file" class="form-control bg-dark-subtle" id="inputGroupFile02" name="proimg">
                            <label class="input-group-text" for="inputGroupFile02">Imagen</label>
                        </div>
                        <br>

                        <div class="d-grid gap-2">
                            <input value="Agregar" type="button" onclick="validaProd()" class="btn btn-outline-dark" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
