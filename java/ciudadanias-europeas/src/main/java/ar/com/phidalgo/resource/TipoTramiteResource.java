package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.TipoTramite;
import ar.com.phidalgo.repository.TipoTramiteRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tipoTramitees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoTramiteResource {
    private final TipoTramiteRepository tipoTramiteRepository;

    @Inject
    public TipoTramiteResource(TipoTramiteRepository tipoTramiteRepository) {
        this.tipoTramiteRepository = tipoTramiteRepository;
    }

    @POST
    public TipoTramite crear(TipoTramite tipoTramite) {
        tipoTramiteRepository.persist(tipoTramite);
        return tipoTramite;
    }

    @GET
    public List<TipoTramite> listar() {
        return tipoTramiteRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public TipoTramite actualizar(@PathParam("id") Long id, TipoTramite tipoTramite) {
        TipoTramite nuevoTipoTramite = tipoTramiteRepository.findById(id);
        nuevoTipoTramite.setNombre(tipoTramite.getNombre());
        nuevoTipoTramite.setDescripcion(tipoTramite.getDescripcion());
        tipoTramiteRepository.persist(nuevoTipoTramite);
        return nuevoTipoTramite;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        tipoTramiteRepository.deleteById(id);
    }
}