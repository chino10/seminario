package ar.com.phidalgo.ciudadanias.europeas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consulado")
@Getter
@Setter
public class Consulado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulado")
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Pais pais;
    private String domicilio;
    private String ciudad;
    private String provincia;
}