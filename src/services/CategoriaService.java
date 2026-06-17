package services;

 //@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import entities.Categoria;
import data.Data;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService {
    public static Categoria crear(String nombre, String descripcion) {
        validarNombreDuplicado(nombre);
        
        Categoria categoria = new Categoria(nombre, descripcion);
        Data.categorias.add(categoria);
        return categoria;
    }

    public static List<Categoria> listar() {
        List<Categoria> categoriasFiltradas = new ArrayList<>(Data.categorias); //Creo un nuevo array para no modificar la lista original
        categoriasFiltradas.removeIf(cat -> cat.getEliminado()); //Remuevo de la lista las categorias que tengan valor 'true' en atributo eliminado
        return categoriasFiltradas;
    }

    public static void editar(Long id, String nombre, String descripcion) {
        Categoria categoriaAEditar = buscarPorId(id);
        if (categoriaAEditar == null){
            throw new EntidadNoEncontradaException("Categoria inexistente");
        }//Solo si el nombre no está vacío ni es igual al anteriór lo editamos
        if(!nombre.isBlank() && !nombre.equalsIgnoreCase(categoriaAEditar.getNombre())) {
            validarNombreDuplicado(nombre); //Antes de editarlo validamos que no sea igual al nombre de otra categoria.
            categoriaAEditar.setNombre(nombre);
        }
        if(!descripcion.isBlank()){ //Si la descripcion no está vacía se actualiza
            categoriaAEditar.setDescripcion(descripcion);
        }
    }

    public static void eliminar(Long id) { 
        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            throw new EntidadNoEncontradaException("No existe categoria con id: " + id);
        }
        if (!categoria.getProductos().isEmpty()){ //Si tiene productos asignados a su categoria entonces lanza error.
            throw new RuntimeException("Error: Esta categoría tiene productos asignados, se deben desvincular antes de eliminar la categoría");
        }
        categoria.setEliminado(true);
    }
    
    private static Categoria buscarPorId(Long id) {
        List<Categoria> categoriasFiltradas = listar();
        Categoria categoriaEncontrada = null;
        for (Categoria categoria : categoriasFiltradas) {
            if (id.equals(categoria.getId())){
                categoriaEncontrada = categoria;
            }
        }
        return categoriaEncontrada;
    }
    
    private static void validarNombreDuplicado (String nombre){
        List<Categoria> categoriasFiltradas = listar();
        for (Categoria cat : categoriasFiltradas) { //Recorro la lista de categorias creadas, si hay una con el mismo nombre lanzo error
            if (nombre.equalsIgnoreCase(cat.getNombre())){
                throw new EntidadDuplicadaException("Ya existe una categoria con el nombre '" + nombre + "'");
            }
        }
    }
}
