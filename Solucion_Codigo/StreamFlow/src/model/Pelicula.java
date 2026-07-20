package model;
import model.interfaces.IDescargable;
import model.interfaces.IRecomendable;
import model.interfaces.IReproducible;

public class Pelicula extends Contenido implements IReproducible, IDescargable, IRecomendable{
    private String director;

    public Pelicula(String titulo, String genero, String calidad, int duracionMinutos, String director) {
        super(titulo, genero, calidad, duracionMinutos);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String obtenerDetalles() {
        return String.format("\nPelicula: %s\nGenero: %s\nDirector: %s\nDuracion: %d min\nCalidad: %s\n", 
                         getTitulo(), getGenero(), director, getDuracionMinutos(), getCalidad());
    }

    @Override
    public String reproducir() {
        return String.format("\nReproduciendo pelicula: %s\n", getTitulo());
    }

    @Override
    public String descargar() {
        return String.format("\nDescargando pelicula: %s\n", getTitulo());
    }

    @Override
    public int calcularAfinidad(String generoPreferido) {
        return getGenero().equalsIgnoreCase(generoPreferido) ? 10 : 2;
    }
}