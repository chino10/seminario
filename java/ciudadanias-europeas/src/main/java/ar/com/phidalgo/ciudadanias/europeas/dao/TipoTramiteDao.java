package ar.com.phidalgo.ciudadanias.europeas.dao;

import ar.com.phidalgo.ciudadanias.europeas.entity.TipoTramite;
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
public class TipoTramiteDao extends BaseDao {

    private static final Logger logger = Logger.getLogger(TipoTramiteDao.class.getName());
    private final Connection connection;

    public TipoTramiteDao() {
        super();
        this.connection = this.getConnection();
    }

    public void guardarTipoTramite(TipoTramite tipoTramite) throws Exception {
        String query = "INSERT INTO `ciudadanias_europeas`.`tipo_tramite`(`nombre`,`descripcion`) "
                + "VALUES(?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, tipoTramite.getNombre());
            preparedStatement.setString(2, tipoTramite.getDescripcion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
            throw new Exception(e);
        } finally {
            this.close();
        }
    }

    public List<TipoTramite> consultarTipoTramite(Integer idTramite, String nombre) {
        List<TipoTramite> listaTiposTramite = new ArrayList<>();
        String query = "SELECT * "
                + "FROM ciudadanias_europeas.tipo_tramite tt "
                + "WHERE tt.id_tipo_tramite = ? "
                + "OR nombre LIKE '%?%';";

        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setLong(1, idTramite);
            preparedStatement.setString(2, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TipoTramite tipoTramite = convertRawToTipoTramite(resultSet);
                listaTiposTramite.add(tipoTramite);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
        return listaTiposTramite;
    }

    public List<TipoTramite> consultarTodos() {
        List<TipoTramite> listaTiposTramite = new ArrayList<>();
        String query = "SELECT * "
                + "FROM ciudadanias_europeas.tipo_tramite;";

        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TipoTramite tipoTramite = convertRawToTipoTramite(resultSet);
                listaTiposTramite.add(tipoTramite);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
        return listaTiposTramite;
    }

    public void eliminarTipoTramite(Integer idTramite) {
        String query = "DELETE FROM `ciudadanias_europeas`.`tipo_tramite` tt "
                + "WHERE tt.id_tipo_tramite = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setLong(1, idTramite);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }

    public void modificarTipoTramite(TipoTramite tipoTramite) {
        String query = this.obtenerQueryParaUpdate(tipoTramite);
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }

    private TipoTramite convertRawToTipoTramite(ResultSet resultSet) throws SQLException {
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite.setId(resultSet.getLong(1));
        tipoTramite.setNombre(resultSet.getString(2));
        tipoTramite.setDescripcion(resultSet.getString(3));
        return tipoTramite;
    }

    private String obtenerQueryParaUpdate(TipoTramite tipoTramite) {
        String query = "UPDATE `ciudadanias_europeas`.`tipo_tramite` SET ";
        if(tipoTramite.getNombre() != null && !tipoTramite.getNombre().isEmpty()) {
            query += "`nombre` = " + tipoTramite.getNombre();
        }
        if(tipoTramite.getDescripcion()!= null && !tipoTramite.getDescripcion().isEmpty()) {
            query += "`descripcion` = " + tipoTramite.getDescripcion();
        }
        query += " WHERE `id_tipo_tramite` = " + tipoTramite.getId() + ";";
        return query;
    }
}