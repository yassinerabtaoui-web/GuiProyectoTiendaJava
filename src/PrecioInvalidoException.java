/**
 * Excepción lanzada cuando el precio introducido no es válido (<= 0).
 *
 * @author Yassine
 */
public class PrecioInvalidoException extends Exception {

    public PrecioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
