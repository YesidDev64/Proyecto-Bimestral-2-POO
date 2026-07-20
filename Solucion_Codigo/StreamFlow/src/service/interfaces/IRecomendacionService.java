package service.interfaces;

import model.Contenido;
import java.util.ArrayList;

public interface IRecomendacionService {
    ArrayList<Contenido> recomendar(ArrayList<Contenido> catalogo, String generoPreferido);
}