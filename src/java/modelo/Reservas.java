package modelo;

public class Reservas {

    int idreserva;
    String correo;
    String fecha;
    int cantidad;
    String actividad;

//    public Reservas(int idreserva, String correo, String fecha, int cantidad, String actividad) {
//        this.idreserva = idreserva;
//        this.correo = correo;
//        this.fecha = fecha;
//        this.cantidad = cantidad;
//        this.actividad = actividad;
//    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
    
    public static class Builder{
        private Reservas reserva;
        
        public Builder(){
            reserva = new Reservas();
        }
        
        public Builder id(int id){
            this.reserva.idreserva = id;
            return this;
        }
        public Builder correo(String correo){
            this.reserva.correo = correo;
            return this;
        }
        public Builder fecha(String fecha){
            this.reserva.fecha = fecha;
            return this;
        }
        public Builder cantidad(int cant){
            this.reserva.cantidad = cant;
            return this;
        }
        public Builder actividad(String actividad){
            this.reserva.actividad = actividad;
            return this;
        }
        public Reservas build(){
            return reserva;
        }
    }

}
