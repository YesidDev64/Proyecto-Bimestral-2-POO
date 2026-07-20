package dao;

import config.ConexionSQLite;
import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO implements IUsuarioDAO{
    private ConexionSQLite conexionSQLite;
    
    public UsuarioDAO(){
        this.conexionSQLite = new ConexionSQLite();
    }

    @Override
    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, email, genero_preferido) VALUES (?, ?, ?)";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getGeneroPreferido());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next())
                return mapearUsuario(resultado);
            
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setString(1, email);
            ResultSet resultado = statement.executeQuery();
            
            if(resultado.next())
                return mapearUsuario(resultado);
            
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por email: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery()){
            
            while(resultado.next())
                lista.add(mapearUsuario(resultado));
            
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
    
    private Usuario mapearUsuario(ResultSet resultado) throws  SQLException{
        Usuario usuario = new Usuario(resultado.getString("nombre"), 
                                 resultado.getString("email"), 
                         resultado.getString("genero_preferido"));
        usuario.setId(resultado.getInt("id"));
        return usuario;
    }
}