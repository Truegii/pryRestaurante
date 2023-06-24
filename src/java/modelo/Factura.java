/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Factura {
    private int facnum;
    private String corusu;
    private String facfec;
    private String metpago;
    private String direc;
    private double facimp;

    public Factura(int facnum, String corusu, String facfec, String metpago, String direc, double facimp) {
        this.facnum = facnum;
        this.corusu = corusu;
        this.facfec = facfec;
        this.metpago = metpago;
        this.direc = direc;
        this.facimp = facimp;
    }

    public String getMetpago() {
        return metpago;
    }

    public void setMetpago(String metpago) {
        this.metpago = metpago;
    }

    public int getFacnum() {
        return facnum;
    }

    public void setFacnum(int facnum) {
        this.facnum = facnum;
    }

    public String getCorusu() {
        return corusu;
    }

    public void setCorusu(String corusu) {
        this.corusu = corusu;
    }

    public String getFacfec() {
        return facfec;
    }

    public void setFacfec(String facfec) {
        this.facfec = facfec;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public double getFacimp() {
        return facimp;
    }

    public void setFacimp(double facimp) {
        this.facimp = facimp;
    }
}
