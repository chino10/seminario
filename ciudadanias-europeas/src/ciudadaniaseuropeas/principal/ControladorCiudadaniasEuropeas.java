package ciudadaniaseuropeas.principal;

import ciudadaniaseuropeas.entity.Tramite;
import ciudadaniaseuropeas.exception.TramiteException;

import java.sql.Connection;
import java.util.List;

public class ControladorCiudadaniasEuropeas {
    ModeloCiudadaniasEuropeas modeloCiudadaniasEuropeas;
    VistaCiudadaniasEuropeas vistaCiudadaniasEuropeas;

    public ControladorCiudadaniasEuropeas(Connection connection) {
        this.modeloCiudadaniasEuropeas = new ModeloCiudadaniasEuropeas(connection);
        this.vistaCiudadaniasEuropeas = new VistaCiudadaniasEuropeas();
    }

    public boolean insertarTramite() {
        Tramite tramite = vistaCiudadaniasEuropeas.obtenerTramite();
        return modeloCiudadaniasEuropeas.insertarTramite(tramite);
    }

    public Tramite consultarTramitePorId() throws TramiteException {
        int idTramite = vistaCiudadaniasEuropeas.obtenerIdTramite();
        return this.consultarTramitePorId(idTramite);
    }

    public Tramite consultarTramitePorId(int idTramite) throws TramiteException {
        Tramite tramite = modeloCiudadaniasEuropeas.consultarTramite(idTramite);
        if(tramite == null) {
            throw new TramiteException("El trámite " + (long) idTramite + " NO EXISTE.");
        }
        return tramite;
    }

    public List<Tramite> consultarTramites() {
        return modeloCiudadaniasEuropeas.consultarTramites();
    }

    public boolean actualizarTramite() throws TramiteException {
        int idTramite = vistaCiudadaniasEuropeas.obtenerIdTramite();
        Tramite tramite = this.consultarTramitePorId(idTramite);
        if(tramite == null) {
            throw new TramiteException("El trámite " + (long) idTramite + " NO EXISTE.");
        } else {
            tramite = vistaCiudadaniasEuropeas.actualizarTramite(tramite);
            return modeloCiudadaniasEuropeas.actualizarTramite(tramite);
        }
    }

    public boolean eliminarTramite() throws TramiteException {
        int idTramite = vistaCiudadaniasEuropeas.obtenerIdTramite();
        if(modeloCiudadaniasEuropeas.eliminarTramite(idTramite)) {
            return true;
        } else {
            throw new TramiteException("El trámite " + (long) idTramite + " NO SE PUDO ELIMINAR...");
        }
    }
}