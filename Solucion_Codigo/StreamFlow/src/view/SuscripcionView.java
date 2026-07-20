package view;
import controller.ControladorSuscripcion;
import model.Suscripcion;
import model.Usuario;
import java.util.Scanner;
import java.util.ArrayList;

public class SuscripcionView extends VistaBase{
    private ControladorSuscripcion controladorSuscripcion;

    public SuscripcionView(ControladorSuscripcion controladorSuscripcion, Scanner tcl) {
        super(tcl);
        this.controladorSuscripcion = controladorSuscripcion;
    }
    
    public void mostrarMenu(){
        int opcion;
        
        do{
            System.out.println("\nMenu de Suscripciones");
            System.out.println("[1] Contratar suscripcion: ");
            System.out.println("[2] Consultar precio por calidad: ");
            System.out.println("[3] Buscar suscripcion por id: ");
            System.out.println("[4] Listar todas las suscripciones: ");
            System.out.println("[5] Eliminar suscripcion por id: ");
            System.out.println("[0] Volver al menu principal: ");
            System.out.print("Ingresa una opcion: ");
            
            opcion = leerEntero();
            switch(opcion){
                case 1 ->   contratarSuscripcion();
                case 2 ->   consultarPrecio();
                case 3 ->   buscarSuscripcion();
                case 4 ->   listarSuscripciones();
                case 5 ->   eliminarSuscripcion();
                case 0 ->   System.out.println("Volviendo al menu principal");
                default ->  System.out.println("Opcion no valida...");
            }
        }while(opcion != 0);
    }
    
    private void contratarSuscripcion(){
        ArrayList<Usuario> usuarios = controladorSuscripcion.listarUsuariosDisponibles();
        
        if(usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados...");
            System.out.println("Registra uno primero...");
            return;
        }
        
        System.out.println("----------Usuarios Disponibles----------");
        for (Usuario usuario : usuarios) {
            System.out.printf("\nID: %d\nNombre Usuario: %s\n", 
                          usuario.getId(), usuario.getNombre());
        }
        
        System.out.print("Id del usuario: ");
        int usuarioId = leerEntero();
        System.out.print("Calidad deseada (SD/HD/4K): ");
        String calidad = tcl.nextLine();
        System.out.print("Fecha de inicio (ej 12-07-2026): ");
        String fecha = tcl.nextLine();
        
        Suscripcion suscripcion = controladorSuscripcion.contratarSuscripcion(usuarioId, calidad, fecha);
        System.out.println("Suscripcion creada - Precio Mensual: $" + suscripcion.getPrecioMensual());
    }
    
    private void consultarPrecio(){
        System.out.print("Calidad (SD/HD/4K): ");
        String calidad = tcl.nextLine();
        
        double precio = controladorSuscripcion.consultarPrecio(calidad);
        System.out.println("El precio mensual para calidad " + calidad + " es: $" + precio);
    }
    
    private void buscarSuscripcion(){
        System.out.print("Id de la suscripcion: ");
        int id = leerEntero();
        
        Suscripcion suscripcion = controladorSuscripcion.buscarSuscripcion(id);
        
        if(suscripcion == null)
            System.out.println("No se encontro esa suscripcion");
        else{
            System.out.println("Usuario: " + suscripcion.getUsuarioId()
                           + "\nCalidad: " + suscripcion.getCalidad()
                           + "\nPrecio: $" + suscripcion.getPrecioMensual()
                           + "\nInicio: " + suscripcion.getFechaInicio());
        }
    }
    
    private void listarSuscripciones(){
        ArrayList<Suscripcion> suscripciones = controladorSuscripcion.listarSuscripciones();
        
        if(suscripciones.isEmpty()){
            System.out.println("No hay suscripciones registradas");
            return;
        }
        
        System.out.println("\nSuscripciones registradas");
        for (Suscripcion suscripcion : suscripciones) {
            System.out.println("ID: " + suscripcion.getId()
                           + "\nUsuario: " + suscripcion.getUsuarioId()
                           + "\nCalidad: " + suscripcion.getCalidad()
                           + "\nPrecio: $" + suscripcion.getPrecioMensual());
        }
    }
    
    private void eliminarSuscripcion(){
        System.out.print("Id de la suscripcion a eliminar: ");
        int id = leerEntero();
        
        controladorSuscripcion.eliminarSuscripcion(id);
        System.out.println("Suscripcion eliminada");
    }
}