package dao;

import dao.interfaces.IContenidoDAO;
import model.Contenido;
import model.Documental;
import model.Pelicula;
import model.Podcast;
import model.Serie;
import config.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContenidoDAO implements IContenidoDAO{
    private ConexionSQLite conexionSQLite;
    
    public ContenidoDAO(){
        this.conexionSQLite = new ConexionSQLite();
    }

    @Override
    public void guardar(Contenido contenido) {
        String sql = "INSERT INTO contenido (tipo, titulo, genero, calidad, "
                + "duracion_minutos, director, temporadas, episodios, fuente, "
                + "host, numero_episodio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setString(1, obtenerTipo(contenido));
            statement.setString(2, contenido.getTitulo());
            statement.setString(3, contenido.getGenero());
            statement.setString(4, contenido.getCalidad());
            statement.setInt(5, contenido.getDuracionMinutos());
            
            if(contenido instanceof Pelicula){
                Pelicula pelicula = (Pelicula)contenido;
                statement.setString(6, pelicula.getDirector());
                statement.setNull(7, java.sql.Types.INTEGER);
                statement.setNull(8, java.sql.Types.INTEGER);
                statement.setNull(9, java.sql.Types.VARCHAR);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.INTEGER);
                
            }else if (contenido instanceof Serie){
                Serie serie = (Serie) contenido;
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setInt(7, serie.getTemporadas());
                statement.setInt(8, serie.getEpisodios());
                statement.setNull(9, java.sql.Types.VARCHAR);
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.INTEGER);
                
            }else if (contenido instanceof Documental){
                Documental documental = (Documental) contenido;
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setNull(7, java.sql.Types.INTEGER);
                statement.setNull(8, java.sql.Types.INTEGER);
                statement.setString(9, documental.getFuente());
                statement.setNull(10, java.sql.Types.VARCHAR);
                statement.setNull(11, java.sql.Types.INTEGER);
                
            }else if (contenido instanceof Podcast){
                Podcast podcast = (Podcast) contenido;
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setNull(7, java.sql.Types.INTEGER);
                statement.setNull(8, java.sql.Types.INTEGER);
                statement.setNull(9, java.sql.Types.VARCHAR);
                statement.setString(10, podcast.getHost());
                statement.setInt(11, podcast.getNumeroEpisodio());    
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar contenido: " + e.getMessage());
        }
    }

    @Override
    public Contenido buscarPorId(int id) {
        String sql = "SELECT * FROM contenido WHERE id = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next())
                return mapearContenido(resultado);
            
        } catch (SQLException e) {
            System.out.println("Error al buscar contenido: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Contenido> listarTodos() {
        ArrayList<Contenido> lista = new ArrayList<>();
        String sql = "SELECT * FROM contenido";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery()){
            while(resultado.next())
                lista.add(mapearContenido(resultado));
            
        } catch (SQLException e) {
            System.out.println("Error al listar contenido: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM contenido WHERE id = ?";
        
        try (Connection conexion = conexionSQLite.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)){
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar contenido: " + e.getMessage());
        }
    }
    
    private String obtenerTipo(Contenido contenido){
        if(contenido instanceof Pelicula)
            return "PELICULA";
        else if(contenido instanceof Serie)
            return "SERIE";
        else if(contenido instanceof Documental)
            return "DOCUMENTAL";
        else
            return "PODCAST";
    }
    
    private Contenido mapearContenido( ResultSet resultado) throws SQLException{
        String tipo = resultado.getString("tipo");
        String titulo = resultado.getString("titulo");
        String genero = resultado.getString("genero");
        String calidad = resultado.getString("calidad");
        int duracion = resultado.getInt("duracion_minutos");
        
        Contenido contenido;
        
        if(tipo.equals("PELICULA"))
            contenido = new Pelicula(titulo, genero, calidad, duracion, 
                             resultado.getString("director"));
        else if(tipo.equals("SERIE"))
            contenido = new Serie(titulo, genero, calidad, duracion, 
                        resultado.getInt("temporadas"), 
                         resultado.getInt("episodios"));
        else if(tipo.equals("DOCUMENTAL"))
            contenido = new Documental(titulo, genero, calidad, duracion, 
                                 resultado.getString("fuente"));
        else
            contenido = new Podcast(titulo, genero, calidad, duracion, 
                               resultado.getString("host"), 
                       resultado.getInt("numero_episodio"));
        contenido.setId(resultado.getInt("id"));
        return contenido;
    }
}