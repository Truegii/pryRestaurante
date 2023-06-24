package servlets;

import controlador.DAOFactura;
import controlador.DAOProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import modelo.Detalle;
import modelo.Factura;
import modelo.PDFDetalle;
import modelo.PDFFactura;
import modelo.Producto;
import modelo.Usuario;

@WebServlet(name = "FacturaListar", urlPatterns = {"/FacturaListar"})
public class FacturaListar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String filtro = request.getParameter("filtro");//Recupera parametro filtro
        HttpSession sesion = request.getSession(); //Recupera la sesion
        Usuario usu = (Usuario) sesion.getAttribute("usuario");//Recupera el objeto usuario de la sesion
        //Muestra las facturas de todos los usuarios
        if (filtro.equals("todos")) {
            try {
                DAOFactura fac = new DAOFactura();
                DAOProducto pro = new DAOProducto();
                List<Factura> listFcabe = fac.obtenerFacturas();
                List<Detalle> listFdeta = fac.obtenerDetalle();
                List<Producto> listPro = pro.obtenerProductos();
                request.setAttribute("listaFcabe", listFcabe);
                request.setAttribute("listaFdeta", listFdeta);
                request.setAttribute("listPro", listPro);

            } catch (Exception e) {

            }
            //Muestra solo las facturas del usuario con sesion iniciada
        } else if (filtro.equals("usuario")) {
            try {
                DAOFactura fac = new DAOFactura();
                DAOProducto pro = new DAOProducto();
                List<Factura> listFcabe = fac.buscaFactura(usu.getNombre());
                List<Detalle> listFdeta = fac.obtenerDetalle();
                List<Producto> listPro = pro.obtenerProductos();
                request.setAttribute("listaFcabe", listFcabe);
                request.setAttribute("listaFdeta", listFdeta);
                request.setAttribute("listPro", listPro);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        } else if (filtro.equals("exportar")) {
            try {
                DAOFactura fac = new DAOFactura();
                List<Factura> listFcabe = fac.obtenerFacturas();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String nomFile = dateFormat.format(new Date());
                String variable1 = "Content-Disposition";
                String variable2 = "attachment; filename=Facturas_" + nomFile + ".pdf";
                response.setHeader(variable1, variable2);
                PDFFactura exportarFacturas = new PDFFactura(listFcabe);
                exportarFacturas.export(response);
                request.getRequestDispatcher("FacturaListar?filtro=todos").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error" + ex);
            }
        } else if (filtro.equals("pdfdetalle")) {
            try {
                String idfac = request.getParameter("idfac");
                DAOFactura fac = new DAOFactura();
                
                List<Factura> listFcabe = fac.buscarIdFactura(idfac);
                List<Detalle> listFdeta = fac.buscarIdDetalle(idfac);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String nomFile = dateFormat.format(new Date());
                String variable1 = "Content-Disposition";
                String variable2 = "attachment; filename=FacturaDetalle_" + nomFile + ".pdf";
                response.setHeader(variable1, variable2);
                PDFDetalle exportarFacDetalle = new PDFDetalle(listFcabe,listFdeta);
                exportarFacDetalle.export(response);
                request.getRequestDispatcher("FacturaListar?filtro=usuario").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error" + ex);
            }
        }
        //Redirige a listarfacturas
        RequestDispatcher rd = request.getRequestDispatcher("paginas/facturas/listarfactura.jsp");
        rd.forward(request, response);
    }

}
