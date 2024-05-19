package ar.com.phidalgo.ciudadanias.europeas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
@Getter
@Setter
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;
    private String nombre;
    private String descripcion;
    private String datos;
    private LocalDateTime fecha;
    private String observacion;
}