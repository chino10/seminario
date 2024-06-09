package ciudadaniaseuropeas.entity;

public class Usuario extends Persona {
    private Rol rol;

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                super.toString() +
                "rol=" + rol +
                '}';
    }
}