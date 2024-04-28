package ar.com.phidalgo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mensaje")
@Getter
@Setter
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id;
    private String mensaje;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Cliente cliente;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;
}