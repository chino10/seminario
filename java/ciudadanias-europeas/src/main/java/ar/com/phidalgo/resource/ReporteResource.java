package ar.com.phidalgo.resource;

import ar.com.phidalgo.entity.Reporte;
import ar.com.phidalgo.repository.ReporteRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/reportes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReporteResource {
    private final ReporteRepository reporteRepository;

    @Inject
    public ReporteResource(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @POST
    public Reporte crear(Reporte reporte) {
        reporteRepository.persist(reporte);
        return reporte;
    }

    @GET
    public List<Reporte> listar() {
        return reporteRepository.listAll();
    }

    @PUT
    @Path("/{id}")
    public Reporte actualizar(@PathParam("id") Long id, Reporte reporte) {
        Reporte nuevoReporte = reporteRepository.findById(id);
        nuevoReporte.setNombre(reporte.getNombre());
        nuevoReporte.setUsuario(reporte.getUsuario());
        nuevoReporte.setDatos(reporte.getDatos());
        nuevoReporte.setFecha(reporte.getFecha());
        nuevoReporte.setDescripcion(reporte.getDescripcion());
        nuevoReporte.setObservacion(reporte.getObservacion());
        reporteRepository.persist(nuevoReporte);
        return nuevoReporte;
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        reporteRepository.deleteById(id);
    }
}