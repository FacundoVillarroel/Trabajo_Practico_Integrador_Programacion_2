
import java.util.Scanner;
import subMenu.MenuCategoria;
import subMenu.MenuPedido;
import subMenu.MenuProducto;
import subMenu.MenuUsuario;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class Main {

    public static void main(String[] args) {
        String opcion;

        Scanner input= new Scanner(System.in);
        
        do {
            System.out.println("""
                === SISTEMA DE PEDIDOS (FOOD STORE) === 
                1. Categorías 
                2. Productos 
                3. Usuarios 
                4. Pedidos 
                0. Salir 
                Seleccione: 
                """);
            
            opcion = input.nextLine();
            switch (opcion) {
                case "1": //Categorias
                    MenuCategoria.mostrar(input);
                    break;
                    
                case "2": //Productos
                    MenuProducto.mostrar(input);
                    break;
                    
                case "3": //Usuarios
                    MenuUsuario.mostrar(input);
                    break;
                    
                case "4": //Pedidos
                    MenuPedido.mostrar(input);
                    break;
                    
                case "0": //Salir
                    System.out.println("Cerrando sistema de pedidos...");
                    break;
                    
                default:
                    System.out.println("Opción inválida. \n");;
            }
        } while (!opcion.equals("0")); //Mientras que la opción no sea 0 se sigue en el menú
        
    }
}
