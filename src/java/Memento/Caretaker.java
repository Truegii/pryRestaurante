/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class Caretaker {
        
    private Originator originator;
    private List<Originator.Memento> history= new ArrayList<>();

    public Caretaker(Originator originator) {
        this.originator = originator;
    }
    
    public void addMemento(Originator.Memento m){
        history.add(originator.guardar());
        
    }
    public Originator.Memento restoreMemento(Integer index){
      return history.get(index);
    }
    
}
