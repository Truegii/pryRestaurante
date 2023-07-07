package servlets;

import controlador.DAOReserva;
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

@WebServlet(name = "AgregaReserva", urlPatterns = {"/AgregaReserva"})
public class AgregaReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //Trae los parametros del formulario
        String correo = request.getParameter("txtCor");
        String fecha = request.getParameter("txtFecha");
        int cantidad = Integer.parseInt(request.getParameter("txtPersona"));
        String actividad = request.getParameter("actividad");

        DAOReserva daore = new DAOReserva();
        //Registra la reserva en la BD
        try {
            daore.registrarReserva(daore.obtenerCodigo(), correo, fecha, cantidad, actividad);
        } catch (SQLException ex) {
            Logger.getLogger(AgregaReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.forward(request, response);

    }

}
