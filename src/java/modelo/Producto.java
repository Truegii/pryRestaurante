package modelo;

public class Producto {
     private String procod;
    private String pronom;
    private String proimg;
    private double propre;
    private String protipo;

    public Producto(String procod, String pronom, String proimg, double propre, String protipo) {
        this.procod = procod;
        this.pronom = pronom;
        this.proimg = proimg;
        this.propre = propre;
        this.protipo = protipo;
    }

    public String getProcod() {
        return procod;
    }

    public void setProcod(String procod) {
        this.procod = procod;
    }

    public String getPronom() {
        return pronom;
    }

    public void setPronom(String pronom) {
        this.pronom = pronom;
    }

    public String getProimg() {
        return proimg;
    }

    public void setProimg(String proimg) {
        this.proimg = proimg;
    }

    public double getPropre() {
        return propre;
    }

    public void setPropre(double propre) {
        this.propre = propre;
    }

    public String getProtipo() {
        return protipo;
    }

    public void setProtipo(String protipo) {
        this.protipo = protipo;
    }

    @Override
    public String toString() {
        return "Producto{" + "procod=" + procod + ", pronom=" + pronom + ", proimg=" + proimg + ", propre=" + propre + ", protipo=" + protipo + '}';
    }
}
