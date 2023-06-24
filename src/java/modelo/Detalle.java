package modelo;

public class Detalle {

    private int facnum;
    private String procod;
    private int cant;
    private double facpre;

    public Detalle(int facnum, String procod, int cant, double facpre) {
        this.facnum = facnum;
        this.procod = procod;
        this.cant = cant;
        this.facpre = facpre;
    }

    public int getFacnum() {
        return facnum;
    }

    public void setFacnum(int facnum) {
        this.facnum = facnum;
    }

    public String getProcod() {
        return procod;
    }

    public void setProcod(String procod) {
        this.procod = procod;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getFacpre() {
        return facpre;
    }

    public void setFacpre(double facpre) {
        this.facpre = facpre;
    }

}
