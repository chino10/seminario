package ar.com.phidalgo.ciudadanias.europeas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "detalle_tramite")
@Getter
@Setter
public class DetalleTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_tramite")
    private Long id;
    @OneToMany
    private List<Cliente> listaClientes;
    @OneToMany
    private List<Documento> listaDocumentos;
    @OneToOne
    @PrimaryKeyJoinColumn
    private EstadoTramite estado;
    @OneToMany
    private List<Usuario> listaUsuarios;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Consulado consulado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Tramite tramite;
    @OneToMany
    private List<Observacion> listaObservaciones;
}