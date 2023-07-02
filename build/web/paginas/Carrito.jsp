
<%@page import="controlador.DAOProducto"%>
<%@page import="servlets.AgregaCarrito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Articulo"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(false);
    Usuario usu = (Usuario) sesion.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
        <title>Carrito</title>
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
                <a class="navbar-brand" href="../index.html"><img style="width: 170px;" id="logo" src="../img/logorest.jpg" alt="alt"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="../index.html">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Reserva.jsp">RESERVAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="../ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li>
                            <a class="nav-link" href="../FacturaListar?filtro=usuario">FACTURAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../CerrarSesion">CERRAR SESION</a>
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
                    <div class="nav-item d-flex">
                        <span class="nav-link"><%out.println(usu.getNombre());
                            %></span>

                    </div>
                </div>
            </div>
        </nav>
        <script>
            function mostrarAlerta() {
                alert("Pago realizado con exito");
            }
        </script>
        <main>
            <section class="container">

                <form action="pagar.jsp" method="POST">
                    <h1 align="center" style="color: black;">CARRITO</h1>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">DESCRIPCION</th>
                                <th scope="col">PRECIO</th>
                                <th scope="col">CANTIDAD</th>
                                <th scope="col">TOTAL</th>
                                <th scope="col">ELIMINAR</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <%
                                ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
                                AgregaCarrito ac = new AgregaCarrito();
                                DAOProducto daop = new DAOProducto();
                                int contador = 0;
                                int total = 0;
                                if (articulos != null) {
                                    for (Articulo a : articulos) {
                                        Producto producto = daop.buscaProducto(a.getCodigoProducto());
                                        total += a.getCantidad() * producto.getPropre();
                                        contador++;


                            %>


                            <tr>
                                <th scope="row"><%=contador%></th>
                                <td><%=producto.getPronom()%></td>
                                <td><%=producto.getPropre()%></td>
                                <td><%=a.getCantidad()%></td>
                                <td><%=Math.round(producto.getPropre() * a.getCantidad())%></td>
                                <td>
                                    <a href="../BorrarCarrito?filtro=DeleteOne&id=<%=contador%>" class="btn btn-danger">Eliminar</a>
                                </td>
                            </tr>

                            <% }
                            } else {%>
                        <h1 align="center"> No hay articulos en el carro</h1>


                        <%}
                            sesion.setAttribute("carrito", articulos);%>
                        <tr id="thcarro" align="center">
                            <td colspan="6">
                                <a>El valor total de su compra es: S/.<%= total%></a>

                            </td>

                        </tr>
                        <tr>
                            <td colspan="5"></td>
                            <td>
                                <a class="btn btn-primary" href="../BorrarCarrito?filtro=ReDo"> REDO</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="hidden" value="<%=total%>" name="monto"/>
                    <br/>
                    <div class="container  position-relative">

                        <a class="btn btn-dark position-absolute top-0 start-0" href="../ProductoListar?filtro=todos">Seguir comprando</a>

                        <input type="submit" class="btn btn-primary position-absolute top-0 start-50" value="Pagar tarjeta"></input>

                    </div>
                </form>

                <form action="../AgregaFactura" method="POST">
                    <%
                        sesion.setAttribute("carrito", articulos);
                    %>
                    <input type="hidden" name="metpago" value="Efectivo">
                    <input type="hidden" name="direccion" value="Local" />
                    <div class="container  position-relative mt-5">
                        <input onclick="mostrarAlerta()" type="submit" class="btn btn-primary position-absolute top-0 start-50" value="Pagar efectivo"></input>
                    </div>
                </form>

                <a href="../BorrarCarrito?filtro=DeleteAll" class="btn btn-danger">Vaciar Carrito</a>

                <div class="mx-auto p-2">
                    <br><h2 class="mt-5">Selecciona tus métodos de pago:</h2>
                    <img width="30%" src="../img/metodospago.png" alt="alt"/>
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
                        <a class="link-dark text-decoration-none me-3" href="Reclamaciones.jsp">Libro de Reclamaciones</a>
                        <a class="link-dark text-decoration-none" href="#">Terminos de uso</a>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>