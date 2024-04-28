package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Pais;
import ar.com.phidalgo.repository.PaisRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/paises")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaisResource {
    private final PaisRepository paisRepository;

    @Inject
    public PaisResource(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @POST
    public Pais crear(Pais pais) {
        paisRepository.persist(pais);
        return pais;
    }

    @GET
    public List<Pais> listar() {
        return paisRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Pais actualizar(@PathParam("id") Long id, Pais pais) {
        Pais nuevoPais = paisRepository.findById(id);
        nuevoPais.setNombre(pais.getNombre());
        paisRepository.persist(nuevoPais);
        return nuevoPais;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        paisRepository.deleteById(id);
    }
}