/**
 * Clase Producto con validaciones mediante excepciones.
 * Atributos: nombre, precio, stock.
 *
 * @author Yassine
 */
public class Producto {

    private String nombre;
    private double precio;
    private int stock;

    /** Constructor con validaciones */
    public Producto(String nombre, double precio, int stock)
            throws PrecioInvalidoException, StockInvalidoException {
        this.nombre = nombre;
        setPrecio(precio);
        setStock(stock);
    }

    /** Constructor vacío */
    public Producto() {
        this.nombre = "";
        this.precio = 0;
        this.stock = 0;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // ── Setters con validación ────────────────────────────────────────────────
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna el precio del producto.
     * @throws PrecioInvalidoException si el precio es <= 0
     */
    public void setPrecio(double precio) throws PrecioInvalidoException {
        if (precio <= 0) {
            throw new PrecioInvalidoException(
                    "ERROR: El precio debe ser mayor que 0. Valor recibido: " + precio);
        }
        this.precio = precio;
    }

    /**
     * Asigna el stock del producto.
     * @throws StockInvalidoException si el stock es negativo
     */
    public void setStock(int stock) throws StockInvalidoException {
        if (stock < 0) {
            throw new StockInvalidoException(
                    "ERROR: El stock no puede ser negativo. Valor recibido: " + stock);
        }
        this.stock = stock;
    }

    /**
     * Vende una cantidad del producto.
     * @throws StockInvalidoException si cantidad es negativa o mayor que el stock disponible
     */
    public void vender(int cantidad) throws StockInvalidoException {
        if (cantidad < 0) {
            throw new StockInvalidoException(
                    "ERROR: La cantidad a vender no puede ser negativa. Valor recibido: " + cantidad);
        }
        if (cantidad > this.stock) {
            throw new StockInvalidoException(
                    "ERROR: Stock insuficiente. Stock disponible: " + this.stock
                    + ", cantidad solicitada: " + cantidad);
        }
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return nombre + " - " + precio + "€ - " + stock + " unidades";
    }
}
