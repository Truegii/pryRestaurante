package servlets;

import controlador.AccesoDatos;
import controlador.DAOProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Usuario;

@WebServlet(name = "LoginConexion", urlPatterns = {"/LoginConexion"})
public class LoginConexion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Trae los parametros del formulario
            String nom = request.getParameter("txtNom");
            String cla = request.getParameter("txtCla");
            AccesoDatos ad = new AccesoDatos();
            int res = ad.Buscar(nom, cla);
            if (res == 1) {
                Usuario usu =new Usuario.Builder()
                        .nombre(nom)
                        .clave(cla)
                        .build();
                //Usuario usu = new Usuario(nom, cla); //Crea objeto usuario
                HttpSession sesion = request.getSession(); //Obtiene una session
                sesion.setAttribute("usuario", usu); //Establece el usuario dentro de la sesion
                //Si el usuario es admin, redirige a admin.jsp
                if (nom.equals("admin@nagoya.com") && cla.equals("admin")) {
                    response.sendRedirect("paginas/admin.jsp");
                } else {
                    //Sino redirige a Carta.jsp
                    DAOProducto pro = new DAOProducto();
                    //Trae la lista de todos los productos
                    List<Producto> listProd = pro.obtenerProductos();
                    //Establece la lista de productos como atributo y lo manda a Carta
                    request.setAttribute("listaProd", listProd);
                    RequestDispatcher rd = request.getRequestDispatcher("/paginas/Carta.jsp");
                    rd.forward(request, response);
                }
            } else {

                //Si la contraseña es incorrecta, redirige a Login.jsp denuevo
                response.sendRedirect("paginas/Login.jsp");
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
