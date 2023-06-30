/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Reclamos {
    private int idrec;
    private String nombres;
    private String dni;
    private String correo;
    private String servi;
    private String comentario;

//    public Reclamos(int idrec, String nombres, String dni, String correo, String servi, String comentario) {
//        this.idrec = idrec;
//        this.nombres = nombres;
//        this.dni = dni;
//        this.correo = correo;
//        this.servi = servi;
//        this.comentario = comentario;
//    }
    
    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getServi() {
        return servi;
    }

    public void setServi(String servi) {
        this.servi = servi;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public static class Builder {
        private Reclamos reclamos;
        
        public Builder(){
            reclamos = new Reclamos();
        }
        
        public Builder id(int id){
            this.reclamos.idrec = id;
            return this;
        }
        public Builder nombres(String nombres){
            this.reclamos.nombres = nombres;
            return this;
        }
        public Builder dni(String dni){
            this.reclamos.dni = dni;
            return this;
        }
        public Builder correo(String correo){
            this.reclamos.correo = correo;
            return this;
        }
        public Builder servicio(String servicio){
            this.reclamos.servi = servicio;
            return this;
        }
        public Builder comentario(String comentario){
            this.reclamos.comentario = comentario;
            return this;
        }
        public Reclamos build(){
            return reclamos;
        }
    }
    
    
}
