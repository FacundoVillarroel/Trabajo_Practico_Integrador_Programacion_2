package data;

 //@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import java.util.ArrayList;
import java.util.List;
import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;

//La clase Data almacena en memoria todas las instancias creadas de las entidades en un único lugar centralizado
public class Data {
    public static final List<Categoria> categorias = new ArrayList<>();
    public static final List<Producto> productos = new ArrayList<>();
    public static final List<Usuario> usuarios = new ArrayList<>();
    public static final List<Pedido> pedidos = new ArrayList<>();
    
}
