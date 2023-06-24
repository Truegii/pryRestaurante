package servlets;

import controlador.AccesoDatos;
import controlador.DAOFactura;
import controlador.DAOReserva;
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
import modelo.PDFReserva;
import modelo.PDFReservaDetalle;
import modelo.Producto;
import modelo.Reservas;
import modelo.Usuario;

@WebServlet(name = "ReservaListar", urlPatterns = {"/ReservaListar"})
public class ReservaListar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession sesion = request.getSession();//Recupera la sesion
        Usuario usu = (Usuario) sesion.getAttribute("usuario");//Recupera objeto usuario de la sesion
        String filtro = (String) request.getParameter("filtro");//Trae el parametro filtro
        DAOReserva daore = new DAOReserva();
        if (filtro.equals("todos")) { //Lista todas las reservas
            try {
                List<Reservas> listRes = daore.obtenerReserva();
                //Establece la lista de reservas como atributo
                request.setAttribute("listaReserva", listRes);
                //La redirige a Listar Reservas
                RequestDispatcher rd = request.getRequestDispatcher("paginas/reservas/listarreserva.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else if (filtro.equals("usuario")) {//Lista solo las reservas del usuario ingresado
            String correo = usu.getNombre(); //Guarda el correo del objeto usuario
            try {
                List<Reservas> listRes = daore.obtenerReservaUsu(correo);
                //Establece la lista de reservas de un solo usuario
                request.setAttribute("listaReserva", listRes);
                //Redirige la lista a Listar Reservas
                RequestDispatcher rd = request.getRequestDispatcher("paginas/reservas/listarreserva.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else if (filtro.equals("exportar")) {
            try {
                DAOReserva resev = new DAOReserva();
                AccesoDatos ad = new AccesoDatos();
                List<Reservas> listRes = resev.obtenerReservaUsu(usu.getNombre());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String nomFile = dateFormat.format(new Date());
                String variable1 = "Content-Disposition";
                String variable2 = "attachment; filename=Reservas_" + nomFile+".pdf";
                response.setHeader(variable1, variable2);
                PDFReserva exportarReserva = new PDFReserva(listRes, ad.buscaUsuario(usu.getNombre()));
                exportarReserva.export(response);
                request.getRequestDispatcher("ReservaListar?filtro=usuario").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error" + ex);
            }
        } else if (filtro.equals("pdfdetalle")) {
            try {
                String idres = request.getParameter("idres");
                AccesoDatos ad = new AccesoDatos();
                DAOReserva resev = new DAOReserva();
                List<Reservas> listRes = resev.buscarIdReserva(idres);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String nomFile = dateFormat.format(new Date());
                String variable1 = "Content-Disposition";
                String variable2 = "attachment; filename=ReservaDetalle_" + nomFile + ".pdf";
                response.setHeader(variable1, variable2);
                PDFReservaDetalle exportarResDetalle = new PDFReservaDetalle(listRes,ad.buscaUsuario(listRes.get(0).getCorreo()));
                exportarResDetalle.export(response);
                request.getRequestDispatcher("ReservaListar?filtro=usuario").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error" + ex);
            }
        }

    }

}
