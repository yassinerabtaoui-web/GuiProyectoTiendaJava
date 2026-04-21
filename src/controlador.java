/**
 * Controlador del patrón MVC.
 * Conecta VistaVendedor y VistaComprador con la lógica de Tienda.
 *
 * @author Yassine
 */
public class Controlador {

    private Tienda tienda;
    private VistaV vistaV;
    private VistaC vistaC;

    public Controlador(VistaV vistaV, VistaC vistaC) {
        this.tienda = new Tienda();
        this.vistaV = vistaV;
        this.vistaC = vistaC;

        // Registrar listeners en las vistas
        vistaV.setControlador(evt -> añadirProducto());
        vistaC.setControlador(evt -> comprarProducto());
    }

    /** Lee los campos del vendedor, crea el producto y lo añade a la tienda */
    public void añadirProducto() {
        String nombre = vistaV.getNombre();
        String precioStr = vistaV.getPrecio();
        String stockStr = vistaV.getStock();

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vistaV,
                    "Por favor, rellena todos los campos.",
                    "Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

            Producto p = new Producto(nombre, precio, stock);
            tienda.añadirProducto(p);
            actualizarListas();
            vistaV.limpiarCampos();

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(vistaV,
                    "Precio debe ser un número decimal y Stock un número entero.",
                    "Formato incorrecto", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (PrecioInvalidoException e) {
            javax.swing.JOptionPane.showMessageDialog(vistaV,
                    e.getMessage(), "Precio inválido", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (StockInvalidoException e) {
            javax.swing.JOptionPane.showMessageDialog(vistaV,
                    e.getMessage(), "Stock inválido", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Lee la selección del comprador, la cantidad y llama a vender() */
    public void comprarProducto() {
        int indice = vistaC.getIndiceSeleccionado();
        if (indice < 0) {
            javax.swing.JOptionPane.showMessageDialog(vistaC,
                    "Selecciona un producto de la lista.",
                    "Sin selección", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cantidadStr = vistaC.getCantidad();
        if (cantidadStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vistaC,
                    "Introduce la cantidad a comprar.",
                    "Cantidad vacía", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            Producto p = tienda.getProductos().get(indice);
            p.vender(cantidad);
            actualizarListas();
            vistaC.limpiarCampos();
            javax.swing.JOptionPane.showMessageDialog(vistaC,
                    "Compra realizada: " + cantidad + " unidad(es) de " + p.getNombre(),
                    "Compra exitosa", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(vistaC,
                    "La cantidad debe ser un número entero.",
                    "Formato incorrecto", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (StockInvalidoException e) {
            javax.swing.JOptionPane.showMessageDialog(vistaC,
                    e.getMessage(), "Stock insuficiente", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Sincroniza las listas de ambas vistas con los datos de la tienda */
    public void actualizarListas() {
        javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();
        for (Producto p : tienda.getProductos()) {
            modelo.addElement(p.toString());
        }
        vistaV.actualizarLista(modelo);
        vistaC.actualizarLista(modelo);
    }
}
