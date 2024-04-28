package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Observacion;
import ar.com.phidalgo.repository.ObservacionRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/observaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObservacionResource {
    private final ObservacionRepository observacionRepository;

    @Inject
    public ObservacionResource(ObservacionRepository observacionRepository) {
        this.observacionRepository = observacionRepository;
    }

    @POST
    public Observacion crear(Observacion observacion) {
        observacionRepository.persist(observacion);
        return observacion;
    }

    @GET
    public List<Observacion> listar() {
        return observacionRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Observacion actualizar(@PathParam("id") Long id, Observacion observacion) {
        Observacion nuevoObservacion = observacionRepository.findById(id);
        nuevoObservacion.setFecha(observacion.getFecha());
        nuevoObservacion.setDescripcion(observacion.getDescripcion());
        observacionRepository.persist(nuevoObservacion);
        return nuevoObservacion;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        observacionRepository.deleteById(id);
    }
}