
package utils;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

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
}