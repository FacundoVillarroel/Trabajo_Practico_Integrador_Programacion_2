
package subMenu;

 //@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import entities.Categoria;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadNoEncontradaException;
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
                Seleccione: """);

            opcion = input.nextLine();

            switch (opcion) {
                case "1": {//Listar
                    mostrarCategorias();
                    break;
                }
                case "2": { //Crear
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
                }
                case "3": {//Editar
                    mostrarCategorias();
                    
                    Long id = solicitarId(input);
                    
                    System.out.println("Nuevo nombre(Enter para mantener el actual)");
                    String nombre = input.nextLine();
                    
                    System.out.println("Nueva descripcion (Enter para mantener la actual)");
                    String descripcion = input.nextLine();
                    
                    try{
                        CategoriaService.editar(id, nombre, descripcion);
                        System.out.println("Categoría editada correctamente");
                    } catch (EntidadDuplicadaException error){
                        System.out.println(error.getMessage());
                    } catch (EntidadNoEncontradaException error){
                        System.out.println(error.getMessage());
                    }
                    break;
                }
                case "4":{ //Eliminar
                    System.out.println("");
                    mostrarCategorias(); 
                    
                    //Solicito el id con la función auxiliar.
                    Long id = solicitarId(input);
                    if(id == null) {
                        break;
                    }
                    
                    Boolean fueEliminada = CategoriaService.eliminar(id);  //Según la respuesta del service muestro un mensaje u otro.
                    if (fueEliminada) {
                        System.out.println("Categoria eliminada correctamente");
                    } else {
                        System.out.println("No existe categoria con id " + id);
                    }
                    break;
                }
                case "0":{
                    break;
                }
                default:{
                    System.out.println("Opción inválida.\n");
                }
            }

        } while (!opcion.equals("0"));
    }
    
    private static void mostrarCategorias() {
        List<Categoria> categorias = CategoriaService.listar();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorías cargadas");
        } else {
            System.out.println(categorias);
        }
    }
    
    private static Long solicitarId(Scanner input) { //Solicita un id como tipo de dato Long, en caso que sea inválido retorna null
        try {
            System.out.print("Ingrese Id: ");
            return Long.valueOf(input.nextLine());
        } catch (NumberFormatException error) {
            System.out.println("Error: Debe ingresar un número id válido");
            return null;
        }
    }   
}
