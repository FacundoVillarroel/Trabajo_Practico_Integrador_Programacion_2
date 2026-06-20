
package entities;

//@authors - Fiorella Salazar, Jonathan Soza, Virginia Paloma, Facundo Villarroel

import java.time.LocalDateTime;


public abstract class Base {
    
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;
    
    // Constructores
    public Base() {
        this.createdAt = LocalDateTime.now();
        this.eliminado = false;
    }
    
    public Base (Long id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.eliminado=false;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public boolean getEliminado() {
        return eliminado;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    // Setters
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public abstract String toString();
}
