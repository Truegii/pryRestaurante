package servlets;

import controlador.AccesoDatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateUsuario", urlPatterns = {"/UpdateUsuario"})
public class UpdateUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //Trae los parametros del formulario
        String correo = request.getParameter("txtNom");
        String pass = request.getParameter("txtCla");
        try {
            AccesoDatos ad = new AccesoDatos();
            //Actualiza la contraseña de un usuario, segun su correo
            ad.updateUsuario(correo, pass);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        //Redirige a Login
        response.sendRedirect("paginas/Login.jsp");

    }

}
