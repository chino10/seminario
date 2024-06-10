package ciudadaniaseuropeas.turbinaseolicas;

import java.util.ArrayList;
import java.util.List;

public class CentralEolica {
    private String nombre;
    private List<TurbinaEolica> turbinas;

    public CentralEolica(String nombre) {
        this.nombre = nombre;
        this.turbinas = new ArrayList<>();
    }

    public void agregarTurbina(TurbinaEolica turbina) {
        turbinas.add(turbina);
    }

    public void iniciarTurbina(int numeroTurbina) throws TurbinaExcepcion {
        if (numeroTurbina >= 1 && numeroTurbina <= turbinas.size()) {
            
                turbinas.get(numeroTurbina - 1).iniciar(); //Recordar que la primera posici�n del arrayList e 0
            
        } else {
            System.out.println("N�mero de turbina no v�lido");
        }
    }

    public void detenerTurbina(int numeroTurbina) {
        if (numeroTurbina >= 1 && numeroTurbina <= turbinas.size()) {
            turbinas.get(numeroTurbina - 1).detener();
        } else {
            System.out.println("N�mero de turbina no v�lido");
        }
    }

    public double calcularEnergiaGenerada() {
        double energiaTotal = 0;
        for (TurbinaEolica turbina : turbinas) {
            energiaTotal += turbina.generarElectricidad();
        }
        return energiaTotal;
    }

    public List<TurbinaEolica> getTurbinas() {
        return turbinas;
    }
    
    public boolean hayTurbinas() {
        return !turbinas.isEmpty();
    }
    
    public void mostrarTodasLasTurbinas() {
        System.out.println("Turbinas en la central:");
        for (TurbinaEolica turbina : turbinas) {
            System.out.println("Turbina #" + turbina.numeroTurbina + " - Modelo: " 
                    + turbina.modelo + " - Estado: " + turbina.getEstado());
        }
    }
}
