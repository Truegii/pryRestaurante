

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../styles/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous"/>
        <style>
            .footer {
                text-align: center;
                font-size: 0.9rem;
                font-family: "Montserrat", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            }
        </style>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <title>Login</title>
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
                            <a class="nav-link" href="../ProductoListar?filtro=todos">CARTA</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="Login.jsp">INICIAR SESION</a>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <script>
            function ValidarLogin() {
                let corr = document.frmLog.txtNom.value;
                let pass = document.frmLog.txtCla.value;

                if (corr == 0) {
                    alert("El correo es necesario");
                    return 0;
                } else if (pass == 0) {
                    alert("La contraseña es obligatoria");
                    return 0;
                } else {
                    document.frmLog.submit();
                }
            }
        </script>
        <section class="mb-5 mt-5"> 
            <div align="center">
                <section class="card" style="width: 25rem;">

                    <form name="frmLog" action="../LoginConexion" method="POST" class="card-body">
                        <h4 class="mb-5" style="text-align: center;"><b>INICIAR SESIÓN</b></h4>
                        <div class="texto-reclamos">
                            <label for="txtNom">Correo de usuario</label>
                            <input id="cod" class="form-control" type="text" name="txtNom" placeholder="Usuario"><br>	
                            <label for="txtCla">Contraseña</label>
                            <input id="nombre" class="form-control" type="password" name="txtCla" placeholder="Contraseña"><br>
                            <input onclick="ValidarLogin()" class="btn btn-secondary mb-2" type="button" name="aceptar" value="   Aceptar   ">
                            <br/>
                            <a style="font-size: 17px;" href="Register.jsp">¿Aùn no tienes cuenta? Registrate aquì</a>
                            <a style="font-size: 17px;" href="Olvidar.jsp">¿Olvidaste tu contraseña?</a>
                        </div>
                    </form>
                </section>
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
