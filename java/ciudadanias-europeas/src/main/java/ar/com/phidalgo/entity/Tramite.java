package ar.com.phidalgo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tramite")
@Getter
@Setter
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tramite")
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Consulado consulado;
    @OneToOne
    @PrimaryKeyJoinColumn
    private TipoTramite tipoTramite;
    private Float importe;
}