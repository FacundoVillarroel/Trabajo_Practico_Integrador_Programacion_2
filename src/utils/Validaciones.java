
package utils;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import java.util.Scanner;


public class Validaciones {
   
    public static boolean esTextoVacio(String texto) {
        return texto == null || texto.trim().isEmpty(); //Si el texto está vacío o es null devuelve true
    }
    
    // Valida que los precios no sean menores a cero (Solicitado en HU-PROD-02)
    public static boolean esPrecioValido(Double precio) {
        return precio != null && precio >= 0;
    }

    // Valida que el stock no sea menor a cero (Solicitado en HU-PROD-02)
    public static boolean esStockValido(int stock) {
        return stock >= 0;
    }

    // Valida que la cantidad en un pedido sea estrictamente mayor a cero (Solicitado en HU-PED-02)
    public static boolean esCantidadValida(int cantidad) {
        return cantidad > 0;
    }
    
    //Solicita un id como tipo de dato Long, en caso que sea inválido retorna null
    public static Long solicitarId(Scanner input) { 
        try {
            System.out.print("Ingrese Id: ");
            return Long.valueOf(input.nextLine());
        } catch (NumberFormatException error) {
            System.out.println("Error: Debe ingresar un número id válido");
            return null;
        }
    }
}