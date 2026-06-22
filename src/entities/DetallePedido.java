
package entities;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import exceptions.AtributoInvalidoException;


public class DetallePedido extends Base {
    
    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        
        if (cantidad <0){ // Validacion segura.
            throw new AtributoInvalidoException("La cantidad debe ser mayor a 0.");
        }
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal(); // El subtotal se recalcula siempre.
    }
    
    public double calcularSubtotal(){
        return (this.producto != null) ? (cantidad * producto.getPrecio()): 0.0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(double subtotal) { //deberia tner setter?
        this.subtotal = subtotal;
    }
    
    
    
    
    @Override
    public String toString() {
        return "DetallePedido{" + "cantidad=" + cantidad + ", subtotal=" + subtotal + ", producto=" + producto + '}';
    }
    
    
}
