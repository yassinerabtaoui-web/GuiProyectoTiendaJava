import java.util.ArrayList;

/**
 * Modelo de la tienda. Contiene la lista de productos.
 * Sigue el UML: productos: ArrayList, añadirProducto(Producto), getProductos()
 *
 * @author Yassine
 */
public class Tienda {

    private ArrayList<Producto> productos;

    public Tienda() {
        productos = new ArrayList<>();
    }

    /** Añade un producto a la tienda */
    public void añadirProducto(Producto p) {
        productos.add(p);
    }

    /** Devuelve la lista de productos */
    public ArrayList<Producto> getProductos() {
        return productos;
    }
}