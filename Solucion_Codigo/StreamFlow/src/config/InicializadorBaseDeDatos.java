package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBaseDeDatos {
    private ConexionSQLite conexionSQLite;
    
    public InicializadorBaseDeDatos(){
        this.conexionSQLite = new ConexionSQLite();
    }
    
    public void inicializar(){        
        String crearTablaContenido = "CREATE TABLE IF NOT EXISTS contenido "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT NOT NULL, titulo TEXT NOT NULL, "
                + "genero TEXT NOT NULL, calidad TEXT NOT NULL, duracion_minutos INTEGER NOT NULL, director TEXT, "
                + "temporadas INTEGER, episodios INTEGER, fuente TEXT, host TEXT, "
                + "numero_episodio INTEGER)";
        
        String crearTablaUsuario = "CREATE TABLE IF NOT EXISTS usuario "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, email TEXT NOT NULL, "
                + "genero_preferido TEXT NOT NULL)";
        
        String crearTablaSuscripcion = "CREATE TABLE IF NOT EXISTS suscripcion "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, usuario_id INTEGER NOT NULL, "
                + "calidad TEXT NOT NULL, precio_mensual REAL NOT NULL, fecha_inicio TEXT NOT NULL)";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                Statement statement = conexion.createStatement()){
            
            statement.execute(crearTablaContenido);
            statement.execute(crearTablaUsuario);
            statement.execute(crearTablaSuscripcion);
            
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }        
    }
}