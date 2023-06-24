package servlets;

import controlador.AccesoDatos;
import controlador.DAOProducto;
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


@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
public class RegistrarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            //Trae los parametros del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String materno = request.getParameter("materno");
            String dni = request.getParameter("dni");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            //Obtiene el ultimo codigo de usuario
            AccesoDatos adc = new AccesoDatos();
            String codigo = adc.obtenerCodigo();
            //Registra el usuario en la BD
            adc.registrarUsuario(codigo, nombre, apellido,materno, pass, dni, email);
            //Redirige a index
            RequestDispatcher rd2 = request.getRequestDispatcher("index.html");
            rd2.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
