
package subMenu;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import exceptions.AtributoInvalidoException;
import java.util.List;
import java.util.Scanner;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;
import utils.Validaciones;
import static utils.Validaciones.esCantidadValida;

public class MenuPedido {
    
    public static void mostrar(Scanner input) {
       String opcion;
    
        do {
        
            System.out.println("""
                
                Gestión de Pedidos y Detalles:
                1. Listar
                2. Crear
                3. Editar
                4. Eliminar
                0. Volver al menú principal
                Seleccione: """);
            
            opcion = input.nextLine();
            
            switch (opcion) {
                case "1": {
                    mostrarPedidos();
                    break;
                }
                
                case "2": {
                    System.out.println("\nListado de Usuarios Activos: ");
                    List<Usuario> usuariosActivos = UsuarioService.listar(); //Creo la lista de usuarios activos
                    
                    if (!usuariosActivos.isEmpty()) { //Si no es vacia...
                        for (Usuario usuario : usuariosActivos) {
                            System.out.println(usuario); //Muestro usuarios activos
                        }
                        System.out.println("\nPara crear un pedido debe ingresar el ID del usuario. ");
                        Long idUsuario = Validaciones.solicitarId(input); //Pido ID
                        
                        Usuario u = UsuarioService.buscarPorId(idUsuario);
                        System.out.println("Usuario encontrado!");
                        
                        //Instancio el pedido
                        Pedido pedido = new Pedido(u);
                        
                        //Permito agregar varios detallePedido con do - while
                        String opc = "";
                        
                        try {
                            do {
                                System.out.println("Ingrese ID del producto: ");
                                Long idProducto = Long.valueOf(input.nextLine());
                                Producto p = ProductoService.buscarPorId(idProducto);
                                                        
                                System.out.println("Ingrese la cantidad: ");
                                int cant = Integer.parseInt(input.nextLine());
                                
                                if (!esCantidadValida(cant)) {
                                    throw new AtributoInvalidoException("Error: Debe ser un entero positivo.");
                                }
                            
                                if (p.getStock() <= 0) {
                                    throw new AtributoInvalidoException("Error: Stock insuficiente.");
                                }
                            
                                //Calculo subtotal
                                double subtotal = cant * p.getPrecio();
                            
                                //Agrego el detallePedido
                                pedido.addDetallePedido(cant, subtotal, p);
                            
                                System.out.println("Desea agregar otro producto? (S/N) ");
                                opc = input.nextLine();
                            
                            } while (!opc.equalsIgnoreCase("N"));
                            
                            //Calculo el total y creo el pedido
                            pedido.calcularTotal();
                            PedidoService.crear(pedido);
                            System.out.println("Pedido creado con éxito! Total: $" + pedido.getTotal());
                            
                        } catch (AtributoInvalidoException aie) {
                            System.out.println("Error al crear pedido: " + aie.getMessage());
                            System.out.println("Pedido cancelado.");
                            
                        } catch (NumberFormatException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        
                    } else {
                        System.out.println("No existen usuarios cargados.");
                    }
                    break;
                   
                }
                
                case "3": {
                    //con un for muestro la lista de pedidos ---> metodo listar()
                    //solicitarId pedido a actualizar
                    //Pregunto que desea actualizar (estado/formaPago)
                    //Llamo a actualizar usando el valor a actualizar y el valor existente del atributo que no se actualiza
                    
                    System.out.println("\nListado de pedidos: ");
                    List<Pedido> pedidosNoElim = PedidoService.listar();
                    
                    
                    if (!pedidosNoElim.isEmpty()) {
                        //Con un for each recorro la lista de pedidos
                        for (Pedido pedido : pedidosNoElim) {
                            System.out.println(pedido);
                        }
                        
                        System.out.println("\nPara actualizar un pedido debe ingresar el ID del pedido. ");
                        Long idPedido = Validaciones.solicitarId(input); //Pido ID
                        
                        //Busco el pedido por ID
                        Pedido pedidoAActualizar = PedidoService.buscarPedidoPorId(idPedido);
                    
                        System.out.println("""
                                       
                        Para actualizar:
                                           
                            1. Estado del Pedido
                            2. Forma de Pago
                                           
                        Seleccione una opcion: """);
                        String o = input.nextLine();
                        
                        switch (o) {
                            case "1": {
                                System.out.println("""
                                       
                                    ESTADOS:
                                           
                                        1. Pendiente
                                        2. Confirmado
                                        3. Terminado
                                        4. Cancelado
                                           
                                    Seleccione un estado: """);
                                String nuevoEstado = input.nextLine();
                                
                                try {
                                    if (nuevoEstado.equals("1")) {
                                    PedidoService.actualizar(pedidoAActualizar, Estado.PENDIENTE, pedidoAActualizar.getFormaPago());
                                    }
                                    if (nuevoEstado.equals("2")) {
                                    PedidoService.actualizar(pedidoAActualizar, Estado.CONFIRMADO, pedidoAActualizar.getFormaPago());
                                    }
                                    if (nuevoEstado.equals("3")) {
                                    PedidoService.actualizar(pedidoAActualizar, Estado.TERMINADO, pedidoAActualizar.getFormaPago());
                                    }
                                    if (nuevoEstado.equals("4")) {
                                    PedidoService.actualizar(pedidoAActualizar, Estado.CANCELADO, pedidoAActualizar.getFormaPago());
                                    }
                                } catch (AtributoInvalidoException aie) {
                                    System.out.println("Error al actualizar pedido: " + aie.getMessage());
                                }
                                                                
                            }
                            case "2": {
                                System.out.println("""
                                       
                                    FORMAS DE PAGO:
                                           
                                        1. Tarjeta
                                        2. Transferencia
                                        3. Efectivo
                                                                                   
                                    Seleccione una forma de pago: """);
                                String nuevoEstado = input.nextLine();
                                
                                try {
                                    if (nuevoEstado.equals("1")) {
                                        PedidoService.actualizar(pedidoAActualizar, pedidoAActualizar.getEstado(), FormaPago.TARJETA);
                                    }
                                    if (nuevoEstado.equals("2")) {
                                        PedidoService.actualizar(pedidoAActualizar, pedidoAActualizar.getEstado(), FormaPago.TRANSFERENCIA);
                                    }
                                    if (nuevoEstado.equals("3")) {
                                        PedidoService.actualizar(pedidoAActualizar, pedidoAActualizar.getEstado(), FormaPago.EFECTIVO);
                                    }
                                    
                                } catch (AtributoInvalidoException aie) {
                                    System.out.println("Error al actualizar pedido: " + aie.getMessage());
                                }
                            }
                            default: {
                                System.out.println("Opción inválida.\n");
                            }      
                        } 
                    } else {
                        System.out.println("No existen pedidos cargados.");
                    }
                    break;
                  
                }
                
                case "4": {
                    //Pido id para buscar el pedido
                    //Pregunto si esta seguro que desea eliminar
                    //llamo a eliminar()
                    //Muestro confirmacion
                    
                    //Listo los pedidos
                    System.out.println("\nListado de pedidos: ");
                    List<Pedido> pedidosNoElim = PedidoService.listar();
                    
                    if (!pedidosNoElim.isEmpty()) {
                        //Con un for each recorro la lista de pedidos
                        for (Pedido pedido : pedidosNoElim) {
                            System.out.println(pedido);
                        }
                        
                        System.out.println("\nPara eliminar un pedido debe ingresar el ID del pedido. ");
                        Long idPedido = Validaciones.solicitarId(input); //Pido ID
                                        
                        System.out.println("¿Está seguro que desea eliminar este pedido? S/N");
                        String aux = input.nextLine();
                        if (aux.equalsIgnoreCase("S")) {
                            PedidoService.eliminar(idPedido);
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                    } else {
                        System.out.println("No existen pedidos cargados.");
                    }
                    break;
                    
                }
                case "0": {
                    System.out.println("Volviendo al menú principal...\n");
                    break;
                }
                
                default: {
                    System.out.println("Opción inválida.\n");
                }
                break;
            }
        
        } while (!opcion.equals("0")); 
        
       
    }
    
    private static void mostrarPedidos() {
        List<Pedido> pedidos = PedidoService.listar(); //Creo la lista de pedidos
        
        if (!pedidos.isEmpty()) { //Si no esta vacia
           System.out.println("\n-----------------------------------------------------------------"); 
           for (Pedido pedido : pedidos) {
                System.out.println(pedido); //Muestro la informacion del pedido
            }
           System.out.println("-----------------------------------------------------------------\n");
        } else {
            System.out.println("\nNo existen pedidos cargados."); //Si esta vacia, muestro mensaje.
        }
        
    }
    
    
}
