package ar.com.phidalgo.ciudadanias.europeas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pago")
@Getter
@Setter
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private DetalleTramite detalleTramite;
    private Float importe;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Observacion observacion;
}