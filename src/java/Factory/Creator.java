/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Factory;

import java.util.ArrayList;
import java.util.List;
import modelo.Detalle;
import modelo.Factura;
import modelo.Reservas;

/**
 *
 * @author Usuario
 */
public abstract class Creator {
    public abstract IPDF createDetalle(List<Factura> getFacturas, List<Detalle> getDetalles);
    public abstract IPDF createFactura(List<Factura> listFcabe);
    public abstract IPDF createReserva(List<Reservas> getReservas, ArrayList<String> usuarios);
    public abstract IPDF createDetaReserva(List<Reservas> getReservas, ArrayList<String> usuarios);
    
}
