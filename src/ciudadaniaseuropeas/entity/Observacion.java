package ciudadaniaseuropeas.entity;

import java.time.LocalDateTime;

public class Observacion {
    private Long id;
    private LocalDateTime fecha;
    private String descripcion;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Observacion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}