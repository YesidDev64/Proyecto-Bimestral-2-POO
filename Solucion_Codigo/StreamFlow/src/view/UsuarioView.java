package view;
import controller.ControladorUsuario;
import model.Usuario;
import java.util.Scanner;
import java.util.ArrayList;

public class UsuarioView extends VistaBase{
    private ControladorUsuario controladorUsuario;

    public UsuarioView(ControladorUsuario controladorUsuario, Scanner tcl) {
        super(tcl);
        this.controladorUsuario = controladorUsuario;
    }
    
    public void mostrarMenu(){
        int opcion;
        
        do{
            System.out.println("\nMenu de Usuarios");
            System.out.println("[1] Registrar usuario: ");
            System.out.println("[2] Buscar usuario por id: ");
            System.out.println("[3] Listar todos los usuarios: ");
            System.out.println("[4] Eliminar usuario por id: ");
            System.out.println("[0] Volver al menu principal");
            System.out.print("Ingresa una opcion: ");
            
            opcion = leerEntero();
            
            switch(opcion){
                case 1 ->   registrarUsuario();
                case 2 ->   buscarUsuario();
                case 3 ->   listarUsuarios();
                case 4 ->   eliminarUsuario();
                case 0 ->   System.out.println("Volviendo al menu principal...");
                default ->  System.out.println("Opcion no valida...");
            }
        }while(opcion != 0);
    }
    
    private void registrarUsuario(){
        System.out.print("Nombre: ");
        String nombre = tcl.nextLine();
        System.out.print("Email: ");
        String email = tcl.nextLine();
        System.out.print("Genero preferido: ");
        String generoPreferido = tcl.nextLine();
        
        Usuario usuario = new Usuario(nombre, email, generoPreferido);
        boolean registrado = controladorUsuario.registrarUsuario(usuario);
        
        if(registrado)
            System.out.println("Usuario registrado correctamente");
        else
            System.out.println("Ya existe un usuario con el mismo correo...");
    }
    
    private void buscarUsuario(){
        ArrayList<Usuario> usuarios = controladorUsuario.listarUsuarios();
        
        if(usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados todavia...");
            return;
        }
        
        System.out.println("\n----------Usuarios registrados----------");
        for (Usuario usuario : usuarios) {
            System.out.printf("\nId: %s\nNombre Usuario: %s", 
                    usuario.getId(), usuario.getNombre());
        }
        System.out.print("Ingresa el id del usuario que quieres ver: ");
        int id = leerEntero();
        
        Usuario usuario = controladorUsuario.buscarUsuario(id);
        
        if(usuario == null)
            System.out.println("No se encontro ese usuario");
        else{
            System.out.println("Nombre: " + usuario.getNombre()
                           + "\nEmail: " + usuario.getEmail()
                           + "\nGenero preferido: " + usuario.getGeneroPreferido());
        }
    }
    
    private void listarUsuarios(){
        ArrayList<Usuario> usuarios = controladorUsuario.listarUsuarios();
        
        if(usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados todavia...");
            return;
        }
        
        System.out.println("\n----------Usuarios registrados----------");
        for (Usuario usuario : usuarios) {
            System.out.printf("\nId: %d\nNombre Usuario: %s\nEmail: %s", 
                          usuario.getId(), usuario.getNombre(), 
                          usuario.getEmail());
        }
    }
    
    private void eliminarUsuario(){
        System.out.print("Id del usuario a eliminar: ");
        int id = leerEntero();
        
        controladorUsuario.eliminarUsuario(id);
        System.out.println("Usuario eliminado");
    }
}