package view;
import controller.ControladorContenido;
import model.Contenido;
import model.Documental;
import model.Pelicula;
import model.Podcast;
import model.Serie;
import java.util.ArrayList;
import java.util.Scanner;

public class ContenidoView extends VistaBase{
    private ControladorContenido controladorContenido;

    public ContenidoView(ControladorContenido controladorContenido, Scanner tcl) {
        super(tcl);
        this.controladorContenido = controladorContenido;
    }
    
    public void mostrarMenu(){
        int opcion;
        do{
            System.out.println("\nMenu de Contenido");
            System.out.println("[1] Agregar pelicula: ");
            System.out.println("[2] Agregar serie: ");
            System.out.println("[3] Agregar documental: ");
            System.out.println("[4] Agregar podcast: ");
            System.out.println("[5] Listar catalogo completo: ");
            System.out.println("[6] Buscar contenido por id: ");
            System.out.println("[7] Eliminar contenido por id: ");
            System.out.println("[8] Ver recomendaciones por genero: ");
            System.out.println("[0] Volver al menu principal: ");
            System.out.print("Ingresa una opcion: ");
            
            opcion = leerEntero();
            
            switch(opcion){
                case 1 ->   agregarPelicula();
                case 2 ->   agregarSerie();
                case 3 ->   agregarDocumental();
                case 4 ->   agregarPodcast();
                case 5 ->   listarCatalogo();
                case 6 ->   buscarContenido();
                case 7 ->   eliminarContenido();
                case 8 ->   verRecomendaciones();
                case 0 ->   System.out.println("Volviendo al menu principal...");
                default ->  System.out.println("Opcion no valida...");
            }
        }while(opcion != 0);
    }
    
    private void agregarPelicula(){
        System.out.print("Titulo: ");
        String titulo = tcl.nextLine();
        System.out.print("Genero: ");
        String genero = tcl.nextLine();
        System.out.print("Calidad (SD/HD/4K): ");
        String calidad = tcl.nextLine();
        System.out.print("Duracion en minutos: ");
        int duracion = leerEntero();
        System.out.print("Director: ");
        String director = tcl.nextLine();
        
        Contenido pelicula = new Pelicula(titulo, genero, calidad, duracion, director);
        controladorContenido.agregarContenido(pelicula);
        System.out.println("Pelicula agregada correctamente");
           
    }
    
    private void agregarSerie(){
        System.out.print("Titulo: ");
        String titulo = tcl.nextLine();
        System.out.print("Genero: ");
        String genero = tcl.nextLine();
        System.out.print("Calidad (SD/HD/4K): ");
        String calidad = tcl.nextLine();
        System.out.print("Duracion en minutos: ");
        int duracion = leerEntero();
        System.out.print("Numero de temporadas: ");
        int temporadas = leerEntero();
        System.out.print("Numero de episodios: ");
        int episodios = leerEntero();
        
        Contenido serie = new Serie(titulo, genero, calidad, duracion, temporadas, episodios);
        controladorContenido.agregarContenido(serie);
        System.out.println("Serie agregada correctamente");
    }
    
    private void agregarDocumental(){
        System.out.print("Titulo: ");
        String titulo = tcl.nextLine();
        System.out.print("Genero: ");
        String genero = tcl.nextLine();
        System.out.print("Calidad (SD/HD/4K): ");
        String calidad = tcl.nextLine();
        System.out.print("Duracion en minutos: ");
        int duracion = leerEntero();
        System.out.print("Fuente/Productora: ");
        String fuente = tcl.nextLine();
        
        Contenido documental = new Documental(titulo, genero, calidad, duracion, fuente);
        controladorContenido.agregarContenido(documental);
        System.out.println("Documental agregado correctamente");
    }
    
    private void agregarPodcast(){
        System.out.print("Titulo: ");
        String titulo = tcl.nextLine();
        System.out.print("Genero: ");
        String genero = tcl.nextLine();
        System.out.print("Calidad de audio (Baja/Media/Alta): ");
        String calidad = tcl.nextLine();
        System.out.print("Duracion en minutos: ");
        int duracion = leerEntero();
        System.out.print("Host: ");
        String host = tcl.nextLine();
        System.out.print("Numero de episodio: ");
        int numeroEpisodio = leerEntero();
        
        Contenido podcast = new Podcast(titulo, genero, calidad, duracion, host, numeroEpisodio);
        controladorContenido.agregarContenido(podcast);
        System.out.println("Podcast agregado correctamente");
    }
    
    private void listarCatalogo(){
        ArrayList<Contenido> catalogo = controladorContenido.listarCatalogo();
        
        if(catalogo.isEmpty()){
            System.out.println("El catalogo esta vacio...");
            return;
        }
        
        System.out.println("\nCatalogo Completo");
        for (Contenido contenido : catalogo) 
            System.out.println("ID: " + contenido.getId() + " | " + contenido.obtenerDetalles());
    }
    
    private void buscarContenido(){
        System.out.println("Ingresa el id del contenido: ");
        int id = leerEntero();
        
        Contenido contenido = controladorContenido.buscarContenido(id);
        
        if(contenido == null)
            System.out.println("No se encontro contenido con ese id...");
        else
            System.out.println(contenido.obtenerDetalles());
    }
    
    private void eliminarContenido(){
        System.out.print("Ingresa el id del contenido a eliminar: ");
        int id = leerEntero();
        
        controladorContenido.eliminarContenido(id);
        System.out.println("Contenido eliminado");
    }
    
    private void verRecomendaciones(){
        System.out.print("Ingresa tu genero preferido: ");
        String genero = tcl.nextLine();
        
        ArrayList<Contenido> recomendados = controladorContenido.obtenerRecomendaciones(genero);
        
        if(recomendados.isEmpty()){
            System.out.println("No hay recomendaciones disponibles");
            return;
        }
        
        System.out.println("\nRecomendaciones para genero: " + genero);
        for (Contenido contenido : recomendados) 
            System.out.println(contenido.obtenerDetalles());
    }
}