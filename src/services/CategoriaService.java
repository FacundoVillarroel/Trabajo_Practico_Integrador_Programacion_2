package services;

 //@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import entities.Categoria;
import data.Data;
import java.util.List;

public class CategoriaService {
    public static void crear(String nombre, String descripcion) {
        Categoria categoria = new Categoria(nombre, descripcion);
        Data.categorias.add(categoria);
    }

    public static List<Categoria> listar() {
        return Data.categorias;
    }

    public static Categoria buscarPorId(Long id) {
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

    public static String eliminar(Long id) {
        Categoria categoriaEncontrada = buscarPorId(id);
        if (categoriaEncontrada != null) {
            categoriaEncontrada.setEliminado(true);
            return "Categoria eliminada correctamente";
        } else {
            return "No existe categoria con id " + id;
        }
    }
}
