
package entities;

//@authors - Fiorella, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import java.util.ArrayList;
import java.util.List;
import enums.Rol;

public class Usuario extends Base {
    // Atributos 
    private String nombre; 
    private String apellido;
    private String mail; 
    private String celular; 
    private String contrasenia; 
    private Rol rol; 
    private List<Pedido> pedidos; 
    
    // Constructor adaptado (El ID se maneja por herencia de Base de forma dinámica)
    public Usuario(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        super(); // Llama al constructor de Base que inicializa el id y la fecha [cite: 223]
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.pedidos = new ArrayList<>();
    }
    
    // Métodos - Getters y Setters [cite: 146]
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCellular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    // Relación 1...n con Pedido requerida por el UML [cite: 147, 150]
    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    // El toString que respeta exactamente el reporte pedido por consola 
    @Override
    public String toString() {
        return String.format(
                "USUARIO: %s %s | Mail: %s | Rol: %s",
                nombre, apellido, mail, rol
        );
    }  
}