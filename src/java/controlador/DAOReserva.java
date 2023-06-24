package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.Factura;
import modelo.Reservas;

public class DAOReserva extends Conexion {

    Connection cnn = conectar();

    public void registrarReserva(String cod, String email, String fecha, int cantidad, String actividad) throws SQLException {
        String sql = "Insert into reserva Values(?,?,?,?,?)";
        Statement set = cnn.createStatement();
        PreparedStatement pasar = cnn.prepareStatement(sql);
        pasar.setString(1, cod);
        pasar.setString(2, email);
        pasar.setString(3, fecha);
        pasar.setInt(4, cantidad);
        pasar.setString(5, actividad);

        pasar.executeUpdate();
    }

    public List<Reservas> obtenerReserva() throws SQLException {
        List<Reservas> listaRes = new LinkedList<>();

        Statement stmt = cnn.createStatement();
        String sql = "SELECT * FROM reserva";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int cod = rs.getInt(1);
            String email = rs.getString(2);
            String fecha = rs.getString(3);
            int cant = rs.getInt(4);
            String actividad = rs.getString(5);

            Reservas r = new Reservas(cod, email, fecha, cant, actividad);
            listaRes.add(r);

        }

        stmt.close();
        return listaRes;
    }
    //Listar reserva de cada usuario
    public List<Reservas> obtenerReservaUsu(String correo) throws SQLException {
        List<Reservas> listaRes = new LinkedList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM reserva WHERE emailusu=?";
        stmt = cnn.prepareStatement(sql);
        stmt.setString(1, correo);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int cod = rs.getInt(1);
            String email = rs.getString(2);
            String fecha = rs.getString(3);
            int cant = rs.getInt(4);
            String actividad = rs.getString(5);

            Reservas r = new Reservas(cod, email, fecha, cant, actividad);
            listaRes.add(r);

        }

        stmt.close();
        return listaRes;
    }
    
    public List<Reservas> buscarIdReserva(String id) throws SQLException {
        List<Reservas> listaRes = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM reserva where idreserv=?";
        stmt = cnn.prepareStatement(sql);
        stmt.setString(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int idreserv = rs.getInt(1);
            String correo = rs.getString(2);
            String fecha = rs.getString(3);
            int cantidad = rs.getInt(4);
            String actividad = rs.getString(5);

            Reservas f = new Reservas(idreserv, correo, fecha, cantidad, actividad);
            listaRes.add(f);

        }

        stmt.close();
        return listaRes;
    }

    public String obtenerCodigo() {
        String cod = "";
        String sql = "SELECT MAX(idreserv)+1 from reserva;";
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cod = rs.getString(1);

            }

            stmt.close();
        } catch (Exception ex) {

        }
        return cod;
    }
}
