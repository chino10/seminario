package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Cliente;
import ar.com.phidalgo.repository.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    private final ClienteRepository clienteRepository;

    @Inject
    public ClienteResource(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @POST
    public Cliente crear(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }

    @GET
    public List<Cliente> listar() {
        return clienteRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Cliente actualizar(@PathParam("id") Long id, Cliente cliente) {
        Cliente nuevoCliente = clienteRepository.findById(id);
        nuevoCliente.setNombre(cliente.getNombre());
        nuevoCliente.setApellido(cliente.getApellido());
        nuevoCliente.setDni(cliente.getDni());
        nuevoCliente.setEmail(cliente.getEmail());
        clienteRepository.persist(nuevoCliente);
        return nuevoCliente;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        clienteRepository.deleteById(id);
    }
}