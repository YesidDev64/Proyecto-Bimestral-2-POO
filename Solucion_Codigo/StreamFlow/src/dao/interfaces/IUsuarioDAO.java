package dao.interfaces;

import model.Usuario;
import java.util.ArrayList;

public interface IUsuarioDAO {
    void guardar(Usuario usuario);
    Usuario buscarPorId(int id);
    Usuario buscarPorEmail(String email);
    ArrayList<Usuario> listarTodos();
    void eliminar(int id);
}
