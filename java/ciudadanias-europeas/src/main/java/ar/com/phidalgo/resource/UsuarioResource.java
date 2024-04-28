package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Usuario;
import ar.com.phidalgo.repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/usuarioes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    private final UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioResource(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @POST
    public Usuario crear(Usuario usuario) {
        usuarioRepository.persist(usuario);
        return usuario;
    }

    @GET
    public List<Usuario> listar() {
        return usuarioRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Usuario actualizar(@PathParam("id") Long id, Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.findById(id);
        nuevoUsuario.setNombre(usuario.getNombre());
        nuevoUsuario.setApellido(usuario.getApellido());
        nuevoUsuario.setDni(usuario.getDni());
        nuevoUsuario.setEmail(usuario.getEmail());
        nuevoUsuario.setRol(usuario.getRol());
        usuarioRepository.persist(nuevoUsuario);
        return nuevoUsuario;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        usuarioRepository.deleteById(id);
    }
}