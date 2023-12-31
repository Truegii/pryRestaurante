package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

public class AccesoDatos extends Conexion {

    Connection cnn = conectar();

    //Login
    public int Buscar(String n, String c) {
        int r = 0;
        try {
            PreparedStatement st = null;
            ResultSet rs = null;
            st = cnn.prepareStatement("SELECT emailusu,passusu FROM usuarios WHERE emailusu=? AND passusu=?");
            st.setString(1, n);
            st.setString(2, c);
            rs = st.executeQuery();
            if (rs.next() == true) {
                r = 1;
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return r;
    }

    //Register
    public void registrarUsuario(String cod, String nom, String ape,String materno, String pass, String dni, String email) throws SQLException {
        String sql = "Insert into usuarios Values(?,?,?,?,?,?,?)";
        Statement set = cnn.createStatement();

        PreparedStatement pasar = cnn.prepareStatement(sql);
        pasar.setString(1, cod);
        pasar.setString(2, nom);
        pasar.setString(3, ape);
        pasar.setString(4, materno);
        pasar.setString(5, pass);
        pasar.setString(6, dni);
        pasar.setString(7, email);

        pasar.executeUpdate();

    }

    public String obtenerCodigo() {
        String cod = "";
        //Obtiene el último codigo de usuario y suma +1
        String sql = "SELECT MAX(codusu)+1 from usuarios;";
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //Guarda el codigo nuevo en un String
            while (rs.next()) {
                cod = rs.getString(1);

            }
            stmt.close();
        } catch (Exception ex) {

        }
        return cod;
    }
    //Olvidaste Contraseña
    public void updateUsuario(String correo, String clave) throws SQLException {
        //Actualiza passusu de la tabla usuarios
        String sql = "UPDATE usuarios SET passusu=? WHERE emailusu=?";
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, clave);
            ps.setString(2, correo);
            //Ejecuta la consulta
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    //Busca usuario por carreo
    public static ArrayList<String> buscaUsuario(String correo) throws SQLException {
        ArrayList usu = null;
        Connection cnn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            cnn = conectar(); // Reemplaza getConnection() con el método para obtener la conexión a la base de datos

            String sql = "SELECT * FROM usuarios WHERE emailusu=?";
            stmt = cnn.prepareStatement(sql);
            stmt.setString(1, correo);
            rs = stmt.executeQuery();
            usu = new ArrayList<>();
            if (rs.next()) {
                usu.add(rs.getString(1));
                usu.add(rs.getString(2));
                usu.add(rs.getString(3));
                usu.add(rs.getString(4));
                usu.add(rs.getString(5));
                usu.add(rs.getString(6));
                usu.add(rs.getString(7));

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }

        return usu;
    }

}
