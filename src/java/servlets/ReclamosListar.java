package servlets;

import controlador.DAOReclamos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Reclamos;
import modelo.Reservas;
import modelo.Usuario;

@WebServlet(name = "ReclamosListar", urlPatterns = {"/ReclamosListar"})
public class ReclamosListar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            DAOReclamos daorec = new DAOReclamos();
            List<Reclamos> listRec = daorec.obtenerReclamo();
            //Establece la lista de reservas como atributo
            request.setAttribute("listaReclamos", listRec);
            //La redirige a Listar Reservas
            RequestDispatcher rd = request.getRequestDispatcher("paginas/reclamo/listareclamos.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamosListar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
}
