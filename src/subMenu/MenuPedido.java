
package subMenu;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import static data.Data.pedidos;
import static data.Data.usuarios;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
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
                        
                        //Usuario u = UsuarioService.buscarPorId(idUsuario); //Metodo privado - Hablar con creador
                        System.out.println("Usuario encontrado!");
                        
                        //Instancio el pedido
                        //Pedido pedido = new Pedido(u);
                        
                        //Agregar varios detallePedido
                        String opc = "";
                        
                        try {
                            do {
                                System.out.println("Ingrese ID del producto: ");
                                Long idProducto = Long.parseLong(input.nextLine());
                                //Producto p = ProductoService.buscarPorId(idProducto); //Metodo privado - Hablar con creador
                                                        
                                System.out.println("Ingrese la cantidad: ");
                                int cant = Integer.parseInt(input.nextLine());
                                
                                if (!esCantidadValida(cant)) {
                                    throw new AtributoInvalidoException("Error: Debe ser un entero positivo.");
                                }
                            
                                /*if (p.getStock() <= 0) {
                                    throw new AtributoInvalidoException("Error: Stock insuficiente.");
                                }*/
                            
                                //Calculo subtotal
                                //double subtotal = cant * p.getPrecio();
                            
                                //Agrego el detallePedido
                                //pedido.addDetallePedido(cant, subtotal, p);
                            
                                System.out.println("Desea agregar otro producto? (S/N) ");
                                opc = input.nextLine();
                            
                            } while (!opc.equalsIgnoreCase("N"));
                            
                            //Calculo el total y creo el pedido
                            //pedido.calcularTotal();
                            //PedidoService.crear(pedido);
                            //System.out.println("Pedido creado con éxito! Total: $" + pedido.getTotal());
                            
                        } catch (AtributoInvalidoException aie) {
                            System.out.println("Error al crear pedido: " + aie.getMessage());
                            System.out.println("Pedido cancelado.");
                            
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        
                        
                    } else {
                        System.out.println("No existen usuarios cargados.");
                    }
                   
                }
                case "3": {
                    
                }
                case "4": {
                    
                }
                default: {
                    System.out.println("Opción inválida.\n");
                }
            }
        
        } while (!opcion.equals("0")); 
        
       
    }
    
    private static void mostrarPedidos() {
        List<Pedido> pedidos = PedidoService.listarPedidos(); //Creo la lista de pedidos
        
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
