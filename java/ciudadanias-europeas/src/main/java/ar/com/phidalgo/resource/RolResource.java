package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Rol;
import ar.com.phidalgo.repository.RolRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolResource {
    private final RolRepository rolRepository;

    @Inject
    public RolResource(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @POST
    public Rol crear(Rol rol) {
        rolRepository.persist(rol);
        return rol;
    }

    @GET
    public List<Rol> listar() {
        return rolRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Rol actualizar(@PathParam("id") Long id, Rol rol) {
        Rol nuevoRol = rolRepository.findById(id);
        nuevoRol.setNombre(rol.getNombre());
        nuevoRol.setDescripcion(rol.getDescripcion());
        rolRepository.persist(nuevoRol);
        return nuevoRol;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        rolRepository.deleteById(id);
    }
}