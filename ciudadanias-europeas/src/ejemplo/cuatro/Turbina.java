package ejemplo.cuatro;

public class Turbina {
    private int id;
    private String modelo;
    private double potenciaMax;
    private String estado;

    public Turbina(int id, String modelo, double potenciaMax, String estado) {
        this.id = id;
        this.modelo = modelo;
        this.potenciaMax = potenciaMax;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPotenciaMax() {
        return potenciaMax;
    }

    public String getEstado() {
        return estado;
    }
}