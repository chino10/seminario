package ciudadaniaseuropeas.entity;

public class Mensaje {
    private Long id;
    private String mensaje;
    private Cliente cliente;
    private Usuario usuario;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", cliente=" + cliente +
                ", usuario=" + usuario +
                '}';
    }
}