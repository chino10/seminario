package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Observacion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ObservacionRepository implements PanacheRepository<Observacion> {
}