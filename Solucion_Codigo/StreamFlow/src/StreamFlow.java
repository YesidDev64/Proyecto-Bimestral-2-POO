import config.InicializadorBaseDeDatos;
import controller.ControladorContenido;
import controller.ControladorSuscripcion;
import controller.ControladorUsuario;
import dao.interfaces.IContenidoDAO;
import dao.interfaces.ISuscripcionDAO;
import dao.interfaces.IUsuarioDAO;
import dao.ContenidoDAO;
import dao.SuscripcionDAO;
import dao.UsuarioDAO;
import service.interfaces.IRecomendacionService;
import service.interfaces.ISuscripcionService;
import service.RecomendacionService;
import service.SuscripcionService;
import view.ContenidoView;
import view.UsuarioView;        
import view.SuscripcionView;
import view.MenuPrincipalView;
import java.util.Scanner;

public class StreamFlow {
    public static void main(String[] args) {
        new InicializadorBaseDeDatos().inicializar();
        
        IContenidoDAO iContenidoDAO = new ContenidoDAO();
        IUsuarioDAO iUsuarioDAO = new UsuarioDAO();
        ISuscripcionDAO iSuscripcionDAO = new SuscripcionDAO();
        
        IRecomendacionService iRecomendacionService = new RecomendacionService();
        ISuscripcionService iSuscripcionService = new SuscripcionService(iSuscripcionDAO);
        
        ControladorContenido controladorContenido = new ControladorContenido(iContenidoDAO, iRecomendacionService);
        ControladorSuscripcion controladorSuscripcion = new ControladorSuscripcion(iSuscripcionDAO, iSuscripcionService, iUsuarioDAO);
        ControladorUsuario controladorUsuario = new ControladorUsuario(iUsuarioDAO);
        
        Scanner tcl = new Scanner(System.in);
        ContenidoView contenidoView = new ContenidoView(controladorContenido, tcl);
        SuscripcionView suscripcionView = new SuscripcionView(controladorSuscripcion, tcl);
        UsuarioView usuarioView = new UsuarioView(controladorUsuario, tcl);
        
        MenuPrincipalView menuPrincipal = new MenuPrincipalView(contenidoView, suscripcionView, usuarioView, tcl);
        menuPrincipal.iniciarMenu();
    }
}