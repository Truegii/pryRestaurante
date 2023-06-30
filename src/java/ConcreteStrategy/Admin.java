/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConcreteStrategy;

/**
 *
 * @author alex
 */
public class Admin implements Interfaces.IUsuario{

    @Override
    public String login() {
        return "paginas/admin.jsp";
    }
    
}
