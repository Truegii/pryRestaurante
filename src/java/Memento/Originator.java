/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Memento;

import java.util.ArrayList;
import modelo.Articulo;

/**
 *
 * @author alex
 */
public class Originator {
    private  Articulo estado;

    public Articulo getEstado() {
        return estado;
    }

    public void setEstado(Articulo estado) {
        this.estado = estado;
    }

    public Memento guardar(){
        
        return new Memento(this.estado);
    }
    
    public void restaurar(Memento m){
       this.estado = m.getEstado();
    }
    
    class Memento {

        private Articulo estado;

        private Memento(Articulo estado) {
            this.estado = estado;
        }

        private Articulo getEstado() {
            return estado;
        }

    }
}
