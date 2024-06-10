package ciudadaniaseuropeas.entity;

public class Consulado {
    private Long id;
    private Pais pais;
    private String domicilio;
    private String ciudad;
    private String provincia;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Pais getPais() {
        return pais;
    }
    public void setPais(Pais pais) {
        this.pais = pais;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    @Override
    public String toString() {
        return "Consulado{" +
                "id=" + id +
                ", pais=" + pais +
                ", domicilio='" + domicilio + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}