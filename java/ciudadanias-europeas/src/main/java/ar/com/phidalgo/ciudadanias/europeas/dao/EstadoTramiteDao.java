package ar.com.phidalgo.ciudadanias.europeas.dao;

import ar.com.phidalgo.ciudadanias.europeas.entity.EstadoTramite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author phidalgo
 */
public class EstadoTramiteDao extends BaseDao {
    private static final Logger logger = Logger.getLogger(EstadoTramiteDao.class.getName());
    private final Connection connection;

    public EstadoTramiteDao() {
        super();
        this.connection = this.getConnection();
    }

    public void guardarEstadoTramite(EstadoTramite estadoTramite) {
        String query = "INSERT INTO `ciudadanias_europeas`.`estado_tramite`(`nombre`,`descripcion`) "
                + "VALUES(?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, estadoTramite.getNombre());
            preparedStatement.setString(2, estadoTramite.getDescripcion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }
}