package ciudadaniaseuropeas.principal;

import ciudadaniaseuropeas.entity.*;
import ciudadaniaseuropeas.exception.TramiteNoEncontradoExcepcion;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CiudadaniasEuropeas {
    private static final Scanner scanner = new Scanner(System.in);
    private static Tramite tramite;
    private static List<Tramite> listaTramites;

    public static void main(String[] args) throws TramiteNoEncontradoExcepcion {
        boolean ejecutando = true;
        while(ejecutando) {
            System.out.println("_______________________________________________");
            System.out.println("MENU PRINCIPAL - CIUDADANÍAS EUROPEAS");
            System.out.println("_______________________________________________");
            System.out.println("1. Iniciar trámite");
            System.out.println("2. Consultar trámite");
            System.out.println("3. Editar trámite");
            System.out.println("4. Listar trámites");
            System.out.println("5. Salir");
            System.out.println("_______________________________");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            switch(opcion) {
                case 1 -> crearTramite();
                case 2 -> {
                    try {
                        buscarTramite();
                    } catch(TramiteNoEncontradoExcepcion e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> System.out.println("Funcionalidad aún no implementada...");  // TODO ...
                case 4 -> mostrarTramites();
                case 5 -> {
                    System.out.println("Programa Finalizado");
                    ejecutando = false;
                }
                default -> System.out.println("Opción no válida, intenta nuevamente");
            }
        }
    }

    private static void crearTramite() {
        tramite = obtenerTramite();
        listaTramites.add(tramite);
        System.out.println("Tramite generado con éxito.");
    }

    private static void buscarTramite() throws TramiteNoEncontradoExcepcion {
        System.out.print("Ingrese número de trámite: ");
        int numeroTramite = scanner.nextInt();
        for(Tramite t : listaTramites) {
            if(t.getId() == numeroTramite) {
                System.out.println(t);
                break;
            }
        }
        throw new TramiteNoEncontradoExcepcion(String.valueOf(numeroTramite));
    }

    private static void mostrarTramites() {
        for(Tramite t : listaTramites) {
            System.out.println(t);
        }
    }

    private static Tramite obtenerTramite() {
        Tramite tramite = new Tramite();
        tramite.setId(1L);
        tramite.setTipoTramite(obtenerTipoTramite());
        tramite.setImporte(200.00F);
        tramite.setMoneda("EUR");
        List<DetalleTramite> listaDetallesTramite = new ArrayList<>();
        listaDetallesTramite.add(obtenerDetalleTramite());
        tramite.setListaDetallesTramite(listaDetallesTramite);
        return tramite;
    }

    private static DetalleTramite obtenerDetalleTramite() {
        DetalleTramite detalleTramite = new DetalleTramite();
        // cliente
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(obtenerCliente());
        detalleTramite.setListaClientes(listaClientes);
        // consulado
        detalleTramite.setConsulado(obtenerConsulado());
        // fechas
        detalleTramite.setFechaInicio(LocalDateTime.now());
        detalleTramite.setFechaFin(LocalDateTime.of(2025, Month.JULY, 29, 12, 00, 00));
        // estado
        detalleTramite.setEstado(obtenerEstadoTramite());
        // documentos
        detalleTramite.setListaDocumentos(new ArrayList<>());
        // observaciones
        List<Observacion> listaObservaciones = new ArrayList<>();
        listaObservaciones.add(obtenerObservacion());
        detalleTramite.setListaObservaciones(listaObservaciones);
        // usuario
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(obtenerUsuario());
        detalleTramite.setListaUsuarios(listaUsuarios);
        return detalleTramite;
    }

    private static Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        // nombre
        System.out.print("Sólo Nombre: ");
        String nombre = scanner.nextLine();
        scanner.nextLine();
        cliente.setNombre(nombre);
        // apellido
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        scanner.nextLine();
        cliente.setApellido(apellido);
        // dni
        System.out.print("DNI: ");
        Long dni = scanner.nextLong();
        scanner.nextLine();
        cliente.setDni(dni);
        // email
        System.out.print("Email: ");
        String email = scanner.nextLine();
        scanner.nextLine();
        cliente.setEmail(email);
        return cliente;
    }

    private static Consulado obtenerConsulado() {
        Consulado consulado = new Consulado();
        consulado.setPais(obtenerPais());
        consulado.setProvincia("Buenos Aires");
        consulado.setCiudad("CABA");
        consulado.setDomicilio("Guido 1770");
        return consulado;

    }

    private static Pais obtenerPais() {
        Pais pais = new Pais();
        pais.setNombre("España");
        return pais;
    }

    private static EstadoTramite obtenerEstadoTramite() {
        EstadoTramite estadoTramite = new EstadoTramite();
        estadoTramite.setNombre("INICIADO");
        estadoTramite.setDescripcion("Es el Primer estado, es el inicio de cualquier trámite de ciudadanía.");
        return estadoTramite;
    }

    private static Observacion obtenerObservacion() {
        Observacion observacion = new Observacion();
        observacion.setFecha(LocalDateTime.now());
        observacion.setDescripcion("Trámite iniciado, solicitar documentación correspondiente.");
        return observacion;
    }

    private static Usuario obtenerUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pablo");
        usuario.setApellido("Hidalgo");
        usuario.setDni(12345678L);
        usuario.setEmail("phidalgo@ciudadanias");
        usuario.setRol(obtenerRol());
        return usuario;
    }

    private static Rol obtenerRol() {
        Rol rol = new Rol();
        rol.setNombre("Operador");
        rol.setDescripcion("Sólo operaciones diarias ó rutinarias.");
        return rol;
    }

    private static TipoTramite obtenerTipoTramite() {
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite.setNombre("Ciudadanía");
        tipoTramite.setDescripcion("Solicitud de ciudadanía");
        return tipoTramite;
    }
}