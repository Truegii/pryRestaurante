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

    public Reclamos(int idrec, String nombres, String dni, String correo, String servi, String comentario) {
        this.idrec = idrec;
        this.nombres = nombres;
        this.dni = dni;
        this.correo = correo;
        this.servi = servi;
        this.comentario = comentario;
    }
    
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
    
    
}
