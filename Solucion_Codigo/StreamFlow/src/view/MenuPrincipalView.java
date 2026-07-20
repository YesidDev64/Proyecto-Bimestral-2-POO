package view;
import java.util.Scanner;

public class MenuPrincipalView extends VistaBase{
    private ContenidoView contenidoView;
    private SuscripcionView suscripcionView;
    private UsuarioView usuarioView;

    public MenuPrincipalView(ContenidoView contenidoView, 
                             SuscripcionView suscripcionView, UsuarioView usuarioView, 
                             Scanner tcl) {
        super(tcl);
        this.contenidoView = contenidoView;
        this.suscripcionView = suscripcionView;
        this.usuarioView = usuarioView;
    }
    
    public void iniciarMenu(){
        int opcion;
        
        do{
            System.out.println("\n----------StreamFlow----------");
            System.out.println("[1] Gestionar contenido: ");
            System.out.println("[2] Gestionar suscripciones: ");
            System.out.println("[3] Gestionar usuarios: ");
            System.out.println("[0] Salir: ");
            System.out.print("Ingrese una opcion: ");
            
            opcion = leerEntero();
            
            switch(opcion){
                case 1 ->   contenidoView.mostrarMenu();
                case 2 ->   suscripcionView.mostrarMenu();
                case 3 ->   usuarioView.mostrarMenu();
                case 0 ->   System.out.println("Gracias por usar SteamFlow");
                default ->  System.out.println("Opcion no valida...");
            }
        }while(opcion != 0);
    }
}