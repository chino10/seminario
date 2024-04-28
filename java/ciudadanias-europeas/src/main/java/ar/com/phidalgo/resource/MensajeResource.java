package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Mensaje;
import ar.com.phidalgo.repository.MensajeRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/mensajes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensajeResource {
    private final MensajeRepository mensajeRepository;

    @Inject
    public MensajeResource(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    @POST
    public Mensaje crear(Mensaje mensaje) {
        mensajeRepository.persist(mensaje);
        return mensaje;
    }

    @GET
    public List<Mensaje> listar() {
        return mensajeRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Mensaje actualizar(@PathParam("id") Long id, Mensaje mensaje) {
        Mensaje nuevoMensaje = mensajeRepository.findById(id);
        nuevoMensaje.setUsuario(mensaje.getUsuario());
        nuevoMensaje.setCliente(mensaje.getCliente());
        nuevoMensaje.setMensaje(mensaje.getMensaje());
        mensajeRepository.persist(nuevoMensaje);
        return nuevoMensaje;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        mensajeRepository.deleteById(id);
    }
}