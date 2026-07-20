package dao.interfaces;

import model.Suscripcion;
import java.util.ArrayList;

public interface ISuscripcionDAO {
    void guardar(Suscripcion suscripcion);
    Suscripcion buscarPorId(int id);
    ArrayList<Suscripcion> listarTodos();
    void eliminar(int id);
}