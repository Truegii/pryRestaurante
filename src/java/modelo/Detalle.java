package modelo;

public class Detalle {

    private int facnum;
    private String procod;
    private int cant;
    private double facpre;

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

    public static class Builder {

        private Detalle detalle;

        public Builder() {
            detalle = new Detalle();
        }
        
        public Builder facnum(int facnum){
            this.detalle.facnum = facnum;
            return this;
        }
        public Builder procod(String procod){
            this.detalle.procod = procod;
            return this;
        }
        
        public Builder cantidad(int cantidad){
            this.detalle.cant = cantidad;
            return this;
        }
        
        public Builder precio(double precio){
            this.detalle.facpre = precio;
            return this;
        }
        
        public Detalle build(){
            return detalle;
        }

    }

}
