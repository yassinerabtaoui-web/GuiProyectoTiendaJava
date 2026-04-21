/**
 * Excepción lanzada cuando el stock introducido no es válido (negativo o insuficiente).
 *
 * @author Yassine
 */
public class StockInvalidoException extends Exception {

    public StockInvalidoException(String mensaje) {
        super(mensaje);
    }
}
