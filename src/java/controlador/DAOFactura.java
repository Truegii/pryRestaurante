package controlador;

import conexion.Conexion;
import static conexion.Conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.Detalle;
import modelo.Factura;

public class DAOFactura extends Conexion {

    Connection cnn = conectar();

    public void registrarFactura(String cod, String usu, String fecha, String metpago, String direc, double importe) throws SQLException {
        String sql = "Insert into fac_cabe Values(?,?,?,?,?,?)";
        Statement set = cnn.createStatement();
        PreparedStatement pasar = cnn.prepareStatement(sql);
        pasar.setString(1, cod);
        pasar.setString(2, usu);
        pasar.setString(3, fecha);
        pasar.setString(4, metpago);
        pasar.setString(5, direc);
        pasar.setDouble(6, importe);

        pasar.executeUpdate();
    }

    public void registrarDetalle(String facnum, String procod, int cant, double facpre) throws SQLException {
        String sql = "Insert into fac_deta Values(?,?,?,?)";
        Statement set = cnn.createStatement();

        PreparedStatement pasar = cnn.prepareStatement(sql);
        pasar.setString(1, facnum);
        pasar.setString(2, procod);
        pasar.setString(3, cant + "");
        pasar.setString(4, facpre + "");

        pasar.executeUpdate();

    }

    public List<Factura> obtenerFacturas() throws SQLException {
        List<Factura> listaFcabe = new LinkedList<>();

        Statement stmt = cnn.createStatement();
        String sql = "SELECT * FROM fac_cabe";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int facnum = rs.getInt(1);
            String usu = rs.getString(2);
            String facfec = rs.getString(3);
            String metpago = rs.getString(4);
            String direc = rs.getString(5);
            double facimp = rs.getDouble(6);

            Factura f = new Factura(facnum, usu, facfec, metpago, direc, facimp);
            listaFcabe.add(f);

        }

        stmt.close();
        return listaFcabe;
    }

    public List<Factura> buscaFactura(String corusu) throws SQLException {
        List<Factura> listaFcabe = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM fac_cabe where corusu=?";
        stmt = cnn.prepareStatement(sql);
        stmt.setString(1, corusu);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int facnum = rs.getInt(1);
            String usu = rs.getString(2);
            String facfec = rs.getString(3);
            String metpago = rs.getString(4);
            String direc = rs.getString(5);
            double facimp = rs.getDouble(6);

            Factura f = new Factura(facnum, usu, facfec, metpago, direc, facimp);
            listaFcabe.add(f);

        }

        stmt.close();
        return listaFcabe;
    }
    
    
    public List<Factura> buscarIdFactura(String id) throws SQLException {
        List<Factura> listaFcabe = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM fac_cabe where facnum=?";
        stmt = cnn.prepareStatement(sql);
        stmt.setString(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int facnum = rs.getInt(1);
            String usu = rs.getString(2);
            String facfec = rs.getString(3);
            String metpago = rs.getString(4);
            String direc = rs.getString(5);
            double facimp = rs.getDouble(6);

            Factura f = new Factura(facnum, usu, facfec, metpago, direc, facimp);
            listaFcabe.add(f);

        }

        stmt.close();
        return listaFcabe;
    }
    
    public List<Detalle> buscarIdDetalle(String id) throws SQLException {
        List<Detalle> listaFdeta = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM fac_deta where facnum=?";
        stmt = cnn.prepareStatement(sql);
        stmt.setString(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int facnum = rs.getInt(1);
            String procod = rs.getString(2);
            int cant = rs.getInt(3);
            double facpre = rs.getDouble(4);

            Detalle d = new Detalle(facnum, procod, cant, facpre);
            listaFdeta.add(d);

        }

        stmt.close();
        return listaFdeta;
    }
    
    

    public List<Detalle> obtenerDetalle() throws SQLException {
        List<Detalle> listaFdeta = new LinkedList<>();

        Statement stmt = cnn.createStatement();
        String sql = "SELECT * FROM fac_deta";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int facnum = rs.getInt(1);
            String procod = rs.getString(2);
            int cant = rs.getInt(3);
            double facpre = rs.getDouble(4);

            Detalle d = new Detalle(facnum, procod, cant, facpre);
            listaFdeta.add(d);

        }

        stmt.close();
        return listaFdeta;
    }

    public String obtenerCodigo() {
        String cod = "";
        //Obtiene el último numero de factura y le suma +1
        String sql = "SELECT MAX(facnum)+1 from fac_cabe;";
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //Guarda el nuevo numero de factura en un String
            while (rs.next()) {
                cod = rs.getString(1);
            }
            stmt.close();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return cod;
    }

}
