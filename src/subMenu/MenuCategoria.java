
package subMenu;

 //@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import entities.Categoria;
import exceptions.EntidadDuplicadaException;
import java.util.List;
import java.util.Scanner;
import services.CategoriaService;


public class MenuCategoria {
    public static void mostrar(Scanner input) {

        String opcion;

        do {
            System.out.println("""
                Gestión de Categorías:
                1. Listar
                2. Crear
                3. Editar
                4. Eliminar
                0. Volver al menú principal
                Seleccione:
                """);

            opcion = input.nextLine();

            switch (opcion) {
                case "1":
                    List<Categoria> categorias = CategoriaService.listar();
                    if (categorias.isEmpty()){
                        System.out.println("No hay categorías cargadas"); 
                    } else {
                        System.out.println(categorias);
                    }
                    break;

                case "2":
                    System.out.print("Ingrese Nombre de Categoria: ");
                    String nombre = input.nextLine();
                    System.out.print("Ingrese la descripción: ");
                    String descripcion = input.nextLine();
                    try {
                        Categoria categoriaCreada = CategoriaService.crear(nombre, descripcion);
                        System.out.println("Categoria creada correctamente con id: " + categoriaCreada.getId());
                    } catch(IllegalArgumentException error) {
                        System.out.println(error.getMessage()); //Imprimo el mensaje de error creado en la clase Categoria
                    } catch(EntidadDuplicadaException error) {
                        System.out.println(error.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("Editar categoría");
                    break;

                case "4":
                    System.out.println("");
                    System.out.println(CategoriaService.listar()); 
                    
                    Long id = null;
                    try {
                        System.out.print("Ingrese Id de Categoria a eliminar: ");
                        id = Long.valueOf(input.nextLine());  
                    } catch (NumberFormatException error){
                        System.out.println("Error: Debe ingresar un número id válido");
                        break; //Si se ingresó un string que no puede ser convertido a Long termino el case
                    }
                    
                    Boolean fueEliminada = CategoriaService.eliminar(id);  //Según la respuesta del service muestro un mensaje u otro.
                    if (fueEliminada) {
                        System.out.println("Categoria eliminada correctamente");
                    } else {
                        System.out.println("No existe categoria con id " + id);
                    }
                    break;

                case "0":
                    break;

                default:
                    System.out.println("Opción inválida.\n");
            }

        } while (!opcion.equals("0"));
    }
}
