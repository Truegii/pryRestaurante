package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static Conexion instance = null;
    private Connection con;
    private static String URL = "jdbc:mysql://localhost:3307/bdrestaurante";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USER = "root";
    private static String PASS = "";

    private Conexion() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public synchronized static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    public void close() {
        instance = null;
    }


}
