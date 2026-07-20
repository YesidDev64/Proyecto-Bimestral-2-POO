package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {
    public Connection obtenerConexion() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:db/streamflow.db");
    }
}