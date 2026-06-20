
package exceptions;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class AtributoInvalidoException extends RuntimeException {
    public AtributoInvalidoException(String mensaje) {
        super(mensaje);
    }
}