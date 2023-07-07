package servlets;

import controlador.DAOProducto;
import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "ProductoConexion", urlPatterns = {"/ProductoConexion"})
public class ProductoConexion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(); //Recupera la sesion
        try (PrintWriter out = response.getWriter()) {
            //Trae los parametros del formulario
            String pronom = request.getParameter("pronom");
            String proimg = request.getParameter("proimg");
            double propre = Double.parseDouble(request.getParameter("propre"));
            String protipo = request.getParameter("protipo");
            //Registra el producto en la BD
            DAOProducto pro = new DAOProducto();
            String procod = pro.obtenerCodigo();
            pro.registrarProducto(procod, pronom, proimg, propre, protipo);

//            Redirige a admin
            response.sendRedirect("paginas/admin.jsp");
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

}
