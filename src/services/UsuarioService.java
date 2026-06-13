
package services;

import entities.Usuario;
import enums.Rol;
import data.Data;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;
import utils.Validaciones;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class UsuarioService {

    // Metodo
    public static Usuario crear(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        // Uso de validaciones
        if (Validaciones.esTextoVacio(nombre) || Validaciones.esTextoVacio(mail)) {
            throw new IllegalArgumentException("Error: El nombre y el mail son obligatorios.");
        }

        validarMailDuplicado(mail);

        // Instanciacion 
        Usuario usuario = new Usuario(nombre, apellido, mail, celular, contrasenia, rol);
        
        // Asignacion de ID 
        usuario.setId(generarSiguienteId());

        Data.usuarios.add(usuario);
        return usuario;
    }

    // Devuelve solo los usuarios activos
    public static List<Usuario> listar() {
        List<Usuario> usuariosFiltrados = new ArrayList<>(Data.usuarios);
        usuariosFiltrados.removeIf(usr -> usr.getEliminado());
        return usuariosFiltrados;
    }

    // Modifica si vienen datos nuevos
    public static void editar(Long id, String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        Usuario usrAEditar = buscarPorId(id);
        if (usrAEditar == null) {
            throw new EntidadNoEncontradaException("Error: No existe ningún usuario activo con el ID " + id);
        }

        // Validacion email
        if (mail != null && !mail.isBlank() && !mail.equalsIgnoreCase(usrAEditar.getMail())) {
            validarMailDuplicado(mail);
            usrAEditar.setMail(mail.trim());
        }

        if (!Validaciones.esTextoVacio(nombre)) usrAEditar.setNombre(nombre.trim());
        if (!Validaciones.esTextoVacio(apellido)) usrAEditar.setApellido(apellido.trim());
        if (!Validaciones.esTextoVacio(celular)) usrAEditar.setCelular(celular.trim());
        if (!Validaciones.esTextoVacio(contrasenia)) usrAEditar.setContrasenia(contrasenia.trim());
        if (rol != null) usrAEditar.setRol(rol);
    }

    // Metodo de eliminar
    public static Boolean eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null) return false;

        usuario.setEliminado(true);
        return true;
    }

    private static Usuario buscarPorId(Long id) {
        List<Usuario> usuariosFiltrados = listar(); // Trae solo activos
        for (Usuario usr : usuariosFiltrados) {
            if (id.equals(usr.getId())) {
                return usr;
            }
        }
        return null;
    }

    private static void validarMailDuplicado(String mail) {
        List<Usuario> usuariosFiltrados = listar();
        for (Usuario usr : usuariosFiltrados) {
            if (mail.trim().equalsIgnoreCase(usr.getMail())) {
                throw new EntidadDuplicadaException("Error: Ya existe un usuario registrado con el mail '" + mail + "'");
            }
        }
    }
    
    private static Long generarSiguienteId() {
        long maxId = 0L;
        for (Usuario usr : Data.usuarios) {
            if (usr.getId() > maxId) {
                maxId = usr.getId();
            }
        }
        return maxId + 1L;
    }
}