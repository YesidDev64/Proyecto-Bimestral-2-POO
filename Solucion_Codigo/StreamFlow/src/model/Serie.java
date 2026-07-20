package model;
import model.interfaces.IDescargable;
import model.interfaces.IRecomendable;
import model.interfaces.IReproducible;

public class Serie extends Contenido implements IReproducible, IDescargable, IRecomendable{
    private int temporadas;
    private int episodios;

    public Serie(String titulo, String genero, String calidad, 
                 int duracionMinutos, int temporadas, int episodios) {
        super(titulo, genero, calidad, duracionMinutos);
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    @Override
    public String obtenerDetalles() {
        return String.format("\nSerie: %s\nGenero: %s\nTemporadas: %s\nEpisodios: %s\nCalidad: %s\n", 
                         getTitulo(), getGenero(), temporadas, episodios, getCalidad());
    }

    @Override
    public String reproducir() {
        return String.format("\nReproduciendo serie: %s\n", getTitulo());
    }

    @Override
    public String descargar() {
        return String.format("\nDescargando seri: %s\n", getTitulo());
    }

    @Override
    public int calcularAfinidad(String generoPreferido) {
        return getGenero().equalsIgnoreCase(generoPreferido) ? 10 : 2;
    } 
}