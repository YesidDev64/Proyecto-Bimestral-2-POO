package model;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String generoPreferido;
    private ArrayList<Contenido> favoritos;

    public Usuario(String nombre, String email, String generoPreferido) {
        this.nombre = nombre;
        this.email = email;
        this.generoPreferido = generoPreferido;
        this.favoritos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeneroPreferido() {
        return generoPreferido;
    }

    public void setGeneroPreferido(String generoPreferido) {
        this.generoPreferido = generoPreferido;
    }

    public ArrayList<Contenido> getFavoritos() {
        return favoritos;
    }

    public void agregarFavorito(Contenido contenido) {
        this.favoritos.add(contenido);
    }
}