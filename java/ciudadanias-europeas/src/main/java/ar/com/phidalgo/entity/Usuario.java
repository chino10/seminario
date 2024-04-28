package ar.com.phidalgo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    private Long dni;
    private String nombre;
    private String apellido;
    private String email;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Rol rol;
}