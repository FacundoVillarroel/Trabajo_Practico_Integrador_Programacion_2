
package utils;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class Validaciones {
   
    public static boolean esTextoVacio(String texto) {
        return texto == null || texto.trim().isEmpty(); //Si el texto está vacío o es null devuelve true
    }
}
