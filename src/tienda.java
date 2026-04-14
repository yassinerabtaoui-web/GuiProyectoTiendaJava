
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author Yassine
 */
public class tienda {

    private Producto[] productos;

    public tienda(int n) {
        productos = new Producto[n];
        for (int i = 0; i < productos.length; i++) {
            productos[i] = new Producto(); 
        }
    }

   
    public void introduceDatos() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < productos.length; i++) {

            System.out.println("\n--- Producto " + (i + 1) + " ---");


            System.out.print("Nombre del producto: ");
            productos[i].setNombre(sc.next());

          
            boolean precioValido = false;
            while (!precioValido) {
                System.out.print("Precio del producto: ");
                double precio = sc.nextDouble();
                try {
                    productos[i].setPrecio(precio);
                    precioValido = true;
                } catch (PrecioInvalidoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Por favor, introduce un precio válido (> 0).");
                }
            }

           
            boolean stockValido = false;
            while (!stockValido) {
                System.out.print("Stock del producto: ");
                int stock = sc.nextInt();
                try {
                    productos[i].setStock(stock);
                    stockValido = true;
                } catch (StockInvalidoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Por favor, introduce un stock válido (>= 0).");
                }
            }
        }
    }

    public void mostrarProductos() {
        System.out.println("\nListado de productos:");
        for (int i = 0; i < productos.length; i++) {
            System.out.println((i + 1) + ". " + productos[i]);
        }
    }

    public void maximoStock() {
        Producto max = productos[0];
        for (int i = 1; i < productos.length; i++) {
            if (productos[i].getStock() > max.getStock()) {
                max = productos[i];
            }
        }
        System.out.println("\nProducto con mayor stock:");
        System.out.println(max);
    }

    public void ordenarProductos() {
        for (int i = 0; i < productos.length - 1; i++) {
            for (int j = 0; j < productos.length - 1 - i; j++) {
                if (productos[j].getStock() < productos[j + 1].getStock()) {
                    Producto temp = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = temp;
                }
            }
        }
    }

    
    public Producto[] getProductos() {
        return productos;
    }
}
 class Producto {

    private String nombre;
    private double precio;
    private int stock;

    // Constructor con validaciones (lanza excepciones checked)
    Producto(String nombre, double precio, int stock)throws PrecioInvalidoException, StockInvalidoException {
          
        this.nombre = nombre;
        setPrecio(precio);
        setStock(stock);
    }

    // Constructor vacío
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
     * 
     * @throws PrecioInvalidoException si el precio es menor o igual a 0
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
     * 
     * @throws StockInvalidoException si el stock es negativo
     */
    public void setStock(int stock) throws StockInvalidoException {
        if (stock < 0) {
            throw new StockInvalidoException(
                    "ERROR: El stock no puede ser negativo. Valor recibido: " + stock);
        }
        this.stock = stock;
    }

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


 class PrecioInvalidoException extends Exception {

    public PrecioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
 class StockInvalidoException extends Exception {

    public StockInvalidoException(String mensaje) {
        super(mensaje);
    }
}