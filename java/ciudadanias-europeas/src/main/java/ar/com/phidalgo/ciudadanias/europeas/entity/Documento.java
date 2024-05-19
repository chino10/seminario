package ar.com.phidalgo.ciudadanias.europeas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "documento")
@Getter
@Setter
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;
    private String nombre;
    private String descripcion;
    @OneToOne
    @PrimaryKeyJoinColumn
    private TipoDocumento tipoDocumento;
    @Column(name = "fecha_presentacion")
    private LocalDateTime fechaPresentacion;
}