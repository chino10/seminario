package ciudadaniaseuropeas.entity;

import java.time.LocalDateTime;

public class Documento {
    private Long id;
    private String nombre;
    private String descripcion;
    private TipoDocumento tipoDocumento;
    private LocalDateTime fechaPresentacion;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public LocalDateTime getFechaPresentacion() {
        return fechaPresentacion;
    }
    public void setFechaPresentacion(LocalDateTime fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipoDocumento=" + tipoDocumento +
                ", fechaPresentacion=" + fechaPresentacion +
                '}';
    }
}