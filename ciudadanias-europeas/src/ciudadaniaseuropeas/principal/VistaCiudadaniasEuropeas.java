package ciudadaniaseuropeas.principal;

import ciudadaniaseuropeas.entity.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VistaCiudadaniasEuropeas {
    private final Scanner scanner;

    public VistaCiudadaniasEuropeas() {
        this.scanner = new Scanner(System.in);
    }

    public Tramite obtenerTramite() {
        Tramite tramite = new Tramite();
        tramite.setTipoTramite(obtenerTipoTramiteCiudadania());
        tramite.setImporte(200.00F);
        tramite.setMoneda("EUR");
        List<DetalleTramite> listaDetallesTramite = new ArrayList<>();
        listaDetallesTramite.add(obtenerDetalleTramite());
        tramite.setListaDetallesTramite(listaDetallesTramite);
        return tramite;
    }

    private TipoTramite obtenerTipoTramiteCiudadania() {
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite.setNombre("Ciudadanía");
        tipoTramite.setDescripcion("Solicitud de ciudadanía");
        return tipoTramite;
    }

    private DetalleTramite obtenerDetalleTramite() {
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

    private Cliente obtenerCliente() {
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

    private Consulado obtenerConsulado() {
        Consulado consulado = new Consulado();
        consulado.setPais(obtenerPais());
        consulado.setProvincia("Buenos Aires");
        consulado.setCiudad("CABA");
        consulado.setDomicilio("Guido 1770");
        return consulado;
    }

    private EstadoTramite obtenerEstadoTramite() {
        EstadoTramite estadoTramite = new EstadoTramite();
        estadoTramite.setNombre("INICIADO");
        estadoTramite.setDescripcion("Es el Primer estado, es el inicio de cualquier trámite de ciudadanía.");
        return estadoTramite;
    }

    private Observacion obtenerObservacion() {
        Observacion observacion = new Observacion();
        observacion.setFecha(LocalDateTime.now());
        observacion.setDescripcion("Trámite iniciado, solicitar documentación correspondiente.");
        return observacion;
    }

    private Usuario obtenerUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pablo");
        usuario.setApellido("Hidalgo");
        usuario.setDni(12345678L);
        usuario.setEmail("phidalgo@ciudadanias");
        usuario.setRol(obtenerRol());
        return usuario;
    }

    private Pais obtenerPais() {
        Pais pais = new Pais();
        pais.setNombre("España");
        return pais;
    }

    private Rol obtenerRol() {
        Rol rol = new Rol();
        rol.setNombre("Operador");
        rol.setDescripcion("Sólo operaciones diarias ó rutinarias.");
        return rol;
    }

    public int obtenerIdTramite() {
        System.out.print("Ingrese número de trámite: ");
        return scanner.nextInt();
    }

    /**
     * Sólo se modifica el tipo de trámite y el importe.
     */
    public Tramite actualizarTramite(Tramite tramite) {
        if(tramite != null) {
            tramite.setTipoTramite(obtenerTipoTramiteRectificacion());
            tramite.setImporte(275.00F);
            return tramite;
        }
        return null;
    }

    private TipoTramite obtenerTipoTramiteRectificacion() {
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite.setNombre("Rectificación");
        tipoTramite.setDescripcion("Solicitud de modificación de partidas.");
        return tipoTramite;
    }
}