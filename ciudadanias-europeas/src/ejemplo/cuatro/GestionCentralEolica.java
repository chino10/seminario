package ejemplo.cuatro;

import ejemplo.tres.CentralEolica;
import ejemplo.tres.TurbinaEolica;
import ejemplo.tres.TurbinaEolicaNacional;
import ejemplo.tres.TurbinaExcepcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionCentralEolica {
    public static void main(String[] args) throws TurbinaExcepcion {
        Scanner scanner = new Scanner(System.in);
        CentralEolica central = new CentralEolica("ECOViento");
        boolean ejecutando = true;
        while(ejecutando) {
            System.out.println("_______________________________");
            System.out.println("MENU PRINCIPAL - ECOViento");
            System.out.println("___________________________");
            System.out.println("1. Agregar Turbina");
            System.out.println("2. Iniciar Turbina(s)");
            System.out.println("3. Detener Turbina(s)");
            System.out.println("4. Listar turbinas");
            System.out.println("5. Calcular Energúa Generada");
            System.out.println("6. Almacenar mediciones de viento");
            System.out.println("7. Salir");
            System.out.println("_______________________________");
            System.out.print("Elige una opciún: ");
            int opcion = -1;
            try {
                opcion = scanner.nextInt();
            } catch(java.util.InputMismatchException e) {
                System.err.println("Opción no válida, ingresa un número");
                scanner.nextLine();
            }
            switch(opcion) {
                case 1 -> {
                    System.out.print("Número de turbina: ");
                    int numeroTurbina = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Modelo de turbina: ");
                    String modeloTurbina = scanner.nextLine();
                    int potenciaMax = 100; // Potencia múxima de la turbina eúlica Nacional en Kw/h
                    TurbinaEolica nuevaTurbina = new TurbinaEolicaNacional(numeroTurbina, modeloTurbina, potenciaMax);
                    central.agregarTurbina(nuevaTurbina);
                    System.out.println("Turbina agregada con éxito.");
                }
                case 2 -> {
                    System.out.print("Número de turbina a iniciar (0 para todas): ");
                    int numeroTurbinaInicio = scanner.nextInt();
                    if(numeroTurbinaInicio == 0) {
                        for(TurbinaEolica turbina : central.getTurbinas()) {
                            try {
                                turbina.iniciar();
                            } catch(TurbinaExcepcion e) {
                                System.out.println("Error al iniciar la turbina: " + e.getMessage());
                            }
                        }
                    } else {
                        central.iniciarTurbina(numeroTurbinaInicio);
                    }
                }
                case 3 -> {
                    System.out.print("Número de turbina a detener (0 para todas): ");
                    int numeroTurbinaDetener = scanner.nextInt();
                    if(numeroTurbinaDetener == 0) {
                        for(TurbinaEolica turbina : central.getTurbinas()) {
                            turbina.detener();
                        }
                    } else {
                        central.detenerTurbina(numeroTurbinaDetener);
                    }
                }
                case 4 -> {
                    if(central.hayTurbinas()) {
                        central.mostrarTodasLasTurbinas();
                    } else {
                        System.out.println("No hay turbinas en la central");
                    }
                }
                case 5 -> {
                    double energiaGenerada = central.calcularEnergiaGenerada();
                    System.out.println("Energía total generada por todas las turbinas: " + energiaGenerada + " kWh");
                }
                case 6 -> {

                    try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecoviento", "root", "")) {
                        ControladorRegistroViento controlador = new ControladorRegistroViento(conexion);
                        controlador.mostrarTurbinasDisponibles();
                        controlador.ejecutar();
                    } catch(SQLException e) {
                        System.err.println("Error de conexión a la base de datos: " + e.getMessage());
                    }
                }
                case 7 -> {
                    System.out.println("Programa finalizado");
                    ejecutando = false;
                }
                default -> System.out.println("Opción no válida, intenta nuevamente");
            }
        }
    }
}