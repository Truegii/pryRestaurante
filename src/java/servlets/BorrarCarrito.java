package servlets;

import Memento.Caretaker;
import Memento.Originator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;

@WebServlet(name = "BorrarCarrito", urlPatterns = {"/BorrarCarrito"})
public class BorrarCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
    Originator originator = new Originator();
    Caretaker caretaker = new Caretaker(originator);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);//Recupera sesion si existe
        PrintWriter out = response.getWriter();
        if (sesion == null) { //Si la sesion está vacia
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Debes iniciar sesión para visualizar la carta');");
            out.println("window.location.href = 'paginas/Login.jsp';"); // Redireccionar al inicio
            out.println("</script>");
        } else {

            String filtro = (String) request.getParameter("filtro"); //Recupera parametro filtro
            if (filtro.equals("DeleteAll")) { //Caso admin

                try {
                    //Recupera el carrito de la sesion
                    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");
                    //Limpia el arraylist del carrito
                    articulos.clear();
                    //Manda el arraylist de carrito vacío
                    sesion.setAttribute("carrito", articulos);
                    response.sendRedirect("paginas/Carrito.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (filtro.equals("DeleteOne")) { //Elimina 1
                try {
                    originator = new Originator();
                    caretaker = new Caretaker(originator);
                    String id = (String) request.getParameter("id");
                    System.out.println(id);
                    //Recupera el carrito de la sesion
                    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");
                    Articulo art = articulos.get(Integer.parseInt(id) - 1);
                    Guardar(art, Integer.parseInt(id) - 1);
                    articulos.remove(Integer.parseInt(id) - 1);
                    //Manda el arraylist de carrito vacío

                    sesion.setAttribute("carrito", articulos);
                    response.sendRedirect("paginas/Carrito.jsp");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (filtro.equals("ReDo")) { //Vuelve hacer un backup
                try {

                    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");

                    articulos.add(Backup());

                    sesion.setAttribute("carrito", articulos);

                    response.sendRedirect("paginas/Carrito.jsp");

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    //METODO QUE GUARDA EN SESION EL MEMENTO
    public void Guardar(Articulo articulo, int index) {
        originator.setEstado(articulo);
        caretaker.addMemento(originator.guardar());

    }

    //METODO QUE GENERA BACKUPS EN SESION SIN PERDER DATA
    public Articulo Backup() {
        Articulo articulo;

        originator.restaurar(caretaker.restoreMemento(0));
        articulo = originator.getEstado();
        return articulo;
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
