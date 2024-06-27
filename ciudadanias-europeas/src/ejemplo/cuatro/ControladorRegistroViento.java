package ejemplo.cuatro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControladorRegistroViento {

    private ModeloRegistroViento modelo;
    private VistaRegistroViento vista;

    public ControladorRegistroViento(Connection conexion) {
        this.modelo = new ModeloRegistroViento(conexion);
        this.vista = new VistaRegistroViento();
    }

    public void ejecutar() throws SQLException {
        int idTurbina = vista.solicitarIdTurbina();
        double velocidadViento = vista.solicitarVelocidadViento();
        String direccionViento = vista.solicitarDireccionViento();

        modelo.insertarRegistroViento(idTurbina, velocidadViento, direccionViento);
        System.out.println("Medici�n insertada en base de datos");
    }

    public void mostrarTurbinasDisponibles() {
        List<Turbina> turbinasDisponibles = modelo.consultarTurbinasDisponibles();
        vista.mostrarTurbinas(turbinasDisponibles);
    }
}