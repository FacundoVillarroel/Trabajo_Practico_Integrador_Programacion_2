
package entities;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import enums.Estado;
import enums.FormaPago;
import exceptions.AtributoInvalidoException;
import exceptions.EntidadNoEncontradaException;
import interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import static utils.Validaciones.esCantidadValida;
import static utils.Validaciones.esPrecioValido;


public class Pedido extends Base implements Calculable {
    
    //-----Atributos-----
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private ArrayList<DetallePedido> detallePedidos;
    
    //-----Constructor-----
    public Pedido(LocalDate fecha, Estado estado, double total, FormaPago formaPago) {
        super(); // super() de Base en primera linea
        this.fecha = LocalDate.now();
        this.estado = estado;
        this.total = total;
        this.formaPago = formaPago;
        this.detallePedidos = new ArrayList<>();//Ver Relacion de composicion (1aN) con DetallePedido
       
    }
    
    //-----Getters y setters-----
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }
    
    //-----Metodos de comportamiento-----

    @Override
    public String toString() {
        return String.format("ID: %d | Fecha: %s | Estado: %s | Total: $%.2f | Forma de Pago: %s",
                getId(), fecha, estado, total, formaPago);
    }

    @Override
    public void calcularTotal() {
        double suma = 0;
        for (DetallePedido detallePedido : detallePedidos) {
            //suma += detallePedido.getSubtotal(); Esperar getter de la clase DetallePedido
        }
        this.total = suma;
        System.out.println("Total del pedido: $" + total);
    }
    
    /**
     * Agrega un detallePedido a la lista de detallePedidos, con validaciones previas
     * @param cantidad int Cantidad de productos
     * @param subtotal double Precio subtotal (cant*precioUnitarioProducto)
     * @param producto Objeto de la clase Producto
     */
    public void addDetallePedido(int cantidad, double subtotal, Producto producto) {
        if (!esCantidadValida(cantidad)) {
            throw new AtributoInvalidoException("Error: Atributo invalido.");
        }  
        if (!esPrecioValido(subtotal)) {
            throw new AtributoInvalidoException("Error: Atributo invalido."); //Esta bien dicho atributo?
        }
        if (producto == null) {
            throw new EntidadNoEncontradaException("Error: Entidad nula.");
        }
        /*detallePedidos.add(new DetallePedido(cantidad, subtotal, producto)); 
        DESCOMENTAR CUANDO ESTE LISTO DETALLEPEDIDO*/
        
        //Recalculo el total con el nuevo detallePedido:
        calcularTotal();
    }
    
    /**
     * Lista el detallePedido de un producto especifico
     * @param producto Objeto de la clase Producto
     * @return 
     */
    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        if (producto == null) {
            throw new EntidadNoEncontradaException("Error: Entidad nula.");
        }
        return buscarDetallePorProducto(producto);
    }
    
    /**
     * Elimina un detalleProducto de un producto especifico
     * @param producto Objeto de la clase Producto
     */
    public void deleteDetallePedidoByProducto(Producto producto) {
        if (producto == null) {
            throw new EntidadNoEncontradaException("Error: Entidad nula.");
        }
        DetallePedido detPedidoEnc = buscarDetallePorProducto(producto);
        /*detPedidoEnc.setEliminado(true); //Soft delete - DESCOMENTAR CUANDO ESTE LISTO DETALLEPEDIDO
        detPedidoEnc.setCantidad(0);
        detPedidoEnc.setSubtotal(0);*/
        System.out.println("Detalle pedido eliminado.");
        
        //Recalculo el total sin el detallePedido eliminado:
        calcularTotal();
    }
    
    /**
     * Busca el detallePedido por producto
     * @param producto Objeto de clase Producto
     * @return 
     */
    private DetallePedido buscarDetallePorProducto(Producto producto) {
        for (DetallePedido detallePedido : detallePedidos) {
            /*if (detallePedido.getProducto().getId().equals(producto.getId())) {
                return detallePedido;
            }*/
            //DESCOMENTAR CUANDO ESTE LISTO DETALLEPEDIDO
        }
        throw new EntidadNoEncontradaException("Error: Entidad no encontrada.");
    }
    
}
