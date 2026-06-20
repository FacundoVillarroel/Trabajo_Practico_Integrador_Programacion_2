
package services;

import entities.Usuario;
import enums.Rol;
import data.Data;
import exceptions.AtributoInvalidoException;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;
import utils.Validaciones;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class UsuarioService {

    // Metodo
    public static Usuario crear(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        // Uso de validaciones
        if (Validaciones.esTextoVacio(nombre) || Validaciones.esTextoVacio(mail)) {
            throw new AtributoInvalidoException("Error: El nombre y el mail son obligatorios.");
        }

        // Validación
        validarMailDuplicado(mail);

        // Instanciacion 
        Usuario usuario = new Usuario(nombre, apellido, mail, celular, contrasenia, rol);
        
        // Asignacion de ID 
        usuario.setId(generarSiguienteId());

        // Agregación de objeto a un Array
        Data.usuarios.add(usuario);
        
        return usuario;
    }

    public static List<Usuario> listar() {
    // Creamos una lista nueva vacía para guardar el resultado
    List<Usuario> usuariosActivos = new ArrayList<>();
    
    // Recorremos la lista original de usuarios uno por uno
    for (Usuario usr : Data.usuarios) {
        // Si el usuario NO está eliminado
        if (!usr.getEliminado()) {
            // Lo agregamos a lista de activos
            usuariosActivos.add(usr);
        }
    }
    // Devolvemos la lista de activos
    return usuariosActivos;
}

    // Modifica si vienen datos nuevos
    public static void editar(Long id, String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        // Busqueda de usuario
        Usuario usrAEditar = buscarPorId(id);
        
        // Validación de existencia
        if (usrAEditar == null) {
            throw new EntidadNoEncontradaException("Error: No existe ningún usuario activo con el ID " + id);
        }

        // Validacion email
        if (mail != null && !mail.isBlank() && !mail.equalsIgnoreCase(usrAEditar.getMail())) {
            validarMailDuplicado(mail);
            usrAEditar.setMail(mail.trim());
        }

        // Validaciones
        if (!Validaciones.esTextoVacio(nombre)) usrAEditar.setNombre(nombre.trim());
        if (!Validaciones.esTextoVacio(apellido)) usrAEditar.setApellido(apellido.trim());
        if (!Validaciones.esTextoVacio(celular)) usrAEditar.setCelular(celular.trim());
        if (!Validaciones.esTextoVacio(contrasenia)) usrAEditar.setContrasenia(contrasenia.trim());
        if (rol != null) usrAEditar.setRol(rol);
    }

    // Metodo de eliminar
    public static Boolean eliminar(Long id) {
        // Busqueda de usuario
        Usuario usuario = buscarPorId(id);
        if (usuario == null) return false;
        // Si usuario es encontrado, se setea true en atributo eliminado
        usuario.setEliminado(true);
        return true;
    }

    public static Usuario buscarPorId(Long id) {
        List<Usuario> usuariosFiltrados = listar(); // Trae solo activos
        // Se recorre la lista de usuarios filtrados
        for (Usuario usr : usuariosFiltrados) {
            // Se compara el id ingresado, con los id almacenados
            if (id.equals(usr.getId())) {
                // Si hay coincidencia, se devuelve el usuario
                return usr;
            }
        }
        return null;
    }

    private static void validarMailDuplicado(String mail) {
        List<Usuario> usuariosFiltrados = listar();
        // Se recorre la lista de usuarios filtrados
        for (Usuario usr : usuariosFiltrados) {
            // Se compara el email ingresado, con los email almacenados
            if (mail.trim().equalsIgnoreCase(usr.getMail())) {
                // Si hay coincidencia, se lanza una exception
                throw new EntidadDuplicadaException("Error: Ya existe un usuario registrado con el mail '" + mail + "'");
            }
        }
    }
    
    // Generador incremental de ID 
    private static Long generarSiguienteId() {
        // Inicializamos variable en 0
        long maxId = 0L;
        
        // Recorremos la lista de usuarios
        for (Usuario usr : Data.usuarios) {
            // Comparamos el ID del usuario actual con el ID máximo que tenemos guardado.
            if (usr.getId() > maxId) {
                // Si el ID de este usuario es mayor, lo actualizamos con este nuevo valor
                maxId = usr.getId();
            }
        }
        // Al finalizar el bucle, maxId tiene el ID mayor de la lista, y le sumamos 1 (para generar un ID unico) 
        return maxId + 1L;
    }
}