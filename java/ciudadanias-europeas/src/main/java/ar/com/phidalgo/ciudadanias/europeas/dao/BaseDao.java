package ar.com.phidalgo.ciudadanias.europeas.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author phidalgo
 */
public class BaseDao {
    private static final Logger logger = Logger.getLogger(BaseDao.class.getName());
    private Connection connection;

    public BaseDao() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("db.properties"));
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String dbUrl = properties.getProperty("dbUrl");
            connection = DriverManager.getConnection(dbUrl, user, password);
            logger.log(Level.INFO, "Conexión con BD exitosa.");
        } catch(FileNotFoundException e) {
            logger.log(Level.SEVERE, "El archivo 'db.properties' NO EXISTE.\n" + e.getMessage(), e);
        } catch(IOException e) {
            logger.log(Level.SEVERE, "Error intentando obtener el archivo 'db.properties'.\n" + e.getMessage(), e);
        } catch(SQLException e) {
            logger.log(Level.SEVERE, "Error intentando conectar con la base de datos.\n" + e.getMessage(), e);
        }
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
           logger.log(Level.SEVERE, "Error intentando cerrar la conexión con la base de datos.\n" + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}