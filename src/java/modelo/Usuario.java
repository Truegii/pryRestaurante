package modelo;

public class Usuario {

    private String nombre;
    private String clave;

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", clave=" + clave + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public static class Builder {

        private Usuario usuario;

        public Builder() {
            usuario = new Usuario();
        }


        public Builder nombre(String nombre) {
            this.usuario.nombre = nombre;
            return this;
        }

        public Builder clave(String clave) {
            this.usuario.clave = clave;
            return this;
        }

        public Usuario build() {
            return usuario;
        }

    }
}
