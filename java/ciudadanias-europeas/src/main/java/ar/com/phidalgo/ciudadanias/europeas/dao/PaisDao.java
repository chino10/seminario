package ar.com.phidalgo.ciudadanias.europeas.dao;

import ar.com.phidalgo.ciudadanias.europeas.entity.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author phidalgo
 */
public class PaisDao extends BaseDao {
    private static final Logger logger = Logger.getLogger(PaisDao.class.getName());
    private final Connection connection;

    public PaisDao() {
        super();
        this.connection = this.getConnection();
    }

    public void guardarPais(Pais pais) {
        String query = "INSERT INTO `ciudadanias_europeas`.`pais`(`nombre`) "
                + "VALUES(?);";
        try {
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, pais.getNombre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error intentando cerrar la conexión de PreparedStatement con la BD.\n" + e.getMessage(), e);
        } finally {
            this.close();
        }
    }
}