/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controlador.DAOReclamos;
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

/**
 *
 * @author Usuario
 */
@WebServlet(name = "AgregaReclamo", urlPatterns = {"/AgregaReclamo"})
public class AgregaReclamo extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String dni = request.getParameter("dni");
            String correo = request.getParameter("correo");
            String servicio = request.getParameter("servicio");
            String comentario = request.getParameter("comentario");
            
            DAOReclamos daorec = new DAOReclamos();
            
            daorec.registraReclamo(daorec.obtenerCodigo(),nombre+" "+apellido, dni, correo, servicio, comentario);
        } catch (SQLException ex) {
            Logger.getLogger(AgregaReclamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("index.html");
        
    }



}
