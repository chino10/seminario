package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.TipoTramite;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoTramiteRepository implements PanacheRepository<TipoTramite> {
}