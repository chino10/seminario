package ejemplo.cuatro;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class VistaRegistroViento {
    private Scanner scanner;

    public VistaRegistroViento() {
        this.scanner = new Scanner(System.in);
    }

    public int solicitarIdTurbina() {
        System.out.print("Seleccionar turbina a registrar ");
        return scanner.nextInt();
    }

    public double solicitarVelocidadViento() {

        double velocidadViento = Math.random() * 50;
        System.out.println("Velocidad detectada por el sensor: " + velocidadViento);
        return velocidadViento;
    }

    public String solicitarDireccionViento() {
        String[] array = {"Norte", "Sur", "Este", "Oeste"};
        Random random = new Random();
        int indiceAleatorio = random.nextInt(array.length);

        System.out.println("Direcci�n detectada por el sensor: " + array[indiceAleatorio]);
        return array[indiceAleatorio];
    }

    public void mostrarTurbinas(List<Turbina> turbinas) {
        System.out.println("________________________________________");
        System.out.println("Turbinas Disponibles:");
        System.out.println("________________________________________");
        System.out.println("ID | Modelo | Pot. M�xima | Estado");

        for(Turbina turbina : turbinas) {
            System.out.println(turbina.getId() + " | " + turbina.getModelo() + " | " +
                    turbina.getPotenciaMax() + " | " + turbina.getEstado());
        }
        System.out.println("________________________________________");
    }
}