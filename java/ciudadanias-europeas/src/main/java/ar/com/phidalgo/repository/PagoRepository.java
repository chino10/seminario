package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Pago;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagoRepository implements PanacheRepository<Pago> {
}