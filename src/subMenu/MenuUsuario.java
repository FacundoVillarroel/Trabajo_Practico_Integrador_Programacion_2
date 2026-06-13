
package subMenu;

import entities.Usuario;
import enums.Rol;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadNoEncontradaException;
import java.util.List;
import java.util.Scanner;
import services.UsuarioService;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class MenuUsuario {
    
    public static void mostrar(Scanner input) {
        String opcion;

        do {
            System.out.println("""
                
                Gestión de Usuarios:
                1. Listar
                2. Crear
                3. Editar
                4. Eliminar
                0. Volver al menú principal
                Seleccione: """);

            opcion = input.nextLine();

            switch (opcion) {
                case "1": { // Listar
                    mostrarUsuarios();
                    break;
                }
                case "2": { // Crear
                    try {
                        System.out.print("Ingrese Nombre: ");
                        String nombre = input.nextLine();
                        System.out.print("Ingrese Apellido: ");
                        String apellido = input.nextLine();
                        System.out.print("Ingrese Mail: ");
                        String mail = input.nextLine();
                        System.out.print("Ingrese Celular: ");
                        String celular = input.nextLine();
                        System.out.print("Ingrese Contraseña: ");
                        String contrasenia = input.nextLine();
                        
                        System.out.print("Seleccione Rol (1. ADMIN, 2. USUARIO): ");
                        String rolOp = input.nextLine();
                        
                        Rol rol;
                        if (rolOp.equals("1")) {
                            rol = Rol.ADMIN;
                        } else if (rolOp.equals("2")) {
                            rol = Rol.USUARIO;
                        } else {
                            throw new IllegalArgumentException("Error: Opción de rol inválida. Debe elegir 1 o 2.");
                        }
                        
                        Usuario nuevo = UsuarioService.crear(nombre, apellido, mail, celular, contrasenia, rol);
                        System.out.println("Usuario creado correctamente con id: " + nuevo.getId());
                    } catch (EntidadDuplicadaException | IllegalArgumentException error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                }
                case "3": { // Editar
                    mostrarUsuarios();
                    Long id = solicitarId(input);
                    if (id == null) break;

                    try {
                        System.out.print("Nuevo Nombre (Enter para mantener el actual): ");
                        String nombre = input.nextLine();
                        System.out.print("Nuevo Apellido (Enter para mantener el actual): ");
                        String apellido = input.nextLine();
                        System.out.print("Nuevo Mail (Enter para mantener el actual): ");
                        String mail = input.nextLine();
                        System.out.print("Nuevo Celular (Enter para mantener el actual): ");
                        String celular = input.nextLine();
                        System.out.print("Nueva Contraseña (Enter para mantener el actual): ");
                        String contrasenia = input.nextLine();
                        
                        System.out.print("Nuevo Rol (1. ADMIN, 2. USUARIO, Enter para mantener el actual): ");
                        String rolOp = input.nextLine();

                        Rol rol;
                        if (rolOp.trim().isEmpty()) {
                            rol = null; // Mantiene el actual
                        } else {
                            if (rolOp.equals("1")) {
                                rol = Rol.ADMIN;
                            } else if (rolOp.equals("2")) {
                                rol = Rol.USUARIO;
                            } else {
                                throw new IllegalArgumentException("Error: Opción de rol inválida. Debe elegir 1 o 2.");
                            }
                        }
                        
                        UsuarioService.editar(id, nombre, apellido, mail, celular, contrasenia, rol);
                        System.out.println("Usuario editado correctamente");
                    } catch (EntidadDuplicadaException | EntidadNoEncontradaException error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                }
                case "4": { // Eliminar
                    System.out.println("");
                    mostrarUsuarios();
                    
                    Long id = solicitarId(input);
                    if (id == null) break;

                    System.out.print("¿Está seguro desea eliminar este usuario? S/N: ");
                    String confirmacion = input.nextLine();
                    
                    if (confirmacion.equalsIgnoreCase("S")) {
                        Boolean fueEliminado = UsuarioService.eliminar(id);
                        if (fueEliminado) {
                            System.out.println("Usuario eliminado correctamente");
                        } else {
                            System.out.println("No existe usuario con id " + id);
                        }
                    } else {
                        System.out.println("Operación cancelada");
                    }
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.out.println("Opción inválida.\n");
                }
            }
        } while (!opcion.equals("0"));
    }

    private static void mostrarUsuarios() {
        // Lista de usuarios activos
        List<Usuario> usuarios = UsuarioService.listar();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios cargados");
        } else {
            System.out.println("\n-----------------------------------------------------------------");
            // Se recorre la lista para imprimir en consola 1 usuario por fila
            for (Usuario usuarioInd : usuarios) {
                System.out.println(usuarioInd);
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