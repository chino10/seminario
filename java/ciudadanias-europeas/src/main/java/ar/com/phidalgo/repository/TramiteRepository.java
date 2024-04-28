package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Tramite;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TramiteRepository implements PanacheRepository<Tramite> {
}