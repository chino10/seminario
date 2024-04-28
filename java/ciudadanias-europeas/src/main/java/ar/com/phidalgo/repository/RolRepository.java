package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.Rol;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RolRepository implements PanacheRepository<Rol> {
}