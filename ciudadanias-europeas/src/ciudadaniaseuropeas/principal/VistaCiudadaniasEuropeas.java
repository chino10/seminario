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
        tramite.setTipoTramite(this.obtenerTipoTramiteCiudadania());
        tramite.setImporte(200.00F);
        tramite.setMoneda("EUR");
        tramite.setConsulado(this.obtenerConsulado());
        List<DetalleTramite> listaDetallesTramite = new ArrayList<>();
        listaDetallesTramite.add(this.obtenerDetalleTramite(tramite));
        tramite.setListaDetallesTramite(listaDetallesTramite);
        return tramite;
    }

    private TipoTramite obtenerTipoTramiteCiudadania() {
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite.setId(2L);
        tipoTramite.setNombre("Ciudadanía");
        tipoTramite.setDescripcion("Solicitud de ciudadanía");
        return tipoTramite;
    }

    private DetalleTramite obtenerDetalleTramite(Tramite tramite) {
        DetalleTramite detalleTramite = new DetalleTramite();
        // tramite
        detalleTramite.setTramite(tramite);
        // cliente
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(this.obtenerCliente());
        detalleTramite.setListaClientes(listaClientes);
        // fechas
        detalleTramite.setFechaInicio(LocalDateTime.now());
        detalleTramite.setFechaFin(LocalDateTime.of(2025, Month.JULY, 29, 12, 00, 00));
        // estado
        detalleTramite.setEstado(this.obtenerEstadoTramite());
        // documentos
        List<Documento> listaDocumentos = new ArrayList<>();
        listaDocumentos.add(this.obtenerDocumento());
        detalleTramite.setListaDocumentos(listaDocumentos);
        // observaciones
        List<Observacion> listaObservaciones = new ArrayList<>();
        listaObservaciones.add(this.obtenerObservacion());
        detalleTramite.setListaObservaciones(listaObservaciones);
        // usuario
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(this.obtenerUsuario());
        detalleTramite.setListaUsuarios(listaUsuarios);
        return detalleTramite;
    }

    private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(4L);
        cliente.setNombre("Pedro");
        cliente.setApellido("Torres");
        cliente.setDni(98765432L);
        cliente.setEmail("ptorres@gmail.com");
        return cliente;
    }

    private Consulado obtenerConsulado() {
        Consulado consulado = new Consulado();
        consulado.setId(1L);
        consulado.setPais(this.obtenerPais());
        consulado.setProvincia("Buenos Aires");
        consulado.setCiudad("CABA");
        consulado.setDomicilio("Guido 1770");
        return consulado;
    }

    private EstadoTramite obtenerEstadoTramite() {
        EstadoTramite estadoTramite = new EstadoTramite();
        estadoTramite.setId(1L);
        estadoTramite.setNombre("INICIADO");
        estadoTramite.setDescripcion("Es el Primer estado, es el inicio de cualquier trámite de ciudadanía.");
        return estadoTramite;
    }

    private Documento obtenerDocumento() {
        Documento documento = new Documento();
        documento.setFechaPresentacion(LocalDateTime.now());
        documento.setNombre("Partida Nacimiento");
        documento.setDescripcion("Partida de nacimiento que necesita ser rectificada.");
        documento.setTipoDocumento(this.obtenerTipoDocumento());
        return documento;
    }

    private TipoDocumento obtenerTipoDocumento() {
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(3L);
        tipoDocumento.setNombre("Partida");
        tipoDocumento.setDescripcion("De nacimiento, matrimonio, defunción.");
        return tipoDocumento;
    }

    private Observacion obtenerObservacion() {
        Observacion observacion = new Observacion();
        observacion.setFecha(LocalDateTime.now());
        observacion.setDescripcion("Trámite iniciado, solicitar documentación correspondiente.");
        return observacion;
    }

    private Usuario obtenerUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Empleado");
        usuario.setApellido("López");
        usuario.setDni(11223344L);
        usuario.setEmail("empleado@ciudadanias.com.ar");
        usuario.setRol(obtenerRol());
        return usuario;
    }

    private Pais obtenerPais() {
        Pais pais = new Pais();
        pais.setId(1L);
        pais.setNombre("España");
        return pais;
    }

    private Rol obtenerRol() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("Operador");
        rol.setDescripcion("Sólo operaciones diarias ó rutinarias.");
        return rol;
    }

    /**
     * Solicito y obtengo el id del trámite ingresado por el usuario.
     */
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
        tipoTramite.setId(1L);
        tipoTramite.setNombre("Rectificación");
        tipoTramite.setDescripcion("Solicitud de modificación de partidas.");
        return tipoTramite;
    }

    /**
     * Solicito y obtengo la cantidad de trámites a buscar.
     */
    public int obtenerCantidadTramites() {
        System.out.print("Ingrese cantidad de trámites a buscar (más 50): ");
        return scanner.nextInt();
    }
}