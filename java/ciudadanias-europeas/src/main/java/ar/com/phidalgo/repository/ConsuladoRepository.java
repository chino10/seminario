package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Consulado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsuladoRepository implements PanacheRepository<Consulado> {
}