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
import modelo.Reclamos;


public class DAOReclamos extends Conexion{
    Connection cnn = conectar();
    public void registraReclamo(String cod, String nombre, String dni, String correo, String servicio, String comentario) throws SQLException {
        String sql = "Insert into reclamos Values(?,?,?,?,?,?)";
        Statement set = cnn.createStatement();
        PreparedStatement pasar = cnn.prepareStatement(sql);
        pasar.setString(1, cod);
        pasar.setString(2, nombre);
        pasar.setString(3, dni);
        pasar.setString(4, correo);
        pasar.setString(5, servicio);
        pasar.setString(6, comentario);

        pasar.executeUpdate();
    }
    
    
     public List<Reclamos> obtenerReclamo() throws SQLException {
        List<Reclamos> listaRec = new LinkedList<>();

        Statement stmt = cnn.createStatement();
        String sql = "SELECT * FROM reclamos";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int cod = rs.getInt(1);
            String nombres = rs.getString(2);
            String dni = rs.getString(3);
            String correo = rs.getString(4);
            String servicio = rs.getString(5);
            String comentario = rs.getString(6);

            Reclamos r = new Reclamos(cod, nombres, dni, correo, servicio,comentario);
            listaRec.add(r);

        }

        stmt.close();
        return listaRec;
    }
    
    public String obtenerCodigo() {
        String cod = "";
        //Obtiene el último numero de factura y le suma +1
        String sql = "SELECT MAX(idrec)+1 from reclamos;";
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
