package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;

@WebServlet(name = "AgregaCarrito", urlPatterns = {"/AgregaCarrito"})
public class AgregaCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        String codigoProducto = request.getParameter("codigoProducto");

        HttpSession sesion = request.getSession(true);

        //Creamos un ArrayList llamado articulos, preguntamos si la variable sesion esta null
        //Si esta null, creamos un nuevo ArrayList, si no esta nulo, recuperamos el existente
        ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");

        boolean flag = false;

        //Preguntamos si el Array esta vacio
        
            //Recorremos todos los articulos
            for (Articulo a : articulos) {
                //Preguntamos si existe uno con el mismo id, en caso de ser así, actualizamos su cantidad
                if (codigoProducto.equals(a.getCodigoProducto())) {
                    a.setCantidad(a.getCantidad() + cantidad);
                    flag = true;
                    break;
                }
            }
        

        //Añadimos un producto al carrito
        if (flag == false) {
            articulos.add(new Articulo.Builder()
                    .codigo(codigoProducto)
                    .cantidad(cantidad)
                    .build());
        }

        sesion.setAttribute("carrito", articulos);

        response.sendRedirect("paginas/Carrito.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
