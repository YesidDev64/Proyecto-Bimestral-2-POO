package controller;

import dao.interfaces.ISuscripcionDAO;
import dao.interfaces.IUsuarioDAO;
import model.Suscripcion;
import model.Usuario;
import service.interfaces.ISuscripcionService;
import java.util.ArrayList;

public class ControladorSuscripcion {
    private ISuscripcionDAO iSuscripcionDAO;
    private ISuscripcionService iSuscripcionService;
    private IUsuarioDAO iUsuarioDAO;
    
    public ControladorSuscripcion(ISuscripcionDAO iSuscripcionDAO, ISuscripcionService iSuscripcionService, 
                                  IUsuarioDAO iUsuarioDAO){
        this.iSuscripcionDAO = iSuscripcionDAO;
        this.iSuscripcionService = iSuscripcionService;
        this.iUsuarioDAO = iUsuarioDAO;
    }
    
    public Suscripcion contratarSuscripcion(int usuarioId, String calidad, String fechaInicio){
        return iSuscripcionService.crearSuscripcion(usuarioId, calidad, fechaInicio);
    }
    
    public Suscripcion buscarSuscripcion(int id){
        return iSuscripcionDAO.buscarPorId(id);
    }
    
    public ArrayList<Suscripcion> listarSuscripciones(){
        return iSuscripcionDAO.listarTodos();
    }
    
    public void eliminarSuscripcion(int id){
        iSuscripcionDAO.eliminar(id);
    }
    
    public double consultarPrecio(String calidad){
        return iSuscripcionService.calcularPrecio(calidad);
    }
    
    public String obtenerNombreUsuario(int usuarioId){
        Usuario usuario = iUsuarioDAO.buscarPorId(usuarioId);
        return (usuario != null) ? usuario.getNombre() : "\nUsuario no encontrado...\n" ;
    }
    
    public ArrayList<Usuario> listarUsuariosDisponibles(){
        return iUsuarioDAO.listarTodos();
    }
}