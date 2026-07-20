package model;

public class Suscripcion {
    private int id;
    private int usuarioId;
    private String calidad;
    private double precioMensual;
    private String fechaInicio;

    public Suscripcion(int usuarioId, String calidad, double precioMensual, String fechaInicio) {
        this.usuarioId = usuarioId;
        this.calidad = calidad;
        this.precioMensual = precioMensual;
        this.fechaInicio = fechaInicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}