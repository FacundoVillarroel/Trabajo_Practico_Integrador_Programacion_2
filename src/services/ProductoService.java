
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
        List<Producto> productosFiltrados = new ArrayList<>(Data.productos);
        productosFiltrados.removeIf(prod -> prod.getEliminado());
        return productosFiltrados;
    }
    
    // Modifica si vienen datos nuevos
    public static void editar(Long id, String nombre, String descripcion, Double precio, Integer stock, String imagen, Boolean disponible, Long categoriaId) {
        Producto prodAEditar = buscarPorId(id);
       
        // Validaciones
        if (prodAEditar == null) {
            throw new EntidadNoEncontradaException("Error: No existe ningún producto activo con el ID " + id);
        }

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
        Producto producto = buscarPorId(id);
        if (producto == null) return false;

        producto.setEliminado(true);
        return true;
    }

    private static Producto buscarPorId(Long id) {
        List<Producto> productosFiltrados = listar(); // Trae solo disponibles
        for (Producto prod : productosFiltrados) {
            if (id.equals(prod.getId())) {
                return prod;
            }
        }
        return null;
    }

    // Generador incremental de ID 
    private static Long generarSiguienteId() {
        long maxId = 0L;
        for (Producto prod : Data.productos) {
            if (prod.getId() > maxId) {
                maxId = prod.getId();
            }
        }
        return maxId + 1L;
    }
}