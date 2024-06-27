package ejemplo.cuatro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloRegistroViento {
    private Connection conexion;

    public ModeloRegistroViento(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarRegistroViento(int idTurbina, double velocidadViento, String direccionViento) throws SQLException {
        String consultaSQL = "INSERT INTO registros_viento (turbina_id, velocidad_viento, direccion_viento) VALUES (?, ?, ?)";
        try(PreparedStatement sentencia = conexion.prepareStatement(consultaSQL)) {
            sentencia.setInt(1, idTurbina);
            sentencia.setDouble(2, velocidadViento);
            sentencia.setString(3, direccionViento);
            sentencia.executeUpdate();
        }
    }

    public List<Turbina> consultarTurbinasDisponibles() {
        List<Turbina> turbinas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String consultaSQL = "SELECT * FROM turbinas_eolicas WHERE estado = 'Activa'";
            statement = conexion.prepareStatement(consultaSQL);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("turbina_id");
                String modelo = resultSet.getString("modelo");
                double potenciaMax = resultSet.getDouble("potencia_max");
                String estado = resultSet.getString("estado");

                Turbina turbina = new Turbina(id, modelo, potenciaMax, estado);
                turbinas.add(turbina);
            }
        } catch(SQLException e) {
            System.err.println("Error al consultar las turbinas disponibles: " + e.getMessage());
        }
        return turbinas;
    }

}