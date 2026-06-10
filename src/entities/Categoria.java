
package entities;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import java.util.ArrayList;
import java.util.List;


public class Categoria extends Base{
    private static Long contadorId = 0L;
    
    private String nombre;
    private String descripcion;
    private List<Producto> productos;
    
    // Constructor
    public Categoria (String nombre, String descripcion){
        super(++contadorId);
        this.nombre = nombre;
        this.descripcion = descripcion;
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
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    // Metodos
    
    public void agregarProducto(Producto producto){
        productos.add(producto);
        // producto.setCategoria(this);  Cuando se desarrolle la clase producto, se descomenta esa linea.
    }
    
    // toString
    @Override
    public String toString() {
        return "Categoria{" + "id=" + getId() + ", nombre=" + nombre + ", descripcion=" + descripcion + ", productos=" + productos + '}';
    }
    
    
}
