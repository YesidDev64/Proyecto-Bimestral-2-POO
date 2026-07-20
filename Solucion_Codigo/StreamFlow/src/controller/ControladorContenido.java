package controller;

import dao.interfaces.IContenidoDAO;
import model.Contenido;
import service.interfaces.IRecomendacionService;
import java.util.ArrayList;

public class ControladorContenido {
    private IContenidoDAO iContenidoDAO;
    private IRecomendacionService iRecomendacionService;
    
    public ControladorContenido(IContenidoDAO iContenidoDAO, IRecomendacionService iRecomendacionService){
        this.iContenidoDAO = iContenidoDAO;
        this.iRecomendacionService = iRecomendacionService;
    }
    
    public void agregarContenido(Contenido contenido){
        iContenidoDAO.guardar(contenido);
    }
    
    public ArrayList<Contenido> listarCatalogo(){
        return iContenidoDAO.listarTodos();
    }
    
    public Contenido buscarContenido(int id){
        return iContenidoDAO.buscarPorId(id);
    }
    
    public void eliminarContenido(int id){
        iContenidoDAO.eliminar(id);
    }
    
    public ArrayList<Contenido> obtenerRecomendaciones(String generoPreferido){
        ArrayList<Contenido> catalogo = iContenidoDAO.listarTodos();
        return iRecomendacionService.recomendar(catalogo, generoPreferido);
    }
}