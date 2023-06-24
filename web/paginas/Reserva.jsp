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
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
        <title>Reserva</title>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            .footer {
                text-align: center;
                font-size: 0.9rem;
                font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            }
        </style>
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
                            <a class="nav-link" href="#">RESERVAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../ReservaListar?filtro=usuario">MIS RESERVACIONES</a>
                        </li>
                        <li>
                            <a class="nav-link" href="FacturaListar?filtro=usuario">FACTURAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="paginas/Carrito.jsp">CARRITO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="Login.jsp">INICIAR SESION</a>
                        </li>

                    </ul>
                </div>
                <div class="nav-item d-flex">
                    <span class="nav-link"><%out.println(usu.getNombre());
                        %></span>

                </div>
            </div>
        </nav>
        <script>
            function mostrarAlerta() {
                let corr = document.frmLog.txtCor.value;
                let fecha = document.frmLog.txtFecha.value;
                let activ = document.frmLog.actividad.value;
                let person = document.frmLog.txtPersona.value;
                if (corr == 0) {
                    alert("El correo es necesario");
                    return 0;
                } else if (fecha == 0) {
                    alert("La fecha es obligatoria");
                    return 0;
                } else if (activ == 0) {
                    alert("La actividad es obligatoria");
                    return 0;
                } else if (person == 0) {
                    alert("La cantidad de personas es obligatoria");
                    return 0;
                } else {
                    alert("Reserva realizada con exito");
                    document.frmLog.submit();
                }
            }
        </script>
        <section class="mb-5 mt-5"> 
            <div align="center">
                <section class="card" style="width: 25rem;">

                    <form name="frmLog" action="../AgregaReserva" method="POST" class="card-body">
                        <h4 class="mb-5" style="text-align: center;"><b>RESERVACION</b></h4>
                        <div class="texto-reclamos">
                            <label for="txtCor">Correo de usuario</label>
                            <input id="correo" class="form-control" type="text" value="<% if (usu == null) {
                                    out.println("");
                                } else {
                                    out.println(usu.getNombre());
                                }
                                   %>" name="txtCor" placeholder="Usuario@gmail.com"/><br>	
                            <label for="txtFecha">Fecha</label>
                            <input id="fecha" class="form-control" type="datetime-local" name="txtFecha" placeholder="dd-MM-YYYY"/><br>
                            <label for="txtPersonas">Cantidad de personas</label>
                            <input id="cantPer" class="form-control" type="text" name="txtPersona"><br>


                            <div class="form-floating">
                                <select class="form-select" id="floatingSelect" aria-label="Floating label select example" name="actividad">
                                    <option selected>---Seleccionar---</option>
                                    <option value="Cumpleanos">Cumpleaños</option>
                                    <option value="Dia festivo">Dia festivo</option>
                                    <option value="Pedida de mano">Pedida de mano</option>
                                    <option value="Ocasion especial">Ocasión especial</option>
                                </select>
                                <label for="floatingSelect">Actividad</label>
                            </div>
                            <input onclick="mostrarAlerta()" class="btn btn-secondary mb-2 mt-3" type="button" name="aceptar" value="   Aceptar   ">
                            <br/>
                            <p>Las reservaciones tienen una tarifa de 50 a 200 soles dependiendo la actividad.</p>
                            <p>Dependiendo el tipo de evento, se brindará una sala de eventos o una mesa reservada. La tarifa deberá ser cancelada al momento de pagar el consumo dentro del local.</p>
                            <a style="font-size: 17px;" href="#">¿Preguntas sobre las reservaciones? Ingresa aquì</a>
                        </div>
                    </form>
                </section>
            </div>
        </section>

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
