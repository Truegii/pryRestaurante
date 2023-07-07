<%-- 
    Document   : listarreserva
    Created on : 09-jun-2023, 14:24:17
    Author     : Usuario
--%>

<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Reservas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    Usuario usu = (Usuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Reservas</title>
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
            <a href="ReservaListar?filtro=exportar" class="btn btn-secondary">Exportar PDF</a>
            <br><br>
            <section class="container">
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Código</th>
                            <th scope="col">Usuario</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Personas</th>
                            <th scope="col">Actividad</th>
                            <th scope="col">PDF</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            List<Reservas> listRs = (List<Reservas>) request.getAttribute("listaReserva");
                            for (Reservas p : listRs) {
                        %>
                        <tr>
                            <th scope="row"><%out.print(p.getIdreserva());%></th>
                            <td><%out.print(p.getCorreo());%></td>
                            <td><%out.print(p.getFecha());%></td>
                            <td><%out.print(p.getCantidad());%></td>
                            <td><%out.print(p.getActividad());%></td>
                            <td><a href="ReservaListar?filtro=pdfdetalle&idres=<%out.print(p.getIdreserva());%>" class="btn btn-warning">Descargar</a></td>
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
