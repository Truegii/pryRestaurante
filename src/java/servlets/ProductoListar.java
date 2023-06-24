package servlets;

import controlador.DAOProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Producto;
import modelo.Usuario;

@WebServlet(name = "ProductoListar", urlPatterns = {"/ProductoListar"})
public class ProductoListar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);//Recupera sesion si existe
        PrintWriter out = response.getWriter();
        if (sesion == null) { //Si la sesion está vacia
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Debes iniciar sesión para visualizar la carta');");
            out.println("window.location.href = 'paginas/Login.jsp';"); // Redireccionar al inicio
            out.println("</script>");
        } else {
            String filtro = (String) request.getParameter("filtro"); //Recupera parametro filtro
            if (filtro.equals("crud")) { //Caso admin

                try {
                    DAOProducto pro = new DAOProducto();

                    List<Producto> listProd = pro.obtenerProductos();

                    request.setAttribute("listaProd", listProd);

                    RequestDispatcher rd = request.getRequestDispatcher("paginas/producto/listarProducto.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (filtro.equals("todos")) { //Carta mostrar todos
                try {
                    DAOProducto pro = new DAOProducto();

                    List<Producto> listProd = pro.obtenerProductos();

                    request.setAttribute("listaProd", listProd);

                    RequestDispatcher rd = request.getRequestDispatcher("paginas/Carta.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (filtro.equals("eliminar")) {
                try {
                    DAOProducto pro = new DAOProducto();
                    String codigo = (String) request.getParameter("codigo");
                    pro.eliminarProducto(codigo);
                    List<Producto> listProd = pro.obtenerProductos();

                    request.setAttribute("listaProd", listProd);

                    RequestDispatcher rd = request.getRequestDispatcher("paginas/producto/listarProducto.jsp");
                    rd.forward(request, response);
                } catch (Exception e) {

                }
            } else if (filtro.equals("modificar")) {
                try {
                    DAOProducto pro = new DAOProducto();
                    String codigo = (String) request.getParameter("codigo");
                    Producto produc = pro.buscaProducto(codigo);

                    request.setAttribute("product", produc);

                    RequestDispatcher rd = request.getRequestDispatcher("paginas/producto/modificarProducto.jsp");
                    rd.forward(request, response);
                } catch (Exception e) {

                }
            } else if (filtro.equals("actualizar")) {
                try {
                    String cod = request.getParameter("codigo");
                    String nom = request.getParameter("pronom");
                    double pre = Double.parseDouble(request.getParameter("propre"));
                    String tipo = request.getParameter("protipo");
                    DAOProducto pro = new DAOProducto();
                    pro.modificarProducto(cod, nom, pre, tipo);
                    List<Producto> listProd = pro.obtenerProductos();
                    //Establece la lista como atributo
                    request.setAttribute("listaProd", listProd);
                    //Manda la lista a Admin Listar Productos
                    RequestDispatcher rd = request.getRequestDispatcher("paginas/producto/listarProducto.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else { //Muestra solo los productos filtrados
                try {
                    DAOProducto pro = new DAOProducto();
                    //Trae solo los productos de cierto Tipo
                    List<Producto> listProd = pro.obtenerProductoTipo(filtro);
                    //Establece la lista como atributo
                    request.setAttribute("listaProd", listProd);
                    //Manda la lista a Carta.jsp
                    RequestDispatcher rd = request.getRequestDispatcher("paginas/Carta.jsp");
                    rd.forward(request, response);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
