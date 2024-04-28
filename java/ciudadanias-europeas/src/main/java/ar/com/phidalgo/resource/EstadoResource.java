package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Estado;
import ar.com.phidalgo.repository.EstadoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {
    private final EstadoRepository estadoRepository;

    @Inject
    public EstadoResource(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @POST
    public Estado crear(Estado estado) {
        estadoRepository.persist(estado);
        return estado;
    }

    @GET
    public List<Estado> listar() {
        return estadoRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Estado actualizar(@PathParam("id") Long id, Estado estado) {
        Estado nuevoEstado = estadoRepository.findById(id);
        nuevoEstado.setNombre(estado.getNombre());
        nuevoEstado.setDescripcion(estado.getDescripcion());
        estadoRepository.persist(nuevoEstado);
        return nuevoEstado;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        estadoRepository.deleteById(id);
    }
}