package ciudadaniaseuropeas.entity;

import java.time.LocalDateTime;
import java.util.List;

public class DetalleTramite {
    private Long id;
    private List<Cliente> listaClientes;
    private List<Documento> listaDocumentos;
    private EstadoTramite estado;
    private List<Usuario> listaUsuarios;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<Observacion> listaObservaciones;
    private Tramite tramite;
    private boolean activo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }
    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
    public EstadoTramite getEstado() {
        return estado;
    }
    public void setEstado(EstadoTramite estado) {
        this.estado = estado;
    }
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    public List<Observacion> getListaObservaciones() {
        return listaObservaciones;
    }
    public void setListaObservaciones(List<Observacion> listaObservaciones) {
        this.listaObservaciones = listaObservaciones;
    }
    public Tramite getTramite() {
        return tramite;
    }
    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }
    public boolean getActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "DetalleTramite{" +
                "id=" + id +
                ", listaClientes=" + listaClientes +
                ", listaDocumentos=" + listaDocumentos +
                ", estado=" + estado +
                ", listaUsuarios=" + listaUsuarios +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", listaObservaciones=" + listaObservaciones +
                '}';
    }
}