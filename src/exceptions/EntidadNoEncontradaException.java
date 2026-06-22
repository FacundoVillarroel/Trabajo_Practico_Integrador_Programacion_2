
package exceptions;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
