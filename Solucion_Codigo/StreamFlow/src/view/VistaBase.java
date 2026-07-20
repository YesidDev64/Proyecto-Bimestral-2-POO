package view;
import java.util.Scanner;

public abstract class VistaBase {
    protected Scanner tcl;
    
    public VistaBase(Scanner tcl){
        this.tcl = tcl;
    }
    
    public int leerEntero() {
        while (!tcl.hasNextInt()) {
            System.out.println("Por favor ingresa un numero valido.");
            tcl.next();
        }
        int valor = tcl.nextInt();
        tcl.nextLine(); 
        return valor;
    }
}