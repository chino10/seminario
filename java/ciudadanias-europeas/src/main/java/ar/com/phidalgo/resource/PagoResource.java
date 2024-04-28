package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Pago;
import ar.com.phidalgo.repository.PagoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/pagos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagoResource {
    private final PagoRepository pagoRepository;

    @Inject
    public PagoResource(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @POST
    public Pago crear(Pago pago) {
        pagoRepository.persist(pago);
        return pago;
    }

    @GET
    public List<Pago> listar() {
        return pagoRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Pago actualizar(@PathParam("id") Long id, Pago pago) {
        Pago nuevoPago = pagoRepository.findById(id);
        nuevoPago.setDetalleTramite(pago.getDetalleTramite());
        nuevoPago.setObservacion(pago.getObservacion());
        nuevoPago.setImporte(pago.getImporte());
        pagoRepository.persist(nuevoPago);
        return nuevoPago;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        pagoRepository.deleteById(id);
    }
}