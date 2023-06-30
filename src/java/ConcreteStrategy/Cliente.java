/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConcreteStrategy;

import controlador.DAOProducto;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import modelo.Producto;
import modelo.Usuario;

/**
 *
 * @author alex
 */
public class Cliente implements Interfaces.IUsuario {

    @Override
    public String login() {
        return "/paginas/Carta.jsp";
    }

}
