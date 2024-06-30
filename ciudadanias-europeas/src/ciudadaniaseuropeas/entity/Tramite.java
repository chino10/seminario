package ciudadaniaseuropeas.entity;

import java.util.List;

public class Tramite {
    private Long id;
    private Consulado consulado;
    private TipoTramite tipoTramite;
    private Float importe;
    private String moneda;
    private boolean activo;
    private List<DetalleTramite> listaDetallesTramite;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Consulado getConsulado() {
        return consulado;
    }
    public void setConsulado(Consulado consulado) {
        this.consulado = consulado;
    }
    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }
    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    public Float getImporte() {
        return importe;
    }
    public void setImporte(Float importe) {
        this.importe = importe;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public boolean getActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public List<DetalleTramite> getListaDetallesTramite() {
        return listaDetallesTramite;
    }
    public void setListaDetallesTramite(List<DetalleTramite> listaDetallesTramite) {
        this.listaDetallesTramite = listaDetallesTramite;
    }

    @Override
    public String toString() {
        return "Tramite{" +
                "id=" + id +
                ", consulado=" + consulado +
                ", tipoTramite=" + tipoTramite +
                ", importe=" + importe +
                ", moneda='" + moneda + '\'' +
                ", listaDetallesTramite=" + listaDetallesTramite +
                '}';
    }
}