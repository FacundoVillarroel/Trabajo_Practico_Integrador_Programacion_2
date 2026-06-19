
package services;

import entities.Producto;
import entities.Categoria;
import data.Data;
import exceptions.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;
import utils.Validaciones;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

public class ProductoService {

    // Metodo
    public static Producto crear(String nombre, String descripcion, Double precio, int stock, String imagen, Boolean disponible, Long categoriaId) {
        // Uso de validaciones
        if (Validaciones.esTextoVacio(nombre)) {
            throw new IllegalArgumentException("Error: El nombre del producto no puede estar vacío.");
        }
        if (!Validaciones.esPrecioValido(precio)) {
            throw new IllegalArgumentException("Error: El precio no puede ser menor a 0.");
        }
        if (!Validaciones.esStockValido(stock)) {
            throw new IllegalArgumentException("Error: El stock no puede ser menor a 0.");
        }

        // Busqueda de categoria asociada
        Categoria categoriaAsociada = null;
        for (Categoria cat : CategoriaService.listar()) {
            if (cat.getId().equals(categoriaId)) {
                categoriaAsociada = cat;
                break;
            }
        }

        // Si no hay categoria asociada, se lanza Exception
        if (categoriaAsociada == null) {
            throw new EntidadNoEncontradaException("Error: La categoría con ID " + categoriaId + " no existe o fue eliminada.");
        }

        // Instanciacion
        Producto producto = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoriaAsociada);
        producto.setId(generarSiguienteId());
        
        // Almacenamiento en memoria
        Data.productos.add(producto);
        return producto;
    }

    // Lista elementos no eliminados
    public static List<Producto> listar() {
    // Creamos una lista nueva vacía para guardar el resultado
    List<Producto> productosActivos = new ArrayList<>();
    
    // Recorremos la lista original de productos uno por uno    
    for (Producto prod : Data.productos) {
        // Si el prudcto NO está eliminado
        if (!prod.getEliminado()) {
            // Lo agregamos a lista de activos
            productosActivos.add(prod);
        }
    }
    // Devolvemos la lista de activos
    return productosActivos;
    }
    
    // Modifica si vienen datos nuevos
    public static void editar(Long id, String nombre, String descripcion, Double precio, Integer stock, String imagen, Boolean disponible, Long categoriaId) {
        // Busqueda de producto
        Producto prodAEditar = buscarPorId(id);
       
        // Validacion de existencia
        if (prodAEditar == null) {
            throw new EntidadNoEncontradaException("Error: No existe ningún producto activo con el ID " + id);
        }

        // Validaciones
        if (!Validaciones.esTextoVacio(nombre)) prodAEditar.setNombre(nombre);
        if (!Validaciones.esTextoVacio(descripcion)) prodAEditar.setDescripcion(descripcion);
        
        if (precio != null) {
            if (!Validaciones.esPrecioValido(precio)) throw new IllegalArgumentException("Error: El precio no puede ser menor a 0.");
            prodAEditar.setPrecio(precio);
        }
        
        if (stock != null) {
            if (!Validaciones.esStockValido(stock)) throw new IllegalArgumentException("Error: El stock no puede ser menor a 0.");
            prodAEditar.setStock(stock);
        }
        
        if (!Validaciones.esTextoVacio(imagen)) prodAEditar.setImagen(imagen);
        
        if (disponible != null) prodAEditar.setDisponible(disponible);

        if (categoriaId != null) {
            Categoria nuevaCat = null;
            for (Categoria cat : CategoriaService.listar()) {
                if (cat.getId().equals(categoriaId)) {
                    nuevaCat = cat;
                    break;
                }
            }
            if (nuevaCat == null) {
                throw new EntidadNoEncontradaException("Error: La categoría especificada no existe.");
            }
            prodAEditar.setCategoria(nuevaCat);
        }
    }

    // Metodo de eliminar
    public static Boolean eliminar(Long id) {
        // Busqueda de producto
        Producto producto = buscarPorId(id);
        if (producto == null) return false;
        // Si producto es encontrado, se setea true en atributo eliminado
        producto.setEliminado(true);
        return true;
    }

    public static Producto buscarPorId(Long id) {
        List<Producto> productosFiltrados = listar(); // Trae solo disponibles
        // Se recorre la lista de productos filtrados
        for (Producto prod : productosFiltrados) {
            // Se compara el id ingresado, con los id almacenados
            if (id.equals(prod.getId())) {
                // Si hay coincidencia, se devuelve el producto
                return prod;
            }
        }
        return null;
    }

    // Generador incremental de ID 
    private static Long generarSiguienteId() {
        // Inicializamos variable en 0
        long maxId = 0L;
        // Recorremos la lista de productos
        for (Producto prod : Data.productos) {
            // Comparamos el ID del producto actual con el ID máximo que tenemos guardado.
            if (prod.getId() > maxId) {
                // Si el ID de este producto es mayor, lo actualizamos con este nuevo valor
                maxId = prod.getId();
            }
        }
        // Al finalizar el bucle, maxId tiene el ID mayor de la lista, y le sumamos 1 (para generar un ID unico) 
        return maxId + 1L;
    }
}