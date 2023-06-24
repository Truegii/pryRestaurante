package modelo;

public class Reservas {

    int idreserva;
    String correo;
    String fecha;
    int cantidad;
    String actividad;

    public Reservas(int idreserva, String correo, String fecha, int cantidad, String actividad) {
        this.idreserva = idreserva;
        this.correo = correo;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.actividad = actividad;
    }

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

}
