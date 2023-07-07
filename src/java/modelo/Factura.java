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

    @Override
    public String toString() {
        return "Factura{" + "facnum=" + facnum + ", corusu=" + corusu + ", facfec=" + facfec + ", metpago=" + metpago + ", direc=" + direc + ", facimp=" + facimp + '}';
    }

    public static class Builder {

        private Factura factura;

        public Builder() {
            factura = new Factura();
        }

        public Builder facnum(int facnum) {
            this.factura.facnum = facnum;
            return this;
        }

        public Builder correo(String correo) {
            this.factura.corusu = correo;
            return this;
        }

        public Builder fecha(String fecha) {
            this.factura.facfec = fecha;
            return this;
        }

        public Builder metodo(String metodo) {
            this.factura.metpago = metodo;
            return this;
        }

        public Builder direccion(String direccion) {
            this.factura.direc = direccion;
            return this;
        }

        public Builder importe(double importe) {
            this.factura.facimp = importe;
            return this;
        }

        public Factura build() {
            return factura;
        }

    }
}
