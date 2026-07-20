package model;

public abstract class Contenido {
    protected int id;
    protected String titulo;
    protected String genero;
    protected String calidad;
    protected int duracionMinutos;

    public Contenido(String titulo, String genero, String calidad, int duracionMinutos) {
        this.titulo = titulo;
        this.genero = genero;
        this.calidad = calidad;
        this.duracionMinutos = duracionMinutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
    
    public abstract String obtenerDetalles();
}