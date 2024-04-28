package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Mensaje;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MensajeRepository implements PanacheRepository<Mensaje> {
}