package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.Producto;

public class DAOProducto {

    Conexion conexion = Conexion.getInstance();

    public void registrarProducto(String procod, String pronom, String proimg, double propre, String protipo) throws SQLException {
        String sql = "Insert into producto Values(?,?,?,?,?)";

        PreparedStatement pasar = conexion.getConnection().prepareStatement(sql);
        pasar.setString(1, procod);
        pasar.setString(2, proimg);
        pasar.setString(3, pronom);
        pasar.setString(4, propre + "");
        pasar.setString(5, protipo);

        pasar.executeUpdate();
        conexion.close();
    }

    public List<Producto> obtenerProductos() throws SQLException {
        List<Producto> listaProdu = new LinkedList<>();

        Statement stmt = conexion.getConnection().createStatement();
        String sql = "SELECT * FROM producto";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String procod = rs.getString(1);
            String proimg = rs.getString(2);
            String pronom = rs.getString(3);
            double propre = rs.getDouble(4);
            String protipo = rs.getString(5);

            Producto p = new Producto.Builder()
                    .codigo(procod)
                    .nombre(pronom)
                    .imagen(proimg)
                    .precio(propre)
                    .tipo(protipo)
                    .build();
            listaProdu.add(p);

        }
        stmt.close();
        conexion.close();
        return listaProdu;
    }

    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public String obtenerCodigo() {
        String cod = "";
        String sql = "SELECT RIGHT(MAX(procod),3)+1 from producto;";
        try {
            Statement stmt = conexion.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String cuenta = rs.getString(1);

                cod = "P" + padLeftZeros(cuenta, 4);

            }

            stmt.close();
        } catch (Exception ex) {

        }
        conexion.close();
        return cod;
    }

    public void eliminarProducto(String cod) throws SQLException {
        String sql = "DELETE FROM producto where procod=?;";

        PreparedStatement pasar = conexion.getConnection().prepareStatement(sql);
        pasar.setString(1, cod);

        pasar.executeUpdate();
        conexion.close();
    }

    public void modificarProducto(String cod, String nom, double pre, String tipo) {
        String sql = "update producto set pronom=?, propre=?, protipo=? where procod=?";
        try {
            PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, pre + "");
            ps.setString(3, tipo);
            ps.setString(4, cod);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        conexion.close();
    }

    //Busca producto por idProducto
    public Producto buscaProducto(String codigo) throws SQLException {
        Producto producto = null;
        ResultSet rs = null;

        try {
            //cnn = conectar(); // Reemplaza getConnection() con el método para obtener la conexión a la base de datos

            String sql = "SELECT * FROM producto WHERE procod=?";
            PreparedStatement stmt = conexion.getConnection().prepareStatement(sql);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String procod = rs.getString(1);
                String proimg = rs.getString(2);
                String pronom = rs.getString(3);
                double propre = rs.getDouble(4);
                String protipo = rs.getString(5);

                producto = new Producto.Builder()
                    .codigo(procod)
                    .nombre(pronom)
                    .imagen(proimg)
                    .precio(propre)
                    .tipo(protipo)
                    .build();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

        }
        conexion.close();
        return producto;
    }

    //Filtrar por tipo de producto
    public List<Producto> obtenerProductoTipo(String tipo) throws SQLException {
        List<Producto> listaProdu = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        //cnn = conectar();
        String sql = "SELECT * FROM producto WHERE protipo=?";
        stmt = conexion.getConnection().prepareStatement(sql);
        stmt.setString(1, tipo);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String procod = rs.getString(1);
            String pronom = rs.getString(3);
            String proimg = rs.getString(2);
            double propre = rs.getDouble(4);
            String protipo = rs.getString(5);

            Producto p = new Producto.Builder()
                    .codigo(procod)
                    .nombre(pronom)
                    .imagen(proimg)
                    .precio(propre)
                    .tipo(protipo)
                    .build();
            listaProdu.add(p);

        }
        stmt.close();
        conexion.close();
        return listaProdu;
    }

}
