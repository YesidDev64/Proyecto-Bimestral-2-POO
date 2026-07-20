package dao;

import config.ConexionSQLite;
import dao.interfaces.ISuscripcionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Suscripcion;

public class SuscripcionDAO implements ISuscripcionDAO{
    private ConexionSQLite conexionSQLite;
    
    public SuscripcionDAO(){
        this.conexionSQLite = new ConexionSQLite();
    }

    @Override
    public void guardar(Suscripcion suscripcion) {
        String sql = "INSERT INTO suscripcion (usuario_id, calidad, precio_mensual, "
                   + "fecha_inicio) VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, suscripcion.getUsuarioId());
            statement.setString(2, suscripcion.getCalidad());
            statement.setDouble(3, suscripcion.getPrecioMensual());
            statement.setString(4, suscripcion.getFechaInicio());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al guardar suscripcion: " + e.getMessage());
        }
    }

    @Override
    public Suscripcion buscarPorId(int id) {
        String sql = "SELECT * FROM suscripcion WHERE id = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next())
                return mapearSuscripcion(resultado);
            
        } catch (SQLException e) {
            System.out.println("Error al buscar suscripcion: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Suscripcion> listarTodos() {
        ArrayList<Suscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM suscripcion";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery()){
            
            while(resultado.next())
                lista.add(mapearSuscripcion(resultado));
            
        } catch (SQLException e) {
            System.out.println("Error al listar suscripciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM suscripcion WHERE id = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar suscripcion: " + e.getMessage());
        }
    }
    
    private Suscripcion mapearSuscripcion(ResultSet resultado) throws SQLException{
        Suscripcion suscripcion = new Suscripcion(resultado.getInt("usuario_id"),
                                           resultado.getString("calidad"), 
                                      resultado.getDouble("precio_mensual"), 
                                        resultado.getString("fecha_inicio"));
        suscripcion.setId(resultado.getInt("id"));
        return suscripcion;
    }
}