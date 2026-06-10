
package subMenu;

 //@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel
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
                    System.out.println(CategoriaService.listar());
                    break;

                case "2":
                    System.out.print("Ingrese Nombre de Categoria: ");
                    String nombre = input.nextLine();
                    System.out.print("Ingrese la descripción: ");
                    String descripcion = input.nextLine();
                    CategoriaService.crear(nombre, descripcion);
                    break;

                case "3":
                    System.out.println("Editar categoría");
                    break;

                case "4":
                    System.out.println("Eliminar categoría");
                    break;

                case "0":
                    break;

                default:
                    System.out.println("Opción inválida.\n");
            }

        } while (!opcion.equals("0"));
    }
}
