package ciudadaniaseuropeas.principal;

import ciudadaniaseuropeas.entity.Consulado;
import ciudadaniaseuropeas.entity.Pais;
import ciudadaniaseuropeas.entity.TipoTramite;
import ciudadaniaseuropeas.entity.Tramite;
import ciudadaniaseuropeas.exception.TramiteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class ModeloCiudadaniasEuropeas {
    // TODO - Acá va todo lo que es conexión a BD...
    private final Connection connection;
    private static final String CONSULTA_GENERICA = "SELECT * FROM ";
    private static final String ERROR_GENERICO = "Ha ocurrido un error consultando ";

    public ModeloCiudadaniasEuropeas(Connection connection) {
        this.connection = connection;
    }

    public boolean insertarTramite(Tramite tramite) {
        /*String consultaSQL = "INSERT INTO registros_viento (turbina_id, velocidad_viento, direccion_viento) VALUES (?, ?, ?)";
        try(PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {
            sentencia.setInt(1, idTurbina);
            sentencia.setDouble(2, velocidadViento);
            sentencia.setString(3, direccionViento);
            sentencia.executeUpdate();
        }*/
        return false;
    }

    public Tramite consultarTramite(int idTramite) throws TramiteException {
        Tramite tramite = null;
        String consultaSQL = CONSULTA_GENERICA + "tramite WHERE id_tramite = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setInt(1, idTramite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long idTramiteResult = resultSet.getLong("id_tramite");
                float importe = resultSet.getFloat("importe");
                long idConsulado = resultSet.getLong("id_consulado");
                long idTipoTramite = resultSet.getLong("id_tipo_tramite");
                // TODO - Falta agregar campo moneda en la BD...
                String moneda = resultSet.getString("moneda");
                Consulado consulado = this.obtenerConsuladoPorId(idConsulado);
                TipoTramite tipoTramite = this.obtenerTipoTramitePorId(idTipoTramite);
                tramite = new Tramite();
                tramite.setId(idTramiteResult);
                tramite.setImporte(importe);
                tramite.setConsulado(consulado);
                tramite.setTipoTramite(tipoTramite);
                tramite.setMoneda(moneda);
                // TODO
                // tramite.setListaDetallesTramite();
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el trámite: " + idTramite);
        }
        return tramite;
    }

    public List<Tramite> consultarTramites() {

    }

    public boolean actualizarTramite(Tramite tramite) {

    }

    public boolean eliminarTramite(int idTramite) {

    }

    /*
    */
    /*
     */
    //////// INICIO CONSULTAS AUXILIARES (+)
    /*
     */
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
                long idPais = resultSet.getLong("id_pais");
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
            throw new TramiteException(ERROR_GENERICO + "el consulado: " + idConsulado);
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
                long idTipoTramiteResult = resultSet.getLong("id_tipoTramite");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                tipoTramite = new TipoTramite();
                tipoTramite.setId(idTipoTramiteResult);
                tipoTramite.setNombre(nombre);
                tipoTramite.setDescripcion(descripcion);
            }
            resultSet.close();
        } catch(SQLException e) {
            throw new TramiteException(ERROR_GENERICO + "el tipo de trámite: " + idTipoTramite);
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
            throw new TramiteException(ERROR_GENERICO + "el país: " + idPais);
        }
        return pais;
    }
}