package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Documento;
import ar.com.phidalgo.repository.DocumentoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/documentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentoResource {
    private final DocumentoRepository documentoRepository;

    @Inject
    public DocumentoResource(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @POST
    public Documento crear(Documento documento) {
        documentoRepository.persist(documento);
        return documento;
    }

    @GET
    public List<Documento> listar() {
        return documentoRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Documento actualizar(@PathParam("id") Long id, Documento documento) {
        Documento nuevoDocumento = documentoRepository.findById(id);
        nuevoDocumento.setNombre(documento.getNombre());
        nuevoDocumento.setDescripcion(documento.getDescripcion());
        nuevoDocumento.setTipoDocumento(documento.getTipoDocumento());
        nuevoDocumento.setFechaPresentacion(documento.getFechaPresentacion());
        documentoRepository.persist(nuevoDocumento);
        return nuevoDocumento;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        documentoRepository.deleteById(id);
    }
}