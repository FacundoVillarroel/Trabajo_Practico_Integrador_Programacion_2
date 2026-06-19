
package services;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import static data.Data.pedidos;
import entities.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    
    /**
     * Crea una lista de pedidos no eliminados
     * @return ArrayList - Lista de pedidos no eliminados
     */
    public static List<Pedido> listarPedidos() {
        List<Pedido> pedidosNoEliminados = new ArrayList<>(); //Creo Lista vacia
        
        for (Pedido pedido : pedidos) { //Recorro la lista de pedidos
            if (pedido.getEliminado() == false) {
                pedidosNoEliminados.add(pedido); //Agrego aquellos que no han sido eliminados
            }
        }
        
        return pedidosNoEliminados;
        
    }
    
    public static crearPedido(int cantidad, Producto producto) {
        
        
    }
    
}
