package servlets;

import controlador.DAOFactura;
import controlador.DAOProducto;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;
import modelo.Producto;
import modelo.Usuario;

@WebServlet(name = "AgregaFactura", urlPatterns = {"/AgregaFactura"})
public class AgregaFactura extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession sesion = request.getSession(); //Recupera la sesion
        Usuario usu = (Usuario) sesion.getAttribute("usuario"); //Trae objeto usuario de la sesion
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String fecha = (String) dtf4.format(LocalDateTime.now());
        ArrayList<Articulo> articulos = (ArrayList<Articulo>) sesion.getAttribute("carrito"); //Trae el list Carrito de la sesion
        String direccion = request.getParameter("direccion");
        String metodo = request.getParameter("metpago");
        DAOFactura daofac = new DAOFactura();

        double total = 0;

        try {
            for (Articulo a : articulos) {
                //Registra el detalle de la factura
                String codigoprod = a.getCodigoProducto();
                DAOProducto daop = new DAOProducto();
                Producto producto = daop.buscaProducto(codigoprod);
                daofac.registrarDetalle(daofac.obtenerCodigo(), a.getCodigoProducto(), a.getCantidad(), producto.getPropre());

                total += a.getCantidad() * producto.getPropre();
            }
            //Registra la cabecera de factura
            daofac.registrarFactura(daofac.obtenerCodigo(), usu.getNombre(), fecha, metodo, direccion, total);
        } catch (SQLException ex) {
            Logger.getLogger(AgregaFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Regresa al index
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.forward(request, response);
    }

}
