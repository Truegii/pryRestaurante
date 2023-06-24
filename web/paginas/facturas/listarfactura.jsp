<%-- 
    Document   : listarfactura
    Created on : 08-jun-2023, 14:00:25
    Author     : Usuario
--%>

<%@page import="modelo.Usuario"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Detalle"%>
<%@page import="modelo.Factura"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    Usuario usu = (Usuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Facturas</title>
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
                <div class="nav-item d-flex">
                    <span class="nav-link"><%out.println(usu.getNombre());
                        %></span>

                </div>
            </div>
        </nav>
        <main class="section_all container">
            <%
                            if (usu.getNombre().equals("admin@nagoya.com")) {
                        %>
            <a href="FacturaListar?filtro=exportar" class="btn btn-secondary">Exportar PDF</a>
            <%
                            }
                        %>
            <br>
            <br>
            <section class="container">
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Nro Factura</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Metodo Pago</th>
                            <th scope="col">Direccion</th>
                            <th scope="col">Total</th>
                            <th scope="col">PDF</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Factura> listFc = (List<Factura>) request.getAttribute("listaFcabe");
                            for (Factura p : listFc) {
                                int idx = 0;
                        %> 
                        <tr>
                            <th scope="row"><%out.print(p.getFacnum());%></th>
                            <td><%out.print(p.getCorusu());%></td>
                            <td><%out.print(p.getFacfec());%></td>
                            <td><%out.print(p.getMetpago());%></td>
                            <td><%out.print(p.getDirec());%></td>
                            <td><%out.print(p.getFacimp());%></td>
                            <td><a href="FacturaListar?filtro=pdfdetalle&idfac=<%out.print(p.getFacnum());%>" class="btn btn-warning">Descargar</a></td>
                        </tr>
                        <tr>
                            <td colspan="7">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col"><small>Pedido</small></th>
                                            <th scope="col"><small>Cantidad<small></th>
                                                        <th scope="col"><small>Importe</small></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody class="table-group-divider">
                                                            <%
                                                                List<Detalle> listFd = (List<Detalle>) request.getAttribute("listaFdeta");
                                                                List<Producto> listPro = (List<Producto>) request.getAttribute("listPro");
                                                                for (Detalle o : listFd) {

                                                                    if (o.getFacnum() == p.getFacnum()) {
                                                                        idx++;
                                                            %>
                                                            <tr>
                                                                <th scope="row"><small><%out.print(idx);%></small></th>
                                                                <td><small><%for (Producto d : listPro) {
                                                                        if (d.getProcod().equals(o.getProcod())) {
                                                                            out.print(d.getPronom());
                                                                        }
                                                                    }

                                                                        %></small></td>
                                                                <td><small><%out.print(o.getCant());%></small></td>
                                                                <td><small><%out.print(o.getFacpre());%></small></td>
                                                            </tr>
                                                            <%
                                                                    }

                                                                }
                                                            %>
                                                            </table>
                                                            </td>
                                                            </tr>
                                                            <%
                                                                }
                                                            %>



                                                        </tbody>
                                                        </table>



                                                        </section>

                                                        </main>


                                                        </body>
                                                        </html>
