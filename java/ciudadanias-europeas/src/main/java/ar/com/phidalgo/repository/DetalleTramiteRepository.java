package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.DetalleTramite;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DetalleTramiteRepository implements PanacheRepository<DetalleTramite> {
}