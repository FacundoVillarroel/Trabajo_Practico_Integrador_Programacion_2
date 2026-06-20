package data;

 //@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel
import java.util.ArrayList;
import java.util.List;
import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Rol;

//La clase Data almacena en memoria todas las instancias creadas de las entidades en un único lugar centralizado
public class Data {
    public static final List<Categoria> categorias = new ArrayList<>();
    public static final List<Producto> productos = new ArrayList<>();
    public static final List<Usuario> usuarios = new ArrayList<>();
    public static final List<Pedido> pedidos = new ArrayList<>();
    
    
    public static void cargarDatosDemo() {

    // CATEGORÍAS
    Categoria lomitos = new Categoria(
            "Lomitos",
            "Lomito en distintas variedades"
    );
    Categoria bebidas = new Categoria(
            "Bebidas",
            "Gaseosas y aguas"
    );

    categorias.add(lomitos);
    categorias.add(bebidas);

    // USUARIOS
    Usuario juan = new Usuario(
            "Juan",
            "Perez",
            "juan@gmail.com",
            "3815551111",
            "1234",
            Rol.USUARIO
    );
    juan.setId(1L);
    
    Usuario maria = new Usuario(
            "Maria",
            "Gomez",
            "maria@gmail.com",
            "3815552222",
            "1234",
            Rol.ADMIN
    );
    maria.setId(2L);

    usuarios.add(juan);
    usuarios.add(maria);

    // PRODUCTOS
    Producto lomitoCompleto = new Producto(
            "Lomito Completo",
            12000,
            "Lomito con jamon, queso, huevo y verduras",
            10,
            "lomito.jpg",
            true,
            lomitos
    );
    lomitoCompleto.setId(1L);
    
    Producto lomitoEspecial = new Producto(
            "Lomito Especial",
            14500,
            "Lomito doble carne con papas",
            4,
            "especial.jpg",
            true,
            lomitos
    );
    lomitoEspecial.setId(2L);
    
    Producto coca = new Producto(
            "Coca Cola 500ml",
            2500,
            "Gaseosa Coca Cola",
            5,
            "coca.jpg",
            true,
            bebidas
    );
    coca.setId(3L);
    
    Producto sprite = new Producto(
            "Sprite 500ml",
            2500,
            "Gaseosa Sprite",
            2,
            "sprite.jpg",
            true,
            bebidas
    );
    sprite.setId(4L);
    
    productos.add(lomitoCompleto);
    productos.add(lomitoEspecial);
    productos.add(coca);
    productos.add(sprite);

    // PEDIDO 1
    Pedido pedido1 = new Pedido(juan);
    pedido1.setId(1L);

    pedido1.addDetallePedido(2, lomitoCompleto);
    pedido1.addDetallePedido(2, coca);

    pedidos.add(pedido1);
    juan.agregarPedido(pedido1);

    // PEDIDO 2
    Pedido pedido2 = new Pedido(maria);
    pedido2.setId(2L);
    
    pedido2.addDetallePedido(1, lomitoEspecial);
    pedido2.addDetallePedido(2, sprite);

    pedidos.add(pedido2);
    maria.agregarPedido(pedido2);

    // PEDIDO 3
    Pedido pedido3 = new Pedido(juan);
    pedido3.setId(3L);
    
    pedido3.addDetallePedido(1, lomitoCompleto);
    pedido3.addDetallePedido(1, sprite);

    pedidos.add(pedido3);
    juan.agregarPedido(pedido3);
}
}
