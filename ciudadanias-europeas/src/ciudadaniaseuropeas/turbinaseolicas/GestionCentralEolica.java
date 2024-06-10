package ciudadaniaseuropeas.turbinaseolicas;

import java.util.Scanner;

public class GestionCentralEolica {
    public static void main(String[] args) throws TurbinaExcepcion {
        Scanner scanner = new Scanner(System.in);

        CentralEolica central = new CentralEolica("ECOViento");
        boolean ejecutando = true; 

        while (ejecutando) {
            System.out.println("_______________________________");
            System.out.println("MENU PRINCIPAL - ECOViento");
            System.out.println("___________________________");
            System.out.println("1. Agregar Turbina");
            System.out.println("2. Iniciar Turbina(s)");
            System.out.println("3. Detener Turbina(s)");
            System.out.println("4. Listar turbinas");
            System.out.println("5. Calcular Energ�a Generada");
            System.out.println("6. Salir");
            System.out.println("_______________________________");
            System.out.print("Elige una opci�n: ");
            

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("N�mero de turbina: ");
                    int numeroTurbina = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Modelo de turbina: ");
                    String modeloTurbina = scanner.nextLine();
                    int potenciaMax = 100; // Potencia m�xima de la turbina e�lica Nacional en Kw/h
                    TurbinaEolica nuevaTurbina = new TurbinaEolicaNacional(numeroTurbina,modeloTurbina,potenciaMax);
                    central.agregarTurbina(nuevaTurbina);
                    System.out.println("Turbina agregada con �xito");
                }

                case 2 -> {
                    System.out.print("N�mero de turbina a iniciar (0 para todas): ");
                    int numeroTurbinaInicio = scanner.nextInt();
                    if (numeroTurbinaInicio == 0) {
                        for (TurbinaEolica turbina : central.getTurbinas()) {
                            try {
                                turbina.iniciar();
                            } catch (TurbinaExcepcion e) {
                                System.out.println("Error al iniciar la turbina: " + e.getMessage());
                            }
                        }
                    } else {
                        central.iniciarTurbina(numeroTurbinaInicio);
                    }
                }
                case 3 -> {
                    System.out.print("N�mero de turbina a detener (0 para todas): ");
                    int numeroTurbinaDetener = scanner.nextInt();
                    if (numeroTurbinaDetener == 0) {
                        for (TurbinaEolica turbina : central.getTurbinas()) {
                            turbina.detener();
                        }
                    } else {
                        central.detenerTurbina(numeroTurbinaDetener);
                    }
                }
                case 4 -> {
                    if (central.hayTurbinas()) {
                        central.mostrarTodasLasTurbinas();
                    } else {
                        System.out.println("No hay turbinas en la central");
                    }
                }
               
                case 5 -> {
                    double energiaGenerada = central.calcularEnergiaGenerada();
                    System.out.println("Energ�a total generada por todas las turbinas: " + energiaGenerada + " kWh");
                }
                case 6 -> {
                    System.out.println("Programa Finalizado");
                    ejecutando = false;
                }
                default -> System.out.println("Opci�n no v�lida, intenta nuevamente");
            }
        }
    }
}
