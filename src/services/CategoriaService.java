package services;

 //@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import entities.Categoria;
import data.Data;
import exceptions.EntidadDuplicadaException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService {
    public static Categoria crear(String nombre, String descripcion) {
        for (Categoria cat : Data.categorias) { //Recorro la lista de categorias creadas, si hay una con el mismo nombre lanzo error
            if (nombre.equalsIgnoreCase(cat.getNombre())){
                throw new EntidadDuplicadaException("Ya existe una categoria con el nombre '" + nombre + "'");
            }
        }
        
        Categoria categoria = new Categoria(nombre, descripcion);
        Data.categorias.add(categoria);
        return categoria;
    }

    public static List<Categoria> listar() {
        List<Categoria> categoriasFiltradas = new ArrayList<>(Data.categorias); //Creo un nuevo array para no modificar la lista original
        categoriasFiltradas.removeIf(cat -> cat.getEliminado()); //Remuevo de la lista las categorias que tengan valor 'true' en atributo eliminado
        return categoriasFiltradas;
    }

    private static Categoria buscarPorId(Long id) {
        Categoria categoriaEncontrada = null;
        for (Categoria categoria : Data.categorias) {
            if (id.equals(categoria.getId())){
                categoriaEncontrada = categoria;
            }
        }
        return categoriaEncontrada;
    }

    public static void editar(Long id) {
        
    }

    public static Boolean eliminar(Long id) { //Retorna true si se eliminó categoria o false si no se eliminó
        Categoria categoria = buscarPorId(id);
        if (categoria == null) return false;

        categoria.setEliminado(true);
        return true;
    }
}
