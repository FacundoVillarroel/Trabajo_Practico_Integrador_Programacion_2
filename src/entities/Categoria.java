
package entities;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import java.util.ArrayList;
import java.util.List;
import utils.Validaciones;


public class Categoria extends Base{
    private static Long contadorId = 0L;
    
    private String nombre;
    private String descripcion;
    private List<Producto> productos;
    
    // Constructor
    public Categoria (String nombre, String descripcion){
        super(++contadorId);
        
        // Se valida que nombre y descripción no estén vacios o sean null.
        if(Validaciones.esTextoVacio(nombre)) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        if(Validaciones.esTextoVacio(descripcion)) {
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía");
        }
        
        this.nombre = nombre.trim();
        this.descripcion = descripcion.trim();
        this.productos = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    
    // Setters

    public void setNombre(String nombre) {
        if(Validaciones.esTextoVacio(nombre)) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public void setDescripcion(String descripcion) {
        if(Validaciones.esTextoVacio(descripcion)) {
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía");
        }
        this.descripcion = descripcion.trim();
    }
    
    // Metodos
    
    public void agregarProducto(Producto producto){
        //Se valida que el producto no sea nulo ni esté duplicado
        if(producto == null){
            throw new IllegalArgumentException("No se puede agregar un producto nulo");
        }
        if(productos.contains(producto)) {
            throw new IllegalArgumentException("Este producto ya existe en esta categoría");
        }
        productos.add(producto);
        producto.setCategoria(this);  
    }
    
    // toString
    @Override
    public String toString() {
        return String.format(
                "CATEGORIA ID %d: %s | Descripcion: %s",
                getId(), nombre, descripcion);
    }
    
    
}
