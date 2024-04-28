package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.DetalleTramite;
import ar.com.phidalgo.repository.DetalleTramiteRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/ciudadanias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetalleTramiteResource {
    private final DetalleTramiteRepository detalleTramiteRepository;

    @Inject
    public DetalleTramiteResource(DetalleTramiteRepository detalleTramiteRepository) {
        this.detalleTramiteRepository = detalleTramiteRepository;
    }

    @POST
    public DetalleTramite crear(DetalleTramite detalleTramite) {
        detalleTramiteRepository.persist(detalleTramite);
        return detalleTramite;
    }

    @GET
    public List<DetalleTramite> listar() {
        return detalleTramiteRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public DetalleTramite actualizar(@PathParam("id") Long id, DetalleTramite detalleTramite) {
        DetalleTramite nuevoDetalleTramite = detalleTramiteRepository.findById(id);
        nuevoDetalleTramite.setListaClientes(detalleTramite.getListaClientes());
        nuevoDetalleTramite.setConsulado(detalleTramite.getConsulado());
        nuevoDetalleTramite.setListaUsuarios(detalleTramite.getListaUsuarios());
        nuevoDetalleTramite.setEstado(detalleTramite.getEstado());
        nuevoDetalleTramite.setListaDocumentos(detalleTramite.getListaDocumentos());
        detalleTramiteRepository.persist(nuevoDetalleTramite);
        return nuevoDetalleTramite;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        detalleTramiteRepository.deleteById(id);
    }
}