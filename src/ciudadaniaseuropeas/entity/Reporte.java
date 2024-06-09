package ciudadaniaseuropeas.entity;

import java.time.LocalDateTime;

public class Reporte {
    private Long id;
    private Usuario usuario;
    private String nombre;
    private String descripcion;
    private String datos;
    private LocalDateTime fecha;
    private String observacion;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public String getDatos() {
        return datos;
    }
    public void setDatos(String datos) {
        this.datos = datos;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", datos='" + datos + '\'' +
                ", fecha=" + fecha +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}