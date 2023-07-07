/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;

import java.util.ArrayList;
import java.util.List;
import modelo.Detalle;
import modelo.Factura;
import modelo.PDFDetalle;
import modelo.PDFFactura;
import modelo.PDFReserva;
import modelo.PDFReservaDetalle;
import modelo.Reservas;

/**
 *
 * @author Usuario
 */
public class CConcretePDF extends Creator{

    @Override
    public IPDF createDetalle(List<Factura> getFacturas, List<Detalle> getDetalles) {
        return new PDFDetalle(getFacturas,getDetalles);
    }

    @Override
    public IPDF createFactura(List<Factura> listFcabe) {
        return new PDFFactura(listFcabe);
    }

    @Override
    public IPDF createReserva(List<Reservas> getReservas, ArrayList<String> usuarios) {
        return new PDFReserva(getReservas,usuarios);
    }

    @Override
    public IPDF createDetaReserva(List<Reservas> getReservas, ArrayList<String> usuarios) {
        return new PDFReservaDetalle(getReservas,usuarios);
    }


    
}
