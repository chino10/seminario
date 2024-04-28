package ar.com.phidalgo.repository;

import ar.com.phidalgo.entity.TipoDocumento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TipoDocumentoRepository implements PanacheRepository<TipoDocumento> {
}