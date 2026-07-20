package model;
import model.interfaces.IRecomendable;
import model.interfaces.IReproducible;

public class Documental extends Contenido implements IReproducible, IRecomendable{
    private String fuente;

    public Documental(String titulo, String genero, String calidad, int duracionMinutos, String fuente) {
        super(titulo, genero, calidad, duracionMinutos);
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    @Override
    public String obtenerDetalles() {
        return String.format("\nDocumental: %s\nGenero: %s\nFuente: %s\nDuracion: %d\nCalidad: %s\n", 
                getTitulo(), getGenero(), fuente, getDuracionMinutos(), getCalidad());
    }

    @Override
    public String reproducir() {
        return String.format("\nReproduciendo documental: %s\n", getTitulo());
    }

    @Override
    public int calcularAfinidad(String generoPreferido) {
        return getGenero().equalsIgnoreCase(generoPreferido) ? 8 : 1;
    }
}