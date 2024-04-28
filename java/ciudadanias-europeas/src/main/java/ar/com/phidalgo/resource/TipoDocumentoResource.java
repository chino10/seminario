package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.TipoDocumento;
import ar.com.phidalgo.repository.TipoDocumentoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tipoDocumentoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoDocumentoResource {
    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Inject
    public TipoDocumentoResource(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @POST
    public TipoDocumento crear(TipoDocumento tipoDocumento) {
        tipoDocumentoRepository.persist(tipoDocumento);
        return tipoDocumento;
    }

    @GET
    public List<TipoDocumento> listar() {
        return tipoDocumentoRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public TipoDocumento actualizar(@PathParam("id") Long id, TipoDocumento tipoDocumento) {
        TipoDocumento nuevoTipoDocumento = tipoDocumentoRepository.findById(id);
        nuevoTipoDocumento.setNombre(tipoDocumento.getNombre());
        nuevoTipoDocumento.setDescripcion(tipoDocumento.getDescripcion());
        tipoDocumentoRepository.persist(nuevoTipoDocumento);
        return nuevoTipoDocumento;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        tipoDocumentoRepository.deleteById(id);
    }
}