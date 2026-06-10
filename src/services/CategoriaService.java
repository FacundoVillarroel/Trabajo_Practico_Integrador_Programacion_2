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
        return null;
    }

    public static void editar(Long id) {

    }

    public static void eliminar(Long id) {

    }
}
