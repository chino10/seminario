package ciudadaniaseuropeas.turbinaseolicas;

public abstract class TurbinaEolica {
    protected int numeroTurbina;
    protected String modelo;
    protected boolean funcionando;

    public TurbinaEolica(int numeroTurbina,String modelo) {
        this.numeroTurbina = numeroTurbina;
        this.modelo=modelo;
        this.funcionando = false;
    }

    public abstract double generarElectricidad();
        
    public void iniciar() throws TurbinaExcepcion {
        if (!funcionando) {
            if (verificarCondicionesArranque()) {
                funcionando = true;
                System.out.println("Turbina e�lica #" + numeroTurbina + " iniciada.");
            } else {
                throw new TurbinaExcepcion("No se cumplen condiciones de arranque en turbina #" 
                        + numeroTurbina + " en las condiciones actuales");
            }
        } else {
            System.out.println("La turbina e�lica #" + numeroTurbina + " ya est� funcionando");
        }
    }

    public void detener() {
        if (funcionando) {
            funcionando = false;
            System.out.println("Turbina e�lica #" + numeroTurbina + " detenida");
        } else {
            System.out.println("La turbina e�lica #" + numeroTurbina + " ya est� detenida");
        }
    }

    private boolean verificarCondicionesArranque() {
        boolean condicionesOk = false;
        double velocidadViento = obtenerVelocidadViento();

        if (velocidadViento >= 10.0) {
            condicionesOk = true;
        }

        return condicionesOk;
    }

    private double obtenerVelocidadViento() {
        double velocidadViento = Math.random() * 50;
        System.out.println("Velocidad detectada por el sensor: " + velocidadViento);
        return velocidadViento;
    }

    public boolean estaFuncionando() {
        return funcionando;
    }

    public String getEstado() {
        return funcionando ? "Funcionando" : "Detenida";
    }
    
    
}
