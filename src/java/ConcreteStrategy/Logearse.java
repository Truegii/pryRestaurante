/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConcreteStrategy;

/**
 *
 * @author alex
 */
public class Logearse {
    private Interfaces.IUsuario iUsuario;
    
    public void setIUsuario(Interfaces.IUsuario iUsuario){
        this.iUsuario = iUsuario;
    }
    
    public String login(){
        return this.iUsuario.login();
    }
}
