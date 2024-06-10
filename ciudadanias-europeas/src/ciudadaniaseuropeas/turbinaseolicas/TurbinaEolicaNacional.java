package ciudadaniaseuropeas.turbinaseolicas;

public class TurbinaEolicaNacional extends TurbinaEolica {
      
    private int potenciaMaxima;
    
    public TurbinaEolicaNacional(int numeroTurbina, String modelo, int potenciaMax) {
        super(numeroTurbina,modelo);
        this.potenciaMaxima=potenciaMax; 
             
    }

    @Override
    public double generarElectricidad() {
        if (funcionando) {
            double energiaGenerada = Math.random() * potenciaMaxima; //Simula energia generada detectada por sensor
            System.out.println("Energia turbina eolica #" + numeroTurbina + ": " + energiaGenerada + " kWh");
            return energiaGenerada;
        } else {
            System.out.println("La turbina e�lica #" + numeroTurbina + " no est� funcionando");
            return 0.0;
        }
    }
}