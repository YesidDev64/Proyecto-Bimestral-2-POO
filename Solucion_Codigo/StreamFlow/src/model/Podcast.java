package model;
import model.interfaces.IDescargable;
import model.interfaces.IRecomendable;
import model.interfaces.IReproducible;

public class Podcast extends Contenido implements IReproducible, IDescargable, IRecomendable{
    private String host;
    private int numeroEpisodio;

    public Podcast(String titulo, String genero, String calidad, int duracionMinutos, 
                   String host, int numeroEpisodio) {
        super(titulo, genero, calidad, duracionMinutos);
        this.host = host;
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    @Override
    public String obtenerDetalles() {
        return String.format("\nPodcast: %s\nGenero: %s\nHost: %s\nEpisodio: %d\nCalidad de audio: %s\n", 
                getTitulo(), getGenero(), host, numeroEpisodio, getCalidad());
    }

    @Override
    public String reproducir() {
        return String.format("\nReproduciendo podcast: %s\nEpisodio: %s\n", 
                         getTitulo(), numeroEpisodio);
    }

    @Override
    public String descargar() {
        return String.format("\nDescargando podcast: %s\nEpisodio: %s\n", 
                         getTitulo(), numeroEpisodio);
    }

    @Override
    public int calcularAfinidad(String generoPreferido) {
        return getGenero().equalsIgnoreCase(generoPreferido) ? 7 : 1;
    }
}