package ciudadaniaseuropeas.principal;

import ciudadaniaseuropeas.entity.Tramite;
import ciudadaniaseuropeas.exception.TramiteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GestionCiudadaniasEuropeas {
    private static final Scanner scanner = new Scanner(System.in);
    private static ControladorCiudadaniasEuropeas controladorCiudadaniasEuropeas;

    public static void main(String[] args) throws TramiteException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ciudadanias_europeas", "root", "root")) {
            controladorCiudadaniasEuropeas = new ControladorCiudadaniasEuropeas(connection);
            boolean ejecutando = true;
            while(ejecutando) {
                System.out.println("_______________________________________________");
                System.out.println("MENU PRINCIPAL - CIUDADANÍAS EUROPEAS");
                System.out.println("_______________________________________________");
                System.out.println("1. Iniciar trámite");
                System.out.println("2. Consultar trámite");
                System.out.println("3. Editar trámite");
                System.out.println("4. Listar trámites");
                System.out.println("5. Eliminar trámite");
                System.out.println("6. Salir");
                System.out.println("_______________________________");
                System.out.print("Elige una opción: ");
                int option = scanner.nextInt();
                switch(option) {
                    case 1 -> crearTramite();
                    case 2 -> buscarTramite();
                    case 3 -> actualizarTramite();
                    case 4 -> mostrarTramites();
                    case 5 -> eliminarTramite();
                    case 6 -> {
                        System.out.println("Programa Finalizado");
                        ejecutando = false;
                    }
                    default -> System.out.println("Opción no válida, intenta nuevamente");
                }
            }
        } catch(SQLException e) {
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
        }
    }

    private static void crearTramite() throws TramiteException {
        if(controladorCiudadaniasEuropeas.insertarTramite()) {
            System.out.println("Tramite generado con éxito.");
        }
    }

    private static void buscarTramite() throws TramiteException {
        Tramite tramite = controladorCiudadaniasEuropeas.consultarTramitePorId();
        System.out.println(tramite);
    }

    private static void mostrarTramites() throws TramiteException {
        List<Tramite> listaTramites = controladorCiudadaniasEuropeas.consultarTramites();
        if(listaTramites != null && !listaTramites.isEmpty()) {
            for(Tramite t : listaTramites) {
                System.out.println(t);
            }
        } else {
            // No necesariamente es un error que no haya trámites cargados.
            System.err.println("NO SE ENCONTRARON TRÁMITES CARGADOS...");
        }
    }

    private static void actualizarTramite() throws TramiteException {
        if(controladorCiudadaniasEuropeas.actualizarTramite()) {
            System.out.println("Tramite actualizado con éxito.");
        }
    }

    private static void eliminarTramite() throws TramiteException {
        if(controladorCiudadaniasEuropeas.eliminarTramite()) {
            System.out.println("Tramite eliminado.");
        }
    }
}