package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Pais;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaisRepository implements PanacheRepository<Pais> {
}