package service;

import model.Contenido;
import model.interfaces.IRecomendable;
import service.interfaces.IRecomendacionService;
import java.util.ArrayList;

public class RecomendacionService implements IRecomendacionService{

    @Override
    public ArrayList<Contenido> recomendar(ArrayList<Contenido> catalogo, String generoPreferido) {
        ArrayList<Contenido> recomendados = new ArrayList<>();
        ArrayList<Integer> puntajes = new ArrayList<>();
        
        for (Contenido contenido : catalogo) {
            if(contenido instanceof IRecomendable){
                IRecomendable iRecomendable = (IRecomendable) contenido;
                int puntaje = iRecomendable.calcularAfinidad(generoPreferido);
                
                recomendados.add(contenido);
                puntajes.add(puntaje);
            }
        }
        
        for (int i = 0; i < recomendados.size() - 1; i++) {
            for (int j = 0; j < recomendados.size() - 1 - i; j++) {
                if(puntajes.get(j) < puntajes.get(j + 1)){
                    int puntajeAux = puntajes.get(j);
                    puntajes.set(j, puntajes.get(j + 1));
                    puntajes.set(j + 1, puntajeAux);
                    
                    Contenido contenidoAux = recomendados.get(j);
                    recomendados.set(j, recomendados.get(j + 1));
                    recomendados.set(j + 1, contenidoAux);
                }
            }
        }
        return recomendados;
    } 
}