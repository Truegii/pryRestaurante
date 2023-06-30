package modelo;

public class Articulo {

    private String codigoProducto;
    private int cantidad;

//    public Articulo(String codigoProducto, int cantidad) {
//        this.codigoProducto = codigoProducto;
//        this.cantidad = cantidad;
//    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public static class Builder {
        private Articulo articulo;
        
        public Builder(){
            articulo = new Articulo();
        }
        
        public Builder codigo(String codigo){
            this.articulo.codigoProducto = codigo;
            return this;
        }
        
        public Builder cantidad(int cantidad){
            this.articulo.cantidad = cantidad;
            return this;
        }
        
        public Articulo build(){
            return articulo;
        }
    }

}
