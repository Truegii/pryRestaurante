<%-- 
    Document   : pagar
    Created on : 07-jun-2023, 20:59:09
    Author     : Usuario
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Articulo"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
    Usuario usu = (Usuario) sesion.getAttribute("usuario");
    int montototal = Integer.parseInt(request.getParameter("monto"));
    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagar</title>
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
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
                            <a class="nav-link" href="Reserva.jsp">RESERVAS</a>
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
                        <%
                            if (usu.getNombre().equals("admin@nagoya.com")) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="admin.jsp">ADMIN</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>
        <script>
            function validaPago() {
                let direc = document.frmPag.direccion.value;
                let nrotar = document.frmPag.nrotar.value;
                let cvv = document.frmPag.cvv.value;
                let fechavenc = document.frmPag.fecvenc.value;
                let nomtit = document.frmPag.nomtitular.value;
                let apetit = document.frmPag.apetitular.value;
                if (direc == 0) {
                    alert("La direccion es necesario");
                    return 0;
                } else if (nrotar == 0) {
                    alert("El número de tarjeta es obligatorio");
                    return 0;
                } else if (cvv == 0) {
                    alert("El CVV es obligatorio");
                    return 0;
                } else if (nomtit == 0) {
                    alert("El nombre es necesario");
                    return 0;
                } else if (apetit == 0) {
                    alert("El apellido es necesario");
                    return 0;
                } else if (fechavenc == 0) {
                    alert("La fecha de vencimiento es obligatoria");
                    return 0;
                } else {
                    alert("Pago realizado con exito");
                    document.frmPag.submit();
                }

            }
        </script>
        <main>
            <section class="mt-5 mb-3">
                <div class="d-grid gap-2 col-9 mx-auto">

                    <form name="frmPag" action="../AgregaFactura" method="POST">

                        <div class="card bg-light border-secondary mx-auto" style="width:100%;">
                            <div class="card-body">
                                <h5 class="text-center text-dark">Monto a pagar</h5>
                                <h3 class="text-dark text-center ">S/. <%=montototal%></h3>
                            </div>
                        </div>

                        <div class="card bg-light border-secondary mx-auto mt-4" style="width:100%;">
                            <div class="card-body mx-auto">
                                <h3 class="text-center text-dark">Paga seguro con:</h3>
                                <img src="../img/visa.png" width="110" alt="alt"/>
                            </div>
                        </div>

                        <div class="card bg-light border-secondary mx-auto mt-4" style="width:100%;">
                            <div class="card-body">
                                <div class="input-group">
                                    <i class="fa fa-envelope fa-2x "></i>
                                    <input name="correo" class=" form-control border-0 bg-transparent text-center text-dark" value="<%out.println(usu.getNombre());%>">
                                </div>
                            </div>
                        </div>

                        <div class="card bg-light border-secondary mx-auto mt-4" style="width:100%;">
                            <div class="card-body">
                                <div class="input-group">
                                    <i class="fa fa-map-marker fa-2x"></i>
                                    <input name="direccion" type="text" value="" class="form-control ms-1 rounded" placeholder="Dirección">
                                </div>
                            </div>
                        </div>

                        <div class="card bg-light border-secondary mx-auto mt-4" style="width:100%;">
                            <div class="card-body ">
                                <p class="text-center text-dark">Recuerda activar las compras por internet con tu banco.</p>
                                <br/>
                                <div class="input-group">
                                    <i class="fa fa-credit-card fa-2x "></i>
                                    <input type="number" class="form-control ms-1 rounded" name="nrotar" placeholder="Número de tarjeta">
                                </div>
                                <div class="input-group mt-2">
                                    <i class="fa fa-calendar fa-2x "></i>
                                    <input type="month" value="" class="form-control ms-1 rounded" name="fecvenc" placeholder="MM/AA">
                                    <i class="fa fa-credit-card fa-2x  ms-1"></i>
                                    <input type="number" class="form-control ms-1 rounded" name="cvv" placeholder="CVV">
                                </div>
                                <div class="input-group mt-2">
                                    <i class="fa fa-user fa-2x"></i>
                                    <input type="text" class="form-control ms-1 rounded" name="nomtitular" placeholder="Nombre">
                                    <i class="fa fa-user fa-2x ms-1"></i>
                                    <input type="text" class="form-control ms-1 rounded" name="apetitular" placeholder="Apellidos">
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="metpago" value="Tarjeta"/>
                        <input type="hidden" name="idtar" value="">

                        <%
                            sesion.setAttribute("carrito", articulos);
                        %>
                        <br/>
                        <div style="width: 18rem;" class="d-grid gap-2 mt-4 mx-auto" >
                            <input type="button" onclick="validaPago()" class="btn btn-outline-primary" value="Pagar"/>                    
                        </div>

                    </form>

                </div>

            </section>

        </main>
    </body>
</html>
