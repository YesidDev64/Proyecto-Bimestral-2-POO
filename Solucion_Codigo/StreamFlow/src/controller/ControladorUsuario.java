package controller;
import dao.interfaces.IUsuarioDAO;
import model.Usuario;
import java.util.ArrayList;

public class ControladorUsuario {
    private IUsuarioDAO iUsuarioDAO;
    
    public ControladorUsuario(IUsuarioDAO iUsuarioDAO){
        this.iUsuarioDAO = iUsuarioDAO;
    }
    
    public boolean registrarUsuario(Usuario usuario){
        Usuario existente = iUsuarioDAO.buscarPorEmail(usuario.getEmail());
        
        if(existente != null){
            return false;
        }
        iUsuarioDAO.guardar(usuario);
        return true;
    }
    
    public Usuario buscarUsuario(int id){
        return iUsuarioDAO.buscarPorId(id);
    }
    
    public ArrayList<Usuario> listarUsuarios(){
        return iUsuarioDAO.listarTodos();
    }
    
    public void eliminarUsuario(int id){
        iUsuarioDAO.eliminar(id);
    }
}