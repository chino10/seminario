package ciudadaniaseuropeas.entity;

public class Pago {
    private Long id;
    private DetalleTramite detalleTramite;
    private Float importe;
    private Observacion observacion;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DetalleTramite getDetalleTramite() {
        return detalleTramite;
    }
    public void setDetalleTramite(DetalleTramite detalleTramite) {
        this.detalleTramite = detalleTramite;
    }
    public Float getImporte() {
        return importe;
    }
    public void setImporte(Float importe) {
        this.importe = importe;
    }
    public Observacion getObservacion() {
        return observacion;
    }
    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", detalleTramite=" + detalleTramite +
                ", importe=" + importe +
                ", observacion=" + observacion +
                '}';
    }
}