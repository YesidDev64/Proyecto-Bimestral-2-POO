package service;

import dao.interfaces.ISuscripcionDAO;
import model.Suscripcion;
import service.interfaces.ISuscripcionService;

public class SuscripcionService implements ISuscripcionService{
    private ISuscripcionDAO iSuscripcionDAO;
    
    public SuscripcionService(ISuscripcionDAO iSuscripcionDAO){
        this.iSuscripcionDAO = iSuscripcionDAO;
    }

    @Override
    public double calcularPrecio(String calidad) {
        if(calidad.equalsIgnoreCase("4K"))
            return 15.99;
        else if(calidad.equalsIgnoreCase("HD"))
            return 10.99;
        else if(calidad.equalsIgnoreCase("SD"))
            return 6.99;
        else
            return 8.99;
    }

    @Override
    public Suscripcion crearSuscripcion(int usuarioId, String calidad, String fechaInicio) {
        double precio = calcularPrecio(calidad);
        Suscripcion suscripcion = new Suscripcion(usuarioId, calidad, precio, fechaInicio);
        iSuscripcionDAO.guardar(suscripcion);
        return suscripcion;
    }
}