package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Consulado;
import ar.com.phidalgo.repository.ConsuladoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/consulados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsuladoResource {
    private final ConsuladoRepository consuladoRepository;

    @Inject
    public ConsuladoResource(ConsuladoRepository consuladoRepository) {
        this.consuladoRepository = consuladoRepository;
    }

    @POST
    public Consulado crear(Consulado consulado) {
        consuladoRepository.persist(consulado);
        return consulado;
    }

    @GET
    public List<Consulado> listar() {
        return consuladoRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Consulado actualizar(@PathParam("id") Long id, Consulado consulado) {
        Consulado nuevoConsulado = consuladoRepository.findById(id);
        nuevoConsulado.setDomicilio(consulado.getDomicilio());
        nuevoConsulado.setCiudad(consulado.getCiudad());
        nuevoConsulado.setPais(consulado.getPais());
        nuevoConsulado.setProvincia(consulado.getProvincia());
        consuladoRepository.persist(nuevoConsulado);
        return nuevoConsulado;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        consuladoRepository.deleteById(id);
    }
}