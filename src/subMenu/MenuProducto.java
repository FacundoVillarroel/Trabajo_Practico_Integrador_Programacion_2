package subMenu;

import entities.Producto;
import java.util.List;
import java.util.Scanner;
import services.ProductoService;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class MenuProducto {
    public static void mostrar(Scanner input) {
        String opcion;

        do {
            System.out.println("""
                
                Gestión de Productos:
                1. Listar
                2. Crear
                3. Editar
                4. Eliminar
                0. Volver al menú principal
                Seleccione: """);

            opcion = input.nextLine();

            switch (opcion) {
                case "1": {
                    mostrarProductos();
                    break;
                }
                case "2": {
                    try {
                        System.out.print("Ingrese Nombre del Producto: ");
                        String nombre = input.nextLine();
                        System.out.print("Ingrese Descripción: ");
                        String descripcion = input.nextLine();
                        System.out.print("Ingrese Precio: ");
                        Double precio = Double.valueOf(input.nextLine());
                        System.out.print("Ingrese Stock: ");
                        int stock = Integer.parseInt(input.nextLine());
                        System.out.print("Ingrese URL de la Imagen: ");
                        String imagen = input.nextLine();
                        System.out.print("¿Está disponible? S/N: ");
                        String dispOp = input.nextLine();
                        
                        Boolean disponible;
                        if (dispOp.equalsIgnoreCase("S")) {
                            disponible = true;
                        } else {
                            disponible = false;
                        }
                        
                        System.out.print("Ingrese ID de la Categoría asociada: ");
                        Long categoriaId = Long.valueOf(input.nextLine());

                        Producto nuevo = ProductoService.crear(nombre, descripcion, precio, stock, imagen, disponible, categoriaId);
                        System.out.println("Producto creado correctamente con id: " + nuevo.getId());
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Formato numérico inválido.");
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                }
                case "3": {
                    mostrarProductos();
                    Long id = solicitarId(input);
                    if (id == null) {
                        break;
                    }

                    try {
                        System.out.print("Nuevo nombre (Enter para mantener el actual): ");
                        String nombre = input.nextLine();
                        System.out.print("Nueva descripción (Enter para mantener la actual): ");
                        String descripcion = input.nextLine();
                        
                        System.out.print("Nuevo precio (Enter para mantener el actual): ");
                        String precioStr = input.nextLine();
                        Double precio;
                        if (precioStr.trim().isEmpty()) {
                            precio = null;
                        } else {
                            precio = Double.valueOf(precioStr);
                        }
                        
                        System.out.print("Nuevo stock (Enter para mantener el actual): ");
                        String stockStr = input.nextLine();
                        Integer stock;
                        if (stockStr.trim().isEmpty()) {
                            stock = null;
                        } else {
                            stock = Integer.valueOf(stockStr);
                        }
                        
                        System.out.print("Nueva imagen (Enter para mantener la actual): ");
                        String imagen = input.nextLine();
                        
                        System.out.print("Nueva disponibilidad (S/N o Enter para mantener la actual): ");
                        String dispStr = input.nextLine();
                        Boolean disponible;
                        if (dispStr.trim().isEmpty()) {
                            disponible = null;
                        } else {
                            if (dispStr.equalsIgnoreCase("S")) {
                                disponible = true;
                            } else {
                                disponible = false;
                            }
                        }
                        
                        System.out.print("Nueva Categoría ID (Enter para mantener la actual): ");
                        String catStr = input.nextLine();
                        Long categoriaId;
                        if (catStr.trim().isEmpty()) {
                            categoriaId = null;
                        } else {
                            categoriaId = Long.valueOf(catStr);
                        }

                        ProductoService.editar(id, nombre, descripcion, precio, stock, imagen, disponible, categoriaId);
                        System.out.println("Producto editado correctamente");
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                }
                case "4": {
                    System.out.println("");
                    mostrarProductos();
                    Long id = solicitarId(input);
                    if (id == null) {
                        break;
                    }

                    System.out.print("¿Está seguro desea eliminar este producto S/N: ");
                    if (input.nextLine().equalsIgnoreCase("S")) {
                        if (ProductoService.eliminar(id)) {
                            System.out.println("Producto eliminado correctamente");
                        } else {
                            System.out.println("No existe producto con id " + id);
                        }
                    } else {
                        System.out.println("Operación cancelada");
                    }
                    break;
                }
            }
        } while (!opcion.equals("0"));
    }
    
    private static void mostrarProductos() {
        // Lista de productos no eliminados
        List<Producto> productos = ProductoService.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados");
        } else {
            System.out.println("\n-----------------------------------------------------------------");
            // Se recorre la lista para imprimir en consola 1 producto por fila
            for (Producto productoInd : productos) {
                System.out.println(productoInd);
            }
            System.out.println("-----------------------------------------------------------------\n");
        }
    }

    private static Long solicitarId(Scanner input) {
        try {
            System.out.print("Ingrese Id: ");
            return Long.valueOf(input.nextLine());
        } catch (NumberFormatException error) {
            System.out.println("Error: Debe ingresar un número id válido");
            return null;
        }
    }
}