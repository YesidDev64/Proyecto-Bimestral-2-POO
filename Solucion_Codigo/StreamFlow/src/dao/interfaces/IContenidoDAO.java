package dao.interfaces;
import model.Contenido;
import java.util.ArrayList;

public interface IContenidoDAO {
    void guardar(Contenido contenido);
    Contenido buscarPorId(int id);
    ArrayList<Contenido> listarTodos();
    void eliminar(int id);
}