package service.interfaces;
import model.Suscripcion;

public interface ISuscripcionService {
    double calcularPrecio(String calidad);
    Suscripcion crearSuscripcion(int usuarioId, String calidad, String fechaInicio);
}
