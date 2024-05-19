package ar.com.phidalgo.ciudadanias.europeas.dao;

import ar.com.phidalgo.ciudadanias.europeas.entity.TipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author phidalgo
 */
public class TipoDocumentoDao extends BaseDao {

    private static final Logger logger = Logger.getLogger(TipoDocumentoDao.class.getName());
    private final Connection connection;

    public TipoDocumentoDao() {
        super();
        this.connection = this.getConnection();
    }

    public void guardarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
        String query = "INSERT INTO `ciudadanias_europeas`.`tipo_documento`(`nombre`,`descripcion`) "
                + "VALUES(?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, tipoDocumento.getNombre());
            preparedStatement.setString(2, tipoDocumento.getDescripcion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
            throw new Exception(e);
        } finally {
            this.close();
        }
    }

    public List<TipoDocumento> consultarTipoDocumento(Integer idDocumento, String nombre) {
        List<TipoDocumento> listaTiposDocumento = new ArrayList<>();
        String query = "SELECT * "
                + "FROM ciudadanias_europeas.tipo_documento td "
                + "WHERE td.id_tipo_documento = ? "
                + "OR nombre LIKE '%?%';";

        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setLong(1, idDocumento);
            preparedStatement.setString(2, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TipoDocumento tipoDocumento = convertRawToTipoDocumento(resultSet);
                listaTiposDocumento.add(tipoDocumento);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
        return listaTiposDocumento;
    }

    public List<TipoDocumento> consultarTodos() {
        List<TipoDocumento> listaTiposDocumento = new ArrayList<>();
        String query = "SELECT * "
                + "FROM ciudadanias_europeas.tipo_documento;";

        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TipoDocumento tipoDocumento = convertRawToTipoDocumento(resultSet);
                listaTiposDocumento.add(tipoDocumento);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
        return listaTiposDocumento;
    }

    public void eliminarTipoDocumento(Integer idDocumento) {
        String query = "DELETE FROM `ciudadanias_europeas`.`tipo_documento` td "
                + "WHERE td.id_tipo_documento = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setLong(1, idDocumento);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }

    public void modificarTipoDocumento(TipoDocumento tipoDocumento) {
        String query = this.obtenerQueryParaUpdate(tipoDocumento);
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }

    private TipoDocumento convertRawToTipoDocumento(ResultSet resultSet) throws SQLException {
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(resultSet.getLong(1));
        tipoDocumento.setNombre(resultSet.getString(2));
        tipoDocumento.setDescripcion(resultSet.getString(3));
        return tipoDocumento;
    }

    private String obtenerQueryParaUpdate(TipoDocumento tipoDocumento) {
        String query = "UPDATE `ciudadanias_europeas`.`tipo_documento` SET ";
        if(tipoDocumento.getNombre() != null && !tipoDocumento.getNombre().isEmpty()) {
            query += "`nombre` = " + tipoDocumento.getNombre();
        }
        if(tipoDocumento.getDescripcion()!= null && !tipoDocumento.getDescripcion().isEmpty()) {
            query += "`descripcion` = " + tipoDocumento.getDescripcion();
        }
        query += " WHERE `id_tipo_documento` = " + tipoDocumento.getId() + ";";
        return query;
    }
}
