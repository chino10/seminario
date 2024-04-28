package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Reporte;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReporteRepository implements PanacheRepository<Reporte> {
}