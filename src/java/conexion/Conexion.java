package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection conectar() {
        Connection cn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/bdrestaurante";
            cn = DriverManager.getConnection(url, "root", "");
        } catch (Exception ex) {
            ex.getMessage(); 
        }
        return cn;
    }
}
