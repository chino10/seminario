package ciudadaniaseuropeas.principal;

import ciudadaniaseuropeas.entity.*;
import ciudadaniaseuropeas.exception.TramiteException;

/*import java.sql.*;
import java.time.LocalDateTime;*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ModeloCiudadaniasEuropeas {
    private final Connection connection;
    private static final String CONSULTA_GENERICA = "SELECT * FROM ";
    private static final String ERROR_GENERICO = "Ha ocurrido un error consultando ";
    private static final String ERROR_DETALLE_TRAMITE = "Ha ocurrido un error insertando detalle_tramite_";

    public ModeloCiudadaniasEuropeas(Connection connection) {
        this.connection = connection;
    }

    public long insertarAll(Tramite tramite) throws TramiteException {
        long idTramite = this.insertarTramite(tramite);
        if(idTramite > 0) {
            tramite.setId(idTramite);
            DetalleTramite detalleTramite = tramite.getListaDetallesTramite().getFirst();
            if(detalleTramite != null) {
                long idDetalleTramite = this.insertarDetallesTramite(tramite);
                if(idDetalleTramite > 0) {
                    detalleTramite.setId(idDetalleTramite);
                    if(this.insertarDetalleTramiteCliente(detalleTramite)) {
                        if(this.insertarDetalleTramiteUsuario(detalleTramite)) {
                            if( this.insertarDetalleTramiteObservacion(detalleTramite)) {
                                if(this.insertarDetalleTramiteDocumento(detalleTramite)) {
                                    return idTramite;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public long insertarTramite(Tramite tramite) throws TramiteException {
        String consultaSQL = "INSERT INTO tramite (importe, id_consulado, id_tipo_tramite, moneda) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, tramite.getImporte());
            preparedStatement.setLong(2, tramite.getConsulado().getId());
            preparedStatement.setLong(3, tramite.getTipoTramite().getId());
            preparedStatement.setString(4, tramite.getMoneda());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                long idTramite =  resultSet.getLong(1);
                resultSet.close();
                return idTramite;
            }
        } catch(SQLException e) {
            throw new TramiteException("Ha ocurrido un error insertando el trámite.\n" + e.getMessage());
        }
        return 0;
    }

    private long insertarDetallesTramite(Tramite tramite) throws TramiteException {
        String consultaSQL = "INSERT INTO detalle_tramite (fecha_inicio, fecha_fin, id_estado_tramite, id_tramite) VALUES (?, ?, ?, ?)";
        DetalleTramite detalleTramite = tramite.getListaDetallesTramite().getFirst();
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, this.desdeLocalDateTimeHaciaTimestamp(detalleTramite.getFechaInicio()));
            preparedStatement.setTimestamp(2, this.desdeLocalDateTimeHaciaTimestamp(detalleTramite.getFechaFin()));
            preparedStatement.setLong(3, detalleTramite.getEstado().getId());
            preparedStatement.setLong(4, tramite.getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                long idDetalleTramite =  resultSet.getLong(1);
                resultSet.close();
                return idDetalleTramite;
            }
        } catch(SQLException e) {
            throw new TramiteException("Ha ocurrido un error insertando el detalle del trámite: " + detalleTramite.getTramite().getId() + "\n" + e.getMessage());
        }
        return 0;
    }

    private boolean insertarDetalleTramiteCliente(DetalleTramite detalleTramite) throws TramiteException {
        String consultaSQL = "INSERT INTO detalle_tramite_cliente (id_detalle_tramite, id_cliente) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, detalleTramite.getId());
            preparedStatement.setLong(2, detalleTramite.getListaClientes().getFirst().getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_DETALLE_TRAMITE + "cliente: " + detalleTramite.getId() + " - " + detalleTramite.getListaClientes().getFirst().getId() + "\n" + e.getMessage());
        }
        return true;
    }

    private boolean insertarDetalleTramiteUsuario(DetalleTramite detalleTramite) throws TramiteException {
        String consultaSQL = "INSERT INTO detalle_tramite_usuario (id_detalle_tramite, id_usuario) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, detalleTramite.getId());
            preparedStatement.setLong(2, detalleTramite.getListaUsuarios().getFirst().getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_DETALLE_TRAMITE + "usuario: " + detalleTramite.getId() + " - " + detalleTramite.getListaUsuarios().getFirst().getId() + "\n" + e.getMessage());
        }
        return true;
    }

    private boolean insertarDetalleTramiteObservacion(DetalleTramite detalleTramite) throws TramiteException {
        String consultaSQL = "INSERT INTO detalle_tramite_observacion (id_detalle_tramite, id_observacion) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, detalleTramite.getId());
            long idObservacion = this.insertarObservacion(detalleTramite.getListaObservaciones().getFirst());
            if(idObservacion > 0) {
                preparedStatement.setLong(2, idObservacion);
            } else {
                throw new SQLException("No insertó la observación.");
            }
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_DETALLE_TRAMITE + "observacion: " + detalleTramite.getId() + " - " + detalleTramite.getListaObservaciones().getFirst().getId() + "\n" + e.getMessage());
        }
        return true;
    }

    private long insertarObservacion(Observacion observacion) throws TramiteException {
        String consultaSQL = "INSERT INTO observacion (fecha, descripcion) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, this.desdeLocalDateTimeHaciaTimestamp(observacion.getFecha()));
            preparedStatement.setString(2, observacion.getDescripcion());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                long idObservacion =  resultSet.getLong(1);
                resultSet.close();
                return idObservacion;
            }
        } catch(SQLException e) {
            throw new TramiteException("Ha ocurrido un error insertando la observación.\n" + e.getMessage());
        }
        return 0;
    }

    private boolean insertarDetalleTramiteDocumento(DetalleTramite detalleTramite) throws TramiteException {
        String consultaSQL = "INSERT INTO detalle_tramite_documento (id_detalle_tramite, id_documento) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, detalleTramite.getId());
            long idDocumento = this.insertarDocumento(detalleTramite.getListaDocumentos().getFirst());
            if(idDocumento > 0) {
                preparedStatement.setLong(2, idDocumento);
            } else {
                throw new SQLException("No insertó el documento.");
            }
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_DETALLE_TRAMITE + "documento: " + detalleTramite.getId() + " - " + detalleTramite.getListaDocumentos().getFirst().getId() + "\n" + e.getMessage());
        }
        return true;
    }

    private long insertarDocumento(Documento documento) throws TramiteException {
        String consultaSQL = "INSERT INTO documento (fecha_presentacion, descripcion, nombre, id_tipo_documento) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, this.desdeLocalDateTimeHaciaTimestamp(documento.getFechaPresentacion()));
            preparedStatement.setString(2, documento.getDescripcion());
            preparedStatement.setString(3, documento.getNombre());
            preparedStatement.setLong(4, documento.getTipoDocumento().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                long idDocumento =  resultSet.getLong(1);
                resultSet.close();
                return idDocumento;
            }
        } catch(SQLException e) {
            throw new TramiteException("Ha ocurrido un error insertando el documento.\n" + e.getMessage());
        }
        return 0;
    }

    /**
     * Se muestra la cantidad de trámites seleccionada por el usuario (máximo 50).
     */
    public List<Tramite> consultarTramites(int cantidadTramites) throws TramiteException {
        List<Tramite> listaTramites = new ArrayList<>();
        String consultaSQL = "SELECT * FROM tramite ORDER BY id_tramite DESC LIMIT ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setInt(1, cantidadTramites);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idTramiteResult = resultSet.getLong("id_tramite");
                Tramite tramite = this.obtenerTramitePorId(idTramiteResult);
                listaTramites.add(tramite);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "los trámites.\n" + e.getMessage());
        }
        return listaTramites;
    }

    public Tramite obtenerTramitePorId(long idTramite) throws TramiteException {
        Tramite tramite = null;
        String consultaSQL = CONSULTA_GENERICA + "tramite WHERE id_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idTramiteResult = resultSet.getLong("id_tramite");
                float importe = resultSet.getFloat("importe");
                long idConsulado = resultSet.getLong("id_consulado");
                long idTipoTramite = resultSet.getLong("id_tipo_tramite");
                String moneda = resultSet.getString("moneda");
                Consulado consulado = this.obtenerConsuladoPorId(idConsulado);
                TipoTramite tipoTramite = this.obtenerTipoTramitePorId(idTipoTramite);
                tramite = new Tramite();
                tramite.setId(idTramiteResult);
                tramite.setImporte(importe);
                tramite.setConsulado(consulado);
                tramite.setTipoTramite(tipoTramite);
                tramite.setMoneda(moneda);
                tramite.setListaDetallesTramite(this.obtenerDetallesTramite(idTramite));
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el trámite: " + idTramite + "\n" + e.getMessage());
        }
        return tramite;
    }

    public boolean actualizarTramite(Tramite tramite) throws TramiteException {
        String consultaSQL = "UPDATE tramite SET importe = ?, id_consulado = ?, id_tipo_tramite = ?, moneda = ? WHERE id_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setFloat(1, tramite.getImporte());
            preparedStatement.setLong(2, tramite.getConsulado().getId());
            preparedStatement.setLong(3, tramite.getTipoTramite().getId());
            preparedStatement.setString(4, tramite.getMoneda());
            preparedStatement.setLong(5, tramite.getId());
            int updateResult = preparedStatement.executeUpdate();
            System.out.println("Cantidad de trámites actualizados: " + updateResult);
            return true;
        } catch(SQLException e) {
            throw new TramiteException("Ha ocurido un error intentando actualizar el trámite: " + tramite.getId() + "\n" + e.getMessage());
        }
    }

    /**
     * Con motivo de mantener el registro, en un futuro se tiene planeado realizar una baja lógica a través de una columna y no un 'DELETE'.
     */
    public boolean eliminarTramite(int idTramite) throws TramiteException {
        String consultaSQL = "DELETE FROM tramite WHERE id_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setInt(1, idTramite);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new TramiteException("Ha ocurrido un error intentando ELIMINAR el trámite: " + idTramite + "\n" + e.getMessage());
        }
        return true;
    }

    /*
     */
    //////// INICIO CONSULTAS AUXILIARES (+)
    /*
     */
    private Consulado obtenerConsuladoPorId(long idConsulado) throws TramiteException {
        Consulado consulado = null;
        String consultaSQL = CONSULTA_GENERICA + "consulado WHERE id_consulado = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idConsulado);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idConsuladoResult = resultSet.getLong("id_consulado");
                String domicilio = resultSet.getString("domicilio");
                String ciudad = resultSet.getString("ciudad");
                String provincia = resultSet.getString("provincia");
                long idPais = resultSet.getLong("id_pais_fk");
                Pais pais = this.obtenerPaisPorId(idPais);
                consulado = new Consulado();
                consulado.setId(idConsuladoResult);
                consulado.setDomicilio(domicilio);
                consulado.setCiudad(ciudad);
                consulado.setProvincia(provincia);
                consulado.setPais(pais);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el consulado: " + idConsulado + "\n" + e.getMessage());
        }
        return consulado;
    }

    private TipoTramite obtenerTipoTramitePorId(long idTipoTramite) throws TramiteException {
        TipoTramite tipoTramite = null;
        String consultaSQL = CONSULTA_GENERICA + "tipo_tramite WHERE id_tipo_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idTipoTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idTipoTramiteResult = resultSet.getLong("id_tipo_tramite");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                tipoTramite = new TipoTramite();
                tipoTramite.setId(idTipoTramiteResult);
                tipoTramite.setNombre(nombre);
                tipoTramite.setDescripcion(descripcion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el tipo de trámite: " + idTipoTramite + "\n" + e.getMessage());
        }
        return tipoTramite;
    }

    private Pais obtenerPaisPorId(long idPais) throws TramiteException {
        Pais pais = null;
        String consultaSQL = CONSULTA_GENERICA + "pais WHERE id_pais = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idPais);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idPaisResult = resultSet.getLong("id_pais");
                String nombre = resultSet.getString("nombre");
                pais = new Pais();
                pais.setId(idPaisResult);
                pais.setNombre(nombre);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el país: " + idPais + "\n" + e.getMessage());
        }
        return pais;
    }

    private List<DetalleTramite> obtenerDetallesTramite(long idTramite) throws TramiteException {
        List<DetalleTramite> listaDetallesTramite = new ArrayList<>();
        String consultaSQL = CONSULTA_GENERICA + "detalle_tramite WHERE id_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idDetalleTramiteResult = resultSet.getLong("id_detalle_tramite");
                DetalleTramite detalleTramite = this.obtenerDetalleTramitePorIdTramite(idDetalleTramiteResult);
                listaDetallesTramite.add(detalleTramite);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "los detalles de trámite: " + idTramite + "\n" + e.getMessage());
        }
        return listaDetallesTramite;
    }

    private DetalleTramite obtenerDetalleTramitePorIdTramite(long idDetalleTramite) throws TramiteException {
        DetalleTramite detalleTramite = null;
        String consultaSQL = CONSULTA_GENERICA + "detalle_tramite WHERE id_detalle_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idDetalleTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idDetalleTramiteResult = resultSet.getLong("id_detalle_tramite");
                Timestamp fechaInicio = resultSet.getTimestamp("fecha_inicio");
                Timestamp fechaFin = resultSet.getTimestamp("fecha_fin");
                long idEstadoTramite = resultSet.getLong("id_estado_tramite");
                EstadoTramite estadoTramite = this.obtenerEstadoTramitePorId(idEstadoTramite);
                detalleTramite = new DetalleTramite();
                detalleTramite.setId(idDetalleTramiteResult);
                detalleTramite.setFechaInicio(new Timestamp(fechaInicio.getTime()).toLocalDateTime());
                detalleTramite.setFechaFin(fechaFin.toLocalDateTime());
                detalleTramite.setEstado(estadoTramite);
                detalleTramite.setListaClientes(this.obtenerClientes(idDetalleTramiteResult));
                detalleTramite.setListaUsuarios(this.obtenerUsuarios(idDetalleTramiteResult));
                detalleTramite.setListaObservaciones(this.obtenerObservaciones(idDetalleTramiteResult));
                detalleTramite.setListaDocumentos(this.obtenerDocumentos(idDetalleTramiteResult));
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el detalle del trámite: " + idDetalleTramite + "\n" + e.getMessage());
        }
        return detalleTramite;
    }

    private EstadoTramite obtenerEstadoTramitePorId(long idEstadoTramite) throws TramiteException {
        EstadoTramite estadoTramite = null;
        String consultaSQL = CONSULTA_GENERICA + "estado_tramite WHERE id_estado = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idEstadoTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idEstadoTramiteResult = resultSet.getLong("id_estado");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                estadoTramite = new EstadoTramite();
                estadoTramite.setId(idEstadoTramiteResult);
                estadoTramite.setNombre(nombre);
                estadoTramite.setDescripcion(descripcion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el estado del trámite: " + idEstadoTramite + "\n" + e.getMessage());
        }
        return estadoTramite;
    }

    private List<Cliente> obtenerClientes(long idDetalleTramite) throws TramiteException {
        List<Cliente> listaClientes = new ArrayList<>();
        String consultaSQL = CONSULTA_GENERICA + "detalle_tramite_cliente WHERE id_detalle_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idDetalleTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idClienteResult = resultSet.getLong("id_cliente");
                Cliente cliente = this.obtenerClientePorId(idClienteResult);
                listaClientes.add(cliente);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "los clientes para ese detalle de trámite: " + idDetalleTramite + "\n" + e.getMessage());
        }
        return listaClientes;
    }

    private Cliente obtenerClientePorId(long idCliente) throws TramiteException {
        Cliente cliente = null;
        String consultaSQL = CONSULTA_GENERICA + "cliente WHERE id_cliente = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idClienteResult = resultSet.getLong("id_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                long dni = resultSet.getLong("dni");
                String email = resultSet.getString("email");
                cliente = new Cliente();
                cliente.setId(idClienteResult);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setDni(dni);
                cliente.setEmail(email);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el cliente: " + idCliente + "\n" + e.getMessage());
        }
        return cliente;
    }

    private List<Usuario> obtenerUsuarios(long idDetalleTramite) throws TramiteException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String consultaSQL = CONSULTA_GENERICA + "detalle_tramite_usuario WHERE id_detalle_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idDetalleTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idUsuarioResult = resultSet.getLong("id_usuario");
                Usuario usuario = this.obtenerUsuarioPorId(idUsuarioResult);
                listaUsuarios.add(usuario);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "los usuarios para ese detalle de trámite: " + idDetalleTramite + "\n" + e.getMessage());
        }
        return listaUsuarios;
    }

    private Usuario obtenerUsuarioPorId(long idUsuario) throws TramiteException {
        Usuario usuario = null;
        String consultaSQL = CONSULTA_GENERICA + "usuario WHERE id_usuario = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idUsuarioResult = resultSet.getLong("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                long dni = resultSet.getLong("dni");
                String email = resultSet.getString("email");
                long idRol = resultSet.getLong("id_rol");
                usuario = new Usuario();
                usuario.setId(idUsuarioResult);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setDni(dni);
                usuario.setEmail(email);
                usuario.setRol(this.obtenerRolPorId(idRol));
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el usuario: " + idUsuario + "\n" + e.getMessage());
        }
        return usuario;
    }

    private Rol obtenerRolPorId(long idRol) throws TramiteException {
        Rol rol = null;
        String consultaSQL = CONSULTA_GENERICA + "rol WHERE id_rol = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idRol);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idUsuarioResult = resultSet.getLong("id_rol");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                rol = new Rol();
                rol.setId(idUsuarioResult);
                rol.setNombre(nombre);
                rol.setDescripcion(descripcion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el rol: " + idRol + "\n" + e.getMessage());
        }
        return rol;
    }

    private List<Observacion> obtenerObservaciones(long idDetalleTramite) throws TramiteException {
        List<Observacion> listaObservaciones = new ArrayList<>();
        String consultaSQL = CONSULTA_GENERICA + "detalle_tramite_observacion WHERE id_detalle_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idDetalleTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idObservacionResult = resultSet.getLong("id_observacion");
                Observacion observacion = this.obtenerObservacionPorId(idObservacionResult);
                listaObservaciones.add(observacion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "las observaciones para ese detalle de trámite: " + idDetalleTramite + "\n" + e.getMessage());
        }
        return listaObservaciones;
    }

    private Observacion obtenerObservacionPorId(long idObservacion) throws TramiteException {
        Observacion observacion = null;
        String consultaSQL = CONSULTA_GENERICA + "observacion WHERE id_observacion = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idObservacion);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idObservacionResult = resultSet.getLong("id_observacion");
                Timestamp fecha = resultSet.getTimestamp("fecha");
                String descripcion = resultSet.getString("descripcion");
                observacion = new Observacion();
                observacion.setId(idObservacionResult);
                observacion.setFecha(fecha.toLocalDateTime());
                observacion.setDescripcion(descripcion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "la observación: " + idObservacion + "\n" + e.getMessage());
        }
        return observacion;
    }

    private List<Documento> obtenerDocumentos(long idDetalleTramite) throws TramiteException {
        List<Documento> listaDocumentos = new ArrayList<>();
        String consultaSQL = CONSULTA_GENERICA + "detalle_tramite_documento WHERE id_detalle_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idDetalleTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idDocumentoResult = resultSet.getLong("id_documento");
                Documento documento = this.obtenerDocumentoPorId(idDocumentoResult);
                listaDocumentos.add(documento);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "los documentos para ese detalle de trámite: " + idDetalleTramite + "\n" + e.getMessage());
        }
        return listaDocumentos;
    }

    private Documento obtenerDocumentoPorId(long idDocumento) throws TramiteException {
        Documento documento = null;
        String consultaSQL = CONSULTA_GENERICA + "documento WHERE id_documento = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idDocumento);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idDocumentoResult = resultSet.getLong("id_documento");
                Timestamp fechaPresentacion = resultSet.getTimestamp("fecha_presentacion");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                long idTipoDocumento = resultSet.getLong("id_tipo_documento");
                documento = new Documento();
                documento.setId(idDocumentoResult);
                documento.setFechaPresentacion(fechaPresentacion.toLocalDateTime());
                documento.setNombre(nombre);
                documento.setDescripcion(descripcion);
                documento.setTipoDocumento(this.obtenerTipoDocumentoPorId(idTipoDocumento));
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el documento: " + idDocumento + "\n" + e.getMessage());
        }
        return documento;
    }

    private TipoDocumento obtenerTipoDocumentoPorId(long idTipoDocumento) throws TramiteException {
        TipoDocumento tipoDocumento = null;
        String consultaSQL = CONSULTA_GENERICA + "tipo_documento WHERE id_tipo_documento = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setLong(1, idTipoDocumento);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idTipoDocumentoResult = resultSet.getLong("id_tipo_documento");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(idTipoDocumentoResult);
                tipoDocumento.setNombre(nombre);
                tipoDocumento.setDescripcion(descripcion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el tipo de documento: " + idTipoDocumento + "\n" + e.getMessage());
        }
        return tipoDocumento;
    }

    private Timestamp desdeLocalDateTimeHaciaTimestamp(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime.toInstant(LocalDateTime.now().atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
}