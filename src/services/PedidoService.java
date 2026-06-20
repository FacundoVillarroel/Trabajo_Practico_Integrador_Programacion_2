
package services;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import static data.Data.pedidos;
import entities.Pedido;
import enums.Estado;
import enums.FormaPago;
import exceptions.AtributoInvalidoException;
import exceptions.EntidadNoEncontradaException;
import java.util.List;
import utils.Validaciones;

public class PedidoService {
    
    /**
     * Crea una lista de pedidos no eliminados
     * @return ArrayList - Lista de pedidos no eliminados
     */
    public static List<Pedido> listar() {
        return Validaciones.filtrarActivos(pedidos);
    }
    
    /**
     * Asigna ID al pedido y lo agrega a la lista
     * @param nuevoPedido Objeto de tipo Pedido que viene del submenu
     */
    public static void crear(Pedido nuevoPedido) {
        //Genero el ID y se lo asigno al nuevo pedido
        Long nuevoID = generadorIDPedido();
        nuevoPedido.setId(nuevoID);
        
        //Agrego a la lista de pedidos
        pedidos.add(nuevoPedido);
        
        //Confirmacion
        System.out.println("\nPedido creado correctamente.");
        System.out.println(nuevoPedido);
        
    }
    
    /**
     * Actualiza el estado, la forma de pago o ambos del pedido
     * @param pedidoAActualizar Objeto Pedido
     * @param estado Estado nuevo estado del pedido
     * @param formaPago FormaPago nueva forma de pago del pedido
     */
    public static void actualizar(Pedido pedidoAActualizar, Estado estado, FormaPago formaPago) {
               
        //Actualizo el pedido validando que los nuevos atributos no sean null
        if(estado != null) {
           pedidoAActualizar.setEstado(estado); 
        } else {
            throw new AtributoInvalidoException("Error: Estado no puede ser nulo.");
        }
        
        if(formaPago != null) {
           pedidoAActualizar.setFormaPago(formaPago);
        } else {
            throw new AtributoInvalidoException("Error: Forma de Pago no puede ser nula.");
        }
        
        //Confirmacion
        System.out.println("\nPedido actualizado correctamente.");
        System.out.println(pedidoAActualizar);
    }
    
    /**
     * Elimina un pedido
     * @param id Long Identificador de un pedido
     */
    public static void eliminar(Long id) {
        //Busco el pedido por id
        Pedido pedidoAEliminar = buscarPedidoPorId(id);
        
        //Elimino el pedido (soft delete)
        pedidoAEliminar.setEliminado(true);
        
        //Confimarcion de eliminacion
        if (pedidoAEliminar.getEliminado()) {
            System.out.println("Pedido eliminado correctamente.");
            System.out.println("ID Pedido Eliminado: " + pedidoAEliminar.getId());
        }
    }
    
    
    /**
     * Busca pedido por ID
     * @param id Long Identificador unico del pedido
     * @return pedido o null
     */
    public static Pedido buscarPedidoPorId(Long id) {
        //Recorro la lista y busco el pedido por id
        for (Pedido pedido : pedidos) {
            if(pedido.getId().equals(id)) {
               return pedido; 
            }
        }
        throw new EntidadNoEncontradaException("Error: Entidad no encontrada.");
    }
        
    
    
    /**
     * Genera el ID para cada pedido
     * @return Long Id del siguiente pedido
     */
    private static Long generadorIDPedido() {
        Long maxId = 0L; //Inicializo el acumulador
        
        //Recorro pedidos con for each
        for (Pedido pedido : pedidos) {
            if(pedido.getId() > maxId) { //Si el id del ped es mayor que max, entonces me lo guardo
                maxId = pedido.getId();
            }
        }
        return maxId + 1; //Y retorno el siguiente
        //Si la lista esta vacia, no recorre el for, y devuelve 0 + 1 = 1, el ID del primer pedido
    }
    
}
