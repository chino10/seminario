package ar.com.phidalgo.ciudadanias.europeas.dao;

import ar.com.phidalgo.ciudadanias.europeas.entity.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author phidalgo
 */
public class RolDao extends BaseDao {
    private static final Logger logger = Logger.getLogger(RolDao.class.getName());
    private final Connection connection;

    public RolDao() {
        super();
        this.connection = this.getConnection();
    }

    public void guardarRol(Rol rol) {
        String query = "INSERT INTO `ciudadanias_europeas`.`rol`(`nombre`,`descripcion`) "
                + "VALUES(?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, rol.getNombre());
            preparedStatement.setString(2, rol.getDescripcion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }
}